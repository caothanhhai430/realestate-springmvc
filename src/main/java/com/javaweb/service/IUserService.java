package com.javaweb.service;

import java.util.List;

import com.javaweb.dto.UserDTO;
import com.javaweb.paging.Pageable;

public interface IUserService {
	public List<UserDTO> findAll(UserDTO dto,Pageable pageable);
	public UserDTO findById(long id);
	public Long save(UserDTO user);
	public Long update(UserDTO user);
	public void delete(long[] id);
	public void delete(long id);

}
