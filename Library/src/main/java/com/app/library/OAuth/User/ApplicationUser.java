package com.app.library.OAuth.User;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Collection;

//User entity
@Entity
@Data
public class ApplicationUser {
    @Id
    private String id;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Collection<Role> roles;
}
