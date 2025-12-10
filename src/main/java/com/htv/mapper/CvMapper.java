package com.htv.mapper;

import com.google.protobuf.Timestamp;
import com.htv.model.entity.CvEntity;
import com.htv.model.entity.CvTranslationEntity;
import com.htv.proto.cv.*;

import java.time.Instant;
import java.util.Collections;

public class CvMapper {
    public static CV toProto(CvEntity e) {
        CV.Builder b = CV.newBuilder()
                .setId(e.id)
                .setDefaultLang(e.defaultLang)
                .setIsActive(e.isActive)
                .setCreatedAt(toTimestamp(e.createdAt))
                .setUpdatedAt(toTimestamp(e.updatedAt));

        e.translations.forEach(t -> b.addTranslations(toProto(t)));

        return b.build();
    }

    public static Translation toProto(CvTranslationEntity t) {
        Translation.Builder b = Translation.newBuilder()
                .setLangCode(t.langCode)
                .setName(t.name)
                .setTitle(t.title == null ? "" : t.title)
                .setSummary(t.summary == null ? "" : t.summary)
                .setEmail(t.email == null ? "" : t.email)
                .setPhone(t.phone == null ? "" : t.phone)
                .setLocation(t.location == null ? "" : t.location)
                .setWebsite(t.website == null ? "" : t.website);

        t.experiences.forEach(x -> b.addExperience(
                Experience.newBuilder()
                        .setCompany(x.company)
                        .setPosition(x.position)
                        .setDuration(x.duration)
                        .addAllDescription(Collections.singleton(x.description))
                        .build()
        ));

        t.educations.forEach(x -> b.addEducation(
                Education.newBuilder()
                        .setSchool(x.school)
                        .setMajor(x.major)
                        .setDuration(x.duration)
                        .setGpa(x.gpa)
                        .build()
        ));

        t.skills.forEach(s -> b.addSkills(s.skill));

        t.languages.forEach(l -> {
            SpokenLanguage.Builder sb = SpokenLanguage.newBuilder()
                    .setName(l.name);

//            if (l.levelEnum != null) {
//                sb.setLevelEnum(LanguageLevel.valueOf(l.levelEnum));
//            } else {
//                sb.setLevelText(l.levelText);
//            }

            b.addLanguages(sb);
        });

        t.projects.forEach(p -> b.addProjects(
                Project.newBuilder()
                        .setId(p.projectId)
                        .setTitle(p.title)
                        .setShortDesc(p.shortDesc)
                        .setFullDesc(p.fullDesc)
                        .addAllTech(p.tech)
                        .setLink(p.link)
                        .setYear(p.year)
                        .build()
        ));

        return b.build();
    }

    public static CvEntity fromProto(CV proto) {
        CvEntity e = new CvEntity();
        e.id = proto.getId();
        e.defaultLang = proto.getDefaultLang();
        e.isActive = proto.getIsActive();
        return e;
    }

    public static void updateEntity(CvEntity e, CV incoming) {
        e.defaultLang = incoming.getDefaultLang();
        e.isActive = incoming.getIsActive();
        e.updatedAt = Instant.now();
    }

    public static Timestamp toTimestamp(Instant instant) {
        return Timestamp.newBuilder()
                .setSeconds(instant.getEpochSecond())
                .setNanos(instant.getNano())
                .build();
    }
}
