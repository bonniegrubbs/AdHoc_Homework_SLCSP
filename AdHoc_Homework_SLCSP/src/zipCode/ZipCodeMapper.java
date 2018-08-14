package zipCode;

import java.util.HashMap;
import java.util.List;

/**
 * Class maps zipcode rate area objects and inserts into hashmap to handle quick inserts and lookup of zipcode rate area data
 * 
 * @version 1
 * @author x
 */
public class ZipCodeMapper {

	HashMap<String, ZipCodeRateArea> zipCodeRateAreasMap = new HashMap<String, ZipCodeRateArea>();
	// maps the rate_area for the given zipCode and calls the addZipCodeRateAreaToMap
	// returns the data as a HashMap containing both values
	
	public HashMap<String, ZipCodeRateArea> getZipCodeRates(List<ZipCodeRateAreaBean> zipCodeRateAreaBeans) {

		if (zipCodeRateAreaBeans != null) {
			for (int i = 0; i < zipCodeRateAreaBeans.size(); i++) {
				addZipCodeRateAreaToMap(zipCodeRateAreaBeans.get(i));
			}
		}

		return zipCodeRateAreasMap;
	}

	private void addZipCodeRateAreaToMap(ZipCodeRateAreaBean zipCodeRateAreaBean) {
		// checks for null
		if (zipCodeRateAreaBean == null) {
			// could throw invalid arguments exception
			return;
		}
		// gets the zipCode key from the bean
		String zipCodeRateAreaKey = zipCodeRateAreaBean.getZipcode();
		// assigns the existing zipCode to a bean
		ZipCodeRateArea existingZipCodeRateArea = zipCodeRateAreasMap.get(zipCodeRateAreaKey);

		// check hashmap to see if key exists
		if (existingZipCodeRateArea != null) {
			// rate are already exists for this zip code, increment zipCodeRateArea count
			existingZipCodeRateArea.incrementRateArea();
		} else {
			// if not exist add to hashmap
			ZipCodeRateArea newZipCodeRateArea = mapBeanToRateArea(zipCodeRateAreaBean);
			zipCodeRateAreasMap.put(zipCodeRateAreaKey, newZipCodeRateArea);
		}

	}

	private ZipCodeRateArea mapBeanToRateArea(ZipCodeRateAreaBean zipCodeRateAreaBean) {
		// map zipCodeRateAreaBean to zipCodeRateArea
		ZipCodeRateArea newZipCodeRateArea = new ZipCodeRateArea();
		
		newZipCodeRateArea.setZipCode(zipCodeRateAreaBean.getZipcode());
		newZipCodeRateArea.setState(zipCodeRateAreaBean.getState());
		newZipCodeRateArea.setRateArea(zipCodeRateAreaBean.getRate_area());
		
		return newZipCodeRateArea;
	}

}