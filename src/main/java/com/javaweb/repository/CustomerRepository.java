package com.javaweb.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.paging.Pageable;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long>,CustomerRepositoryCustom{
    @Modifying
    @Query("delete from CustomerEntity b where b.id = ?1")
    void delete(Long id);
}

