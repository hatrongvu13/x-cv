package com.htv.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cv_languages")
@Data
public class CvLanguageEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "translation_id", nullable = false)
    public CvTranslationEntity translation;

    public String name;
    public String level;
}
