package zipCode;

import com.opencsv.bean.CsvBindByName;

/**
* Class data access object used by OpenCSV to parse zip records from zips.csv file
* @version 1
* @author x
*/
public class ZipCodeRateAreaBean {
	
	public ZipCodeRateAreaBean() {
		
	}

	public ZipCodeRateAreaBean(String zipcode, String state, int county_code, int rate_area) {
		super();
		this.zipcode = zipcode;
		this.state = state;
		this.county_code = county_code;
		this.rate_area = rate_area;
	}

	@CsvBindByName
	private String zipcode;
	
	@CsvBindByName
	private String state;
	
	@CsvBindByName
	private int county_code;
	
	@CsvBindByName
	private int rate_area;

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getCounty_code() {
		return county_code;
	}

	public void setCounty_code(int county_code) {
		this.county_code = county_code;
	}

	public int getRate_area() {
		return rate_area;
	}

	public void setRate_area(int rate_area) {
		this.rate_area = rate_area;
	}

	
}
