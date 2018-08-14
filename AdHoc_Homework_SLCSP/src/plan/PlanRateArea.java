package plan;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class contains properties and business logic for plan rate areas
 * 
 * @version 1
 * @author x
 */
public class PlanRateArea {
	
	//consider a constructor class to be used for mappings
	
	//this might be some kind of set that can be sorted, you have to decide
	List<BigDecimal> rates = new ArrayList<BigDecimal>();
	
	private int RateAreaId;
	
	private String StateCode;
	
	public int getRateAreaId() {
		return RateAreaId;
	}

	public void setRateAreaId(int rateAreaId) {
		RateAreaId = rateAreaId;
	}

	public String getStateCode() {
		return StateCode;
	}

	public void setStateCode(String stateCode) {
		StateCode = stateCode;
	}

	public BigDecimal getSLCSPRate() throws Exception {
		// throw an error if list is empty/null...don't want to assign incorrect rates
		if (rates.isEmpty()) {
			throw new Exception("No rates present for this area");
		}
		//return rate at index 0 if only one rate
		if (rates.size() == 1) {
			return rates.get(0);
		}
		//return rate at index 1 if more than one rate
			return rates.get(1);
	}
	
	public void addRate(BigDecimal rate) {
		addRateIfNotExists(rate);
		sortRateList();
	}
	
	private void addRateIfNotExists(BigDecimal rate) {
		//check list to see if rate exists
		if (!rates.contains(rate)) {
			//if not exists add
			rates.add(rate);
		}
	}
	
	private void sortRateList() {
		Collections.sort(rates);
	}
}
