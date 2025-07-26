package com.group.appdev.controller;

import com.group.appdev.model.Course;
import com.group.appdev.model.Users;
import com.group.appdev.repo.CourseRepository;
import com.group.appdev.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepo userRepo;

    // Get all courses
    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get course by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseById(@PathVariable int id) {
        Course course = courseRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return ResponseEntity.ok(course);
    }

    // Get all courses by a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getCoursesByUser(@PathVariable int userId) {
        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Course> courses = courseRepository.findByUploadedBy(user);
        return ResponseEntity.ok(courses);
    }

    // Create a new course
    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Course courseData) {
        int userId = courseData.getUploadedBy().getId();

        Users user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Course course = new Course();
        course.setTitle(courseData.getTitle());
        course.setTutorName(courseData.getTutorName());
        course.setOrganizationName(courseData.getOrganizationName());
        course.setImageUrl(courseData.getImageUrl());
        course.setVideoPath(courseData.getVideoPath());
        course.setPrice(courseData.getPrice());
        course.setDescription(courseData.getDescription());
        course.setUploadedBy(user);


        courseRepository.save(course);
        return ResponseEntity.ok("Course saved successfully");
    }

    // Update course
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable int id, @RequestBody Course updatedCourse) {
        Course existingCourse = courseRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        existingCourse.setTitle(updatedCourse.getTitle());
        existingCourse.setTutorName(updatedCourse.getTutorName());
        existingCourse.setOrganizationName(updatedCourse.getOrganizationName());
        existingCourse.setImageUrl(updatedCourse.getImageUrl());
        existingCourse.setVideoPath(updatedCourse.getVideoPath());
        existingCourse.setDescription(updatedCourse.getDescription());
        existingCourse.setPrice(updatedCourse.getPrice());

        // Optional: Update uploader only if needed
        if (updatedCourse.getUploadedBy() != null) {
            Users user = userRepo.findById(updatedCourse.getUploadedBy().getId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            existingCourse.setUploadedBy(user);
        }

        courseRepository.save(existingCourse);
        return ResponseEntity.ok("Course updated successfully");
    }

    // Delete course
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable int id) {
        Course course = courseRepository.findById((long) id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        courseRepository.delete(course);
        return ResponseEntity.ok("Course deleted successfully");
    }
}
