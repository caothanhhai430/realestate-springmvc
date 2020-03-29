package com.javaweb.service.impl;

import com.javaweb.entity.UserEntity;
import com.javaweb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByUsername(s);
        if(userEntity==null){
            throw new UsernameNotFoundException("User not  found");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
//        for(RoleEntity roleEntity : userEntity.getRoleList()){
//            if(roleEntity.getType() == Constant.ADMIN_ROLE){
//                authorities.add(new SimpleGrantedAuthority("ADMIN"));
//            }else if(roleEntity.getType() == Constant.STAFF_ROLE){
//                authorities.add(new SimpleGrantedAuthority("STAFF"));
//            }
//        }
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        authorities.add(new SimpleGrantedAuthority("STAFF"));


        User user = new User(userEntity.getUsername(),userEntity.getPassword(),true,true,true,true,authorities);
//        user.setFullname(userEntity.getFullname());
        return user;
    }
}
