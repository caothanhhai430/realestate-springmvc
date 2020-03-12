//package com.javaweb.service.impl;
//
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import com.javaweb.Helper.ObjectToMap;
//import com.javaweb.converter.DTOConverter;
//import com.javaweb.dto.UserDTO;
//import com.javaweb.entity.UserEntity;
//import com.javaweb.paging.Pageable;
//import com.javaweb.repository.impl.UserRepository;
//import com.javaweb.service.IUserService;
//
//public class UserService implements IUserService{
//
//	@Override
//	public List<UserDTO> findAll(UserDTO dto, Pageable pageable) {
//		Map<String,Object> properties = ObjectToMap.toMap(dto);
//		
//		List<UserDTO> result = new UserRepository()
//				.findAll(properties,pageable,null).stream()
//				.map(item-> (UserDTO)DTOConverter.toModel(item,UserDTO.class))
//				.collect(Collectors.toList());
//		
//		List<Long> result_checked = new UserRepository()
//				.findAll(properties,pageable,dto).stream()
//				.map(e -> e.getId()).collect(Collectors.toList());
//		
//		result.stream().filter(e -> result_checked.contains(e.getId()))
//						.forEach(e -> e.setChecked("checked"));
//		return result;
//	}
//
//	@Override
//	public UserDTO findById(long id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Long save(UserDTO user) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Long update(UserDTO user) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void delete(long[] id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void delete(long id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
