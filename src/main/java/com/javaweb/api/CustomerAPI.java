package com.javaweb.api;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaweb.dto.CustomerDTO;
import com.javaweb.paging.impl.PageRequest;
import com.javaweb.service.impl.CustomerService;
import com.javaweb.utils.HttpUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@WebServlet(urlPatterns= {"/api-server/customer"})
public class CustomerAPI extends HttpServlet{

	@GetMapping("/list")
	public List<CustomerDTO> findAll(@ModelAttribute CustomerDTO customerRequest,@ModelAttribute PageRequest pageRequest){
		List<CustomerDTO> results =  new CustomerService().findAll(customerRequest, pageRequest);
		return  results;
	}

	@PostMapping
	protected CustomerDTO newCustomer(@ModelAttribute CustomerDTO customer){
		customer.setCreatedBy("admin");
		customer.setModifiedBy("admin");
		customer.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		customer.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		
		CustomerService service = new CustomerService();
		Long id = service.save(customer);
		CustomerDTO get = service.findById(id);
		return get;
	}

	@PutMapping
	public CustomerDTO update(@ModelAttribute CustomerDTO customer){
		customer.setModifiedBy("admin");
		customer.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		
		CustomerService service = new CustomerService();
		Long id = service.update(customer);
		CustomerDTO get = service.findById(id);
		return get;
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CustomerDTO customer = HttpUtil.of(req.getReader()).toModel(CustomerDTO.class);
		CustomerService service = new CustomerService();
		long[] ids = customer.getIds();
		if(ids!=null && ids.length>0) {
			service.delete(customer.getIds());
		}
	}

	
	
}
