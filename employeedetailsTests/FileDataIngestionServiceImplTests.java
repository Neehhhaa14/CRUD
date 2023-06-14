package employeedetailsTests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.OrderWith;
import employeedetails.Employee;
import employeedetails.FileDataIngestionServiceImpl;
import employeedetails.HibernateUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.*;


public class FileDataIngestionServiceImplTests {
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	private FileDataIngestionServiceImpl fileDataIngestionService;

	@Before
	public void setUp() {
		Configuration configuration = new Configuration();
		configuration.configure("hibernate.cfg.xml");
		sessionFactory = configuration.buildSessionFactory();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		fileDataIngestionService = new FileDataIngestionServiceImpl();
		fileDataIngestionService.setSessionFactory(sessionFactory);
	}

	@After
	public void tearDown() {
		transaction.rollback(); 
		session.close();
		sessionFactory.close();
	}

	
	@Test
	public void testLoadFileData() throws IOException {
		String csvFilePath = "C:\\Users\\NehaKuSingh\\Desktop\\SampleData (version 1).xlsb.csv";
		Employee employee = new Employee();
		fileDataIngestionService.loadFileData(csvFilePath);
		//assertSame(100L, employee.getEMPLOYEE_ID());
		//assertEquals("Steven", employee.getFIRST_NAME());

	}
	
}