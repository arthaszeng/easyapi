package com.arthaszeng.easyapi.repository;

import com.arthaszeng.easyapi.entity.Source;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SourceRepository extends CrudRepository<Source, Long> {

    Source findBySourceId(Long sourceId);

    Source save(Source source);
}
