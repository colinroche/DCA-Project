package com.student.Models;

import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;

@SessionScoped
@ManagedBean
public class CourseModels {
	private String cID;
	private String cName;
	private int duration;
	
	public CourseModels(String cID, String cName, int duration) {
		super();
		this.cID = cID;
		this.cName = cName;
		this.duration = duration;
	}
	
	public CourseModels()
	{
		
	}
	
	public String getcID() {
		return cID;
	}
	public void setcID( String cID) {
		this.cID = cID;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
}
