package com.htv.model.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cv_translations", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cv_id", "lang_code"})
})
@Setter
@Getter
public class CvTranslationEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "cv_id", nullable = false)
    public CvEntity cv;

    @Column(name = "lang_code", nullable = false, length = 10)
    public String langCode;

    @Column(nullable = false)
    public String name;

    public String title;

    @Column(columnDefinition = "TEXT")
    public String summary;

    public String email;
    public String phone;
    public String location;
    public String website;

    @OneToMany(mappedBy = "translation", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<CvExperienceEntity> experiences;

    @OneToMany(mappedBy = "translation", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<CvEducationEntity> educations;

    @OneToMany(mappedBy = "translation", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<CvSkillEntity> skills;

    @OneToMany(mappedBy = "translation", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<CvLanguageEntity> languages;

    @OneToMany(mappedBy = "translation", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<CvProjectEntity> projects;
}
