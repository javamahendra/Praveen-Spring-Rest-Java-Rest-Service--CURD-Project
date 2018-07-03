package com.restapi.service;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.restapi.dao.EmployeeDetailsDaoImpl;
import com.restapi.model.Employee;

@Path("/emp")
@Singleton
public class EmployeeDetailsServiceImpl {

	@GET
	@Produces({ MediaType.TEXT_HTML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, })
	@Path("/employee")
	public List<Employee> getEmpDetailsAll(String userId) {
		EmployeeDetailsDaoImpl empDaoImpl = new EmployeeDetailsDaoImpl();
		List<Employee> empDetailsList = null;
		empDetailsList = empDaoImpl.getEmpDetailsAll();
		return empDetailsList;
	}

	@GET
	@Path("/employee/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_HTML, MediaType.APPLICATION_XML })
	public Response getEmpDetailsById(@PathParam("id") String empId) {

		EmployeeDetailsDaoImpl empDaoImpl = new EmployeeDetailsDaoImpl();
		List<Employee> empDetailsList = null;

		empDetailsList = empDaoImpl.getEmpDetailsById(empId);

		GenericEntity<List<Employee>> entity = new GenericEntity<List<Employee>>(empDetailsList) {
		};

		Response response = Response.status(Status.OK).entity(entity).build();
		return response;

	}

	@POST
	@Path("/employee")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML })
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void insertEmployeeRecords(Employee json) {

		EmployeeDetailsDaoImpl empDaoImpl = new EmployeeDetailsDaoImpl();

		String name = json.getName();
		String design = json.getDesignation();
		int sal = json.getSalary();
		System.out.println("$$$$$$$" + name + " " + design + " " + sal);
		int dnumber = json.getAddress().getDoorno();
		String street = json.getAddress().getStreet();
		String loc = json.getAddress().getLocation();
		String city = json.getAddress().getCity();

		empDaoImpl.addEmployeeDetails(name, design, sal, dnumber, street, loc, city);

	}

	@POST
	@Path("/employee")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML })
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	public void insertEmployeeRec(@FormParam("name") String name, @FormParam("design") String design,
			@FormParam("sal") int sal, @FormParam("doorno") int dnumber, @FormParam("street") String street,
			@FormParam("location") String loc, @FormParam("city") String city) {

		EmployeeDetailsDaoImpl empDaoImpl = new EmployeeDetailsDaoImpl();

		empDaoImpl.addEmployeeDetails(name, design, sal, dnumber, street, loc, city);

	}

	@DELETE
	@Path("/employee/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void deleteEmployeeRecords(@PathParam("id") String id) {
		EmployeeDetailsDaoImpl empDaoImpl = new EmployeeDetailsDaoImpl();
		empDaoImpl.deleteEmployeeDetails(id);
	}

	@POST
	@Path("/employee/multiple")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML })
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateMultipleEmpRecord(List<Employee> json) {

		EmployeeDetailsDaoImpl empDaoImpl = new EmployeeDetailsDaoImpl();

		for (Employee e : json) {
			String name = e.getName();
			String design = e.getDesignation();
			int sal = e.getSalary();
			int dnumber = e.getAddress().getDoorno();
			String street = e.getAddress().getStreet();
			String loc = e.getAddress().getLocation();
			String city = e.getAddress().getCity();

			empDaoImpl.addEmployeeDetails(name, design, sal, dnumber, street, loc, city);
		}
	}

	@PUT
	@Path("/employee/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateEmpRecord(@PathParam("id") String id, Employee emp) {
		EmployeeDetailsDaoImpl empDaoImpl = new EmployeeDetailsDaoImpl();

		String name = emp.getName();
		String design = emp.getDesignation();
		int sal = emp.getSalary();
		int dnumber = emp.getAddress().getDoorno();
		String street = emp.getAddress().getStreet();
		String loc = emp.getAddress().getLocation();
		String city = emp.getAddress().getCity();

		empDaoImpl.updateEmployeeDetails(id, name, design, sal, dnumber, street, loc, city);
	}

}
