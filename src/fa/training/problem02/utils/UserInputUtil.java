package fa.training.problem02.utils;

import java.sql.Date;
import java.util.Scanner;

public class UserInputUtil {

	public static int inputTypeInt(String value) {
		int intValue = -1;
		do {
			try {
				intValue = Integer.parseInt(value);
			} catch (Exception e) {
				System.out.println("Please input int value!");
			}
			break;
		} while (true);
		return intValue;
	}

	public static Date checkDateFormat(Scanner sc, String date) {
		while (!Validator.validateDate(date)) {
			System.out.println("Wrong date format. The format is 'yyyy-MM-dd'!");
			System.out.print("Enter date again: ");
			date = sc.nextLine();
		}
		return Date.valueOf(date);
	}

	public static String checkFirstName(Scanner sc) {
		String first_name;
		System.out.print("Enter the first name: ");
		first_name = sc.nextLine();
		while (!Validator.validateFirstName(first_name)) {
			System.out.print("Invalid first name! Enter again: ");
			first_name = sc.nextLine();
		}
		return first_name;
	}

	public static String checkLastName(Scanner sc) {
		String last_name;
		System.out.print("Enter the last name: ");
		last_name = sc.nextLine();
		while (!Validator.validateFirstName(last_name)) {
			System.out.print("Invalid last name! Enter again: ");
			last_name = sc.nextLine();
		}
		return last_name;
	}

	public static String checkDeptName(Scanner sc) {
		String dept_name;
		System.out.print("Enter the department name: ");
		dept_name = sc.nextLine();
		while (!Validator.validateFirstName(dept_name)) {
			System.out.print("Invalid department name! Enter again: ");
			dept_name = sc.nextLine();
		}
		return dept_name;
	}

	public static String checkDescription(Scanner sc) {
		String description;
		System.out.print("Enter the description: ");
		description = sc.nextLine();
		while (!Validator.validateDescription(description)) {
			System.out.println("The description is too long. Maximum is 100 characters!");
			System.out.print("Enter the description again: ");
			description = sc.nextLine();
		}
		return description;
	}

	public static char checkGender(Scanner sc) {
		char gender;
		System.out.print("Enter gender (male/female): ");
		gender = sc.nextLine().trim().toUpperCase().charAt(0);
		while (!Validator.validateGender(gender)) {
			System.out.print("You must enter only one type (male/female): ");
			gender = sc.nextLine().trim().toUpperCase().charAt(0);
		}
		return gender;
	}

}
