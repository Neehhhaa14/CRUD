package employeedetails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class main {
	//Creating Object for FileDataIngestionServiceImpl class to access the methods and properties.
	static FileDataIngestionServiceImpl obj = new FileDataIngestionServiceImpl();
	static Logger logger = LoggerFactory.getLogger(main.class);
	    
	    public static void main(String[] args) {
	        // Path to the CSV file
	        String csvFilePath = "C:\\Users\\NehaKuSingh\\Desktop\\SampleData (version 1).xlsb.csv";
	        
	        // Loading the file data
	        obj.loadFileData(csvFilePath);
	        logger.info("The CSV file path was loaded");
	        
	        // Updating the employee name
	        obj.updateEmployeeName(100L, "neha");
	        logger.info("The employee name was updated");
	        
	        // Deleting an employee row
	        obj.deleteEmployee(102L);
	        logger.info("The employee row was deleted");
	    }
	}


