package org.interstellar.urlanalytics.dao;

import org.interstellar.urlanalytics.entity.UrlEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlEventEntityRepository extends JpaRepository<UrlEventEntity, Long> {
}