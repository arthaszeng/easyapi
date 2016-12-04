package com.arthaszeng.easyapi.repository;

import com.arthaszeng.easyapi.entity.Source;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends CrudRepository<Source, String> {

    Source findBySourceId(String sourceId);
}
