package com.group.appdev.controller;

import com.group.appdev.Service.EnrollmentRequest;
import com.group.appdev.model.*;
import com.group.appdev.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentRepository enrollmentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CourseRepository courseRepo;

    // Enroll a user in a course
    @PostMapping
    public ResponseEntity<?> enrollUser(@RequestBody EnrollmentRequest request) {
        Users user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Course course = courseRepo.findById((long) request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // Prevent duplicate enrollment
        if (enrollmentRepo.existsByUserAndCourse(user, course)) {
            return ResponseEntity.badRequest().body("User already enrolled in this course");
        }

        Enrollment enrollment = new Enrollment(user, course);
        enrollmentRepo.save(enrollment);

        return ResponseEntity.ok("User enrolled successfully");
    }

    // Get all courses a user is enrolled in
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserEnrollments(@PathVariable int userId) {
        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Enrollment> enrollments = enrollmentRepo.findByUser(user);
        return ResponseEntity.ok(enrollments);
    }

    // Optional: Unenroll from a course
    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<?> unenroll(@PathVariable Long enrollmentId) {
        enrollmentRepo.deleteById(enrollmentId);
        return ResponseEntity.ok("Enrollment removed");
    }
}
