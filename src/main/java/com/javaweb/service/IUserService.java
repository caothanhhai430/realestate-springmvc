package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.dto.UserDTO;
import com.javaweb.entity.UserEntity;
import com.javaweb.paging.Pageable;

import javax.persistence.Tuple;

public interface IUserService {
	public List<UserDTO> findAll(UserDTO dto,Pageable pageable);
	public List<Tuple> findAssignmentByBuildingId(Long id);
	public UserDTO findById(long id);
	public Long save(UserDTO user);
	public Long update(UserDTO user);
	public void delete(long[] id);
	public void delete(long id);
	public Map<Long,String> findAll();
	public boolean assign(List<Long> staffId, Long buildingId);
//	public long count(UserDTO dto);
}
