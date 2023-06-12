package employeedetails;

import java.util.List;

public interface FileDataIngestionService {
	 public void loadFileData(String csvFilePath);
	void updateEmployeeName(Long EMPLOYEE_ID, String FIRST_NAME);
	void deleteEmployee(Long EMPLOYEE_ID);
	

}

