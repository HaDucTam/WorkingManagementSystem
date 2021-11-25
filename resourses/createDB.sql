
#MySQL queries
DROP SCHEMA IF EXISTS jpl_test01;
CREATE SCHEMA jpl_test01;
USE jpl_test01;

#Employee table
CREATE TABLE Employee(
	emp_no int,
    birth_date date,
    first_name varchar(50),
    last_name varchar(50),
    gender char(1),
    hire_date date,
    PRIMARY KEY(emp_no)
);

#Department table
CREATE TABLE Department(
	dept_no int,
    dept_name varchar(50),
    description varchar(100),
    PRIMARY KEY(dept_no)
);

#Working_history table
CREATE TABLE Working_History(
	dept_no int,
    emp_no int,
    from_date date,
    to_date date,
    CONSTRAINT working_history_PK PRIMARY KEY(dept_no, emp_no),
    CONSTRAINT working_history_FK1 FOREIGN KEY(dept_no) REFERENCES Department(dept_no),
    CONSTRAINT working_history_FK2 FOREIGN KEY(emp_no) REFERENCES Employee(emp_no)
);


