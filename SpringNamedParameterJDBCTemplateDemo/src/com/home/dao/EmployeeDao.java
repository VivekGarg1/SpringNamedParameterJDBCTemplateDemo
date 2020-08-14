package com.home.dao;

import java.sql.SQLException;
import java.util.List;

import com.home.model.Employee;

public interface EmployeeDao {
	
	public abstract void createEmployee(Employee employee) throws SQLException;
	public abstract Employee getEmployeeById(int employeeId);
	public abstract void deleteEmployeeById(int employeeId);
	public abstract void updateEmployeeById(int employeeId,String newEmail);
	public abstract List<Employee> getAllEmployees();

}
