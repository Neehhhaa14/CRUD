package employeedetails;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class main {
	static FileDataIngestionServiceImpl obj=new FileDataIngestionServiceImpl();

	public static void main(String[] args) {
		String csvFilePath = "C:\\Users\\NehaKuSingh\\Desktop\\SampleData (version 1).xlsb.csv";
     obj.loadFileData(csvFilePath);
     System.out.println("loaded");
     obj.updateEmployeeName(198L, "neha");
     System.out.println("updated");
     obj.deleteEmployee(201L);
     System.out.println("deleted");
     
}
}
	