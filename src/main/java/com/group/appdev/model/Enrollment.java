package com.group.appdev.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDateTime enrolledAt = LocalDateTime.now();

    // Constructors
    public Enrollment() {}

    public Enrollment(Users user, Course course) {
        this.user = user;
        this.course = course;
        this.enrolledAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Users getUser() {
        return user;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setEnrolledAt(LocalDateTime enrolledAt) {
        this.enrolledAt = enrolledAt;
    }
}
