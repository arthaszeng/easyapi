package com.arthaszeng.easyapi.service.sourceService;

import com.arthaszeng.easyapi.entity.Source;

import java.util.List;

public interface SourceService {

    Source findSourceBySourceId(Long sourceId);

    Source addSource(Source source);
}
