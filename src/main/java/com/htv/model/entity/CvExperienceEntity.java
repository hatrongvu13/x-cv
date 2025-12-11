package com.htv.model.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cv_experiences")
@Getter
@Setter
public class CvExperienceEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "translation_id", nullable = false)
    public CvTranslationEntity translation;

    @Column(name = "sort_order")
    public Integer sortOrder = 0;

    public String company;
    public String position;
    public String duration;

    @Column(columnDefinition = "TEXT")
    public String description;
}
