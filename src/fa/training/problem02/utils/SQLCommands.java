package fa.training.problem02.utils;

public class SQLCommands {

	//Get all employees
	public static String EMPLOYEE_FIND_ALL_QUERY = "SELECT * FROM employee";
	//Insert new employee
	public static String EMPLOYEE_SAVE_QUERY = "INSERT INTO employee VALUES(?,?,?,?,?,?)";
	//Update specific employee
	public static String EMPLOYEE_UPDATE_QUERY = "UPDATE employee SET birth_date = ?," 
																	+ " first_name = ?, "
																	+ "last_name = ?, " 
																	+ "gender = ?, " 
																	+ "hire_date = ? WHERE emp_no = ?";
	//Get employee by emp_no
	public static String EMPLOYEE_FIND_BY_ID_QUERY = "SELECT * FROM employee WHERE emp_no = ?";

	//Insert new department
	public static String DEPARTMENT_SAVE_QUERY = "INSERT INTO department VALUES(?,?,?)";

	//Insert new working_history
	public static String WORKING_HISTOTY_SAVE_QUERY = "INSERT INTO working_history VALUES(?,?,?,?)";

	//Get all employee with condition
	public static String FIND_WORK_BY_TIME_QUERY = "SELECT * "
													+ "FROM employee AS emp "
													+ "INNER JOIN working_history AS ws ON ws.emp_no = emp.emp_no "
													+ "WHERE ws.from_date >= ? AND ws.to_date <= ?" 
													+ "GROUP BY emp.emp_no";
}
