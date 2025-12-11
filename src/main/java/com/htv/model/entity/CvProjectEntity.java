package com.htv.model.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cv_projects")
@Getter
@Setter
public class CvProjectEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "translation_id", nullable = false)
    public CvTranslationEntity translation;

    @Column(name = "project_id")
    public String projectId;

    public String title;

    @Column(name = "short_desc", columnDefinition = "TEXT")
    public String shortDesc;

    @Column(name = "full_desc", columnDefinition = "TEXT")
    public String fullDesc;

    @Column(columnDefinition = "TEXT[]")
    @ElementCollection
    public List<String> tech;

    public String link;
    public String year;

    @Column(name = "sort_order")
    public Integer sortOrder = 0;
}
