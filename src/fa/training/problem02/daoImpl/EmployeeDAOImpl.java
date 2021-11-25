package fa.training.problem02.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fa.training.problem02.dao.EmployeeDAO;
import fa.training.problem02.entities.Employee;
import fa.training.problem02.utils.DBUtils;
import fa.training.problem02.utils.SQLCommands;

public class EmployeeDAOImpl implements EmployeeDAO {

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet results = null;

	@Override
	public boolean save(Employee employee) {
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommands.EMPLOYEE_SAVE_QUERY);
			preparedStatement.setInt(1, employee.getEmp_no());
			preparedStatement.setDate(2, employee.getBirth_date());
			preparedStatement.setString(3, employee.getFirst_name());
			preparedStatement.setString(4, employee.getLast_name());
			preparedStatement.setString(5, String.valueOf(employee.getGender()));
			preparedStatement.setDate(6, employee.getHire_date());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			System.err.println("Error to add new Employee: " + e.getMessage());
			return false;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Employee> findAll() {
		List<Employee> listEmp = new ArrayList<>();

		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommands.EMPLOYEE_FIND_ALL_QUERY);
			results = preparedStatement.executeQuery();
			while (results.next()) {
				int emp_no = results.getInt("emp_no");
				Date birth_date = results.getDate("birth_date");
				String first_name = results.getString("first_name");
				String last_name = results.getString("last_name");
				char gender = results.getString("gender").charAt(0);
				Date hire_date = results.getDate("hire_date");
				listEmp.add(new Employee(emp_no, birth_date, first_name, last_name, gender, hire_date));
			}
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listEmp;
	}

	@Override
	public int update(Employee employee) {
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommands.EMPLOYEE_UPDATE_QUERY);
			preparedStatement.setDate(1, employee.getBirth_date());
			preparedStatement.setString(2, employee.getFirst_name());
			preparedStatement.setString(3, employee.getLast_name());
			preparedStatement.setString(4, String.valueOf(employee.getGender()));
			preparedStatement.setDate(5, employee.getHire_date());
			preparedStatement.setInt(6, employee.getEmp_no());
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error to update employee: " + e.getMessage());
			return -1;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Employee findById(int emp_no) {
		Employee employee = new Employee();

		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommands.EMPLOYEE_FIND_BY_ID_QUERY);
			preparedStatement.setInt(1, emp_no);
			results = preparedStatement.executeQuery();

			if (results.next()) {
				employee.setEmp_no(results.getInt("emp_no"));
				employee.setBirth_date(results.getDate("birth_date"));
				employee.setFirst_name(results.getString("first_name"));
				employee.setLast_name(results.getString("last_name"));
				employee.setGender(results.getString("gender").charAt(0));
				employee.setHire_date(results.getDate("hire_date"));
			}
		} catch (SQLException e) {
			System.err.println("Error: " + e.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return employee;
	}

}
