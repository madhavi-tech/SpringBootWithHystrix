package com.springboot.VaccinationCenter.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.VaccinationCenter.Entity.VaccinationCenter;

public interface CenterRepo extends JpaRepository<VaccinationCenter, Integer>{

}
