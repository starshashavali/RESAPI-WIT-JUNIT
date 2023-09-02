package com.gis.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gis.entity.CompanyEntity;
import com.gis.restcontroller.companyRestController;
import com.gis.service.CompanyService;

@WebMvcTest(value=companyRestController.class)
public class companyRestControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private CompanyService companyService;
	@Test
	public void PositiveSaveCompanyDts() throws Exception {
		CompanyEntity entity=new CompanyEntity();
		ObjectMapper mapper=new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(entity);
		
		when(companyService.upsert(entity)).thenReturn(true);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(writeValueAsString)).andReturn().getResponse().getStatus();
	}

	@Test
	public void testNegativeSaveCompanyDts() throws Exception {
		CompanyEntity entity=new CompanyEntity();
		ObjectMapper mapper=new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(entity);
		
		when(companyService.upsert(entity)).thenReturn(false);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/save")
				.contentType(MediaType.APPLICATION_JSON)
				.content(writeValueAsString)).andReturn().getResponse().getStatus();
	}
	
	@Test
	public void testUpdateCompanyPositive() throws Exception {
		CompanyEntity entity = new CompanyEntity(1,"TSC","HYd");
		ObjectMapper mapper=new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(entity);
		
		when(companyService.upsert(entity)).thenReturn(true);
		

		mockMvc.perform(MockMvcRequestBuilders.put("/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(writeValueAsString))
		         .andExpect(status().isOk());
	}
	@Test
	public void testUpdateCompanyNegative() throws Exception {
		CompanyEntity entity = new CompanyEntity(1,"TSC","HYd");
		ObjectMapper mapper=new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(entity);
		when(companyService.upsert(entity)).thenReturn(false);
		

		mockMvc.perform(MockMvcRequestBuilders.put("/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content(writeValueAsString));
		         //.andExpect(status().isBadRequest());
	}

	@Test
	void testGetAllCourses() throws Exception {
	
		List<CompanyEntity> mockCourseList = new ArrayList<>();
		mockCourseList.add(new CompanyEntity(100, "TCS", "Vizg"));
		mockCourseList.add(new CompanyEntity(101, "HCL", "Hyd"));
		when(companyService.getAllCompanyDts()).thenReturn(mockCourseList);
		mockMvc.perform(get("/getAllCompanies")).andExpect(status().isOk());
																												// JSON																										// //																											// array																											// // 																											// //																										// returned
	}


	@Test
	void testgetByCompanyPositive() throws Exception {
		CompanyEntity mockCourse = new CompanyEntity(1, "HCL", "HYD");
		when(companyService.getCompanyDts(1)).thenReturn(mockCourse);

		mockMvc.perform(MockMvcRequestBuilders.get("/company/{cid}", 1)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
				
	}

	@Test
	void testDeleteCompany() throws Exception {
		when(companyService.deleteCompanyById(1)).thenReturn("Deleted successfully");

		mockMvc.perform(delete("/company/{cid}", 1).
				contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

	}

	

}
