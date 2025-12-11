package com.htv.domain.repository;

import com.htv.domain.entity.CvEntity;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CvRepository implements PanacheRepository<CvEntity> {

}
