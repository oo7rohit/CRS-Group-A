package com.flipkart.bean;

public class Course {
    private int courseId;
    private String courseName;
    private String professorId;

    public Course() {
    }

    public String getProfessorId() {
		return professorId;
	}

	public void setProfessorId(String professorId) {
		this.professorId = professorId;
	}

	/**
     *
     * @param courseId
     * @param courseName
     */
    public Course(int courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    public int getCourseId() {
        return courseId;
    }

    /**
     *
     * @param courseId
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    /**
     *
     * @param courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
