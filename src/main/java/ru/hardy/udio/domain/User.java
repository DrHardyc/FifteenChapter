package ru.hardy.udio.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "usr", schema = "udio_sec")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "usr_seq")
    @SequenceGenerator(name = "usr_seq", initialValue = 2, allocationSize = 1)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String fio;
    private String position; //должность

    private boolean active;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", schema = "udio_sec", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<AppParam> appParams;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return isActive();
    }
    private boolean isActive(){
        return active;
    }
}
