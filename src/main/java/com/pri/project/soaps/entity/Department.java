package com.interland.ipsh.soaps.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "DEPARTMENT")
public class Department {

	@Id
	@Column(name = "DEPARTMENT_ID")
	private Long departmentId;
	@Column(name = "DEPARTMENT_NAME",nullable = false)
	private String departmentName;
	@Column(name = "DESIGNATION")
	private String designation;
}