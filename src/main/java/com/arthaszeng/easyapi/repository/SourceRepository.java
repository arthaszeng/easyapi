package com.arthaszeng.easyapi.repository;

import com.arthaszeng.easyapi.entity.Category;
import com.arthaszeng.easyapi.entity.Source;
import com.arthaszeng.easyapi.exception.DatabaseException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SourceRepository extends CrudRepository<Source, Long> {
}
