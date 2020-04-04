package com.javaweb.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="role")
public class RoleEntity extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer type;

    private String description;

    @ManyToMany(mappedBy = "roleList")
    private Set<UserEntity> userList=new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserEntity> getUserList() {
        return userList;
    }

    public void setUserList(Set<UserEntity> userList) {
        this.userList = userList;
    }


}
