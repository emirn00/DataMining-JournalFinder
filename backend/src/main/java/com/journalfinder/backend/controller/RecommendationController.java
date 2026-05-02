package com.journalfinder.backend.controller;

import com.journalfinder.backend.dto.RecommendationRequest;
import com.journalfinder.backend.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/recommend")
    public List<String> recommend(@RequestBody RecommendationRequest request) {
        return recommendationService.getRecommendations(request.getAbstractText());
    }
}
