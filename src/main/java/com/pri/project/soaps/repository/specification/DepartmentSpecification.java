package com.interland.ipsh.soaps.repository.specification;

import org.json.simple.JSONObject;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interland.ipsh.soaps.entity.Department;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DepartmentSpecification {
	public static Specification<Department> searchDepartment(String searchParam) {
		return new Specification<Department>() {

			private static final long serialVersionUID = -4702866527041722471L;

			@SuppressWarnings("unused")
			@Override
			public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query,
					CriteriaBuilder criteriaBuilder) {
				Predicate finalPredicate = null;
				try {
					if (StringUtils.hasLength(searchParam)) {
						ObjectMapper mapper = new ObjectMapper();
						JSONObject searchDataJson = mapper.readValue(searchParam, JSONObject.class);
						String departmentId = (String) searchDataJson.get("departmentId");
						String departmentName = (String) searchDataJson.get("departmentName");
						String designation = (String) searchDataJson.get("designation");

						if (StringUtils.hasLength(departmentId)) {
							Predicate idPredicate = criteriaBuilder.equal(root.get("departmentId"), departmentId);
							if (finalPredicate != null) {
								finalPredicate = criteriaBuilder.and(finalPredicate, idPredicate);
							} else {
								finalPredicate = criteriaBuilder.and(idPredicate);
							}
						}

						if (StringUtils.hasLength(departmentName)) {
							Predicate departmentPredicate = criteriaBuilder.like(root.get("departmentName"),
									departmentName + "%");
							if (finalPredicate != null) {
								finalPredicate = criteriaBuilder.and(finalPredicate, departmentPredicate);
							} else {
								finalPredicate = criteriaBuilder.and(departmentPredicate);
							}
						}

						if (StringUtils.hasLength(designation)) {
							Predicate designationPredicate = criteriaBuilder.like(root.get("designation"),
									designation + "%");
							if (finalPredicate != null) {
								finalPredicate = criteriaBuilder.and(finalPredicate, designationPredicate);
							} else {
								finalPredicate = criteriaBuilder.and(designationPredicate);
							}
						}

						query.orderBy(criteriaBuilder.asc(root.get("departmentId")));
					}
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e.getMessage());
				}
				return finalPredicate;
			}
		};
	}
}
