package com.journalfinder.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "AcademicRecord")
@Data
public class AcademicRecord {
    @Id
    @Column(name = "AcademicRecordID")
    private Long id;

    @Column(name = "Title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "PublicationId")
    private Publication publication;
}
