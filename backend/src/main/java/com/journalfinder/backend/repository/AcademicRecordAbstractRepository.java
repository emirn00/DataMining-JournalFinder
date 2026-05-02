package com.journalfinder.backend.repository;

import com.journalfinder.backend.model.AcademicRecordAbstract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicRecordAbstractRepository extends JpaRepository<AcademicRecordAbstract, Long> {
}
