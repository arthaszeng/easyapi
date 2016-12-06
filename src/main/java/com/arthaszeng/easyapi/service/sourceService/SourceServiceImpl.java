package com.arthaszeng.easyapi.service.sourceService;

import com.arthaszeng.easyapi.entity.Source;
import com.arthaszeng.easyapi.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SourceServiceImpl implements SourceService {
    @Autowired
    SourceRepository sourceRepository;

    @Override
    public Source findSourceBySourceId(Long sourceId) {
        return sourceRepository.findBySourceId(sourceId);
    }
}
