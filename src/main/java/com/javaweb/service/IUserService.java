package com.javaweb.service;

import com.javaweb.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import javax.persistence.Tuple;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IUserService {
	public List<UserDTO> findAll(UserDTO dto, Pageable pageable);
	public List<Tuple> findAssignmentByBuildingId(Long id);
	public List<Tuple> findAssignmentByCustomerId(Long id);
	public UserDTO findById(long id);
	public Long save(UserDTO user);
	public Long update(UserDTO user) throws IOException;
	public void delete(List<Long> ids);
	public boolean delete(long id);
	public Map<Long,String> findAll();
	public boolean assign(List<Long> staffId, Long buildingId);
	public boolean assignCustomer(List<Long> staffId,  Long customerId);
	public long count(UserDTO dto);
	public long lockUser(long id);
}
