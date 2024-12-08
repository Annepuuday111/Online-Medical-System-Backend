package com.klef.medical_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int doctorId;

    @Column(nullable = false, length = 500)
    private String treatmentFeedback;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false)
    private String recommendation;

    @Column(nullable = false)
    private String feedbackGivenBy;

    public Feedback() {}

    public Feedback(int doctorId, String treatmentFeedback, int rating, String recommendation, String feedbackGivenBy) {
        this.doctorId = doctorId;
        this.treatmentFeedback = treatmentFeedback;
        this.rating = rating;
        this.recommendation = recommendation;
        this.feedbackGivenBy = feedbackGivenBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getTreatmentFeedback() {
        return treatmentFeedback;
    }

    public void setTreatmentFeedback(String treatmentFeedback) {
        this.treatmentFeedback = treatmentFeedback;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public String getFeedbackGivenBy() {
        return feedbackGivenBy;
    }

    public void setFeedbackGivenBy(String feedbackGivenBy) {
        this.feedbackGivenBy = feedbackGivenBy;
    }
}
