package com.flipkart.application;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.service.AdminInterface;
import com.flipkart.service.AdminOperation;

import java.util.Scanner;

public class AdminMenu {
    public void adminMenu(){

        int choice;
        int d=1;
        while(d!=0) {
//            System.out.println("Enter your choice : ");
//            System.out.println("1. Add Professor");
//            System.out.println("2. Add Course");
//            System.out.println("3. Drop Course");
//            System.out.println("4. Approve Pending Students");
//            System.out.println("5. Assign Course To Professor");
//            System.out.println("6. View All the courses");
//            System.out.println("7. Exit");
        	System.out.println("\t\tEnter your choice : ");
            System.out.println("\t\t1. Add Professor");
            System.out.println("\t\t2. Add Course");
            System.out.println("\t\t3. Drop Course");
            System.out.println("\t\t4. Approve Pending Students");
            System.out.println("\t\t5. Assign Course To Professor");
            System.out.println("\t\t6. View All the courses");
            System.out.println("\t\t7. Exit");
            Scanner in= new Scanner(System.in);

            choice = in.nextInt();
            switch (choice) {

                case 1:
                	System.out.println("\t\tEnter Professor ID");
                    String profId = in.next();
                    System.out.println("\t\tName:");
                    String name = in.next();
                    System.out.println("\t\tProfessor Email id ");
                    String email = in.next();
                    System.out.println("\t\tPassword: ");
                    String password = in.next();
                    System.out.println("\t\tProfessor Contact number");
                    String contact = in.next();
                    System.out.println("\t\tArea of expertise");
                    String areaOfExpertise = in.next();
                    System.out.println("\t\tYears of experience");
                    int yoe = in.nextInt();
                    Professor p = new Professor();
                    p.setUserId(profId);
                    p.setUserName(name);
                    p.setEmailId(email);
                    p.setPassword(password);
                    p.setContactNo(contact);
                    p.setAreaOfExpertise(areaOfExpertise);
                    p.setYearsOfExperience(yoe);
                    AdminInterface admin0 = AdminOperation.getInstance();

                    try{
                        admin0.addProfessor(p);
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case 2:
                	System.out.println("\t\tEnter Course ID");
                    int courseId = in.nextInt();
                    System.out.println("\t\tCourse Name:");
                    String courseName = in.next();
                    Course c = new Course(courseId, courseName);
                    AdminInterface admin1 = AdminOperation.getInstance();

                    try{
                        admin1.addCourse(c);
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case 3:
                    System.out.println("\t\tEnter Course ID");
                    int cId = in.nextInt();

                    AdminInterface admin2= AdminOperation.getInstance();

                    try{
                        admin2.dropCourse(cId);
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case 4:
                    AdminInterface admin3= AdminOperation.getInstance();
                    try{
                        admin3.approveStudents();
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                    break;

                case 5:
                	AdminInterface admin4= AdminOperation.getInstance();
                	System.out.println("\t\tEnter Course ID");
                    int coId = in.nextInt();
                    System.out.println("\t\tEnter Professor ID");
                    String poId = in.next();
                	try{
                        admin4.assignCourse(coId, poId);
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }
                    break;
                case 6:
                	AdminInterface admin5= AdminOperation.getInstance();
                	try {
                		admin5.viewCourse();
                	}
                	catch(Exception e) {
                		System.out.println(e);
                	}
                	break;
                case 7:
                	d = 0;
                	break;
                default:
                    System.out.println("\t\tInvalid choice!");
            }
        }
    }
}
