package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Test;
import zipCode.ZipCodeMapper;
import zipCode.ZipCodeRateArea;
import zipCode.ZipCodeRateAreaBean;

class ZipCodeMapperTests {

	@Test
	void getZipCodeRates_ReturnsEmptyMapIfEmptyList_Test() {
		ZipCodeMapper zipCodeMapper = new ZipCodeMapper();
		List<ZipCodeRateAreaBean> zipCodeRateAreaBeans = new ArrayList<ZipCodeRateAreaBean>();
		 HashMap<String, ZipCodeRateArea> hashMap = zipCodeMapper.getZipCodeRates(zipCodeRateAreaBeans);
		assertEquals(0, hashMap.size());
	}
	
	@Test
	void getZipCodeRates_IncrementsRateAreaWithAmbigiousRateAreas_Test() {
		String zipCode = "80919";
		ZipCodeMapper zipCodeMapper = new ZipCodeMapper();
		List<ZipCodeRateAreaBean> zipCodeRateAreaBeans = new ArrayList<ZipCodeRateAreaBean>();
		
		zipCodeRateAreaBeans.add(new ZipCodeRateAreaBean(zipCode, "CO", 113423, 12));
		zipCodeRateAreaBeans.add(new ZipCodeRateAreaBean(zipCode, "CO", 113555, 1));
		
		HashMap<String, ZipCodeRateArea> hashMap = zipCodeMapper.getZipCodeRates(zipCodeRateAreaBeans);
		assertEquals(1, hashMap.size());
		
		ZipCodeRateArea existing = hashMap.get(zipCode);
		assertEquals(true, existing.isAmbigious());
	}

	
	@Test
	void getZipCodeRates_AddsZipRateAreasCorrectly_Test() {
		String zipCode = "80919";
		ZipCodeMapper zipCodeMapper = new ZipCodeMapper();
		List<ZipCodeRateAreaBean> zipCodeRateAreaBeans = new ArrayList<ZipCodeRateAreaBean>();
		
		zipCodeRateAreaBeans.add(new ZipCodeRateAreaBean(zipCode, "CO", 113423, 12));
		zipCodeRateAreaBeans.add(new ZipCodeRateAreaBean("33914", "FL", 113555, 12));
		
		HashMap<String, ZipCodeRateArea> hashMap = zipCodeMapper.getZipCodeRates(zipCodeRateAreaBeans);
		assertEquals(2, hashMap.size());
		
		ZipCodeRateArea existing = hashMap.get(zipCode);
		assertEquals(false, existing.isAmbigious());
	}

}
