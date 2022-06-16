package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.utils.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProfessorOperations implements ProfessorUtilsInterface {
    public Map<String, ArrayList<String>> viewEnrolledStudentsWithDB(Professor professor) throws SQLException {

        Map<String, ArrayList<String>> students = new LinkedHashMap<>();
        ConnectionWithDB con = new ConnectionWithDB();
        Connection conn = DBUtils.getConnection();
        String sql = "select registrar.userId,user.userName,course.courseId,course.courseName " +
                "from registrar,user,course where registrar.courseId in(select courseId from professorreg " +
                "where professorreg.userId='" + professor.getProfessorId() + "' ) and registrar.userId=user.userId and " +
                "registrar.courseId=course.courseId ";

        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();

        while (rs.next()) {
            String user = rs.getString(1) + " " + rs.getString(2);
            String course = rs.getString(3) + " " + rs.getString(4);
            if (!students.containsKey(course))
                students.put(course, new ArrayList<>());
            students.get(course).add(user);
        }
        return students;
    }

    public void provideGrade(int courseId, String studentId, String Grade) throws SQLException {
        ConnectionWithDB connObj = new ConnectionWithDB();
        Connection con = connObj.getConnection();
        String SQL = "UPDATE registrar set grade='" + Grade + "' where userId='" + studentId + "' and courseId=" + courseId;

        long id = 0;
        //inserting into table
        try (
                PreparedStatement pstmt = con.prepareStatement(SQL,
                        Statement.RETURN_GENERATED_KEYS)) {

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            else
            {
                System.out.println("Enter correct ids! Grades not added");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void registerCoursesWithDB(Professor professor, Course course) throws SQLException {
        ArrayList<Course> courses = new ArrayList<Course>();
        ConnectionWithDB connObj = new ConnectionWithDB();
        Connection con = connObj.getConnection();
        String SQL = "INSERT INTO professorreg(userId,courseId)"
                + "VALUES(?,?)";

        long id = 0;
        //inserting into table
        try (
                PreparedStatement pstmt = con.prepareStatement(SQL,
                        Statement.RETURN_GENERATED_KEYS)) {


            pstmt.setString(1, professor.getProfessorId());
            pstmt.setString(2, String.valueOf(course.getCourseId()));


            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public ArrayList<Course> viewAvailableCoursesWithDB(Professor professor) throws SQLException {
        ArrayList<Course> courses = new ArrayList<Course>();
        ConnectionWithDB connObj = new ConnectionWithDB();
        Connection con = connObj.getConnection();
        String sql = "select courseId,courseName from course where courseId not in (select courseId from professorReg)";
        PreparedStatement statement = con.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Course course = new Course(rs.getInt(1), rs.getString(2));
            course.setCourseId(rs.getInt(1));
            course.setCourseName(rs.getString(2));
            courses.add(course);
        }
        return courses;
    }

    public Professor validateCredentialsWithDB(String userId, String password) {
        try {
            ConnectionWithDB connObj = new ConnectionWithDB();
            Connection con = connObj.getConnection();
            String SQL = "select * from user,professor where user.userId like '" + userId + "' and professor.professorId like '" + userId + "' and user.password like '" + password + "'";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);
            if (rs.next()) {
                Professor professor = new Professor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
                //System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getString(6)+"  "+rs.getString(7)+"  "+rs.getString(8));
                con.close();
                return professor;
            }
            //while(rs.next())
            //    System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getString(4)+"  "+rs.getString(5)+"  "+rs.getString(6)+"  "+rs.getString(7)+"  "+rs.getString(8));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public ArrayList<Course> viewCoursesWithDB() throws SQLException {

        ArrayList<Course> courses = new ArrayList<Course>();
        ConnectionWithDB connection = new ConnectionWithDB();
        Connection conn = connection.getConnection();
        String sql = "SELECT * FROM course";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            Course course = new Course(rs.getInt(1), rs.getString(2));
//            course.setCourseId(rs.getInt(1));
//            course.setCourseName(rs.getString(2));
            courses.add(course);
        }
        return courses;

    }
}