package com.cafeteria.cafeteria.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.cafeteria.Aspects.ValidateTokenAdmin;
import com.cafeteria.cafeteria.Aspects.ValidateTokenUser;
import com.cafeteria.cafeteria.DbModels.Feedback;
import com.cafeteria.cafeteria.ViewModels.CreateFeedback;
import com.cafeteria.cafeteria.service.FeedbackService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/feedback")
@Tag(name = "Feedback", description = "Feedback API for creating and getting feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    @ValidateTokenAdmin
    public ResponseEntity<List<Feedback>> getAllFeedback(@RequestHeader("Authorization") String token) {
        List<Feedback> feedbackEntries = feedbackService.getAllFeedback();
        return ResponseEntity.ok(feedbackEntries);
    }

    @PostMapping
    @ValidateTokenUser
    @Valid
    public ResponseEntity<Void> createFeedback(@RequestBody CreateFeedback feedback , @RequestHeader("Authorization") String token) {
        feedbackService.createFeedback(feedback);
        return ResponseEntity.ok().build();
    }

}
