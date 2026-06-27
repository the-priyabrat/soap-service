package com.pri.project.soaps.service;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.pri.project.designationlist.DepartmentList;
import org.pri.project.designationlist.DepartmentRequest;
import org.pri.project.designationlist.DepartmentSaveRequest;
import org.pri.project.designationlist.DepartmentSaveResponse;
import org.pri.project.designationlist.Designation;
import org.pri.project.designationlist.DesignationList;
import org.pri.project.designationlist.GetDepartmentsRequest;
import org.pri.project.designationlist.SearchParam;
import org.pri.project.designationlist.SearchResponse;
import org.pri.project.designationlist.Status;
import org.json.simple.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pri.project.soaps.entity.Department;
import com.pri.project.soaps.exception.RecordNotExistException;
import com.pri.project.soaps.repository.DepartmentRepository;
import com.pri.project.soaps.repository.specification.DepartmentSpecification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class DesignationService {

	private final DepartmentRepository repository;

	public DesignationList getDesignationList(DepartmentRequest request) throws Exception {
		try {
			List<Department> departments = repository.findByDepartmentName(request.getName());
			DesignationList departmentList = new DesignationList();
			List<Designation> designationList = departmentList.getDesignation();
			if (departments.isEmpty()) {
				throw new RecordNotExistException("No Designation Found With This Department");
			}
			departments.forEach(department -> {
				Designation designation = new Designation();
				BeanUtils.copyProperties(department, designation);
				designationList.add(designation);
			});
			return departmentList;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
	}

	public DepartmentList getDepartments(GetDepartmentsRequest request) {
		try {
			if (StringUtils.hasLength(request.getRequest())) {
				List<String> departments = repository.getDistinctDepartments();
				DepartmentList departmentList = new DepartmentList();
				List<String> response = departmentList.getDepartment();
				response.addAll(departments);
				return departmentList;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return new DepartmentList();
	}

	public DepartmentSaveResponse saveDepartment(DepartmentSaveRequest request) {
		DepartmentSaveResponse response = new DepartmentSaveResponse();
		try {
			Department department = new Department();
			BeanUtils.copyProperties(request.getDepartmentDto(), department);
			department = repository.save(department);
			Designation departmentResponse = new Designation();
			BeanUtils.copyProperties(department, departmentResponse);
			response.setDepartment(departmentResponse);
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return response;
	}

	public SearchResponse searchData(SearchParam request, int iDisplayStart, int iDisplayLength) {
		SearchResponse response = new SearchResponse();
		try {
			ObjectMapper mapper = new ObjectMapper();
			String searchParam = mapper.writeValueAsString(request);
			Pageable pagging = PageRequest.of(iDisplayStart / iDisplayLength, iDisplayLength);
			Specification<Department> spec = DepartmentSpecification.searchDepartment(searchParam);
			Page<Department> departmentPages = repository.findAll(spec, pagging);
			List<Department> departmentList = new ArrayList<>();
			departmentPages.forEach(departmentList::add);
			List<Status> statusList = response.getSearchStatus();
			statusList.addAll(countByStatus());
			long count = repository.count(spec);
			response.setTotalDisplayRecord(count);
			response.setTotalRecords(count);
			List<Designation> designationList = response.getSearchData();
			departmentList.forEach(department -> {
				Designation designation = new Designation();
				BeanUtils.copyProperties(department, designation);
				designationList.add(designation);
			});
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	public List<Status> countByStatus() {
		JSONArray array = new JSONArray();
		try {
			List<Department> departmentList = repository.findAll();
			Map<String, Long> countStatus = departmentList.stream()
					.collect(Collectors.groupingBy(Department::getDepartmentName, Collectors.counting()));
			countStatus.keySet().forEach(key -> {
				Status response = new Status();
				response.setName(key);
				response.setCount(countStatus.get(key));
				array.add(response);
			});
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return array;
	}
}
