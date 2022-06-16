package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.exception.CourseNotFoundException;

public interface AdminInterface {
    public void addProfessor(Professor professor);
    public void addCourse(Course course);
    public void dropCourse(int courseId);
    public boolean approveStudents();
    public void assignCourse(int courseId, String professorId) throws CourseNotFoundException ;
//    public void generateReportCard(String studentId);
    public void viewCourse();
}
