package com.mycompany.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mycompany.model.Client;

@Component
public class Clientdao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private final String INSERT_CLIENT = "INSERT INTO `client` (`id`, `first_name`, `last_name`, `email`, `salary`) VALUES (?, ?, ?, ?, ?)";
	private final String UPDATE_CLIENT = "UPDATE `client` SET `first_name`=?,`last_name`=?,`email`=?,`salary`=? WHERE id = ?";
	private final String DELETE_CLIENT = "DELETE FROM `client` WHERE id= ?";
	private final String GET_BY_ID = "select * from client where id = ?";
	private final String GET_ALL = "select * from client";

	public boolean insert(Client client) {
		int noOfRecordsInserted = jdbcTemplate.update(INSERT_CLIENT, new Object[] { client.getId(),
				client.getFirstName(), client.getLastName(), client.getEmail(), client.getSalary() });
		if (noOfRecordsInserted > 0)
			return true;
		return false;
	}

	public boolean update(Client client) {
		int noOfRecordsInserted = jdbcTemplate.update(UPDATE_CLIENT, new Object[] { client.getFirstName(),
				client.getLastName(), client.getEmail(), client.getSalary(), client.getId() });
		if (noOfRecordsInserted > 0)
			return true;
		else
			return false;

	}

	public boolean delete(Integer id) {
		int noOfRecordsDeleted = jdbcTemplate.update(DELETE_CLIENT, new Object[] { id });
		if (noOfRecordsDeleted > 0)
			return true;
		
			return false;
	}

	public Client get(Integer id) {
		Map<String, Object> row = jdbcTemplate.queryForMap(GET_BY_ID, new Object[] { id });
		Client tempClient = BuildClient(row);
		return tempClient;

	}

	public List<Client> getAll() {
		List<Client> clientList = new ArrayList<Client>();
		List<Map<String, Object>> rowList = jdbcTemplate.queryForList(GET_ALL);

		for (Map<String, Object> row : rowList) {
			clientList.add(BuildClient(row));
		}
		return clientList;

	}

	private Client BuildClient(Map<String, Object> row) {
		Client tempClient = new Client();
		tempClient.setId((Integer) row.get("id"));
		tempClient.setFirstName((String) row.get("first_Name"));
		tempClient.setLastName((String) row.get("last_Name"));
		tempClient.setEmail((String) row.get("email"));
		
	//	BigDecimal salaryBig = (BigDecimal) row.get("salary");
		//tempClient.setSalary(new Double(salaryBig.doubleValue()));
		
		return tempClient;
	}
}
