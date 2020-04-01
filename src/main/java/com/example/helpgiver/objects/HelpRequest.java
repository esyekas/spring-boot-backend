package com.example.helpgiver.objects;

import org.springframework.data.annotation.Id;

import java.util.Collection;

public class HelpRequest {
    @Id
    private String id;

    private String title;
    private User requester;

    private Collection<User> helper;

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

    public Collection<User> getHelper() {
        return helper;
    }

    public void setHelper(Collection<User> helper) {
        this.helper = helper;
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
