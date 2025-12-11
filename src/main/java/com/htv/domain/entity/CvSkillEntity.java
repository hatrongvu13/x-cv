package com.htv.domain.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cv_skills")
@Getter
@Setter
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
