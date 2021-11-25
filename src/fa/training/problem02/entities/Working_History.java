package fa.training.problem02.entities;

import java.io.Serializable;
import java.sql.Date;

public class Working_History implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int dept_no;
	private int emp_no;
	private Date from_date;
	private Date to_date;
	
	public Working_History() {
	}

	public Working_History(int dept_no, int emp_no, Date from_date, Date to_date) {
		super();
		this.dept_no = dept_no;
		this.emp_no = emp_no;
		this.from_date = from_date;
		this.to_date = to_date;
	}

	public int getDept_no() {
		return dept_no;
	}

	public void setDept_no(int dept_no) {
		this.dept_no = dept_no;
	}

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public Date getFrom_date() {
		return from_date;
	}

	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}

	public Date getTo_date() {
		return to_date;
	}

	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}

	@Override
	public String toString() {
		return "Working_History [dept_no=" + dept_no + ", emp_no=" + emp_no + ", from_date=" + from_date + ", to_date="
				+ to_date + "]";
	}
	
	
}
