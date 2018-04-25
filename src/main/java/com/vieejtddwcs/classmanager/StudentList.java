package com.vieejtddwcs.classmanager;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "studentlist_namespace")
@XmlAccessorType(XmlAccessType.FIELD)
class StudentList implements Iterable<Student> {

	@XmlElementWrapper(name = "studentlist")
	@XmlElement(name = "student")
	private List<Student> studentList;
	
	StudentList() {
		studentList = new ArrayList<>();
	}
	
	StudentList(List<Student> list) {
		studentList = new ArrayList<>(list);
	}
	
	List<Student> getStudentList() {
		return studentList;
	}
	
	void setStudentList(List<Student> list) {
		studentList = list;
	}
	
	@Override
	public Iterator<Student> iterator() {
		return studentList.iterator();
	}
	
	static List<Student> readXml(String filePath) {
		StudentList studentList = new StudentList();
		try {
			JAXBContext jaxb = JAXBContext.newInstance(com.vieejtddwcs.classmanager.StudentList.class);
			Unmarshaller u = jaxb.createUnmarshaller();
			
			studentList = (StudentList)u.unmarshal(new File(filePath));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return studentList.getStudentList();
	}
	
	static void writeXml(List<Student> list, String filePath) {
		StudentList studentList = new StudentList(list);
		try {
			JAXBContext jaxb = JAXBContext.newInstance(com.vieejtddwcs.classmanager.StudentList.class);
			Marshaller m = jaxb.createMarshaller();
			
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(studentList, new File(filePath));
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	static List<Student> copyList(List<Student> list) {
		return list.stream().collect(Collectors.toList());
	}
	
	static List<Student> getAvailableStudents(List<Student> list, String shift, int date) {
		List<Student> availableStudents = new ArrayList<>();
		
		for (Student s : list) {
			char[] free = null;
			
			if (shift.equals("Sáng"))
				free = s.getMorningFree().toCharArray();
			if (shift.equals("Chiều"))
				free = s.getAfternoonFree().toCharArray();
			if (shift.equals("Tối"))
				free = s.getEveningFree().toCharArray();
			
			if (free[date-2] == '0') availableStudents.add(s);
		}
		
		return availableStudents;
	}
	
	static List<Student> applyStudentSchedule(List<Student> studentList, List<Student> availableStudentList, String shift, int date) {
		for (Student s : availableStudentList) {
			if (shift.equals("Sáng")) {
				char[] free = s.getMorningFree().toCharArray();
				free[date-2] = '1';
				s.setMorningFree(String.valueOf(free));
			}
			if (shift.equals("Chiều")) {
				char[] free = s.getAfternoonFree().toCharArray();
				free[date-2] = '1';
				s.setAfternoonFree(String.valueOf(free));
			}
			if (shift.equals("Tối")) {
				char[] free = s.getEveningFree().toCharArray();
				free[date-2] = '1';
				s.setEveningFree(String.valueOf(free));
			}
			
		}
		
		List<Student> removingStudentList = new ArrayList<>();
		for (Student s1 : studentList) {
			for (Student s2 : availableStudentList) {
				if (s1.getID() == s2.getID()) removingStudentList.add(s1);
			}
		}
		
		studentList.removeAll(removingStudentList);		
		studentList.addAll(availableStudentList);
		
		return studentList;
	}
	
	static Object[][] get2DArray(List<Student> list, int col, String group) {
		Object[][] array = new String[list.size()][col];
		int i = 0;
		
		for (Student s : list) {
			if (col == 4) {
				array[i][0] = i+1 + "";
				array[i][1] = s.getID();
				array[i][2] = Student.getLastName(s);
				array[i][3] = Student.getFirstName(s);
				i++;
			}
			if (col == 5) {
				array[i][0] = i+1 + "";
				array[i][1] = s.getID();
				array[i][2] = Student.getLastName(s);
				array[i][3] = Student.getFirstName(s);
				array[i][4] = s.getNote();
				i++;
			}
			if (col == 6) {
				array[i][0] = i+1 + "";
				array[i][1] = s.getID();
				array[i][2] = Student.getLastName(s);
				array[i][3] = Student.getFirstName(s);
				array[i][4] = group;
				array[i][5] = s.getNote();
				i++;
			}
		}
		
		return array;
	}
	
	static void removeDatesFree(List<Student> list) {
		for (Student s : list) {
			s.setMorningFree("000000");
			s.setAfternoonFree("000000");
			s.setEveningFree("000000");
		}
	}
	
}