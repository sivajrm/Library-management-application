package com.app.library.OAuth;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface UserService {

    ApplicationUser findByUserName(String userName);
    ApplicationUser save(ApplicationUser user);
    List<ApplicationUser> findAll();
}
