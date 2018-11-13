package com.app.library.OAuth;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}
