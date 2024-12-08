package com.klef.medical_system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.medical_system.model.Feedback;
import com.klef.medical_system.service.FeedbackService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/feedbacks")
    public List<Feedback> getAllFeedbacks() {
        return feedbackService.getAllFeedback();
    }

    @PostMapping("/feedbacks")
    public Feedback createFeedback(@RequestBody Feedback feedback) {
        return feedbackService.createFeedback(feedback);
    }

    @GetMapping("/feedbacks/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable int id) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        return ResponseEntity.ok(feedback);
    }

    @PutMapping("/feedbacks/{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable int id, @RequestBody Feedback feedbackDetails) {
        Feedback updatedFeedback = feedbackService.updateFeedback(id, feedbackDetails);
        return ResponseEntity.ok(updatedFeedback);
    }

    @DeleteMapping("/feedbacks/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteFeedback(@PathVariable int id) {
        feedbackService.deleteFeedback(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
