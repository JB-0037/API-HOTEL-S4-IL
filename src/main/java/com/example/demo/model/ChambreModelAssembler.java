package com.example.demo.model;

import com.example.demo.controller.ChambreController;
import com.example.demo.controller.ReservationController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class ChambreModelAssembler implements RepresentationModelAssembler<Chambre, EntityModel<Chambre>> {


    @Override
    public EntityModel<Chambre> toModel(Chambre entity) {
        return EntityModel.of(entity,
                WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(ChambreController.class)
                                .findById(entity.getId()))
                        .withSelfRel(),
                WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(ChambreController.class)
                                .getAllChambre())
                        .withRel("Chambres"),
                WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                                .methodOn(ReservationController.class)
                                .createNewReservation(entity.getId(), new ReservationRequestBody()))
                        .withRel("Reserver la Chambre"));
    }
}
