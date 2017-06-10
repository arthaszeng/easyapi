package com.arthaszeng.easyapi.controller;

import com.arthaszeng.easyapi.entity.Source;
import com.arthaszeng.easyapi.exception.DatabaseException;
import com.arthaszeng.easyapi.service.sourceService.SourceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SourceController {
    @Autowired
    private SourceService sourceService;

    @RequestMapping("/source/{sourceId}")
    @ApiOperation(notes = "Get source Details Via Querying Source ID", value = "Source ID", httpMethod = "GET")
    public Source querySource(@PathVariable @ApiParam Long sourceId) {
        return sourceService.findSourceBySourceId(sourceId);
    }

    @RequestMapping("/source/add")
    @ApiOperation(value = "Source", notes = "Add Source", httpMethod = "POST", protocols = "app")
    public Source addSource(
            @RequestParam(name = "code") @ApiParam String code,
            @RequestParam(name = "description") @ApiParam String description) throws DatabaseException {

        Source source = null;

        try {
            source = new Source(code, description);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sourceService.addSource(source);
    }
}
