package com.flipkart.service;
import java.util.List;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.dao.AdminDaoImplementation;
import com.flipkart.dao.AdminDaoInterface;
import com.flipkart.exception.CourseNotFoundException;

public class AdminOperation implements AdminInterface {
    private static volatile AdminOperation instance = null;

    // private constructor
    private AdminOperation() {
    }

    public static AdminOperation getInstance() {
        if (instance == null) {
            synchronized (AdminOperation.class) {
                instance = new AdminOperation();
            }
        }
        return instance;
    }

    @Override
    public void addProfessor(Professor professor) {
        AdminDaoInterface admin = AdminDaoImplementation.getInstance();
        boolean ok = admin.addProfessor(professor);
        if(ok) {
            System.out.println("Professor added");
            System.out.println("+++++++++++++++++++++++");
        }
        else {
            System.out.println("Professor not added");
        }
    }

    @Override
    public void addCourse(Course course){
        AdminDaoInterface admin = AdminDaoImplementation.getInstance();
        boolean ok = admin.addCourse(course);
        if(ok) {
            System.out.println("Course added");
            System.out.println("+++++++++++++++++++++++");
        }
        else {
            System.out.println("Course not added");
        }

    }

    @Override
    public void dropCourse(int courseId){
        AdminDaoInterface admin = AdminDaoImplementation.getInstance();
        boolean ok = admin.dropCourse(courseId);
        if(ok) {
            System.out.println("Course Dropped");
            System.out.println("+++++++++++++++++++++++");
        }
        else {
            System.out.println("Cant drop course");
        }

    }

    @Override
    public boolean approveStudents() {
        AdminDaoInterface admin = AdminDaoImplementation.getInstance();
        boolean ok = admin.approveStudents();
        if(ok) {
            System.out.println("");
            System.out.println("+++++++++++++++++++++++");
            return true;
        }
        else {
            System.out.println("Cant drop course");
            return false;
        }
    }

	@Override
	public void assignCourse(int courseId, String professorId) throws CourseNotFoundException {
		// TODO Auto-generated method stub
		AdminDaoInterface admin = AdminDaoImplementation.getInstance();
		admin.assignCourse(courseId, professorId);
		
		
	}

	@Override
	public void viewCourse() {
		// TODO Auto-generated method stub
		AdminDaoInterface admin = AdminDaoImplementation.getInstance();
		try {
		List<Course> courseList = admin.viewCourse();
		if(courseList != null) {
			for(Course course: courseList) {
				String out = "CourseId: " + Integer.toString(course.getCourseId()) + " " + "CourseName: " + course.getCourseName() + " " ;
				if(course.getProfessorId() != null) {
					out += "ProfessorId: " + course.getProfessorId();
				}
				else {
					out += "No Professor";
				}
				out += "\n";
				System.out.println(out);
				
			}
		}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

//    @Override
//    public void generateReportCard(String studentId) {
//
//    }
}
