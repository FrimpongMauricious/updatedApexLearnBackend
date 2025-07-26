package com.group.appdev.repo;

import com.group.appdev.model.Enrollment;
import com.group.appdev.model.Users;
import com.group.appdev.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByUser(Users user);
    boolean existsByUserAndCourse(Users user, Course course);
}
