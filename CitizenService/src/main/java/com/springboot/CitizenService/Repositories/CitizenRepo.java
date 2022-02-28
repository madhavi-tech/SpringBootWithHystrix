package com.springboot.CitizenService.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.CitizenService.Entity.Citizen;


  public interface CitizenRepo extends JpaRepository<Citizen, Integer>{

	List<Citizen> findAllById(Integer id);
	public List<Citizen> findByVaccinationCenterId(Integer id);
  
  }
  
 