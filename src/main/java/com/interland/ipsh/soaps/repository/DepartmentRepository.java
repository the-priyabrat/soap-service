package com.interland.ipsh.soaps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.interland.ipsh.soaps.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>, JpaSpecificationExecutor<Department> {
	@Query("Select distinct d.departmentName from Department d")
	public List<String> getDistinctDepartments();

	public List<Department> findByDepartmentName(String department);
}
