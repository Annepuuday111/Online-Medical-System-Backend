package com.klef.medical_system.service;

import com.klef.medical_system.exception.ResourceNotFoundException;
import com.klef.medical_system.model.Feedback;
import com.klef.medical_system.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    public Feedback getFeedbackById(int id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feedback not found with id: " + id));
    }

    public Feedback updateFeedback(int id, Feedback feedbackDetails) {
        Feedback feedback = getFeedbackById(id);
        feedback.setDoctorId(feedbackDetails.getDoctorId());
        feedback.setTreatmentFeedback(feedbackDetails.getTreatmentFeedback());
        feedback.setRating(feedbackDetails.getRating());
        feedback.setRecommendation(feedbackDetails.getRecommendation());
        return feedbackRepository.save(feedback);
    }

    public void deleteFeedback(int id) {
        Feedback feedback = getFeedbackById(id);
        feedbackRepository.delete(feedback);
    }
}
