package com.gis.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.gis.entity.CompanyEntity;
import com.gis.repo.CompanyRepo;
import com.gis.service.CompanyServiceImpl;

@SpringBootTest
public class CompanyServiceTest {
	@Mock
	private CompanyRepo companyRepo;
	@InjectMocks
	CompanyServiceImpl companyService;

	@Test
	public void testPositiveUpsert() {
		CompanyEntity entity = new CompanyEntity(1, "tcs", "hyd");
		companyService.upsert(entity);
		verify(companyRepo).save(entity);

	}

	@Test
	public void testNegativeUpsert() {
		CompanyEntity entity = new CompanyEntity(1, null, "hyd");
		companyService.upsert(entity);
		// verify(companyRepo).save(entity);

	}

	@Test
	void testGetAllCompanyPositive() {
		List<CompanyEntity> mockCourseList = new ArrayList<>();
		mockCourseList.add(new CompanyEntity(100, "Python", "Tcs"));
		mockCourseList.add(new CompanyEntity(100, "JSE", "TCS"));
		when(companyRepo.findAll()).thenReturn(mockCourseList);

		List<CompanyEntity> result = companyService.getAllCompanyDts();

		assertEquals(result.size(), mockCourseList.size());
	}

	@Test
	void testGetAllCompanyNegative() {
		List<CompanyEntity> mockCourseList = new ArrayList<>();
		when(companyRepo.findAll()).thenReturn(mockCourseList);

		List<CompanyEntity> result = companyService.getAllCompanyDts();

		assertEquals(result.size(), mockCourseList.size());
	}

	@Test
	public void testDeleteCompanyById_PositiveCase() {

		Integer companyId = 1;
		when(companyRepo.existsById(companyId)).thenReturn(true);

		String result = companyService.deleteCompanyById(companyId);

		assertEquals("Delete Success", result);
	}

	@Test
	public void testDeleteCompanyById_NegativeCase() {

		Integer companyId = 1;
		when(companyRepo.existsById(companyId)).thenReturn(false);
		String result = companyService.deleteCompanyById(companyId);
		assertEquals("No Record Found", result);
	}

	@Test
	public void testGetByIdPositive() {
		Integer id = 3;
		CompanyEntity entity = new CompanyEntity(id, "HCL", "HYD");
		CompanyEntity byId = companyService.getCompanyDts(id);
		Optional<CompanyEntity> s = Optional.of(entity);
		when(companyRepo.findById(id)).thenReturn(s);
		CompanyEntity companyObj = companyService.getCompanyDts(id);
	}

	@Test
	public void testGetByIdNegative() {
		Integer id = null;
		when(companyRepo.findById(id)).thenReturn(null);
		String byId = companyService.deleteCompanyById(id);
		// assertEquals(id, byId);

	}

}
