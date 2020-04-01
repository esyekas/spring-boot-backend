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

import java.util.Arrays;

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
        helpRequestRepository.deleteAll();

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

        User user2 = new User();
        user2.setAddressText("Stockholm");
        user2.setEmail("michal@kopec.pl");
        user2.setFirstName("Michał");
        user2.setLastName("Kopeć");
        user2.setPassword("yyyyyyyy");
        user2.setPublicName("MK");
        user2.setRiskGroup("Helper");
        user2.setPhoneNumber("+466666666");
        user2.setAddressCoordinates(new GeoJsonPoint(59.2345, 18.0111));
        userRepository.save(user2);

        // Adding test help request
        HelpRequest request = new HelpRequest();
        request.setRequester(user1);
        request.setTitle("zzz");
        request.setHelper(Arrays.asList(user2));
        helpRequestRepository.save(request);
    }
}
