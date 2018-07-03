package com.restapi.provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.restapi.model.Employee;

@Provider
@Produces(MediaType.TEXT_HTML)
public class EmpToHtmlWriter implements MessageBodyWriter<List<Employee>> {

	@Override
	public boolean isWriteable(Class<?> type, Type arg1, Annotation[] arg2, MediaType arg3) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void writeTo(List<Employee> emp, Class<?> clz, Type type, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> headers, OutputStream out) throws IOException, WebApplicationException {
		// TODO Auto-generated method stub

		for (Employee e : emp) {

			writeContent(out, "<html><body>");
			writeContent(out, "<table border=1>");
			writeContent(out, "<tr><td>Id</td><td>" + e.getId() + "</td></tr>");

			writeContent(out, "<tr><td>Name</td><td>" + e.getName() + "</td></tr>");
			writeContent(out, "<tr><td>Designation</td><td>" + e.getDesignation() + "</td></tr>");
			writeContent(out, "<tr><td>Salary</td><td>" + e.getSalary() + "</td></tr>");
			writeContent(out, "<tr><td>DoorNo</td><td>" + e.getAddress().getDoorno() + "</td></tr>");
			writeContent(out, "<tr><td>Street</td><td>" + e.getAddress().getStreet() + "</td></tr>");
			writeContent(out, "<tr><td>Location</td><td>" + e.getAddress().getLocation() + "</td></tr>");
			writeContent(out, "<tr><td>City</td><td>" + e.getAddress().getCity() + "</td></tr>");

			writeContent(out, "</table></body></html>");
		}
	}

	private void writeContent(OutputStream out, String content) throws IOException {
		// TODO Auto-generated method stub
		out.write(content.getBytes());
	}

	@Override
	public long getSize(List<Employee> arg0, Class<?> arg1, Type arg2, Annotation[] arg3, MediaType arg4) {
		// TODO Auto-generated method stub
		return 0;
	}

}
