package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import zipCode.ZipCodeRateArea;

class ZipCodeRateAreaTests {

	@Test
	void isAmbigious_ReturnsFalseWhenOnlyOneRateExists_Test() {
		ZipCodeRateArea zipCodeRateArea = new ZipCodeRateArea();
		assertEquals(false, zipCodeRateArea.isAmbigious());
	}
	
	@Test
	void isAmbigious_ReturnsTrueWhenMoreThanOneRateExists_Test() {
		ZipCodeRateArea zipCodeRateArea = new ZipCodeRateArea();
		zipCodeRateArea.incrementRateArea();
		assertEquals(true, zipCodeRateArea.isAmbigious());
	}

}
