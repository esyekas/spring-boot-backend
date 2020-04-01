package com.example.helpgiver.objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.Collection;

public class HelpRequest {
    @Id
    private String id;

    private String title;
    private User requester;
    private String address;
    private GeoJsonPoint addressCoordinates;

    private Collection<User> helpers;

    private String category; // TODO enum or from db?
    private String description;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public GeoJsonPoint getAddressCoordinates() {
        return addressCoordinates;
    }

    public void setAddressCoordinates(GeoJsonPoint addressCoordinates) {
        this.addressCoordinates = addressCoordinates;
    }

    public Collection<User> getHelpers() {
        return helpers;
    }

    public void setHelpers(Collection<User> helpers) {
        this.helpers = helpers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
