package com.app.library.auth.model;

import lombok.Data;

@Data
public class JwtUser {
    Long id;
    String userName;
    String role;
}
