package employeedetailsTests;

import org.hibernate.Session;

import org.hibernate.SessionFactory;

import org.hibernate.Transaction;

import org.junit.After;

import org.junit.Before;

import org.junit.Test;

import org.mockito.Mock;

import org.mockito.Mockito;

import org.mockito.MockitoAnnotations;

import employeedetails.Employee;
import employeedetails.FileDataIngestionServiceImpl;
import employeedetails.HibernateUtil;

import java.io.*;

import static org.mockito.Mockito.*;

public class FileDataIngestionServiceImplTests {

	@Mock

	private SessionFactory sessionFactory;

	@Mock

	private Session session;

	@Mock

	private Transaction transaction;

	@Mock

	private FileDataIngestionServiceImpl fileDataIngestionService;

	@Before

	public void setUp() {

		MockitoAnnotations.initMocks(this);

		fileDataIngestionService = new FileDataIngestionServiceImpl();

		fileDataIngestionService.setSessionFactory(sessionFactory);

		when(sessionFactory.openSession()).thenReturn(session);

		when(session.beginTransaction()).thenReturn(transaction);

	}

	@After

	public void closing() {

// verify(transaction, times(1)).rollback();

// verify(session, times(1)).close();

// verify(sessionFactory, times(1)).close();

		transaction.rollback();

		session.close();

		sessionFactory.close();

	}

	@Test

	public void testLoadFileData() throws IOException {

		String csvFilePath = "C:\\Users\\nagmanasa\\Documents\\SampleData.csv";

		Employee employee = new Employee();

		fileDataIngestionService.loadFileData(csvFilePath);

	}

	@Test

	public void testUpdateEmployeeName() {

		Long EMPLOYEE_ID = 100L;

		String FIRST_NAME = "Steven";

		Employee employee = new Employee();

         employee.setEMPLOYEE_ID(EMPLOYEE_ID);

		employee.setFIRST_NAME("neha");

		when(HibernateUtil.getSessionFactory()).thenReturn(sessionFactory);

		when(sessionFactory.openSession()).thenReturn(session);

		when(session.beginTransaction()).thenReturn(transaction);

		when(session.get(Employee.class, employee)).thenReturn(employee);

        fileDataIngestionService.updateEmployeeName(EMPLOYEE_ID, FIRST_NAME);

	}

	@Test

	public void testDeleteEmployee() {

		int EMPLOYEE_ID = 102;

		Employee employee = new Employee();

// when(session.load(Employee.class, (long) EMPLOYEE_ID)).thenReturn(employee);

		fileDataIngestionService.deleteEmployee((long) EMPLOYEE_ID);

	}

}