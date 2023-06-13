package employeedetails;



import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {
     static Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

	// Private static final field for SessionFactory
	private static final SessionFactory sessionFactory = buildSessionFactory();

	// Private static method to build SessionFactory
	private static SessionFactory buildSessionFactory() {
		try {
			// Creating a new Configuration and loading the configuration settings from
			// hibernate.cfg.xml
			Configuration configuration = new Configuration().configure();
			configuration.addAnnotatedClass(Employee.class);

			// Building the StandardServiceRegistry using the configuration properties
			StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();

			// Building and returning the SessionFactory
			return configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
            logger.error("Initial SessionFactory creation failed: " + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	// Public static method to retrieve the SessionFactory
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	// Public static method to shutdown the SessionFactory
	public static void shutdown() {
		getSessionFactory().close();
	}
}
