package com.javaweb.api;

import com.javaweb.dto.CustomerDTO;
import com.javaweb.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api-server/customer")
public class CustomerAPI {
	
	@Autowired
	ICustomerService service;
	
	@GetMapping("/list")
	public List<CustomerDTO> list(@ModelAttribute CustomerDTO customerRequest){
		Pageable pageable = null;
		if(customerRequest.getPage()!=null && customerRequest.getSize()!=null)
			pageable = PageRequest.of(customerRequest.getPage().intValue(),customerRequest.getSize().intValue());

		List<CustomerDTO> results = service.findAll(customerRequest,pageable);
		return results;
		
	}

	@GetMapping("/count")
	public long count(@ModelAttribute CustomerDTO customerRequest){
		return service.count(customerRequest);
	}


	@GetMapping
	public CustomerDTO findById(@RequestParam Long id){
		CustomerDTO result = service.findById(id);
		return result;

	}
	
	@RequestMapping(value = "",method = RequestMethod.POST,consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public CustomerDTO newCustomer(@RequestBody CustomerDTO customer) {
//		customer.setCreatedBy("admin");
//		customer.setModifiedBy("admin");
//		customer.setCreatedDate(new Timestamp(System.currentTimeMillis()));
//		customer.setModifiedDate(new Timestamp(System.currentTimeMillis()));
//
		Long id = service.save(customer);
		
		CustomerDTO result = service.findById(id);
		return result;
	}

	@RequestMapping(value = "",method = RequestMethod.PUT,consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	protected CustomerDTO updateCustomer(@RequestBody CustomerDTO customer) {

		customer.setCreatedBy("admin");
		customer.setModifiedBy("admin");
		customer.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		customer.setModifiedDate(new Timestamp(System.currentTimeMillis()));

		service.update(customer);
		CustomerDTO resp = service.findById(customer.getId());
		return resp;
	}


	@RequestMapping(value = "",method = RequestMethod.DELETE,consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	protected boolean deleteCustomer(@RequestBody Map<String, Object> json) {
			List<Integer> tempIds = (List<Integer>) json.get("ids");
		List<Long> ids=  tempIds.stream().map(e -> (Long)e.longValue()).collect(Collectors.toList());
		return service.delete(ids);
	}

}
