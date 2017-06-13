package com.arthaszeng.easyapi.unit.controller;

import com.arthaszeng.easyapi.controller.SourceController;
import com.arthaszeng.easyapi.entity.Source;
import com.arthaszeng.easyapi.service.sourceService.SourceService;
import org.junit.Before;
import org.junit.Test;

import static com.arthaszeng.easyapi.unit.utils.POJOGenerator.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class SourceControllerTest {
    private SourceController sourceController;
    private SourceService sourceService;

    @Before
    public void setUp() throws Exception {
        sourceController = new SourceController();
        sourceService = mock(SourceService.class);

        setField(sourceController, "sourceService", sourceService);
    }

    @Test
    public void shouldInvokeServiceToQuerySourceWhenIdIsValid() throws Exception {
        sourceController.querySource(VALID_SOURCE_ID);

        verify(sourceService, times(1)).findSourceBySourceId(VALID_SOURCE_ID);
    }

    @Test
    public void shouldNotInvokeServiceToQuerySourceWhenIdIsInvalid() throws Exception {
        sourceController.querySource(INVALID_SOURCE_ID);

        verify(sourceService, times(0)).findSourceBySourceId(any());
    }

    @Test
    public void shouldInvokeServiceToAddSourceWhenParamsAreValid() throws Exception {
        sourceController.addSource(valideSource());

        verify(sourceService, times(1)).addSource(any(Source.class));
    }

    @Test
    public void shouldNotInvokeServiceToAddSourceWhenParamsAreInvalid() throws Exception {
        sourceController.addSource(invalidSource());

        verify(sourceService, times(0)).addSource(any(Source.class));
    }
}