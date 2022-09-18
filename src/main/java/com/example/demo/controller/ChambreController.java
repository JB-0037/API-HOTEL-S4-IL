package com.example.demo.controller;

import com.example.demo.exceptions.ChambreNotFoundException;
import com.example.demo.model.Chambre;
import com.example.demo.model.ChambreModelAssembler;
import com.example.demo.repository.ChambreRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class ChambreController {

    private final ChambreRepository repository;

    private final ChambreModelAssembler assembler;

    public ChambreController(ChambreRepository repository, ChambreModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/chambres/{id}")
    public EntityModel<Chambre> findById(@PathVariable Long id) {
        Optional<Chambre> chambre = repository.findById(id);
        if(chambre.isEmpty()) {
            throw new ChambreNotFoundException(id);
        }
        return assembler.toModel(chambre.get());
    }

    @GetMapping("/chambres")
    public CollectionModel<EntityModel<Chambre>> getAllChambre() {
        List<EntityModel<Chambre>> chambres = new ArrayList<>();
        for (Chambre entity : repository.findAll()) {
            EntityModel<Chambre> chambreEntityModel = assembler.toModel(entity);
            chambres.add(chambreEntityModel);
        }

        return CollectionModel.of(chambres, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ChambreController.class).getAllChambre()).withSelfRel());
    }

}
