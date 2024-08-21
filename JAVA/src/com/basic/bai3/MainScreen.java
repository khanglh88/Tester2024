package com.basic.bai3;

import java.util.ArrayList;
import java.util.Scanner;

public class MainScreen {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Student> studentList = new ArrayList<>();
		
		String personInput = "Y";
		while (personInput.equalsIgnoreCase("Y")) {
			Student student = new Student();
			
			System.out.print("Full Name: ");
			String fullname = scanner.nextLine();
			student.setFullName(fullname);
			
			System.out.print("Address: ");
			String address = scanner.nextLine();
			student.setAddress(address);
			
			System.out.print("Dob (dd/mm/yyy): ");
			String dob = scanner.nextLine();
			student.setDob(dob);
			
			System.out.print("Gender: ");
			String gender = scanner.nextLine();
			student.setGender(gender);
			
			System.out.print("Final Grade: ");
			float finalGrade = scanner.nextFloat();
			student.setFinalGrade(finalGrade);
			
			scanner.nextLine();
			studentList.add(student);
			
			System.out.println("Do you want to continue (Y/N)? ");
			personInput = scanner.nextLine();
		}
		scanner.close();
		
		double totalGrade = 0;
		
		System.out.println("List of Student: ");
		for(int i = 0; i < studentList.size() ; i++) {
			Student student = studentList.get(i);
			System.out.println("Student " + (i+1) + ": ");
			System.out.println("Full Name: " + student.getFullName());
			System.out.println("Address: " + student.getAddress());
			System.out.println("Dob: " + student.getDob());
			System.out.println("Gender: " + student.getGender());
			System.out.println("Final Grade: " + student.getFinalGrade());
			
			totalGrade += student.getFinalGrade();

			double finalGrade = student.getFinalGrade();
		    if (finalGrade < 4.0) {
		        System.out.println("Học sinh: " + student.getFullName() + " - Học lực kém");
		    } else if (finalGrade < 5.0) {
		        System.out.println("Học sinh: " + student.getFullName() + " - Học lực yếu");
		    } else if (finalGrade < 5.5) {
		        System.out.println("Học sinh: " + student.getFullName() + " - Học lực trung bình yếu");
		    } else if (finalGrade < 6.5) {
		        System.out.println("Học sinh: " + student.getFullName() + " - Học lực trung bình");
		    } else if (finalGrade < 7.0) {
		        System.out.println("Học sinh: " + student.getFullName() + " - Học lực trung bình khá");
		    } else if (finalGrade < 8.0) {
		        System.out.println("Học sinh: " + student.getFullName() + " - Học lực khá");
		    } else if (finalGrade < 8.5) {
		        System.out.println("Học sinh: " + student.getFullName() + " - Học lực khá giỏi");
		    } else {
		        System.out.println("Học sinh: " + student.getFullName() + " - Học lực giỏi");
		    }
				System.out.println("=================================");
		}
		double averageGrade = totalGrade / studentList.size(); // Tính điểm trung bình
		System.out.println("Điểm tổng kết trung bình của danh sách học sinh: " + averageGrade);
	}
}