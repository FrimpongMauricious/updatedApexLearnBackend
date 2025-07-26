package com.group.appdev.Service;

public class EnrollmentRequest {
    private int userId;
    private int courseId;

    public int getUserId() {
        return userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
