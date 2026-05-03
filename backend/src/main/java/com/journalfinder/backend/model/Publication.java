package com.journalfinder.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Publication")
@Data
public class Publication {
    @Id
    @Column(name = "PublicationID")
    private Long id;

    @Column(name = "Name")
    private String name;
}
