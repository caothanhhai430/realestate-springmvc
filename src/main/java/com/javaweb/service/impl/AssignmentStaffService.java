package com.javaweb.service.impl;

import com.javaweb.dto.AssignmentStaffDTO;
import com.javaweb.repository.impl.AssignmentStaffRepository;
import com.javaweb.service.IAssignmentStaffService;

public class AssignmentStaffService implements IAssignmentStaffService{

	@Override
	public Long update(AssignmentStaffDTO staff) {
		return new AssignmentStaffRepository().update(staff);
	}

}
