package com.journalfinder.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "AcademicRecord")
@Data
public class AcademicRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;
}
