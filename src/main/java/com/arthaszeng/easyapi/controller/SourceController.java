package com.arthaszeng.easyapi.controller;

import com.arthaszeng.easyapi.entity.Source;
import com.arthaszeng.easyapi.exception.DatabaseException;
import com.arthaszeng.easyapi.service.sourceService.SourceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/sources")
public class SourceController {
    @Autowired
    private SourceService sourceService;

    @RequestMapping("/{sourceId}")
    @ApiOperation(notes = "Get source Details Via Querying Source ID", value = "Source ID", httpMethod = "GET")
    public ResponseEntity<Source> querySource(@PathVariable @ApiParam Long sourceId) {
        if (validateQueryParams(sourceId)) {
            Source source = sourceService.findSourceBySourceId(sourceId);
            return new ResponseEntity<>(source, OK);
        } else {
            return null;
        }
    }

    @RequestMapping("/source")
    @ApiOperation(value = "Source", notes = "Add Source", httpMethod = "POST", protocols = "app")
    public ResponseEntity<Source> addSource(
            @RequestParam(name = "code") @ApiParam String code,
            @RequestParam(name = "description") @ApiParam String description) throws DatabaseException {

        if (validateAddParams(code, description)) {
            Source source = null;

            try {
                source = new Source(code, description);
            } catch (Exception e) {
                e.printStackTrace();
            }

            Source insertedSource = sourceService.addSource(source);
            return new ResponseEntity<>(insertedSource, OK);
        } else {
            return null;
        }
    }

    private boolean validateAddParams(String description, String code) {
        return description != null && code != null;
    }


    private boolean validateQueryParams(Long id) {
        return id > 0L;
    }
}
