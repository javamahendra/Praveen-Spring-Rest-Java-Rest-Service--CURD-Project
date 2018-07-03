package com.restapi.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.restapi.model.Address;
import com.restapi.model.Employee;

public class EmployeeDetailsDaoImpl {

	public List<Employee> getEmpDetailsAll() {

		JDBCConnection jdbcConnection = new JDBCConnection();

		Connection connection = jdbcConnection.getConnnection();
		List<Employee> empList = new ArrayList<>();

		try {
			PreparedStatement ps = connection.prepareStatement(
					"SELECT id,name,designation,salary,doorno,street,location,city FROM employee join address on employee.id=address.empId");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Employee emp = new Employee();
				Address addr = new Address();
				ArrayList<Address> addrList = new ArrayList<>();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setDesignation(rs.getString("designation"));
				emp.setSalary(rs.getInt("salary"));

				addr.setDoorno(rs.getInt("doorno"));
				addr.setStreet(rs.getString("street"));
				addr.setLocation(rs.getString("location"));
				addr.setCity(rs.getString("city"));
				addrList.add(addr);

				emp.setAddress(addr);
				empList.add(emp);

			}

		} catch (SQLException e) {

			e.getLocalizedMessage();
		}

		return empList;
	}

	public List<Employee> getEmpDetailsById(String empId) {
		JDBCConnection jdbcConnection = new JDBCConnection();

		Connection connection = jdbcConnection.getConnnection();
		List<Employee> empList = new ArrayList<>();
		List<Address> addrList = new ArrayList<>();

		try {

			PreparedStatement ps = connection.prepareStatement(
					"SELECT id,name,designation,salary,doorno,street,location,city FROM employee join address on employee.id=address.empId where id=?");
			ps.setString(1, empId);
			ResultSet rs = ps.executeQuery();
			System.out.println(rs);
			while (rs.next()) {
				Employee emp = new Employee();
				Address addr = new Address();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setDesignation(rs.getString("designation"));
				emp.setSalary(rs.getInt("salary"));

				addr.setDoorno(rs.getInt("doorno"));
				addr.setStreet(rs.getString("street"));
				addr.setLocation(rs.getString("location"));
				addr.setCity(rs.getString("city"));
				addrList.add(addr);

				emp.setAddress(addr);
				empList.add(emp);

			}

		} catch (SQLException e) {

			e.getLocalizedMessage();
		}

		return empList;
	}

	public boolean addEmployeeDetails(String name, String designation, int salary, int doorNo, String streetName,
			String location, String city) {
		boolean status = false;

		System.out.println("######" + doorNo + " " + streetName);
		JDBCConnection jdbcConnection = new JDBCConnection();

		Connection connection = jdbcConnection.getConnnection();

		int recCount;
		try {

			PreparedStatement ps = connection
					.prepareStatement("insert into employee (name, designation, salary) values ('" + name + "','"
							+ designation + "','" + salary + "')");
			recCount = ps.executeUpdate();
			if (recCount > 0) {
				status = true;
			}
			PreparedStatement ps1 = connection
					.prepareStatement("insert into address (doorno, street, location,city) values ('" + doorNo + "','"
							+ streetName + "','" + location + "','" + city + "')");

			recCount = ps1.executeUpdate();
			if (recCount > 0) {
				System.out.println("done");
				status = true;
			}

		} catch (Exception e) {
			e.getLocalizedMessage();
		}

		return status;
	}

	public boolean deleteEmployeeDetails(String id) {
		JDBCConnection jdbcConnection = new JDBCConnection();

		Connection connection = jdbcConnection.getConnnection();

		boolean recCount = false;
		try {

			PreparedStatement ps = connection.prepareStatement("delete from address where empId=?");
			ps.setString(1, id);
			recCount = ps.execute();// validation
			System.out.println(recCount);
			if (recCount) {
				System.out.println("@@@@@done");

			}

			PreparedStatement ps1 = connection.prepareStatement("delete from employee where id=?");
			ps1.setString(1, id);
			recCount = ps1.execute();

			System.out.println("$$$$$$done");

		} catch (Exception e) {
			e.getLocalizedMessage();
		}

		return recCount;

	}

	public boolean updateEmployeeDetails(String id, String name, String designation, int salary, int doorNo,
			String streetName, String location, String city) {
		JDBCConnection jdbcConnection = new JDBCConnection();

		Connection connection = jdbcConnection.getConnnection();

		boolean updateCount = false;
		try {

			PreparedStatement ps = connection
					.prepareStatement("UPDATE address SET doorno =?,street = ?, location = ?,city = ? WHERE empId=?");
			ps.setInt(1, doorNo);
			ps.setString(2, streetName);
			ps.setString(3, location);
			ps.setString(4, city);
			ps.setString(5, id);
			updateCount = ps.execute();// validation
			System.out.println(updateCount);
			if (updateCount) {
				System.out.println("@@@@@done");

			}

			PreparedStatement ps1 = connection
					.prepareStatement("UPDATE employee SET name=? ,designation=? ,salary=? WHERE id =? ");
			ps1.setString(1, name);
			ps1.setString(2, designation);
			ps1.setInt(3, salary);
			ps1.setString(4, id);
			updateCount = ps1.execute();

			System.out.println("$$$$$$done");

		} catch (Exception e) {
			e.getLocalizedMessage();
		}

		return updateCount;

	}

}
