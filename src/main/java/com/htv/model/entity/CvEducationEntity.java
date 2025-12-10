package com.htv.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "cv_educations")
public class CvEducationEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "translation_id", nullable = false)
    public CvTranslationEntity translation;

    @Column(name = "sort_order")
    public Integer sortOrder = 0;

    public String school;
    public String major;
    public String duration;
    public String gpa;
}
