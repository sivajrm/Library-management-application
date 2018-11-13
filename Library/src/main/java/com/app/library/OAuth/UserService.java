package com.app.library.OAuth;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService {

//    @Autowired
//    ApplicationUserRepository applicationUserRepository;
//
//    public ApplicationUser signup(ApplicationUser user) {
//        if (applicationUserRepository.findByUsername(user.getUsername()) == null) {
//            applicationUserRepository.save(user);
//            return user;
//        } else {
//            throw new RuntimeException("Username is already in use");
//        }
//    }
    ApplicationUser save(ApplicationUser user);
    List<ApplicationUser> findAll();
}
