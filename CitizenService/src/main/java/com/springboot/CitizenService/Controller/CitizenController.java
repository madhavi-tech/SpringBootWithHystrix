package com.springboot.CitizenService.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.springboot.CitizenService.Entity.Citizen;
import com.springboot.CitizenService.Repositories.CitizenRepo;

@RestController
@RequestMapping(path = "/citizen")
public class CitizenController {
	@Autowired
	CitizenRepo repo;
	@RequestMapping(path = "/test")
	public ResponseEntity<String> test() {
		return new ResponseEntity<String>("helllooooooooo", HttpStatus.OK);
	}
	@PostMapping(path = "/add")
	public ResponseEntity<Citizen> saveCitizen(@RequestBody Citizen newcitizen) {
		Citizen citizen=repo.save(newcitizen);
		return new ResponseEntity<Citizen>(citizen, HttpStatus.OK);
	}
	@RequestMapping(path ="/cid/{id}")
	public ResponseEntity<List<Citizen>> getById(@PathVariable Integer id) {
		List<Citizen> listCitizen=repo.findAllById(id);
		return new ResponseEntity<>(listCitizen, HttpStatus.OK);
		
		
	}
	
	  @RequestMapping(path ="/id/{id}") 
	  public ResponseEntity<java.util.List<Citizen>> getByVaccinationId(@PathVariable Integer id) {
	  
	  List<Citizen> listCitizen = repo.findByVaccinationCenterId(id); 
	  return new ResponseEntity<>(listCitizen, HttpStatus.OK); }
	 
}
