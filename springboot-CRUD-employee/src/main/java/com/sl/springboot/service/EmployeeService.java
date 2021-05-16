package com.sl.springboot.service;

import java.util.List;

import com.sl.springboot.model.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	
	void saveEmployee(Employee employee);
	
	Employee getEmployeeById(long id);
	
	void deleteEmployeeByID(long id);
	

}
