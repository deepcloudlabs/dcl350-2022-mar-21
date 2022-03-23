package com.example.hr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.hr.domain.Department;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

@Entity
@Table(name = "employees")
public class EmployeeEntity {
	@TcKimlikNo
	@Id
	private String identity;
	@NotBlank
	@Column(name = "fname")
	private String firstName;
	@NotBlank
	@Column(name = "lname")
	private String lastName;
	@Iban
	private String iban;
	@Column(name = "maas")
	private double salary;
	@Lob
	@Column(columnDefinition = "longblob")
	private byte[] photo;
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private JobStyle jobStyle;
	@NotNull
	@Enumerated(EnumType.STRING)
	private Department department;

	public EmployeeEntity() {
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public JobStyle getJobStyle() {
		return jobStyle;
	}

	public void setJobStyle(JobStyle jobStyle) {
		this.jobStyle = jobStyle;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "EmployeeEntity [identity=" + identity + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", iban=" + iban + ", salary=" + salary + ", jobStyle=" + jobStyle + ", department=" + department
				+ "]";
	}

}
