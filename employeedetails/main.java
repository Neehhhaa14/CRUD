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

	public static void main(String[] args) {
		String csvFilePath = "C:\\Users\\NehaKuSingh\\Desktop\\SampleData.csv";

		try {

			BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));

			CSVParser records = CSVParser.parse(lineReader,
					CSVFormat.EXCEL.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(Employee.class).buildSessionFactory();

			Session session = factory.openSession();

			session.beginTransaction();

			for (CSVRecord record : records) {

				Employee employee = new Employee();

				employee.setEMPLOYEE_ID(Long.parseLong(record.get(0)));

				employee.setFIRST_NAME(record.get(1));

				employee.setLAST_NAME(record.get(2));

				employee.setEMAIL(record.get(3));

				employee.setPHONE_NUMBER(record.get(4));

				employee.setHIRE_DATE(record.get(5));

				employee.setJOB_ID(record.get(6));

				employee.setSALARY(record.get(7));

				session.save(employee);

			}

			session.getTransaction().commit();

		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
//Reader reader=new FileReader("C:\\Users\\NehaKuSingh\\Desktop\\SampleData.csv");
//List<String[]> data=readCSV(reader);
//for(String[] lines:data) {
//	for(int i=0;i<lines.length;i++) {
//		System.out.println(lines[i]);
//		
//	}
//	System.out.println();
//}
//	
//}
//	
//
//	public static List<String[]> readCSV(Reader reader) throws IOException, CsvException {
//		CSVReader read=new CSVReader(reader);
//		List<String[]> l=read.readAll();
//		reader.close();
//		read.close();
//		
//return l;
//	}
//}
