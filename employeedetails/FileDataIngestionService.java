package employeedetails;

import java.util.List;

public interface FileDataIngestionService {
	List<Employee> loadFileData(String filePath);
	List<Employee> parseFileData(String fileData);
	List<Employee> getAllEmployees();
	void updateEmployeeName(int EMPLOYEE_ID, String FIRST_NAME);
	void deleteEmployee(int EMPLOYEE_ID);

}

