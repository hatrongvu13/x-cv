package com.htv.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "cv_skills")
public class CvSkillEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "translation_id", nullable = false)
    public CvTranslationEntity translation;

    @Column(nullable = false)
    public String skill;
}
