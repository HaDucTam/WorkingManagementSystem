package fa.training.problem02.main;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fa.training.problem02.dao.DepartmentDAO;
import fa.training.problem02.dao.EmployeeDAO;
import fa.training.problem02.dao.Working_HistoryDAO;
import fa.training.problem02.daoImpl.DepartmentDAOImpl;
import fa.training.problem02.daoImpl.EmployeeDAOImpl;
import fa.training.problem02.daoImpl.Working_HistoryDAOImpl;
import fa.training.problem02.entities.Department;
import fa.training.problem02.entities.Employee;
import fa.training.problem02.entities.Working_History;
import fa.training.problem02.utils.UserInputUtil;
import fa.training.problem02.utils.Validator;

public class App {

	private static EmployeeDAO empDao = new EmployeeDAOImpl();
	private static DepartmentDAO deptDao = new DepartmentDAOImpl();
	private static Working_HistoryDAO working_HistoryDAO = new Working_HistoryDAOImpl();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		manageAll(sc);
	}

	public static void manageAll(Scanner sc) {
		switch (menu(sc)) {
		case 1:
			employeeManagement(sc);
			break;
		case 2:
			departmentManagement(sc);
		case 0:
			System.out.println("__________ PROGRAM CLOSED __________");
			System.exit(0);
		}
	}

	public static void employeeManagement(Scanner sc) {
		switch (empMenu(sc)) {
		case 1:
			addNewEmp(sc);
			employeeManagement(sc);
			break;
		case 2:
			updateEmp(sc);
			employeeManagement(sc);
			break;
		case 3:
			findEmpByID(sc);
			employeeManagement(sc);
			break;
		case 4:
			addWorkingHistory(sc);
			employeeManagement(sc);
			break;
		case 5:
			findAllEmpByTime(sc);
			employeeManagement(sc);
			break;
		case 0:
			manageAll(sc);
			break;
		}
	}

	public static void departmentManagement(Scanner sc) {
		switch (deptMenu(sc)) {
		case 1:
			addNewDept(sc);
			departmentManagement(sc);
			break;
		case 0:
			manageAll(sc);
			break;
		}
	}

	public static int menu(Scanner sc) {
		System.out.println("\t __________________________________________");
		System.out.println("\t|                 MENU                     |");
		System.out.println("\t|   [1]  Employee Management               |");
		System.out.println("\t|   [2]  Department Management             |");
		System.out.println("\t|   [0]  Close program                     |");
		System.out.println("\t|__________________________________________|");
		System.out.print("\n\tEnter your choices: ");

		int choice = UserInputUtil.inputTypeInt(sc.nextLine());

		while (choice < 0 || choice > 2) {
			System.out.print("Invalid choice! Input again: ");
			choice = UserInputUtil.inputTypeInt(sc.nextLine());
		}
		return choice;
	}

	public static int empMenu(Scanner sc) {
		System.out.println("\t ______________________________________________________");
		System.out.println("\t|                 EMPLOYEE MANAGEMENT                  |");
		System.out.println("\t|   [1]  Add new Employee                              |");
		System.out.println("\t|   [2]  Update Employee                               |");
		System.out.println("\t|   [3]  Find Employee by employee number              |");
		System.out.println("\t|   [4]  Add working history                           |");
		System.out.println("\t|   [5]  Find all employee by working time period      |");
		System.out.println("\t|   [0]  Exit                                          |");
		System.out.println("\t|______________________________________________________|");
		System.out.print("\n\tEnter your choices: ");

		int empChoice = UserInputUtil.inputTypeInt(sc.nextLine());

		while (empChoice < 0 || empChoice > 5) {
			System.out.print("Invalid choice! Input again: ");
			empChoice = UserInputUtil.inputTypeInt(sc.nextLine());
		}
		return empChoice;
	}

	public static int deptMenu(Scanner sc) {
		System.out.println("\t __________________________________________________");
		System.out.println("\t|               DEPARTMENT MANAGEMENT              |");
		System.out.println("\t|   [1]  Add new Department                        |");
		System.out.println("\t|   [0]  Exit                                      |");
		System.out.println("\t|__________________________________________________|");
		System.out.print("\n\tEnter your choices: ");

		int deptChoice = UserInputUtil.inputTypeInt(sc.nextLine());

		while (deptChoice != 0 && deptChoice != 1) {
			System.out.print("Invalid choice! Input again: ");
			deptChoice = UserInputUtil.inputTypeInt(sc.nextLine());
		}
		return deptChoice;
	}

	public static void addNewEmp(Scanner sc) {
		System.out.println("\n__________________ ADD NEW EMPLOYEE ___________________\n");
		System.out.print("Enter employee number: ");
		int emp_no = UserInputUtil.inputTypeInt(sc.nextLine());
		while (emp_no == -1) {
			System.out.print("Enter again: ");
			emp_no = UserInputUtil.inputTypeInt(sc.nextLine());
		}
		System.out.print("Enter birth date(yyyy-MM-dd): ");
		Date birth_date = UserInputUtil.checkDateFormat(sc, sc.nextLine());
		String first_name = UserInputUtil.checkFirstName(sc);
		String last_name = UserInputUtil.checkLastName(sc);
		char gender = UserInputUtil.checkGender(sc);
		System.out.print("Enter hire date(yyyy-MM-dd): ");
		Date hire_date = UserInputUtil.checkDateFormat(sc, sc.nextLine());
		if (empDao.save(new Employee(emp_no, birth_date, first_name, last_name, gender, hire_date))) {
			System.out.println("This employee added to database!");
		}
	}

	public static void updateEmp(Scanner sc) {
		System.out.println("\n__________________ UPDATE EMPLOYEE ___________________\n");
		System.out.print("Enter employee number to update: ");
		int emp_no = UserInputUtil.inputTypeInt(sc.nextLine());
		while (emp_no == -1) {
			System.out.print("Enter again: ");
			emp_no = UserInputUtil.inputTypeInt(sc.nextLine());
		}
		System.out.print("Enter birth date(yyyy-MM-dd): ");
		Date birth_date = UserInputUtil.checkDateFormat(sc, sc.nextLine());
		String first_name = UserInputUtil.checkFirstName(sc);
		String last_name = UserInputUtil.checkLastName(sc);
		char gender = UserInputUtil.checkGender(sc);
		System.out.print("Enter hire date(yyyy-MM-dd): ");
		Date hire_date = UserInputUtil.checkDateFormat(sc, sc.nextLine());
		if (empDao.update(new Employee(emp_no, birth_date, first_name, last_name, gender, hire_date)) > 0) {
			System.out.println("This employee updated successful!");
		} else {
			System.err.println("Update FAIL. Not find emp_no = " + emp_no + " in database!");
		}
	}

	public static void findEmpByID(Scanner sc) {
		List<Employee> list = new ArrayList<>();
		System.out.println("\n__________________ FIND EMPLOYEE ___________________\n");
		System.out.print("Enter employee number to find: ");
		int emp_no = UserInputUtil.inputTypeInt(sc.nextLine());
		while (emp_no == -1) {
			System.out.print("Enter again: ");
			emp_no = UserInputUtil.inputTypeInt(sc.nextLine());
		}
		Employee em = empDao.findById(emp_no);
		if (em.getEmp_no() != emp_no) {
			System.out.println("No Employee has emp_no = " + emp_no + " in database!");
		} else {
			list.add(em);
			printEmpFormat(list);
		}
	}

	public static void addWorkingHistory(Scanner sc) {
		System.out.println("\n__________________ ADD NEW WORKING HISTORY ___________________\n");
		System.out.print("Enter employee number: ");
		int emp_no = UserInputUtil.inputTypeInt(sc.nextLine());
		while (emp_no == -1) {
			System.out.print("Enter again: ");
			emp_no = UserInputUtil.inputTypeInt(sc.nextLine());
		}
		System.out.print("Enter department number: ");
		int dept_no = UserInputUtil.inputTypeInt(sc.nextLine());
		while (dept_no == -1) {
			System.out.print("Enter again: ");
			dept_no = UserInputUtil.inputTypeInt(sc.nextLine());
		}
		System.out.print("Enter from_date (yyyy-MM-dd): ");
		Date from_date = UserInputUtil.checkDateFormat(sc, sc.nextLine());
		System.out.print("Enter to_date (yyyy-MM-dd): ");
		Date to_date = UserInputUtil.checkDateFormat(sc, sc.nextLine());
		while(!Validator.validateFromToDate(from_date, to_date)) {
			System.out.println("The to_date must be after from_date!");
			System.out.print("Input to_date again: ");
			to_date = UserInputUtil.checkDateFormat(sc, sc.nextLine());
		}
		if (working_HistoryDAO.saveWorkingHistory(new Working_History(emp_no, dept_no, from_date, to_date))) {
			System.out.println("This Working History added successful!");
		}
	}

	public static void findAllEmpByTime(Scanner sc) {
		List<Employee> list = new ArrayList<>();
		System.out.print("Enter from_date (yyyy-MM-dd): ");
		Date from_date = UserInputUtil.checkDateFormat(sc, sc.nextLine());
		System.out.print("Enter to_date (yyyy-MM-dd): ");
		Date to_date = UserInputUtil.checkDateFormat(sc, sc.nextLine());
		while(!Validator.validateFromToDate(from_date, to_date)) {
			System.out.println("The to_date must be after from_date!");
			System.out.print("Input to_date again: ");
			to_date = UserInputUtil.checkDateFormat(sc, sc.nextLine());
		}
		list = working_HistoryDAO.findByWorkTime(from_date, to_date);
		if(list.size() == 0) {
			System.out.println("\nThere are no employee working in this period!");
		} else {
			printEmpFormat(list);
		}
		cont(sc);
	}

	public static void addNewDept(Scanner sc) {
		System.out.println("\n__________________ ADD NEW DEPARTMENT ___________________\n");
		System.out.print("Enter department number: ");
		int dept_no = UserInputUtil.inputTypeInt(sc.nextLine());
		while (dept_no == -1) {
			System.out.print("Enter again: ");
			dept_no = UserInputUtil.inputTypeInt(sc.nextLine());
		}
		String dept_name = UserInputUtil.checkDeptName(sc);
		String description = UserInputUtil.checkDescription(sc);
		if (deptDao.save(new Department(dept_no, dept_name, description))) {
			System.out.println("This Department added successful!");
		}
	}

	public static void printEmpFormat(List<Employee> list) {
		String format = "\t| %6s | %-25s | %13s | %6s | %13s |%n";
		System.out.format("\t+-----------------------------------------------------------------------------+%n");
		System.out.format("\t|                                  EMPLOYEE LIST                              |%n");
		System.out.format("\t+--------+---------------------------+---------------+--------+---------------+%n");
		System.out.format("\t| Emp_no |         Full name         | Date of Birth | Gender |   Hire date   |%n");
		System.out.format("\t+--------+---------------------------+---------------+--------+---------------+%n");

		for (Employee e : list) {
			System.out.format(format, 
							e.getEmp_no(), 
							e.getFirst_name() + " " + e.getLast_name(), 
							e.getBirth_date(),
							e.getGender(), 
							e.getHire_date());
		}
		System.out.format("\t+--------+---------------------------+---------------+--------+---------------+%n");
	}

	/**
	 * @overview check user continued
	 * @param sc
	 */
	public static void cont(Scanner sc) {
		System.out.print("\n_______Continue(Yes/No): ");
		if (!sc.nextLine().equalsIgnoreCase("yes")) {
			manageAll(sc);
		}
	}

}
