package com.example.helpgiver.mongo;

import com.example.helpgiver.objects.HelpRequest;
import com.example.helpgiver.objects.User;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HelpRequestRepository extends MongoRepository<HelpRequest, String> {
    GeoResults<HelpRequest> findByAddressCoordinatesNear(Point location, Distance distance);
}
