package com.example.helpgiver.mongo;

import com.example.helpgiver.objects.HelpRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HelpRequestRepository extends MongoRepository<HelpRequest, String> {
}
