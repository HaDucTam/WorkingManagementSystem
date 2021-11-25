package fa.training.problem02.utils;

import java.sql.Date;

public class Validator {

	private static final int MAX_LENGTH_FIRST_NAME = 50;
	private static final int MAX_LENGTH_LAST_NAME = 50;
	private static final int MAX_LENGTH_DEPT_NAME = 50;
	private static final int MAX_LENGTH_DESCRIPTION = 100;
	/**
	 * @overview validate first_name in employee (not null)
	 * @param first_name
	 * @effects 
	 * 	if length(first_name) > 50 
	 * 		return false 
	 * 	else 
	 * 		return true
	 */
	public static boolean validateFirstName(String first_name) {
		return first_name.length() > 0 && first_name.length() <= MAX_LENGTH_FIRST_NAME;
	}

	/**
	 * @overview validate last_name in employee (not null)
	 * @param last_name
	 * @effects 
	 * 	if length(last_name) > 50 
	 * 		return false 
	 * 	else 
	 * 		return true
	 */
	public static boolean validateLastName(String last_name) {
		return last_name.length() > 0 && last_name.length() <= MAX_LENGTH_LAST_NAME;
	}

	/**
	 * @overview validate dept_name in department (not null)
	 * @param dept_name
	 * @effects 
	 * 	if length(dept_name) > 50 
	 * 		return false 
	 * 	else 
	 * 		return true
	 */
	public static boolean validateDeptName(String dept_name) {
		return dept_name.length() > 0 && dept_name.length() <= MAX_LENGTH_DEPT_NAME;
	}

	/**
	 * @overview validate description in department (can be null)
	 * @param description
	 * @effects 
	 * 	if length(description) > 100 
	 * 		return false 
	 * 	else return 
	 * 		true
	 */
	public static boolean validateDescription(String description) {
		return description.length() <= MAX_LENGTH_DESCRIPTION;
	}

	/**
	 * @overview validate gender
	 * @param gender
	 * @effects 
	 * 	if gender = M (male) or gender = F (female) 
	 * 		return true 
	 * 	else 
	 * 		return false
	 */
	public static boolean validateGender(char gender) {
		return gender == 'M' || gender == 'F';
	}

	/**
	 * @overview validate date (from string to date)
	 * @param date
	 * @effects
	 * 	if date string converted to sql.Date type
	 * 		return true
	 * 	else
	 * 		return false
	 */
	public static boolean validateDate(String date) {
		try {
			Date.valueOf(date);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * @overview check constraint for to_date
	 * @effects
	 * 	if to_date <= from_date
	 * 		return false
	 * 	else
	 * 		return true
	 * @param from_date
	 * @param to_date
	 */
	public static boolean validateFromToDate(Date from_date, Date to_date) {
		return from_date.before(to_date);
	}
}
