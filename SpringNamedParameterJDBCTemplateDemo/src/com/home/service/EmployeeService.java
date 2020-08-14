package com.home.service;

import java.sql.SQLException;
import java.util.List;

import com.home.model.Employee;

public interface EmployeeService {

	public abstract void addEmployee(Employee employee) throws SQLException;
	public abstract Employee fetchEmployeeById(int employeeId);
	public abstract void deleteEmployeeById(int employeeId);
	public abstract void updateEmployeeById(int employeeId,String newEmail);
	public abstract List<Employee> getAllEmployeesInfo();
	
}
