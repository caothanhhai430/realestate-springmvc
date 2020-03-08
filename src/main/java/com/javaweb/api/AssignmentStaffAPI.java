package com.javaweb.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.javaweb.dto.AssignmentStaffDTO;
import com.javaweb.dto.BuildingDTO;
import com.javaweb.service.impl.AssignmentStaffService;
import com.javaweb.utils.FormUtils;
import com.javaweb.utils.HttpUtil;

@WebServlet(urlPatterns= {"/api-server/assignmentstaff"})
public class AssignmentStaffAPI extends HttpServlet{

	private static final long serialVersionUID = 6232136808113036943L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json;");
		
		AssignmentStaffDTO assignment = HttpUtil.of(req.getReader()).toModel(AssignmentStaffDTO.class);
		
		ObjectMapper obj = new ObjectMapper();
		
		if((assignment.getBuildingId().length<1 && assignment.getStaffId().length<1)
				|| (assignment.getBuildingId().length>1 && assignment.getStaffId().length>1))
			obj.writeValue(resp.getOutputStream(), "error");
		else {
			long result = new AssignmentStaffService().update(assignment);
			String status = result==0 ? "success" : "error";
			obj.writeValue(resp.getOutputStream(), status);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	
}

