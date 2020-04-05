package com.javaweb.service;

import com.javaweb.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import javax.persistence.Tuple;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IUserService {
	 List<UserDTO> findAll(UserDTO dto, Pageable pageable);
	 List<Tuple> findAssignmentByBuildingId(Long id);
	 List<Tuple> findAssignmentByCustomerId(Long id);
	 List<Map<String,Object>> findAllActiveStaff();
	 UserDTO findById(long id);
	 Long save(UserDTO user);
	 Long update(UserDTO user) throws IOException;
	 void delete(List<Long> ids);
	 boolean delete(long id);
	 Map<Long,String> findAll();
	 boolean assign(List<Long> staffId, Long buildingId);
	 boolean assignCustomer(List<Long> staffId,  Long customerId);
	 long count(UserDTO dto);
	 long lockUser(long id);
}
