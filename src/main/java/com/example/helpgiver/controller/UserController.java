package com.example.helpgiver.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("v1")
public class UserController {

    @GetMapping
    public ResponseEntity<CollectionModel<Object>> getRoot() {
        return ResponseEntity.ok(new CollectionModel<>(Collections.emptySet(),
                linkTo(methodOn(UserController.class).getUsers()).withRel("users")));
    }

    @GetMapping("users")
    public ResponseEntity<CollectionModel<EntityModel<User>>> getUsers() {
        User dummyUser = new User();
        dummyUser.name = "test user";

        return ResponseEntity.ok(new CollectionModel<>(Arrays.asList(new EntityModel<>(dummyUser)),
                linkTo(methodOn(UserController.class).getUsers()).withSelfRel()));
    }

    // Remove this once there is a mongo repo:
    public static class User {
        String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
