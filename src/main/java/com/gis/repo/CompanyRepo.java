package com.gis.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gis.entity.CompanyEntity;

public interface CompanyRepo extends JpaRepository<CompanyEntity, Integer> {

}
