package com.example.helpgiver;

import com.example.helpgiver.mongo.HelpRequestRepository;
import com.example.helpgiver.mongo.UserRepository;
import com.example.helpgiver.objects.HelpRequest;
import com.example.helpgiver.objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

@SpringBootApplication
public class HelpGiverApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HelpRequestRepository helpRequestRepository;

    public static void main(String[] args) {
        SpringApplication.run(HelpGiverApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // Fresh start
        userRepository.deleteAll();

        // Adding test user
        User user1 = new User();
        user1.setAddressText("Stockholm");
        user1.setEmail("andrei@shumanski.com");
        user1.setFirstName("Andrei");
        user1.setLastName("Shumanski");
        user1.setPassword("xxxxxxxxx");
        user1.setPublicName("AS");
        user1.setRiskGroup("Helper");
        user1.setPhoneNumber("+4677777777");
        user1.setAddressCoordinates(new GeoJsonPoint(59.3293, 18.0686));
        userRepository.save(user1);

        // Adding test help request
        HelpRequest request = new HelpRequest();
        request.setRequester(user1);
        request.setTitle("zzz");
        helpRequestRepository.save(request);
    }
}
