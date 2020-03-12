//package com.javaweb.api;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.codehaus.jackson.map.ObjectMapper;
//
//import com.javaweb.dto.UserDTO;
//import com.javaweb.paging.impl.PageRequest;
//import com.javaweb.service.impl.UserService;
//import com.javaweb.utils.FormUtils;
//
//@WebServlet(urlPatterns= {"/api-server/staff"})
//public class UserAPI extends HttpServlet{
//
//	private static final long serialVersionUID = -2206471147860279623L;
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		resp.setContentType("application/json;");
//		
//		UserDTO customerRequest = FormUtils.toModel(UserDTO.class, req);
//		PageRequest pageRequest = FormUtils.toModel(PageRequest.class, req);
//		
//		
//		List<UserDTO> results =  new UserService().findAll(customerRequest, pageRequest);
//		ObjectMapper obj = new ObjectMapper();
//		obj.writeValue(resp.getOutputStream(), results);
//		
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		super.doPost(req, resp);
//	}
//	
//	
//}
