package com.javaweb.repository;

import com.javaweb.builder.UserSearchBuilder;
import com.javaweb.entity.UserEntity;
import org.springframework.data.domain.Pageable;

import javax.persistence.Tuple;
import java.util.List;

public interface UserRepositoryCustom {
    List<Tuple> findStaffByBuildingId(Long id);
    List<Tuple> findStaffByCustomerId(Long id);
    List<UserEntity> findAll(UserSearchBuilder builder, Pageable pageable);
    long count(UserSearchBuilder builder);
}
