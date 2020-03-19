package com.javaweb.repository;

import javax.persistence.Tuple;
import java.util.List;

public interface UserRepositoryCustom {
    List<Tuple> findStaffByBuildingId(Long id);
}
