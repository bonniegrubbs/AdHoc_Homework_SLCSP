package zipCode;

/**
 * Class contains properties and business logic for zipcode rate areas
 * 
 * @version 1
 * @author x
 */
public class ZipCodeRateArea {
	
	private String ZipCode;
	
	private int RateArea;
	
	private int rateCounter = 1;
	
	private String State;
	
	public String getZipCode() {
		return ZipCode;
	}

	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}

	public int getRateArea() {
		return RateArea;
	}

	public void setRateArea(int rateArea) {
		RateArea = rateArea;
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public void incrementRateArea() {
		rateCounter++;
	}
	
	public boolean isAmbigious() {
		//can only have one rate area for this zipcode
		return rateCounter!=1;
	}
}