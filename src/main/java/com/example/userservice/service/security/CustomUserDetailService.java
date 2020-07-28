package com.example.userservice.service.security;

import com.example.userservice.model.Permission;
import com.example.userservice.model.Role;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(s);

        if(user== null){
            throw new UsernameNotFoundException("User Name not found");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.isActive(),
                true,
                true,
                true,
                grantedAuthorities(user.getRoles())
        );

//        return null;
    }

    private List<GrantedAuthority> grantedAuthorities(List<Role> userRoles){

        List<GrantedAuthority> authorities = new ArrayList<>();

        for(Role role : userRoles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
            for (Permission permission : role.getPermissions()){

                authorities.add(new SimpleGrantedAuthority(permission.getName()));
            }
        }
        return authorities;
    }
}
