package com.mycompany.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.mycompany.dao.Clientdao;
import com.mycompany.model.Client;

@RestController
public class Clientapi {

	Logger logger = LoggerFactory.getLogger(Clientapi.class);
	@Autowired
	Clientdao clientdao;

	@RequestMapping(path = "/client", method = RequestMethod.POST)
	public String create(@RequestBody Client client) {
		logger.info("create method called:" + client.getId() + client.getFirstName() + client.getEmail());

		if (client.getId() == null || client.getId().intValue() == 0)
			throw new RuntimeException("Id must be passed");

		boolean inserted = clientdao.insert(client);
		if (inserted)
			return "New id created";
		else
			return "New id not created";

	}

	@RequestMapping(path = "/client", method = RequestMethod.PUT)
	public String update(@RequestBody Client client) {
		logger.info("Updated record with id:" + client.getId());

		if (client.getId() == null || client.getId() == 0)
			throw new RuntimeException("Id must be passed");

		boolean Updated = clientdao.update(client);
		if (Updated)
			return "updated one record with ID:" + client.getId();
		else
			return "updated zero records";

	}

	@RequestMapping(path= "/client", method = RequestMethod.DELETE)
public String Delete(@RequestParam Integer id){
		logger.info("Deleted record with id:" + id);
		if (id == null || id == 0)
			throw new RuntimeException("Id must be passed");
		boolean Deleted = clientdao.delete( id);
		
		if (Deleted)
			return "Deleted sucessfully";
		else
			return "Delete Unsucessful";
		
		
	}
	@RequestMapping(path = "/client",method = RequestMethod.GET)
	public Client getById(@RequestParam Integer id) {

		logger.info("getById method called for :" + id);

		return clientdao.get(id);
	}
	
	@RequestMapping(path = "/client/getAll", method = RequestMethod.GET)
	public List<Client> getAll() {

		logger.info("getAll method called");

		return clientdao.getAll();
	}
}
