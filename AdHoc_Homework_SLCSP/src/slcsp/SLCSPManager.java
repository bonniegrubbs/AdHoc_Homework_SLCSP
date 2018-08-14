package slcsp;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import fileUtilities.CSVwriter;
import fileUtilities.FileTypeEnum;
import plan.PlanRateArea;
import zipCode.ZipCodeRateArea;

/**
 * Class manages and stores slcsp rate lookups and output of slcsp file
 * 
 * @version 1
 * @author x
 */
public class SLCSPManager {

	private static final Logger LOGGER = Logger.getLogger(SLCSPManager.class.getName());

	private List<SLCSPBean> slcspRates = new ArrayList<SLCSPBean>();

	public SLCSPManager(List<SLCSPBean> slcspRates) {
		this.slcspRates = slcspRates;
	}

	public void mapSLCSPRates(HashMap<String, ZipCodeRateArea> zipCodeRateAreas,
			HashMap<String, PlanRateArea> rateAreaRates) {
		// goes through each slcspRate in the list and calls getRate()
		for (int i = 0; i < slcspRates.size(); i++) {
			SLCSPBean slcspZipRate = slcspRates.get(i);
			BigDecimal slcspRate = getRate(slcspZipRate.getZipCode(), zipCodeRateAreas, rateAreaRates);
			slcspZipRate.setRate(slcspRate);
		}
	}

	public void generateSLCSPRatesCSVFile(Enum<FileTypeEnum> fileType) {
		// checks for file type to offer different formatting options
		if (fileType == FileTypeEnum.CommaDelimited) {

			String slcspOutputFile = "\\output\\slcsp.csv";

			// get path for csv files
			Path currentRelativePath = Paths.get("");
			String filePath = currentRelativePath.toAbsolutePath().toString();

			// write to output file
			CSVwriter<SLCSPBean> slcspWriter = new CSVwriter<SLCSPBean>(SLCSPBean.class);
			slcspWriter.writeCSV(slcspRates, filePath + slcspOutputFile, new String[] { "zipcode", "rate" });
		} else if (fileType == FileTypeEnum.Json) {
			// TODO not implemented
		} else if (fileType == FileTypeEnum.SemiColonDelimited) {
			// TODO not implemented
		}
	}

	// method to getRate and if rateAreaStateKey is not null calls other overload method to return result
	private BigDecimal getRate(String zipCode, HashMap<String, ZipCodeRateArea> zipCodeRateAreasMap,
			HashMap<String, PlanRateArea> rateAreaRatesMap) {

		String rateAreaStateKey = getRateAreaStateKey(zipCode, zipCodeRateAreasMap);
		// if not null call getRate(string, hashmap)
		if (rateAreaStateKey != null) {
			// uses key to get rate from map
			return getRate(rateAreaStateKey, rateAreaRatesMap);
		}
		return null; // return null if not found
	}
	
	private BigDecimal getRate(String rateAreaStateKey, HashMap<String, PlanRateArea> rateAreaRatesMap) {
		PlanRateArea planRateArea = rateAreaRatesMap.get(rateAreaStateKey);
		// check for null
		if (planRateArea != null) {
			try {
				// returns the slcsp rate
				return planRateArea.getSLCSPRate();
			} catch (Exception e) {
				// generic logging and message
				LOGGER.log(Level.FINE, e.toString());
				System.out.println("error getting slcsp rate");
			}
		}

		return null;
	}

	private String getRateAreaStateKey(String zipCode, HashMap<String, ZipCodeRateArea> zipCodeRateAreasMap) {
		ZipCodeRateArea zipCodeRateArea = zipCodeRateAreasMap.get(zipCode);
		// checks to make sure not null and that there is only one rate_area
		if (zipCodeRateArea != null && !zipCodeRateArea.isAmbigious()) {
			// return state and rate_area for slcsp
			return zipCodeRateArea.getState() + zipCodeRateArea.getRateArea();
		}
		return null;
	}

}
