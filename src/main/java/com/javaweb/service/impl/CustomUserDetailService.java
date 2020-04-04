package com.javaweb.service.impl;

import com.javaweb.dto.MyUser;
import com.javaweb.entity.RoleEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.repository.UserRepository;
import com.javaweb.utils.Constant;
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
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findOneByUsernameAndStatus(s,1);
        if(userEntity==null){
            throw new UsernameNotFoundException("User not  found");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(RoleEntity roleEntity : userEntity.getRoleList()){
            if(roleEntity.getType() == Constant.ADMIN_ROLE){
                authorities.add(new SimpleGrantedAuthority("ADMIN"));
            }else if(roleEntity.getType() == Constant.STAFF_ROLE){
                authorities.add(new SimpleGrantedAuthority("STAFF"));
            }
        }


        MyUser user = new MyUser(userEntity.getUsername(),userEntity.getPassword(),true,true,true,true,authorities);
        user.setFullname(userEntity.getFullname());
        user.setId(userEntity.getId());
        user.setAvatar(userEntity.getAvatar());
        return user;
    }
}
