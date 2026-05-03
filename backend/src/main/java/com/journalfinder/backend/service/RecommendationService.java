package com.journalfinder.backend.service;

import com.journalfinder.backend.dto.RecommendationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.ResourceAccessException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RecommendationService {

    private final String PYTHON_API_URL = "http://localhost:5001/recommend";
    private final RestTemplate restTemplate = new RestTemplate();

    public List<String> getRecommendations(String userAbstract) {
        if (userAbstract == null || userAbstract.isEmpty()) {
            return Collections.emptyList();
        }

        try {
            PythonRequest pyRequest = new PythonRequest(userAbstract);
            String[] results = restTemplate.postForObject(PYTHON_API_URL, pyRequest, String[].class);
            
            return results != null ? Arrays.asList(results) : Collections.emptyList();
            
        } catch (ResourceAccessException e) {
            System.err.println("Hata: Python Veri Madenciliği servisine ulaşılamıyor! Lütfen uvicorn'u başlatın.");
            return Arrays.asList("Hata: Analiz Motoru (Python) şu an aktif değil.");
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    private static class PythonRequest {
        private String abstract_text;

        public PythonRequest(String abstract_text) {
            this.abstract_text = abstract_text;
        }

        public String getAbstract_text() {
            return abstract_text;
        }

        public void setAbstract_text(String abstract_text) {
            this.abstract_text = abstract_text;
        }
    }
}
