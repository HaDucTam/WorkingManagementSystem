package fa.training.problem02.dao;

import java.sql.Date;
import java.util.List;

import fa.training.problem02.entities.Employee;
import fa.training.problem02.entities.Working_History;

public interface Working_HistoryDAO {

	boolean saveWorkingHistory(Working_History workingHistory);

	List<Employee> findByWorkTime(Date fromDate, Date toDate);
}
