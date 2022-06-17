package com.flipkart.dao;

import com.flipkart.bean.Admin;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.constants.SQLQueriesConstants;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.utils.DBUtils;
import java.util.List;
import java.util.*;
//import org.apache.log4j.Logger;

import java.sql.*;
import java.util.Scanner;

public class AdminDaoImplementation implements AdminDaoInterface{

    private static volatile AdminDaoImplementation instance = null;
    public AdminDaoImplementation() {}
    public static AdminDaoImplementation getInstance() {
        if (instance == null) {
            synchronized (AdminDaoImplementation.class) {
                instance = new AdminDaoImplementation();
            }
        }
        return instance;
    }
    /**
	 * Method to add professor
	 * 
	 * @param professor of the student
	 */
    @Override
    public boolean addProfessor(Professor professor) {
        boolean ok = true;
        try{
            Connection con = DBUtils.getConnection();
            Statement stmt = con.createStatement();
            
            if(con==null)System.out.println("connection not established");
                PreparedStatement preparedStatement = con.prepareStatement(SQLQueriesConstants.ADD_USER_QUERY);
                preparedStatement.setString(1, professor.getUserId());
                preparedStatement.setString(2, professor.getPassword());
                preparedStatement.setString(3, professor.getUserName());
                preparedStatement.setString(4, professor.getEmailId());
                preparedStatement.setString(5, professor.getContactNo());
                preparedStatement.setString(6, "PROFESSOR");

                int rows = preparedStatement.executeUpdate();

                PreparedStatement preparedStatement1 = con.prepareStatement(SQLQueriesConstants.ADD_PROFESSOR_QUERY);
                preparedStatement1.setString(1, professor.getUserId());
                preparedStatement1.setString(2, professor.getAreaOfExpertise());
                preparedStatement1.setInt(3, professor.getYearsOfExperience());
                int rowsAffected1 = preparedStatement1.executeUpdate();

                if (rowsAffected1 == 1 && rows == 1) {
                    ok=true;
                }
            }
         catch (SQLException e) {
            ok = false;
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return ok;
    }
    
    /**
	 * Method to validate credentials 
	 */

    public boolean validateCredentials(String adminId, String password){
        try{
            Connection con = DBUtils.getConnection();
            if(con==null)System.out.println("connection not established");
            PreparedStatement preparedStatement = con.prepareStatement(SQLQueriesConstants.VERIFY_ADMIN_CREDENTIAL);
            preparedStatement.setString(1, adminId);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            return true;
        }
        catch(Exception e){
            System.out.println(e);
            return false;
        }
    }
    /**
	 * Method to Add Course
	 */
    @Override
    public boolean addCourse(Course course) {
        boolean ok = true;
        try{
            Connection con = DBUtils.getConnection();
            if(con==null) System.out.println("connection not established");
            PreparedStatement preparedStatement = con.prepareStatement(SQLQueriesConstants.ADD_COURSE);
            preparedStatement.setInt(1, course.getCourseId());
            preparedStatement.setString(2, course.getCourseName());        
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            ok = false;
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return ok;
    }
    
    /**
	 * Method to Drop Course
	 */

    @Override
    public boolean dropCourse(int courseId) {
        boolean ok = true;
        try{
            Connection con = DBUtils.getConnection();
            Statement stmt = con.createStatement();
            if(con==null)System.out.println("connection not established");
            PreparedStatement preparedStatement = con.prepareStatement(SQLQueriesConstants.DELETE_COURSE_QUERY);
            preparedStatement.setInt(1, courseId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            ok = false;
            e.printStackTrace();
        }
        return ok;
    }
    /**
	 * Method to Approve Students
	 */
    @Override
    public boolean approveStudents() {
        boolean ok = true;
        try{
            Connection con = DBUtils.getConnection();
            Statement stmt = con.createStatement();
            if(con==null)System.out.println("connection not established");
            Scanner in= new Scanner(System.in);
            int ch =1;
            while(ch!=0) {
            	PreparedStatement preparedStatement = con.prepareStatement(SQLQueriesConstants.VIEW_PENDING_APPROVAL);
            	
                ResultSet rs = preparedStatement.executeQuery();
                int flag=0;
                System.out.println("Here is a list of all pending students");
                while (rs.next()) {
                    String sId = rs.getString(1);
                    System.out.println(rs.getString(1));
                    flag=1;
                }
                if(flag==1) {
                    System.out.println("Enter student id");
                    String id = in.next();
                    String sql1 = "UPDATE student SET isApproved = 1 where studentId = ?";
                    PreparedStatement statement = con.prepareStatement(sql1);
                    statement.setString(1,id);
                    statement.executeUpdate();
                }
                else{
                    System.out.println("<<<<<<< No student left to be approved >>>>>>>>>>>");
                }
                System.out.println("To exit, press 0 : To continue, press 1");
                ch = in.nextInt();
            }
        } catch (SQLException e) {
            ok = false;
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return ok;
    }
    /**
	 * Method to find if student is approved
	 * 
	 * @param studentId of the student
	 */

    public boolean isApproved(String studentId) throws Exception{
        Connection con = DBUtils.getConnection();
        String sql = "select * from student where studentId=? and isApproved = 1";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1,studentId);
        ResultSet rs =  stmt.executeQuery();
        while(rs.next()) {
            return true;
        }
        return false;
    }
    
    /**
	 * Method to approve students
	 * 
	 * @param studentId of the student
	 */
    public void approveStudents(String studentId) throws Exception {
            String id = studentId;
            String sql1 = "UPDATE student SET isApproved = 1 where studentId = ?";
            Connection con = DBUtils.getConnection();
            PreparedStatement statement = con.prepareStatement(sql1);
            statement.setString(1,id);
            statement.executeUpdate();
    }
    /**
	 * Method to assign courses to professor
	 * 
	 * @param courseId of the course
	 * @param professorId of the professor
	 */
    @Override
	public void assignCourse(int courseId, String professorId) throws CourseNotFoundException{
		try {
			Connection connection = DBUtils.getConnection();
			String sql = SQLQueriesConstants.ASSIGN_COURSE_QUERY;
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1,professorId);
			statement.setInt(2,courseId);
			int row = statement.executeUpdate();
			
			System.out.println(row + " Updated");
			if(row == 0) {
				System.out.println(courseId + " not found");
				throw new CourseNotFoundException(courseId);
			}
			System.out.println("Course : " + courseId + " is assigned to " + professorId);
		
		}catch(SQLException se) {
			
			System.out.println(se.getMessage());
			
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
		}
	}
    /**
	 * Method to View Courses
	 */
	@Override
	public List<Course> viewCourse() throws Exception{
		// TODO Auto-generated method stub
		List<Course> courseList = new ArrayList<>();
		
		try {
		Connection con = DBUtils.getConnection();
		PreparedStatement statement = con.prepareStatement(SQLQueriesConstants.VIEW_COURSE_QUERY);
		ResultSet resultSet = statement.executeQuery();		
		while(resultSet.next()) {
			Course course = new Course();
			course.setCourseId(resultSet.getInt(1));
			course.setCourseName(resultSet.getString(2));
			course.setProfessorId(resultSet.getString(3));
			courseList.add(course);
		}
		return courseList;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
