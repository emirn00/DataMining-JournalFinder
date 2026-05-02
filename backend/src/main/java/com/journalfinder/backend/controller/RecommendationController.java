package com.journalfinder.backend.controller;

import com.journalfinder.backend.dto.RecommendationRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class RecommendationController {

    @PostMapping("/recommend")
    public List<String> recommend(@RequestBody RecommendationRequest request) {
        // This is a placeholder. 
        // In a real scenario, this would call a Python service or a ML model.
        return Arrays.asList(
            "IEEE Transactions on Pattern Analysis and Machine Intelligence",
            "Journal of Machine Learning Research",
            "Nature Machine Intelligence",
            "Data Mining and Knowledge Discovery",
            "ACM Transactions on Knowledge Discovery from Data"
        );
    }
}
