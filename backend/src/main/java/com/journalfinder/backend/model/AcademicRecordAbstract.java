package com.journalfinder.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "AcademicRecordAbstract")
@Data
public class AcademicRecordAbstract {
    @Id
    @Column(name = "AcademicRecordAbstractID")
    private Long id;

    @OneToOne
    @JoinColumn(name = "AcademicRecordId")
    private AcademicRecord academicRecord;

    @Column(name = "AbstractText", columnDefinition = "TEXT")
    private String abstractText;
}
