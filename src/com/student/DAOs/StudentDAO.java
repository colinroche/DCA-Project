package com.student.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.PreparedStatement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import com.student.Models.StudentModels;

public class StudentDAO {
	private DataSource mysqlDS;
	
	public StudentDAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/studentdb";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	public ArrayList<StudentModels> loadStudents() throws SQLException{
		System.out.println("Student DAO Works");
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String query = "select * from student";
		ResultSet rs = myStmt.executeQuery(query);

		ArrayList<StudentModels> students = new ArrayList<>();
		
		while(rs.next())
		{
			String sid = rs.getString("sid");
			String cID = rs.getString("cID");
			String name = rs.getString("name");
			String address = rs.getString("address");
			
			students.add(new StudentModels(sid, cID, name, address));
		}
		return students;
		
	}
	
	public void addStudent(StudentModels students)
	{
		String sid = students.getSid();
		String cID = students.getcID();
		String name = students.getName();
		String address = students.getAddress();
		
		PreparedStatement myStmt;
		try {
			Connection conn = mysqlDS.getConnection();
			myStmt = conn.prepareStatement("INSERT into student (sid, cID, name, address) VALUE(?,?,?,?)");
			myStmt.setString(1, sid);
			myStmt.setString(2, cID);
			myStmt.setString(3, name);
			myStmt.setString(4, address);
			
			myStmt.executeUpdate();
		}
		catch(MySQLIntegrityConstraintViolationException e){
			e.printStackTrace();
			System.out.println("valid");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
	}
	
}
