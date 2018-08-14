package app;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.HashMap;
import fileUtilities.CSVreader;
import fileUtilities.FileTypeEnum;
import plan.*;
import slcsp.*;
import zipCode.*;

/**
 * Main driver class for second lowest cost silver plan (slcsp) Ad Hoc homework
 * sample application
 * 
 * @version 1
 * @author x
 */
public class Main {

	/**
	 * Loads CSV files from input folder, Maps CSV values to HashMaps, maps rates to
	 * SLCSP beans, writes rates to slcsp output file in output folder.
	 */
	public static void main(String[] args) {

		// files for loader
		String plansCSVfile = "\\input\\plans.csv";
		String zipsCSVfile = "\\input\\zips.csv";
		String slcspCSVfile = "\\input\\slcsp.csv";

		// get path for csv files
		Path currentRelativePath = Paths.get("");
		String filePath = currentRelativePath.toAbsolutePath().toString();

		// load plans.csv file from flat files
		CSVreader<PlanBean> planLoader = new CSVreader<PlanBean>(PlanBean.class);
		List<PlanBean> planBeans = planLoader.loadBeanAsList(filePath + plansCSVfile);

		// load zips.csv file from flat files
		CSVreader<ZipCodeRateAreaBean> zipCodeLoader = new CSVreader<ZipCodeRateAreaBean>(ZipCodeRateAreaBean.class);
		List<ZipCodeRateAreaBean> zipCodeRateAreaBeans = zipCodeLoader.loadBeanAsList(filePath + zipsCSVfile);

		// load slcsp.csv file from flat files
		CSVreader<SLCSPBean> slcspLoader = new CSVreader<SLCSPBean>(SLCSPBean.class);
		List<SLCSPBean> slcspBeans = slcspLoader.loadBeanAsList(filePath + slcspCSVfile);

		// map zip values to rate areas
		ZipCodeMapper zipCodeMapper = new ZipCodeMapper();
		HashMap<String, ZipCodeRateArea> zipCodeRateAreaMap = zipCodeMapper.getZipCodeRates(zipCodeRateAreaBeans);

		// map plans to rate areas
		PlanMapper planMapper = new PlanMapper();
		HashMap<String, PlanRateArea> planRateAreaMap = planMapper.getPlanRateAreas(planBeans);

		// work with SLCSP
		SLCSPManager slcspManager = new SLCSPManager(slcspBeans);
		// map rates to SLCSP Beans
		slcspManager.mapSLCSPRates(zipCodeRateAreaMap, planRateAreaMap);

		// write to slcsp.csv output file
		slcspManager.generateSLCSPRatesCSVFile(FileTypeEnum.CommaDelimited);

	}

}
