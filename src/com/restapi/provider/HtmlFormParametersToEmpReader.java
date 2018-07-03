package com.restapi.provider;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import com.restapi.model.Address;
import com.restapi.model.Employee;

@Provider
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class HtmlFormParametersToEmpReader implements MessageBodyReader<Employee> {

	@Override
	public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2, MediaType arg3) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Employee readFrom(Class<Employee> emp, Type type, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> map, InputStream in) throws IOException, WebApplicationException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line = br.readLine();
		Map<String, String> parameters = convertToMap(line);
		Employee empl = new Employee();
		Address addr = new Address();
		empl.setName(parameters.get("name"));
		empl.setDesignation(parameters.get("designation"));
		empl.setSalary(Integer.parseInt(parameters.get("salary")));
		addr.setDoorno(Integer.parseInt(parameters.get("doorno")));
		addr.setStreet(parameters.get("street"));
		addr.setLocation(parameters.get("location"));
		addr.setCity(parameters.get("city"));
		empl.setAddress(addr);
		return empl;
	}

	private Map<String, String> convertToMap(String line) {
		// TODO Auto-generated method stub
		Map<String, String> parameters = new HashMap<>();
		String[] keyValuePairs = line.split("&");
		for (String keyValuePair : keyValuePairs) {
			String[] keyValue = keyValuePair.split("=");
			parameters.put(keyValue[0], keyValue[1]);
		}
		return parameters;
	}

}
