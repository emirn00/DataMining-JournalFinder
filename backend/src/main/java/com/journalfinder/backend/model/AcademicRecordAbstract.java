package com.journalfinder.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "AcademicRecordAbstract")
@Data
public class AcademicRecordAbstract {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private AcademicRecord academicRecord;

    @Column(name = "abstract", columnDefinition = "TEXT")
    private String abstractText;
}
