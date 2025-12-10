package com.htv.model.repository;

import com.htv.model.entity.CvEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CvRepository implements PanacheRepository<CvEntity> {
    public Uni<CvEntity> findByIdWithTranslations(long id) {
        return find("FROM CVEntity c LEFT JOIN FETCH c.translations t LEFT JOIN FETCH t.experiences "
                + "LEFT JOIN FETCH t.educations LEFT JOIN FETCH t.skills "
                + "LEFT JOIN FETCH t.languages LEFT JOIN FETCH t.projects WHERE c.id=?1", id)
                .firstResult();
    }
}
