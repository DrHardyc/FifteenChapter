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
import java.util.Set;

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
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantList);
    }
    public boolean addUser(String username, String password, Set<Role> roles){
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(roles);
        user.setActive(true);
        User userFromDb = userRepo.findByUsername(username);
        if (userFromDb != null || username.isEmpty() || password.isEmpty()) {
            return false;
        }
        userRepo.save(user);
        return true;
    }

    public List<User> getAll(){
        return userRepo.findAll();
    }


    public User getWithName(String username){
        return userRepo.findByUsername(username);
    }
}
