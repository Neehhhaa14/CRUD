package employeedetails;

public interface FileDataIngestionService {
   
    
    // Method to load file data from a CSV file
    void loadFileData(String csvFilePath);
    
    // Method to update employee name based on employee ID
    void updateEmployeeName(Long EMPLOYEE_ID, String FIRST_NAME);
    
    // Method to delete an employee based on employee ID
    void deleteEmployee(Long EMPLOYEE_ID);
}


