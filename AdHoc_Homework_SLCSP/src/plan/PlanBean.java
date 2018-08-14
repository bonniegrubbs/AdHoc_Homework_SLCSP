package plan;

import java.math.BigDecimal;

import com.opencsv.bean.CsvBindByName;

/**
* Class data access object used by OpenCSV to parse plan records from plans.csv file
* @version 1
* @author x
*/
public class PlanBean {
	
	public PlanBean() {
		
	}
		// constructor for fields to make creating tests easier	
	public PlanBean(String state, BigDecimal rate, int rate_area, String metal_level) {
		super();
		this.state = state;
		this.rate = rate;
		this.rate_area = rate_area;
		this.metal_level = metal_level;
	}
	// @CsvBindByName is a tag used by openCSV CsvToBeanBuilder to bind CSV data to beans
	// and vice versa
	@CsvBindByName
	private String state;
	
	@CsvBindByName
	private BigDecimal rate;
	
	@CsvBindByName
	private int rate_area;

	@CsvBindByName
	private String metal_level;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public int getRate_area() {
		return rate_area;
	}

	public void setRate_area(int rate_area) {
		this.rate_area = rate_area;
	}
	
	public String getMetal_level() {
		return metal_level;
	}

	public void setMetal_level(String metal_level) {
		this.metal_level = metal_level;
	}
	
}
