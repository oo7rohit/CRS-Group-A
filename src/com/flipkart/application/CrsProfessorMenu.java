package com.flipkart.application;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.service.ProfessorService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class CrsProfessorMenu {
    ProfessorService profServ=new ProfessorService();
    public void professorMenu(Professor professor) throws IOException, SQLException {
        while(true) {
        	System.out.println("\t\t<<< Professor Menu >>>");
            System.out.println("\t\t1.View Details\n\t\t2.view Courses\n\t\t3.Register for Courses\n\t\t4.View Enrolled Students in courses\n\t\t5.Make Report Card for a student\n6.Exit");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\t\tEnter the choice");
            int option = Integer.parseInt(br.readLine());
            switch (option) {
                case 1:
                    System.out.println("\t\tDetails");
                    System.out.println("\t\tId: "+professor.getUserId()+"\n\t\tProfessorName: "+professor.getUserName()+"\n\t\tEmailId: "+professor.getEmailId()+"\n\t\tAreaOfExpertise: "+professor.getAreaOfExpertise()+"\n\t\tYearsOfExperience: "+professor.getYearsOfExperience());
                    break;
                case 2:
                    ArrayList<Course> courses= profServ.viewAllCourses(professor.getUserId());
                    System.out.println("\t\tCourseId-CourseName");
                    for(Course c:courses)
                        System.out.println(c.getCourseId()+"\t-\t"+c.getCourseName());
                    break;
                case 3:
                    System.out.println("\t\tRegister for the courses");
                    profServ.registerCourses(professor);
                    break;
                case 4:
                    System.out.println("\t\tView enrolled students in each course");
                    Map<String,ArrayList<String>> courseWithStudents=profServ.viewEnrolledStudents(professor);
                    int courseindex=1;
                    for(String CourseName:courseWithStudents.keySet()){
                        System.out.println(courseindex+". "+"("+CourseName+")");
                        int studentsIndex=1;
                        for(String student:courseWithStudents.get(CourseName)){
                            System.out.println("\t"+studentsIndex+". ("+student+")");
                            studentsIndex++;
                        }
                        courseindex++;
                    }
                    break;
                case 5:
                    System.out.println("\t\tMake a report card for a student");
                    profServ.assignGrades(professor);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("\t\tInvalid Choice");

            }
        }
    }

}
