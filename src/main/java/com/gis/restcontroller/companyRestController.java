package com.gis.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gis.entity.CompanyEntity;
import com.gis.service.CompanyService;

@RestController
public class companyRestController {
@Autowired
private CompanyService companyService;

@PostMapping("/save")
public ResponseEntity<String> saveCompanyDts(@RequestBody CompanyEntity companyEntity){
	boolean status = companyService.upsert(companyEntity);
	if(status) {
		return new ResponseEntity<String>("success",HttpStatus.CREATED);
	}
	return new ResponseEntity<String>("Not saved",HttpStatus.BAD_REQUEST);

}

@PutMapping("/update")
public ResponseEntity<String> updateCompanyDts(@RequestBody CompanyEntity companyEntity){
	boolean status = companyService.upsert(companyEntity);
	if(status) {
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	return new ResponseEntity<String>("Not saved",HttpStatus.BAD_REQUEST);

}
@GetMapping("/company/{cid}")
public ResponseEntity<CompanyEntity> getCompanyDetails(@PathVariable Integer cid) {
	CompanyEntity company = companyService.getCompanyDts(cid);
	return new ResponseEntity<>(company, HttpStatus.OK);
}


@GetMapping("/getAllCompanies")
public ResponseEntity<String > getAllCompanyDts(){
	companyService.getAllCompanyDts();
	return new ResponseEntity<String>(HttpStatus.OK);
	
}

@DeleteMapping("/company/{cid}")
public ResponseEntity<String> deleteCompany(@PathVariable Integer cid) {
	String status = companyService.deleteCompanyById(cid);
	return new ResponseEntity<>(status, HttpStatus.OK);
}

	
	
}
