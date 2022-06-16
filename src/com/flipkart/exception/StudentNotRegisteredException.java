/**
 * 
 */
package com.flipkart.exception;


@SuppressWarnings("serial")
public class StudentNotRegisteredException extends Exception {

	private String studentName;

	/**
	 * @param studentName
	 */
	public StudentNotRegisteredException(String studentName) {
		this.studentName = studentName;
	}

	/**
	 * Getter Method
	 * @return the studentName
	 */
	public String getStudentName() {
		return studentName;
	}
	
	/**
	 * Message returned when Exception is thrown
	 */
	@Override
	public String getMessage() {
		return "Student with Student Name: " + studentName + " is not registered.";
	}
}
