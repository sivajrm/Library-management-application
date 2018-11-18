package com.app.library.OAuth.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;


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
        List<SimpleGrantedAuthority> authorities = applicationUser.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.toString())).collect(toList());
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), authorities);
    }

    @Override
    public ApplicationUser save(ApplicationUser user) {
        return applicationUserRepository.save(user);
    }

    public ApplicationUser findByUserName(String userName){
        return applicationUserRepository.findByUsername(userName);
    }

    @Override
    public List<ApplicationUser> findAll() {
        List<ApplicationUser> list = new ArrayList<>();
        applicationUserRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }
}
