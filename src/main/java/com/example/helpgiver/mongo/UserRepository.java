package com.example.helpgiver.mongo;

import com.example.helpgiver.objects.User;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumber(String phoneNumber);

    List<User> findByRiskGroup(String riskGroup);

    GeoResults<User> findByAddressCoordinatesNear(Point location, Distance distance);

}
