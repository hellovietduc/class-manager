package com.vieejtddwcs.classmanager;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "student")
@XmlType(propOrder = {"name", "ID", "morningFree", "afternoonFree", "eveningFree", "note"})
class Student {
	
	private String name;
	private String ID;
	private String morningFree;
	private String afternoonFree;
	private String eveningFree;
	private String note;
	
	Student() {
		name = null;
		ID = null;
		morningFree = "000000";
		afternoonFree = "000000";
		eveningFree = "000000";
		note = "";
	}
	
	Student(String name, String ID, String morning, String afternoon, String evening, String note) {
		this.name = name;
		this.ID = ID;
		morningFree = morning;
		afternoonFree = afternoon;
		eveningFree = evening;
		this.note = note;
	}
	
	String getName() {
		return name;
	}
	
	@XmlElement
	void setName(String name) {
		this.name = name;
	}
	
	String getID() {
		return ID;
	}
		
	@XmlElement
	void setID(String ID) {
		this.ID = ID;
	}
		
	String getMorningFree() {
		return morningFree;
	}
		
	@XmlElement
	void setMorningFree(String free) {
		morningFree = free;
	}
		
	String getAfternoonFree() {
		return afternoonFree;
	}
		
	@XmlElement
	void setAfternoonFree(String free) {
		afternoonFree = free;
	}
		
	String getEveningFree() {
		return eveningFree;
	}
		
	@XmlElement
	void setEveningFree(String free) {
		eveningFree = free;
	}
	
	String getNote() {
		return note;
	}
	
	@XmlElement
	void setNote(String note) {
		this.note = note;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof Student)) return false;
		
		Student student = (Student)obj;
		return student.getName().equals(this.getName())
				&& student.getID().equals(this.getID())
				&& student.getMorningFree().equals(this.getMorningFree())
				&& student.getAfternoonFree().equals(this.getAfternoonFree())
				&& student.getEveningFree().equals(this.getEveningFree())
				&& student.getNote().equals(this.getNote());
	}
	
	static String getFirstName(Student s) {
		String name = s.getName();
		return name.substring(name.lastIndexOf(" ")+1, name.length());
	}
	
	static String getLastName(Student s) {
		String name = s.getName();
		return name.substring(0, name.lastIndexOf(" "));
	}
	
	/*
	 * Take a string that contains dates in number
	 * then remove all the letters, keep the numbers
	 * and put them in a 6-digit binary output string. 
	 */
	static String getBinaryDatesFree(String str) {
		char[] inputArray = str.replaceAll("[^0-9]", "").toCharArray();
		char[] binOutput = {'0', '0', '0', '0', '0', '0'};
		for (char c : inputArray)
			binOutput[Character.getNumericValue(c)-2] = '1';
		return new String(binOutput);
	}
	
}