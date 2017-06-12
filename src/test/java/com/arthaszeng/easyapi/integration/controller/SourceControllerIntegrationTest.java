package com.arthaszeng.easyapi.integration.controller;

import com.arthaszeng.easyapi.controller.SourceController;
import com.arthaszeng.easyapi.entity.Source;
import com.arthaszeng.easyapi.integration.BaseIntegrationTest;
import com.arthaszeng.easyapi.repository.SourceRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static java.lang.String.format;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SourceControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private SourceController sourceController;

    @Autowired
    private SourceRepository sourceRepository;

    private MockMvc mockMvc;
    private String DESCRIPTION = "TEST";
    private String CODE = "TEST";


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(sourceController).build();
    }

    @Test
    public void shouldQuerySourceBySourceId() throws Exception {
        Source source = sourceRepository.save(new Source(CODE, DESCRIPTION));

        mockMvc.perform(get(format("http://localhost:8081/sources/%d", source.getSourceId())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value(CODE))
                .andExpect(jsonPath("description").value(DESCRIPTION));
    }

    @Test
    public void shouldAddSource() throws Exception {
        mockMvc.perform(post(format("http://localhost:8081/sources/source?code=%s&description=%s", CODE, DESCRIPTION)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("code").value(CODE))
                .andExpect(jsonPath("description").value(DESCRIPTION));
    }
}
