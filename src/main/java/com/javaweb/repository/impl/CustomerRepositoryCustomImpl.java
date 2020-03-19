package com.javaweb.repository.impl;

import com.javaweb.entity.CustomerEntity;
import com.javaweb.repository.CustomerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomerRepositoryCustomImpl  implements CustomerRepositoryCustom {

	@PersistenceContext
	private EntityManager em;


}