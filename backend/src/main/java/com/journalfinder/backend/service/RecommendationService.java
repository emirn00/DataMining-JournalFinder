package com.journalfinder.backend.service;

import com.journalfinder.backend.model.AcademicRecordAbstract;
import com.journalfinder.backend.repository.AcademicRecordAbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private AcademicRecordAbstractRepository abstractRepository;

    public List<String> getRecommendations(String userAbstract) {
        // Normalde burada Python servisi çağrılabilir veya Java tarafında bir benzerlik algoritması çalıştırılabilir.
        // Şimdilik veritabanındaki tüm kayıtları çekip basit bir eşleşme simülasyonu yapıyoruz.
        
        return abstractRepository.findAll().stream()
                .limit(5)
                .map(ara -> ara.getAcademicRecord().getPublication().getName())
                .distinct()
                .collect(Collectors.toList());
    }
}
