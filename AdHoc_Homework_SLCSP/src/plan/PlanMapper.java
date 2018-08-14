package plan;

import java.util.HashMap;
import java.util.List;

/**
 * Class maps plan rate area objects and inserts into hashmap to handle quick inserts and lookup of plan rate area data
 * 
 * @version 1
 * @author x
 */
public class PlanMapper {
	
	HashMap<String, PlanRateArea> planRateAreasMap = new HashMap<String, PlanRateArea>();
	// obtains the plan's rate area for each planBean in the HashMap
	public HashMap<String, PlanRateArea> getPlanRateAreas(List<PlanBean> planBeans){
		
		for(int i = 0; i < planBeans.size(); i++) {
			addPlanRateAreaToMap(planBeans.get(i));
		}
		
		return planRateAreasMap;
	}

	private void addPlanRateAreaToMap(PlanBean planBean) {
		// only adds the Silver metal_level rate areas to the planBean HashMap
		// for slcsp
		if (MetalLevelEnum.valueOf(planBean.getMetal_level()) == MetalLevelEnum.Silver) {
			// string of the state and rate_area for use as a key to the HashMap values
			String planRateAreaKey = planBean.getState() + planBean.getRate_area();
			
			// get value from HashMap
			PlanRateArea exisitingPlanRateArea = planRateAreasMap.get(planRateAreaKey);
			
			if (exisitingPlanRateArea != null) {
				// if planRateArea exists then addRate to HashMap 
				exisitingPlanRateArea.addRate(planBean.getRate());
				
			} else {
				// if not existing add rate_area and rate to HashMap
				PlanRateArea newPlanRateArea = mapPlanBeanToPlanRateArea(planBean);
				planRateAreasMap.put(planRateAreaKey, newPlanRateArea);
			}
		}

		
	}
	
	private PlanRateArea mapPlanBeanToPlanRateArea(PlanBean planBean) {
		// maps the rate, rate_area, and state to the newPlanRateArea
		PlanRateArea newPlanRateArea = new PlanRateArea();
		newPlanRateArea.addRate(planBean.getRate());
		newPlanRateArea.setRateAreaId(planBean.getRate_area());
		newPlanRateArea.setStateCode(planBean.getState());
		
		return newPlanRateArea;
	}
	
}
