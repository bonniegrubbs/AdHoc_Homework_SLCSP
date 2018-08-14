package slcsp;

import java.math.BigDecimal;

import com.opencsv.bean.CsvBindByName;

/**
* Class data access object used by OpenCSV to parse slcsp records from slcsp.csv file
* @version 1
* @author x
*/
public class SLCSPBean {

	// used string for zipcode to keep leading zeros
	@CsvBindByName
	private String zipCode;

	// used BigDecimal to deal with currency
	@CsvBindByName
	private BigDecimal rate;

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
}
