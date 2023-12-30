package com.cafeteria.cafeteria.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.cafeteria.Aspects.ValidateTokenAdmin;
import com.cafeteria.cafeteria.DbModels.Promotion;
import com.cafeteria.cafeteria.service.PromotionService;

@RestController
@RequestMapping("/api/promotion")
public class PromotionController {
    
    private final PromotionService promotionService;

    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    @GetMapping
    @ValidateTokenAdmin
    public ResponseEntity<List<Promotion>> getAllPromotions() {
        List<Promotion> promotionEntries = promotionService.getAllPromotions();
        return ResponseEntity.ok(promotionEntries);
    }

    @PostMapping
    @ValidateTokenAdmin
    public ResponseEntity<Void> createPromotion(@RequestParam Promotion promotion) {
        try {
            promotionService.createPromotion(promotion);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @ValidateTokenAdmin
    public ResponseEntity<Void> updatePromotion(@RequestParam Promotion promotion) {
        try {
            promotionService.updatePromotion(promotion);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePromotion(@RequestParam Long id) {
        try {
            promotionService.deletePromotion(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

}
