package com.example.helpgiver.controller;

import com.example.helpgiver.mongo.UserRepository;
import com.example.helpgiver.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("v1")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity<CollectionModel<Object>> getRoot() {
        return ResponseEntity.ok(new CollectionModel<>(Collections.emptySet(),
                linkTo(methodOn(UserController.class).getUsers()).withRel("users")));
    }

    @GetMapping("/user/{id}")
    ResponseEntity<EntityModel<User>> getUserById(@PathVariable String id) {
        return repository.findById(id)
                .map(user -> new EntityModel<>(user,
                        linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel(),
                        linkTo(methodOn(UserController.class).getUsers()).withRel("users")))
                .map(ResponseEntity::ok) //
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("users")
    public ResponseEntity<CollectionModel<EntityModel<User>>> getUsers() {
        List<User> users = repository.findAll();

        List<EntityModel<User>> employees = StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(user -> new EntityModel<>(user,
                        linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel(),
                        linkTo(methodOn(UserController.class).getUsers()).withRel("users"))) //
                .collect(Collectors.toList());

        return ResponseEntity.ok( //
                new CollectionModel<>(employees, //
                        linkTo(methodOn(UserController.class).getUsers()).withSelfRel()));
    }

}
