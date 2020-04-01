package com.example.helpgiver.controller;

import com.example.helpgiver.mongo.HelpRequestRepository;
import com.example.helpgiver.objects.HelpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class HelpRequestController {

    @Autowired
    private HelpRequestRepository helpRequestRepository;

    // TODO help requests should link to users

    @GetMapping("helpRequests")
    public ResponseEntity<CollectionModel<EntityModel<HelpRequest>>> getHelpRequests() {
        List<EntityModel<HelpRequest>> helpRequestEntities = StreamSupport.stream(helpRequestRepository.findAll().spliterator(), false)
                .map(helpRequest -> new EntityModel<>(helpRequest,
                        linkTo(methodOn(HelpRequestController.class).getHelpRequest(helpRequest.getId())).withSelfRel(),
                        linkTo(methodOn(HelpRequestController.class).getHelpRequests()).withRel("helpRequests")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new CollectionModel<>(helpRequestEntities,
                        linkTo(methodOn(HelpRequestController.class).getHelpRequests()).withSelfRel()));
    }

    @GetMapping("helpRequest/{id}")
    public ResponseEntity<EntityModel<HelpRequest>> getHelpRequest(@PathVariable String id) {
        return helpRequestRepository.findById(id)
                .map(helpRequest -> new EntityModel<>(helpRequest,
                        linkTo(methodOn(HelpRequestController.class).getHelpRequest(helpRequest.getId())).withSelfRel(),
                        linkTo(methodOn(HelpRequestController.class).getHelpRequests()).withRel("helpRequests")))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("helpRequest")
    public ResponseEntity<EntityModel<HelpRequest>> createHelpRequest(@RequestParam HelpRequest helpRequest) {
        HelpRequest savedHelpRequest = helpRequestRepository.save(helpRequest);

        return ResponseEntity.ok(new EntityModel<>(savedHelpRequest,
                linkTo(methodOn(HelpRequestController.class).getHelpRequest(savedHelpRequest.getId())).withSelfRel(),
                linkTo(methodOn(HelpRequestController.class).getHelpRequests()).withRel("helpRequests")));
    }
}
