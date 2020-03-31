package com.example.helpgiver.mongo;

import com.example.helpgiver.objects.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);

    User findByRiskGroup(String riskGroup);
}
