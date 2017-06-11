package com.arthaszeng.easyapi.unit.service.source;

import com.arthaszeng.easyapi.entity.Source;
import com.arthaszeng.easyapi.service.sourceService.SourceService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
@Rollback
public class SourceServiceImplTest {
    @Autowired
    private SourceService sourceService;

    private Source source;

    @Before
    public void setUp() throws Exception {
        source = new Source("TEST", "TEST");
        sourceService.addSource(source);
    }

    @Test
    public void shouldGetASourceById() throws Exception {
        Source result = sourceService.findSourceBySourceId(source.getSourceId());

        assertThat(result.equals(source), is(true));
    }

    @Test
    public void shouldAddASource() throws Exception {
        Source source = new Source("TEST", "TEST");

        sourceService.addSource(source);

        Source insertedSource = sourceService.findSourceBySourceId(source.getSourceId());

        assertThat(insertedSource.equals(source), is(true));
    }

}