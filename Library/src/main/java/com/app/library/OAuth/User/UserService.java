package com.app.library.OAuth.User;

import java.util.List;

public interface UserService {

    ApplicationUser findByUserName(String userName);
    ApplicationUser save(ApplicationUser user);
    List<ApplicationUser> findAll();
}
