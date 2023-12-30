package com.cafeteria.cafeteria.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.cafeteria.Aspects.ValidateTokenAdmin;
import com.cafeteria.cafeteria.Aspects.ValidateTokenUser;
import com.cafeteria.cafeteria.DbModels.Feedback;
import com.cafeteria.cafeteria.ViewModels.CreateFeedback;
import com.cafeteria.cafeteria.service.FeedbackService;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    @ValidateTokenAdmin
    public ResponseEntity<List<Feedback>> getAllFeedback() {
        List<Feedback> feedbackEntries = feedbackService.getAllFeedback();
        return ResponseEntity.ok(feedbackEntries);
    }

    @PostMapping
    @ValidateTokenUser
    public ResponseEntity<Void> createFeedback(@RequestParam CreateFeedback feedback) {
        try {
            feedbackService.createFeedback(feedback);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}
