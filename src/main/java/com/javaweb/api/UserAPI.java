package com.javaweb.api;

import com.javaweb.dto.UserDTO;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api-server/staff")
public class UserAPI{

	@Autowired
	IUserService service;
	
	@GetMapping("/list")
	protected List<UserDTO> findAll(@ModelAttribute UserDTO userRequest){
		Pageable pageable = null;
		if(userRequest.getPage()!=null && userRequest.getSize()!=null)
			pageable = PageRequest.of(userRequest.getPage().intValue(),userRequest.getSize().intValue());

		List<UserDTO> results =  service.findAll(userRequest, pageable);
		return results;
	}


	@GetMapping
	public UserDTO findById(@RequestParam Long id){
		UserDTO result = service.findById(id);
		return result;

	}


	@RequestMapping(value = "",method = RequestMethod.POST,consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public UserDTO newUser(@RequestBody UserDTO user) {
		Long id = service.save(user);

		UserDTO result = service.findById(id);
		return result;
	}


	@RequestMapping(value = "",method = RequestMethod.PUT,consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public UserDTO updateUser(@RequestBody UserDTO user) {
		Long id = service.update(user);

		UserDTO result = service.findById(id);
		return result;
	}


	@RequestMapping(value = "",method = RequestMethod.DELETE,consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public boolean deleteUser(@RequestBody UserDTO user) {
		try{
			service.lockUser(user.getId());
			return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@GetMapping("/count")
	public long count(@ModelAttribute UserDTO userRequest){

		return service.count(userRequest);
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


	@RequestMapping(value = {"/assignment-customer"},method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	protected boolean assignCustomer(@RequestBody Map<String, Object> json){
		Integer customerId = (Integer) json.get("customerId");
		List<Integer> staffIdList = (List<Integer>) json.get("staffId");
		List<Long> staffId = staffIdList.stream().map(e -> (Long)e.longValue()).collect(Collectors.toList());
		Long customerIdAsLong = (Long) customerId.longValue();
		return service.assignCustomer(staffId, customerIdAsLong);
	}

	@GetMapping("/assignment-customer")
	protected List<Object> findAllAssignCustomer(@RequestParam Long id){

		List<Tuple> data =  service.findAssignmentByCustomerId(id);
		List<Object> results = data.parallelStream().map(e -> e.toArray()).collect(Collectors.toList());
		return results;
	}


	@PostMapping
	protected UserDTO newuser(@ModelAttribute UserDTO user){
		Long id = service.save(user);
		UserDTO resp = service.findById(id);
		return resp;
	}

	@PutMapping
	public UserDTO update(@ModelAttribute UserDTO user){
		Long id = service.update(user);
		UserDTO resp = service.findById(id);
		return resp;
	}


}
