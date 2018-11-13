package com.app.library.OAuth;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class ApplicationUser {
    @Id
    private String id;
    private String username;
    private String password;
}
