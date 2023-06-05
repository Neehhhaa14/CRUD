package employeedetails;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jakarta.persistence.criteria.CriteriaQuery;

public class FileDataIngestionServiceImpl implements FileDataIngestionService {
	@Override
	public List<Employee> loadFileData(String filePath) {
		String fileData = readFile(filePath);
		return parseFileData(fileData);
	}
	

	@Override
	public List<Employee> parseFileData(String fileData) {
		List<Employee> employees = new ArrayList<Employee>();
		String[] lines = fileData.split("\n");
		for (String line : lines) {
			String[] values = line.split(",");
			if (values.length >= 2) {
				String employeeId = values[1];
				String employeeName = values[1];
				Employee employee = new Employee();
				employee.setJOB_ID(employeeId);
				employee.setEMAIL(employeeName);
				employees.add(employee);

			}

		}

		return employees;
	}

	@Override
	public List<Employee> getAllEmployees() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaQuery<Employee> query = session.getCriteriaBuilder().createQuery(Employee.class);
			query.from(Employee.class);
			List<Employee> employees = session.createQuery(query).list();
			return employees;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();

		}

	}

	@Override
	public void updateEmployeeName(int EMPLOYEE_ID, String FIRST_NAME) {
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
	public void deleteEmployee(int EMPLOYEE_ID) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			Transaction transaction = session.beginTransaction();
			Employee employee = session.get(Employee.class, EMPLOYEE_ID);
			if (employee != null) {
				session.delete(employee);
				transaction.commit();

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private String readFile(String filePath) {
		StringBuilder fileData = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = reader.readLine()) != null) {
				fileData.append(line).append("\n");

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileData.toString();
	}

}

