package com.app.library.OAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;


@Service(value = "userService")
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }

    @Override
    public ApplicationUser save(ApplicationUser user) {
        return applicationUserRepository.save(user);
    }

    @Override
    public List<ApplicationUser> findAll() {
        List<ApplicationUser> list = new ArrayList<>();
        applicationUserRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
}
