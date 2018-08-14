package fileUtilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.opencsv.bean.CsvToBeanBuilder;

/**
* Class reads CSV files to a list<T> of beans
* @version 1
* @author x
*/
public class CSVreader<T> {
	
	private static final Logger LOGGER = Logger.getLogger(CSVreader.class.getName());

	final Class<T> beanType;

	public CSVreader(Class<T> beanType) {
		this.beanType = beanType;
	}

	 /**
	   * loads bean object to an unsorted list
	   * @param fileName as a String that will be read by the CSVreader
	   * @return Unsorted list of beans
	   */
	public List<T> loadBeanAsList(String fileName) {
		
		
		File csvFile = new File(fileName);

		BufferedReader reader = null;
		try {
			// read file
			reader = new BufferedReader(new FileReader(csvFile));
		} catch (FileNotFoundException e) {
			// would put error specific handling here
		    System.out.println("File not found.");
		    // logging for good measure
			LOGGER.log(Level.FINE, e.toString());
		}
		// passes in the reader to CsvToBeanBuilder along with the beanType
		// and then sends parsed beans to an unsorted list
		List<T> beanList = new CsvToBeanBuilder<T>(reader).withType(beanType).build().parse();

		return beanList;

	}
}