package test;

import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import plan.PlanRateArea;

class PlanRateAreaTests {

	@Test
	void getSLCSPRate_Empty_Rates_List_ThrowsException_Test() {
		PlanRateArea planRateArea = new PlanRateArea();
		
		  try {
			  planRateArea.getSLCSPRate();
			} catch (Exception e) {
				assertEquals("No rates present for this area", e.getMessage());
			}
		  
	}

	@Test
	void getSLCSPRate_ReturnsRateWhenListHasOneElement_Test() {
		PlanRateArea planRateArea = new PlanRateArea();
		planRateArea.addRate(new BigDecimal(2.56));
		try {
			assertEquals(new BigDecimal(2.56), planRateArea.getSLCSPRate());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	void getSLCSPRate_ReturnsCorrectSLCSPRate_Test() {
		PlanRateArea planRateArea = new PlanRateArea();
		
		BigDecimal duplicateDecimal1 = new BigDecimal(2.66);
		BigDecimal duplicateDecimal2 = new BigDecimal(2.66);
		BigDecimal lowDecimal = new BigDecimal(1.56);
		BigDecimal slcspRateDecimal = new BigDecimal(1.77);
		BigDecimal highDecimal = new BigDecimal(8.99);
		
		planRateArea.addRate(duplicateDecimal1);
		planRateArea.addRate(duplicateDecimal2);
		planRateArea.addRate(slcspRateDecimal);
		planRateArea.addRate(lowDecimal);
		planRateArea.addRate(highDecimal);
		
		try {
			assertEquals(new BigDecimal(1.77), planRateArea.getSLCSPRate());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
