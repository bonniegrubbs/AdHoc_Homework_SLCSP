package test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Test;
import plan.PlanBean;
import plan.PlanMapper;
import plan.PlanRateArea;

class PlanMapperTests {

	@Test
	void getPlanRateAreas_ReturnsEmptyMapIfEmptyList_Test() {
		PlanMapper planCodeMapper = new PlanMapper();
		List<PlanBean> planBeans = new ArrayList<PlanBean>();
		HashMap<String, PlanRateArea> hashMap = planCodeMapper.getPlanRateAreas(planBeans);
		assertEquals(0, hashMap.size());
	}
	
	@Test
	void getPlanRateAreas_FiltersNonSilverPlans_Test() throws Exception {
		PlanMapper planCodeMapper = new PlanMapper();
		List<PlanBean> planBeans = new ArrayList<PlanBean>();
		planBeans.add(new PlanBean("CO", new BigDecimal(500.01), 1, "Silver"));
		planBeans.add(new PlanBean("FL", new BigDecimal(500.02), 1, "Silver"));
		planBeans.add(new PlanBean("CO", new BigDecimal(500.03), 1, "Bronze"));
		HashMap<String, PlanRateArea> hashMap = planCodeMapper.getPlanRateAreas(planBeans);
		assertEquals(2, hashMap.size());
		
		PlanRateArea floridaSilver = hashMap.get("FL1");
		assertEquals(new BigDecimal(500.02), floridaSilver.getSLCSPRate());
		PlanRateArea coloradoSilver = hashMap.get("CO1");
		assertEquals(new BigDecimal(500.01), coloradoSilver.getSLCSPRate());
	}
	
	@Test
	void getPlanRateAreas_ReturnsSecondLowestCost_Test() throws Exception {
		PlanMapper planCodeMapper = new PlanMapper();
		List<PlanBean> planBeans = new ArrayList<PlanBean>();
		planBeans.add(new PlanBean("CO", new BigDecimal(100.01), 1, "Silver"));
		planBeans.add(new PlanBean("FL", new BigDecimal(500.02), 1, "Silver"));
		planBeans.add(new PlanBean("CO", new BigDecimal(200.03), 1, "Silver"));
		planBeans.add(new PlanBean("CO", new BigDecimal(300.03), 1, "Silver"));
		planBeans.add(new PlanBean("CO", new BigDecimal(500.03), 1, "Silver"));
		HashMap<String, PlanRateArea> hashMap = planCodeMapper.getPlanRateAreas(planBeans);
		assertEquals(2, hashMap.size());
		
		PlanRateArea coloradoSilver = hashMap.get("CO1");
		assertEquals(new BigDecimal(200.03), coloradoSilver.getSLCSPRate());
	}

}
