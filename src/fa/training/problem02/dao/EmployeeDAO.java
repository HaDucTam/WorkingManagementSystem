package fa.training.problem02.dao;

import java.util.List;

import fa.training.problem02.entities.Employee;

public interface EmployeeDAO {

	boolean save(Employee employee);

	List<Employee> findAll();

	int update(Employee employee);
	
	Employee findById(int emp_no);
	
}
