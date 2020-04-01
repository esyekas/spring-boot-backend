package com.example.helpgiver.controller;

import com.example.helpgiver.mongo.UserRepository;
import com.example.helpgiver.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/{id}")
    ResponseEntity<EntityModel<User>> getUserById(@PathVariable String id) {
        return userRepository.findById(id)
                .map(user -> new EntityModel<>(user,
                        linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel(),
                        linkTo(methodOn(UserController.class).getUsers()).withRel("users")))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("users")
    public ResponseEntity<CollectionModel<EntityModel<User>>> getUsers() {
        List<EntityModel<User>> userEntities = StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(user -> new EntityModel<>(user,
                        linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel(),
                        linkTo(methodOn(UserController.class).getUsers()).withRel("users")))
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new CollectionModel<>(userEntities,
                        linkTo(methodOn(UserController.class).getUsers()).withSelfRel()));
    }

    @GetMapping("/user")
    ResponseEntity<EntityModel<User>> getUserByByEmailOrPhone(@RequestParam Optional<String> email, @RequestParam Optional<String> phoneNumber) {
        if (email.isPresent() && phoneNumber.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "You need to provide either email or phone number");
        }

        Optional<User> user = Optional.empty();

        if (email.isPresent()) {
            user = userRepository.findByEmail(email.get());
        } else if (phoneNumber.isPresent()) {
            user = userRepository.findByPhoneNumber(phoneNumber.get());
        }

        return user
                .map(u -> new EntityModel<>(u,
                        linkTo(methodOn(UserController.class).getUserById(u.getId())).withSelfRel(),
                        linkTo(methodOn(UserController.class).getUsers()).withRel("users")))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
