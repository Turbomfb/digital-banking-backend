package com.techservices.digitalbanking.core.domain.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "industry_sector")
@Setter
@Getter
@ToString
public class IndustrySector{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "name")
    private String name;
    @Column(name = "position")
    private Long position;
    @Column(name = "active")
    private boolean active;
    @Column(name = "mandatory")
    private boolean mandatory;

}
