package com.flipkart.application;
import com.flipkart.bean.*;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseAlreadyRegisteredException;
import com.flipkart.service.PaymentServiceImplementation;
import com.flipkart.service.PaymentServiceInterface;
import com.flipkart.service.StudentInterface;
import com.flipkart.dao.StudentDaoImplementation;
import com.flipkart.service.StudentOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.*;

public class CrsStudentMenu {

        public void studentMenu(Student student) throws IOException, SQLException, CourseAlreadyRegisteredException {
            while(true) {
            	System.out.println("");
                System.out.println("\t\t<<<< STUDENT MENU >>>>");
                System.out.println("\t\t1.View Details\n\t\t2.View Courses\n\t\t3.Register for Courses\n\t\t4.View Report Card\n\t\t5.Pay Fee\n\t\t6.Check Fee Status\n\t\t7.View Registered Courses\n\t\t8. View Notification\n\t\t8.Exit\n");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("\t\tEnter the choice");
                int option = Integer.parseInt(br.readLine());
                StudentOperations studentOperations=new StudentOperations();
                StudentDaoImplementation studentDaoImplementation=new StudentDaoImplementation();
                switch (option) {
	                case 1:
	                    System.out.println("\t\t<<<< Student Details >>>>");
	                    System.out.println("\t\tStudent Id: "+student.getUserId()+"\n\t\tStudent Name: "+student.getUserName()+"\n\t\tEmail Id: "+student.getEmailId()+"\n\t\tSemester: "+student.getSemester());
	                    System.out.println("");
	                    break;
	                case 2:
	                    System.out.println("\t\tList of Courses Available");
	                    ArrayList<Course> courses= (ArrayList<Course>) studentOperations.viewCourses();
	                    System.out.println("\t\tCourseId-CourseName");
	                    for(Course c:courses)
	                        System.out.println(c.getCourseId()+"\t-\t"+c.getCourseName());
	                    break;
	                case 3:
	                    System.out.println("\t\tRegister for the courses");
	                    studentOperations.registerCourses(student.getUserId());
	                    break;
	                case 4:
	                    System.out.println("\t\tStudent Report Card");
	                    studentOperations.viewGradeCard(student.getUserId());
	                    break;
	                case 5:
	                    System.out.println("\t\tPay Fee");
	                    PaymentServiceInterface psi = new PaymentServiceImplementation();
	                    psi.payFees(student);
	                    break;
	                case 6:
	                    System.out.println("\t\tCurrent Fee Status Says : ");
	                    System.out.println(studentDaoImplementation.getfeeStatus(student.getUserId()));
	                    break;
	                case 7:
	                    System.out.println("\t\tYou are Registered to following Courses :");
	                    studentOperations.registeredCourseList(student.getUserId());
	                    break;
                    case 8:
                    	viewNotifications(student.getUserId());
                    	break;
                    case 9:
                        return;
                    default:
                        System.out.println("\t\tExit");
                }
            }
            
        }
        public static void printNotification(PaymentNotification notification)
    	{
    		
    		System.out.println(String.format("%30s %30s",notification.getReferenceId(),notification.getMessage()));
    	}
        public void viewNotifications(String studentID) throws SQLException
        
    	{
        	StudentInterface studentService = new StudentOperations();
    		List<PaymentNotification> notificationList = new ArrayList<PaymentNotification>();
    		notificationList = studentService.viewNotifications(studentID);
    		
    		if(notificationList.size()==0)
    		{
    			System.err.println("No notifications as of now. Come back again!!");
    			return;
    		}
    		
    		System.out.println(String.format("%30s %30s ","Reference ID","Status"));
    		notificationList.forEach(CrsStudentMenu::printNotification);
    		
    		
    	}

    }