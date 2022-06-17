package com.flipkart.bean;

public class User {
    private String userId;
    private String userName;
    private String emailId;
    private String password;
    private String contactNo;
    private String role;

    public User(){
            }

    /**
     *
      * @param userId
     * @param userName
     * @param emailId
     * @param password
     * @param contactNo
     */
    public User(String userId, String userName, String emailId, String password, String contactNo, String role) {
        this.userId = userId;
        this.userName = userName;
        this.emailId = emailId;
        this.password = password;
        this.contactNo = contactNo;
        this.role = role;
    }

    public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
