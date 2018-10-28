package com.app.library.auth.controller;


import com.app.library.auth.model.JwtUser;
import com.app.library.auth.security.JwtGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/token")
public class TokenController {


    private JwtGenerator jwtGenerator;

    public TokenController(JwtGenerator jwtGenerator) {
        this.jwtGenerator = jwtGenerator;
    }

    @GetMapping()
    public ResponseEntity ping(){
        return new ResponseEntity("I am alive!! :)",HttpStatus.OK);
    }

    @PostMapping()
    public String generate(@RequestBody final JwtUser jwtUser){
        return jwtGenerator.generate(jwtUser);
    }

}
