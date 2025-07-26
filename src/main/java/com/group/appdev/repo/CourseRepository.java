package com.group.appdev.repo;

import com.group.appdev.model.Course;
import com.group.appdev.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByUploadedBy(Users uploadedBy);

}
