package com.student.Controllers;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.student.DAOs.StudentDAO;
import com.student.Models.StudentModels;


@ManagedBean
@SessionScoped
public class StudentController {

	StudentDAO dao;
	ArrayList<StudentModels> students;
	
	public StudentController() throws Exception
	{
		dao = new StudentDAO();
		students = new ArrayList<>();
	}
	
	
	public void loadStudents() throws Exception
	{
		students = dao.loadStudents();
	}
	
	public ArrayList<StudentModels> getStudents()
	{
		return students;
	}
	
	public void setStudents(ArrayList<StudentModels> students)
	{
		this.students = students;
	}
	
	public String addStudent(StudentModels students)
	{
		dao.addStudent(students);
		return "list_students.xhtml";
	}
}
