package fa.training.problem02.daoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fa.training.problem02.dao.Working_HistoryDAO;
import fa.training.problem02.entities.Employee;
import fa.training.problem02.entities.Working_History;
import fa.training.problem02.utils.DBUtils;
import fa.training.problem02.utils.SQLCommands;

public class Working_HistoryDAOImpl implements Working_HistoryDAO{


	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet results = null;
	
	@Override
	public boolean saveWorkingHistory(Working_History workingHistory) {
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommands.WORKING_HISTOTY_SAVE_QUERY);
			preparedStatement.setInt(1, workingHistory.getDept_no());
			preparedStatement.setInt(2, workingHistory.getEmp_no());
			preparedStatement.setDate(3, workingHistory.getFrom_date());
			preparedStatement.setDate(4, workingHistory.getTo_date());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			System.err.println("Error to add Working History: " + e.getMessage());
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
	public List<Employee> findByWorkTime(Date fromDate, Date toDate) {
		List<Employee> listWH = new ArrayList<>();
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommands.FIND_WORK_BY_TIME_QUERY);
			preparedStatement.setDate(1, fromDate);
			preparedStatement.setDate(2, toDate);
			
			results = preparedStatement.executeQuery();
			while (results.next()) {
				int emp_no = results.getInt("emp_no");
				Date birth_date = results.getDate("birth_date");
				String first_name = results.getString("first_name");
				String last_name = results.getString("last_name");
				char gender = results.getString("gender").charAt(0);
				Date hire_date = results.getDate("hire_date");
				listWH.add(new Employee(emp_no, birth_date, first_name, last_name, gender, hire_date));
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
		return listWH;
	}

}
