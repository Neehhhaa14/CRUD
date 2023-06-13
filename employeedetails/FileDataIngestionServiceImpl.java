package employeedetails;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.slf4j.*;

public class FileDataIngestionServiceImpl implements FileDataIngestionService {
 static Logger logger = LoggerFactory.getLogger(FileDataIngestionServiceImpl.class);

/// Method for loading and reading the CSV file
	@Override
	public void loadFileData(String csvFilePath) {
		try {
			logger.info("Reading the data");

			// read the file
			File file = new File(csvFilePath);
			InputStream inStream = new FileInputStream(file);
			BufferedReader lineReader = new BufferedReader(new InputStreamReader(inStream));
			String line;

			// Creating the Session Factory
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(Employee.class).buildSessionFactory();
			Session session = factory.openSession();
			session.beginTransaction();

			// Reading and processing each line of the CSV file
			while ((line = lineReader.readLine()) != null) {
				String[] columns = line.split(",");

				// Creating an Employee object 
				Employee employee = new Employee();
				employee.setEMPLOYEE_ID(Long.valueOf(columns[0]));
				employee.setFIRST_NAME(columns[1]);
				employee.setLAST_NAME(columns[2]);
				employee.setEMAIL(columns[3]);
				employee.setPHONE_NUMBER(columns[4]);
				employee.setHIRE_DATE(columns[5]);
				employee.setJOB_ID(columns[6]);
				employee.setSALARY(columns[7]);

				// Saving the Employee object to the database
				session.save(employee);
			}

			// Committing the transaction and closing the session
			session.getTransaction().commit();
			session.close();
			factory.close();
			logger.info("Data loading is completed");
			
		} catch (FileNotFoundException e) {
			logger.error("Exception occurred while loading the file");
		} catch (IOException e) {
			logger.error("Exception occurred while loading the file");
		}
	}

	// Method for updating the Employee FIRST_NAME based on EmployeeID
	@Override
	public void updateEmployeeName(Long EMPLOYEE_ID, String FIRST_NAME) {
		logger.info("Updating the EmployeeName");
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();

			// Retrieving the Employee object by EmployeeID
			Employee employee = session.get(Employee.class, EMPLOYEE_ID);

			if (employee != null) {
				// Updating the FIRST_NAME field of the Employee object
				employee.setFIRST_NAME(FIRST_NAME);
				session.update(employee);
				transaction.commit();
				logger.info("EmployeeName has been updated successfully");
			}
		} catch (Exception e) {
			logger.error("Exception occurred while updating the EmployeeName");
		}

	}

	// Method to delete an employee based on EmployeeID
	@Override
	public void deleteEmployee(Long EMPLOYEE_ID) {
		logger.info("Deleting the employee row");
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();

			// Retrieving the Employee object by EmployeeID
			Employee employee = session.get(Employee.class, EMPLOYEE_ID);

			if (employee != null) {
				// Deleting the Employee object from the database
				session.delete(employee);
				session.getTransaction().commit();
				logger.info("Employee row has been deleted successfully");
			}
		} catch (Exception e) {
			logger.error("Exception occurred while deleting the Employee");
		}
	}
}
