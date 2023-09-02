package com.gis.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gis.entity.CompanyEntity;
import com.gis.repo.CompanyRepo;


@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyRepo companyRepo;

	public boolean upsert(CompanyEntity companyEntity) {
		companyRepo.save(companyEntity);
			return true;
		}

	
	@Override
	public List<CompanyEntity> getAllCompanyDts() {
		return companyRepo.findAll();
	}



	@Override
	public String deleteCompanyById(Integer id) {
		if (companyRepo.existsById(id)) {
			companyRepo.deleteById(id);
			return "Delete Success";
		} 
			return "No Record Found";
		
	}

	@Override
	public CompanyEntity getCompanyDts(Integer id) {
		Optional<CompanyEntity> findById = companyRepo.findById(id);
		
	if(findById.isPresent()) {
		return  findById.get();
	}
		return null;
	}

}







