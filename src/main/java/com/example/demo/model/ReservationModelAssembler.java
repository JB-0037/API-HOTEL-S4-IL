package com.example.demo.model;

import com.example.demo.controller.ChambreController;
import com.example.demo.controller.ReservationController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class ReservationModelAssembler implements RepresentationModelAssembler<Reservation, EntityModel<Reservation>> {

    @Override
    public EntityModel<Reservation> toModel(Reservation entity) {
        return EntityModel.of(entity,
                WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(ReservationController.class)
                                .findById(entity.getId()))
                        .withSelfRel(),
                WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(ReservationController.class)
                                .getAllReservation())
                        .withRel("Reservations"),
                WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(ChambreController.class)
                                .findById(entity.getIdChambreReservee()))
                        .withRel("Chambre Reservée"),
                WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(ReservationController.class)
                                .annulerUneReservation(entity.getId()))
                        .withRel("Annuler La Réservation"),
                WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(ReservationController.class)
                                .finaliserUneReservation(entity.getId()))
                        .withRel("Finaliser la Reservation"));
    }
}
