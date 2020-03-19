package com.javaweb.api;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Tuple;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.dto.UserDTO;
import com.javaweb.service.IUserService;
import com.javaweb.service.impl.UserService;
import com.javaweb.service.impl.UserService;
import com.javaweb.utils.HttpUtil;
import org.codehaus.jackson.map.ObjectMapper;

import com.javaweb.dto.UserDTO;
import com.javaweb.paging.impl.PageRequest;
import com.javaweb.utils.FormUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api-server/staff")
public class UserAPI{

	@Autowired
	IUserService service;
	
	@GetMapping("/list")
	protected List<UserDTO> findAll(@ModelAttribute UserDTO userRequest, @ModelAttribute PageRequest pageRequest){

		List<UserDTO> results =  service.findAll(userRequest, pageRequest);
		return results;
	}

	@RequestMapping(value = {"/assignment"},method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	protected boolean assign(@RequestBody Map<String, Object> json){
		Integer buildingId = (Integer) json.get("buildingId");
		List<Integer> staffIdList = (List<Integer>) json.get("staffId");
		List<Long> staffId = staffIdList.stream().map(e -> (Long)e.longValue()).collect(Collectors.toList());
		Long buildingIdAsLong = (Long) buildingId.longValue();
		return service.assign(staffId, buildingIdAsLong);
	}

	@GetMapping("/assignment")
	protected List<Object> findAll(@RequestParam Long id){

		List<Tuple> data =  service.findAssignmentByBuildingId(id);
		List<Object> results = data.parallelStream().map(e -> e.toArray()).collect(Collectors.toList());
		return results;
	}

	@PostMapping
	protected UserDTO newuser(@ModelAttribute UserDTO user){
		user.setCreatedBy("admin");
		user.setModifiedBy("admin");
		user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		user.setModifiedDate(new Timestamp(System.currentTimeMillis()));

		UserService service = new UserService();
		Long id = service.save(user);
		UserDTO get = service.findById(id);
		return get;
	}

	@PutMapping
	public UserDTO update(@ModelAttribute UserDTO user){
		user.setModifiedBy("admin");
		user.setModifiedDate(new Timestamp(System.currentTimeMillis()));

		UserService service = new UserService();
		Long id = service.update(user);
		UserDTO get = service.findById(id);
		return get;
	}


}
