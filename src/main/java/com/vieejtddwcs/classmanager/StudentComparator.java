package com.vieejtddwcs.classmanager;

import java.text.Normalizer;
import java.util.Comparator;
import java.util.regex.Pattern;

/*
 * Sort priority: first name khong dau > first name > last name khong dau > last name.
 */
class StudentComparator implements Comparator<Student> {
	
	private String wordsOrder = "aáàảãạăắằẳẵặâấầẩẫậbcđdeéèẻẽẹêếềểễệfghiíìỉĩịjklmnoóòỏõọôốồổỗộơớờởỡợpqrstuúùủũụưứừửữựvwxyýỳỷỹỵz";
	
	@Override
	public int compare(Student s1, Student s2) {
		String s1FirstName = Student.getFirstName(s1).toLowerCase();
		String s2FirstName = Student.getFirstName(s2).toLowerCase();
		String s1FirstName_KhongDau = remove_DauTiengViet(s1FirstName);
		String s2FirstName_KhongDau = remove_DauTiengViet(s2FirstName);
		
		String s1LastName = Student.getLastName(s1).toLowerCase();
		String s2LastName = Student.getLastName(s2).toLowerCase();
		String s1LastName_KhongDau = remove_DauTiengViet(s1LastName);
		String s2LastName_KhongDau = remove_DauTiengViet(s2LastName);
		
		if (!s1FirstName_KhongDau.equals(s2FirstName_KhongDau))
			return compareName_KhongDau(s1FirstName_KhongDau, s2FirstName_KhongDau);
		else if (!s1FirstName.equals(s2FirstName))
			return compareName_CoDau(s1FirstName, s2FirstName);
		else if (!s1LastName_KhongDau.equals(s2LastName_KhongDau))
			return compareName_KhongDau(s1LastName_KhongDau, s2LastName_KhongDau);
		else
			return compareName_CoDau(s1LastName, s2LastName);
	}
	
	private int compareName_KhongDau(String name1, String name2) {
		return String.valueOf(name1).compareTo(String.valueOf(name2));
	}
	
	private int compareName_CoDau(String name1, String name2) {
		for (int i = 0; i < name1.length(); i++) {
			char c1 = name1.charAt(i);
			char c2 = name2.charAt(i);
			
			if (wordsOrder.indexOf(c1) != -1) {
				if (wordsOrder.indexOf(c1) - wordsOrder.indexOf(c2) != 0)
					return wordsOrder.indexOf(c1) - wordsOrder.indexOf(c2);
			}
			else
				return String.valueOf(c1).compareTo(String.valueOf(c2));
		}
		return 0;
	}
	
	private String remove_DauTiengViet(String str) {
		String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").replaceAll("đ", "d");
	}
	
}