package com.springboot.VaccinationCenter.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springboot.VaccinationCenter.Entity.VaccinationCenter;
import com.springboot.VaccinationCenter.Model.Citizen;
import com.springboot.VaccinationCenter.Model.Response;
import com.springboot.VaccinationCenter.Repositories.CenterRepo;

@RestController
@RequestMapping(path="/vaccination")
public class VaccinationController {
	
	@Autowired
	private CenterRepo centerRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping(path ="/add")
	public ResponseEntity<VaccinationCenter> addCitizen(@RequestBody VaccinationCenter vaccinationCenter) {
		
		VaccinationCenter vaccinationCenterAdded = centerRepo.save(vaccinationCenter);
		return new ResponseEntity<>(vaccinationCenterAdded, HttpStatus.OK);
	}
	
	@GetMapping(path = "/id/{id}")
	@HystrixCommand(fallbackMethod = "handleCitizenDownTime")
	public ResponseEntity<Response> getAllDadaBasedonCenterId(@PathVariable Integer id){
		Response requiredResponse =  new Response();
		// Get vaccination center detail
		VaccinationCenter center  = centerRepo.findById(id).get();
		requiredResponse.setCenter(center);
		
		// Get all citizen registerd to vaccination center
		java.util.List<Citizen> listOfCitizens = restTemplate.getForObject("http://localhost:8081/citizen/id/"+id, List.class);
		requiredResponse.setCitizens(listOfCitizens);
		return new ResponseEntity<Response>(requiredResponse, HttpStatus.OK);
	}
	public ResponseEntity<Response> handleCitizenDownTime(@PathVariable Integer id){
		Response requiredResponse =  new Response();
		// Get vaccination center detail
		VaccinationCenter center  = centerRepo.findById(id).get();
		requiredResponse.setCenter(center);
		return new ResponseEntity<Response>(requiredResponse, HttpStatus.OK);
		
	}
	}

