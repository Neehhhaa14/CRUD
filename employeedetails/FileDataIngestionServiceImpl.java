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
	Logger logger=LoggerFactory.getLogger(FileDataIngestionServiceImpl.class);
	
	@Override
	public void loadFileData(String csvFilePath) {
		try {
            logger.info("loading");
			File file = new File(csvFilePath);
			InputStream inStream = new FileInputStream(file);
			BufferedReader lineReader = new BufferedReader(new InputStreamReader(inStream));
			String line;
			SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
					.addAnnotatedClass(Employee.class).buildSessionFactory();
			Session session = factory.openSession();
			session.beginTransaction();
			while ((line = lineReader.readLine()) != null) {
				String[] columns = line.split(",");
				Employee employee = new Employee();
				employee.setEMPLOYEE_ID(Long.valueOf(columns[0]));
				employee.setFIRST_NAME(columns[1]);
				employee.setLAST_NAME(columns[2]);
				employee.setEMAIL(columns[3]);
				employee.setPHONE_NUMBER(columns[4]);
				employee.setHIRE_DATE(columns[5]);
				employee.setJOB_ID(columns[6]);
				employee.setSALARY(columns[7]);
				session.save(employee);
			}
			session.getTransaction().commit();
			session.close();
			factory.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	

	
	@Override
	public void updateEmployeeName(Long EMPLOYEE_ID, String FIRST_NAME) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			Employee employee = session.get(Employee.class, EMPLOYEE_ID);
			if (employee != null) {
				employee.setFIRST_NAME(FIRST_NAME);
				session.update(employee);
				transaction.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteEmployee(Long EMPLOYEE_ID) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			session.beginTransaction();
			Employee employee = session.get(Employee.class, EMPLOYEE_ID);

			if (employee != null) {
				session.delete(employee);
				session.getTransaction().commit();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
