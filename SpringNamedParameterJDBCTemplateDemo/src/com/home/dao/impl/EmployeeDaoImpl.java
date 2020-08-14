package com.home.dao.impl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.home.dao.EmployeeDao;
import com.home.model.Employee;

@Repository
//public class EmployeeDaoImpl  implements EmployeeDao{
//Using JdbcDaoSupport class
public class EmployeeDaoImpl extends NamedParameterJdbcDaoSupport implements EmployeeDao{

	/*@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;*/
	
	@Autowired
	private DataSource dataSource;

	/*public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}*/
	
	@Override
	public void createEmployee(Employee employee) throws SQLException {
		String sql="insert into employee_table(employee_name,email,gender,salary)values(:empName,:email,:gender,:salary)";
		MapSqlParameterSource map=new MapSqlParameterSource();
		map.addValue("empName", employee.getEmployeeName());
		map.addValue("email", employee.getEmail());
		map.addValue("gender", employee.getGender());
		map.addValue("salary", employee.getSalary());
		//int update = namedParameterJdbcTemplate.update(sql,map);
		//Using JdbcDaoSpport Cladss
		int update = getJdbcTemplate().update(sql, map);
		if(update>0)
			System.out.println("Employee created successfully!!!");
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		String sql="select * from employee_table where employee_id=:empId";
		/*Map<String,Object> map=new HashMap<>();
		map.put("empId", employeeId);*/
		// or
		MapSqlParameterSource map=new MapSqlParameterSource();
		map.addValue("empId", employeeId);
		//Employee employee = namedParameterJdbcTemplate.queryForObject(sql, map, new EmployeeRowMapper());
		//Using JdbcDaoSpport Class
		Employee employee = getNamedParameterJdbcTemplate().queryForObject(sql, map,new EmployeeRowMapper());
		return employee;
	}

	@Override
	public void deleteEmployeeById(int employeeId) {
		String sql="delete from employee_table where employee_id=:empId";
		MapSqlParameterSource map=new MapSqlParameterSource();
		map.addValue("empId", employeeId);
		//int update = namedParameterJdbcTemplate.update(sql,map);
		//Using JdbcDaoSpport Class
		 int update = getNamedParameterJdbcTemplate().update(sql,map);
		if(update>0)
			System.out.println("Employeee deleted successfully!!!");
	}

	@Override
	public void updateEmployeeById(int employeeId, String newEmail) {
		String sql="update employee_table set email=:email where employee_id=:empId";
		MapSqlParameterSource map=new MapSqlParameterSource();
		map.addValue("email", newEmail);
		map.addValue("empId", employeeId);
		//int update = namedParameterJdbcTemplate.update(sql, map);
		//Using JdbcDaoSpport Class
		int update = getNamedParameterJdbcTemplate().update(sql, map);
		if(update>0)
			System.out.println("Employeee update successfully!!!");
	}

	@Override
	public List<Employee> getAllEmployees() {
		String sql="select * from employee_table";
		//return namedParameterJdbcTemplate.query(sql, new EmployeeRowMapper());
		//Using JdbcDaoSpport Class
		return getNamedParameterJdbcTemplate().query(sql, new EmployeeRowMapper());
		 
	}
	
	@PostConstruct
	public void init() {
		setDataSource(dataSource);
	}

}
