package fileUtilities;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

/**
* Class writes to a CSV file in the output folder based on list<T>
* @version 1
* @author x
*/
public class CSVwriter<T> {

	private static final Logger LOGGER = Logger.getLogger(CSVreader.class.getName());

	final Class<T> beanType;

	public CSVwriter(Class<T> beanType) {
		this.beanType = beanType;
	}

	 /**
	   * writes to a CSV file in the output folder from a list of beans
	   * @param beanList that is printed to CSV output file, String writeToPath which
	   * contains the path of the file that is written, an array of string headers
	   * that is used that populates the header of the CSV file
	   */
	public void writeCSV(List<T> beanList, String writeToPath, String[] headers) {

		// mapping of columns with their positions
		ColumnPositionMappingStrategy<T> mappingStrategy = new ColumnPositionMappingStrategy<T>();
		// Set mappingStrategy type to beanType
		mappingStrategy.setType(beanType);
		// Fields in Product Bean
		String[] columns = headers;
		// Setting the columns for mappingStrategy
		mappingStrategy.setColumnMapping(columns);

		try {

			Writer writer = new FileWriter(writeToPath);
			StatefulBeanToCsvBuilder<T> builder = new StatefulBeanToCsvBuilder<>(writer);
			builder.withMappingStrategy(mappingStrategy).withApplyQuotesToAll(false);
			StatefulBeanToCsv<T> beanWriter = builder.build();
			// work around for bug in openCSV where headers are not generated
			// when using mapping strategy
			writer.append(generateHeaders(headers));
			beanWriter.write(beanList);
			writer.close();

		} catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
			// generic logging and message
			LOGGER.log(Level.FINE, e.toString());
			System.out.println("CSV write error");
		}
	}

	private String generateHeaders(String[] headers) {
		
		StringBuilder stringBuilder = new StringBuilder();
		// formats header in output CSV file
		for (int i = 0; i < headers.length; i++) {
			stringBuilder.append(headers[i]);
			if (i + 1 != headers.length) {
				stringBuilder.append(",");
			} else {
				stringBuilder.append("\n");
			}
		}
		return stringBuilder.toString();
	}

}