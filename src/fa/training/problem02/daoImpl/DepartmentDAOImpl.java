package fa.training.problem02.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import fa.training.problem02.dao.DepartmentDAO;
import fa.training.problem02.entities.Department;
import fa.training.problem02.utils.DBUtils;
import fa.training.problem02.utils.SQLCommands;

public class DepartmentDAOImpl implements DepartmentDAO{

	private Connection connection = null;
	private PreparedStatement preparedStatement = null;

	@Override
	public boolean save(Department department) {
		try {
			connection = DBUtils.getInstance().getConnection();
			preparedStatement = connection.prepareStatement(SQLCommands.DEPARTMENT_SAVE_QUERY);
			preparedStatement.setInt(1, department.getDept_no());
			preparedStatement.setString(2, department.getDept_name());
			preparedStatement.setString(3, department.getDescription());
			preparedStatement.execute();
			return true;
		} catch (SQLException e) {
			System.err.println("Error to add Department: " + e.getMessage());
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
}
