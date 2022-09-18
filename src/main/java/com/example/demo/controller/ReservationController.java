package com.example.demo.controller;

import com.example.demo.exceptions.ChambreNotFoundException;
import com.example.demo.exceptions.ReservationNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.ChambreRepository;
import com.example.demo.repository.ReservationRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ReservationController {

    private final ReservationRepository repository;

    private final ChambreRepository chRepository;

    private final ReservationModelAssembler assembler;

    public ReservationController(ReservationRepository repository, ReservationModelAssembler assembler, ChambreRepository chRepository) {
        this.repository = repository;
        this.assembler = assembler;
        this.chRepository = chRepository;
    }

    @GetMapping("/reservations")
    public CollectionModel<EntityModel<Reservation>> getAllReservation() {
        List<EntityModel<Reservation>> reservations = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(reservations,
                WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(ReservationController.class)
                        .getAllReservation())
                        .withSelfRel(),
                WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                        .methodOn(ChambreController.class)
                        .getAllChambre())
                        .withRel("Voir Les Chambres à Disposition"));
    }

    @GetMapping("/reservations/{id}")
    public EntityModel<Reservation> findById(@PathVariable Long id) {
        Optional<Reservation> reservation = repository.findById(id);
        if(reservation.isEmpty()) {
            throw new ReservationNotFoundException(id);
        }
        return assembler.toModel(reservation.get());
    }

    @PostMapping("/chambres/reservation/{id}")
    public EntityModel<Reservation> createNewReservation(@PathVariable Long id, @RequestBody ReservationRequestBody reservation) {
        Optional<Chambre> chambre = chRepository.findById(id);
        if(chambre.isEmpty() || chambre.get().getStatut() == Statuts.OCCUPEE) {
            throw new ChambreNotFoundException(id);
        }
        chambre.get().setStatut(Statuts.OCCUPEE);
        chRepository.save(chambre.get());

        int prix = chambre.get().getPrix() * reservation.getDureeSejour();

        Reservation reserv = new Reservation(reservation.getPrenom(), reservation.getNom(), reservation.getDureeSejour(), id, prix);
        repository.save(reserv);


        return assembler.toModel(reserv);
    }

    @DeleteMapping("/reservations/{id}/annuler")
    public ResponseEntity<?> annulerUneReservation(@PathVariable Long id) {
        Optional<Reservation> reservation = repository.findById(id);
        if(reservation.isEmpty()) {
            throw new ReservationNotFoundException(id);
        }
        if(reservation.get().getStatut() == Statuts.EN_ATTENTE_DE_FINALISATION) {
            reservation.get().setStatut(Statuts.ANNULEE);
            chRepository.findById(reservation.get().getIdChambreReservee()).get().setStatut(Statuts.DISPONIBLE);
            chRepository.save(chRepository.findById(reservation.get().getIdChambreReservee()).get());
            return ResponseEntity.ok(assembler.toModel(repository.save(reservation.get())));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                    .withTitle("Methode Non Autorisée")
                        .withDetail("Vous ne pouvez pas annuler une réservation qui est en statut "
                                + reservation.get().getStatut()));
    }

    @PutMapping("/reservations/{id}/finaliser")
    public ResponseEntity<?> finaliserUneReservation(@PathVariable Long id) {
        Optional<Reservation> reservation = repository.findById(id);
        if(reservation.isEmpty()) {
            throw new ReservationNotFoundException(id);
        }
        if(reservation.get().getStatut() == Statuts.EN_ATTENTE_DE_FINALISATION) {
            reservation.get().setStatut(Statuts.COMPLETE);
            return ResponseEntity.ok(assembler.toModel(repository.save(reservation.get())));
        }

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE)
                .body(Problem.create()
                    .withTitle("Methode Non Autorisée")
                        .withDetail("Vous ne pouvez pas finaliser une réservation qui est en statut "
                                + reservation.get().getStatut()));
    }
}
