package com.group.appdev.model;

import jakarta.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String tutorName;
    private String organizationName;

    private String imageUrl;       // thumbnail

    private String videoPath;      // actual video file path or cloud URL
    private String price;



    @Column(length = 2000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users uploadedBy;

    // Constructors
    public Course() {}

    public Course(String title, String tutorName, String organizationName, String imageUrl, String price,
                  String videoPath, String description, Users uploadedBy) {
        this.title = title;
        this.tutorName = tutorName;
        this.organizationName = organizationName;
        this.imageUrl = imageUrl;
        this.videoPath = videoPath;
        this.description = description;
        this.uploadedBy = uploadedBy;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    // Getters and Setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTutorName() {
        return tutorName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public String getDescription() {
        return description;
    }

    public Users getUploadedBy() {
        return uploadedBy;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUploadedBy(Users uploadedBy) {
        this.uploadedBy = uploadedBy;
    }
}
