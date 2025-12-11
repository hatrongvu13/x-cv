package com.htv.model.repository;

import com.htv.model.entity.CvEntity;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CvRepository implements PanacheRepository<CvEntity> {

}
