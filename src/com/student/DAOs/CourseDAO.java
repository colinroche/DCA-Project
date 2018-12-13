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

import com.student.Models.CourseModels;

public class CourseDAO {
	private DataSource mysqlDS;
	
	public CourseDAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/studentdb";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	public ArrayList<CourseModels> loadCourses() throws SQLException{
		System.out.println("Course DAO Works");
		Connection conn = mysqlDS.getConnection();
		Statement myStmt = conn.createStatement();
		String query = "select * from course";
		ResultSet rs = myStmt.executeQuery(query);

		ArrayList<CourseModels> courses = new ArrayList<>();
		
		while(rs.next())
		{
			String cID = rs.getString("cID");
			String cName = rs.getString("cName");
			int duration = rs.getInt("duration");
			
			courses.add(new CourseModels(cID, cName, duration));
		}
		return courses;
		
	}
	
	public void addCourse(CourseModels courses)
	{
		String cID = courses.getcID();
		String cName = courses.getcName();
		int duration = courses.getDuration();
		
		PreparedStatement myStmt;
		try {
			Connection conn = mysqlDS.getConnection();
			myStmt = conn.prepareStatement("INSERT into course (cID, cName, duration) VALUE(?,?,?)");
			myStmt.setString(1, cID);
			myStmt.setString(2, cName);
			myStmt.setInt(3, duration);
			
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
