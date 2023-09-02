package com.gis.service;

import java.util.List;

import com.gis.entity.CompanyEntity;

public interface CompanyService  {
	
	public boolean upsert(CompanyEntity companyEntity);
	
	public CompanyEntity getCompanyDts(Integer id);
	
	public List<CompanyEntity> getAllCompanyDts();
	
	
	public String deleteCompanyById(Integer id);


	
	

	

}
