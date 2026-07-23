package com.employee.leaveManagement.repository;

import com.employee.leaveManagement.entity.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType,Integer> {
}
