package com.example.helpgiver.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class RootController {

    @GetMapping
    public ResponseEntity<CollectionModel<Object>> get() {
        return ResponseEntity.ok(new CollectionModel<>(Collections.emptySet(),
                linkTo(methodOn(RootController.class).getV1()).withRel("v1")));
    }

    @GetMapping("v1")
    public ResponseEntity<CollectionModel<Object>> getV1() {
        return ResponseEntity.ok(new CollectionModel<>(Collections.emptySet(),
                linkTo(methodOn(UserController.class).getUsers()).withRel("users"),
                linkTo(methodOn(HelpRequestController.class).getHelpRequests()).withRel("helpRequests")));
    }
}
