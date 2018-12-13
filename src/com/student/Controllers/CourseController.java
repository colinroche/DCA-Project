package com.student.Controllers;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.student.DAOs.CourseDAO;
import com.student.Models.CourseModels;


@ManagedBean
@SessionScoped
public class CourseController {

	CourseDAO dao;
	ArrayList<CourseModels> courses;
	
	public CourseController() throws Exception
	{
		dao = new CourseDAO();
		courses = new ArrayList<>();
	}
	
	
	public void loadCourses() throws Exception
	{
		courses = dao.loadCourses();
	}
	
	public ArrayList<CourseModels> getCourses()
	{
		return courses;
	}
	
	public void setCourses(ArrayList<CourseModels> courses)
	{
		this.courses = courses;
	}
	
	public String addCourse(CourseModels courses)
	{
		dao.addCourse(courses);
		return "list_courses.xhtml";
	}
}
