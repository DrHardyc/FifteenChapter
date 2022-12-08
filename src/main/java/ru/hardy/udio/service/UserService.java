package ru.hardy.udio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.hardy.udio.domain.Role;
import ru.hardy.udio.domain.User;
import ru.hardy.udio.repo.UserRepo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Component
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<Role> roleNames = new ArrayList<>(user.getRoles());
        List<GrantedAuthority> grantList = new ArrayList();
        for (Role role : roleNames){
            GrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
            grantList.add(authority);
        }
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantList);
        return userDetails;
    }
    public boolean addUser(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Collections.singleton(Role.ROLE_USER));
        user.setActive(true);
        User userFromDb = userRepo.findByUsername(username);
        if (userFromDb != null) {
            return false;
        }
        userRepo.save(user);
        return true;
    }
}
