package com.pri.project.soaps.endpoint;

import org.pri.project.designationlist.DepartmentList;
import org.pri.project.designationlist.DepartmentRequest;
import org.pri.project.designationlist.DepartmentSaveRequest;
import org.pri.project.designationlist.DepartmentSaveResponse;
import org.pri.project.designationlist.DesignationList;
import org.pri.project.designationlist.GetDepartmentsRequest;
import org.pri.project.designationlist.SearchParamRequest;
import org.pri.project.designationlist.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.pri.project.soaps.service.DesignationService;

@Endpoint
public class DesignationListEndpoint {

	@Autowired
	public DesignationService service;

	public static final String NAMESPACE = "http://www.example.org/designationList";

	@PayloadRoot(namespace = NAMESPACE, localPart = "departmentRequest")
	@ResponsePayload
	public DesignationList getDesignations(@RequestPayload DepartmentRequest request) throws Exception {
		return service.getDesignationList(request);
	}

	@PayloadRoot(namespace = NAMESPACE, localPart = "getDepartmentsRequest")
	@ResponsePayload
	public DepartmentList getDepartments(@RequestPayload GetDepartmentsRequest request) {
		return service.getDepartments(request); 
	}

	@PayloadRoot(namespace = NAMESPACE, localPart = "departmentSaveRequest")
	@ResponsePayload
	public DepartmentSaveResponse saveDepartment(@RequestPayload DepartmentSaveRequest request) {
		return service.saveDepartment(request);
	}

	@PayloadRoot(namespace = NAMESPACE, localPart = "searchParamRequest")
	@ResponsePayload
	public SearchResponse searchDepartment(@RequestPayload SearchParamRequest request) {
		return service.searchData(request.getSearchParam(), request.getIDisplayStart(), request.getIDisplayLength());
	}
}
