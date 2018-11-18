package com.app.library.OAuth.User;

import org.elasticsearch.common.util.set.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityExistsException;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    public UserController(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody ApplicationUser user) throws Exception {
        if(userService.findByUserName(user.getUsername()) != null){
            throw new EntityExistsException("Username already exists!!");
        }
        user.setRoles(Sets.newHashSet(Role.ROLE_USER));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/sign-up")
                .buildAndExpand(user.getUsername()).toUri();
        return ResponseEntity.created(location).body( "{\"message\":\"User registered successfully\"}");
    }
}