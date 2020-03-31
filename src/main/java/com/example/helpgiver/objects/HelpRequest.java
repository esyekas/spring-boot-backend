package com.example.helpgiver.objects;

import org.springframework.data.annotation.Id;

public class HelpRequest {
    @Id
    private String id;

    private String title;
    private User requester;


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
}
