package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseNotFoundException;
import java.util.List;

public interface AdminDaoInterface {
    default boolean addProfessor(Professor professor) {
        return false;
    }
    boolean addCourse(Course course);
    boolean dropCourse(int courseId);
    boolean approveStudents();
    boolean validateCredentials(String adminId, String password);
    public void assignCourse(int courseId, String professorId) throws CourseNotFoundException;
    public List<Course> viewCourse() throws Exception;

//    ArrayList<Grade> fetchGrade(int userId);
}
