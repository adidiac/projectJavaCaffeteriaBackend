package com.cafeteria.cafeteria.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.cafeteria.service.AnalyticsService;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {
    
    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/most-popular-items")
    public ResponseEntity<HashMap<String,Integer>> getMostPopularItems() {
        var mostPopularItems = analyticsService.getMostPopularItems();
        return ResponseEntity.ok(mostPopularItems);
    }

    @GetMapping("/most-profitable-dates")
    public ResponseEntity<List<String>> getMostProfitableDates() {
        var mostProfitableDates = analyticsService.getMostProfitableDates();
        return ResponseEntity.ok(mostProfitableDates);
    }

    // Other controller methods as needed
}
