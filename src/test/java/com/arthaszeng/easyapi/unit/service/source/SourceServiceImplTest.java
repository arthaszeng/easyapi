package com.arthaszeng.easyapi.unit.service.source;

import com.arthaszeng.easyapi.entity.Source;
import com.arthaszeng.easyapi.service.sourceService.SourceService;
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

    @Test
    public void shouldGetASourceById() throws Exception {
        Source source = sourceService.findSourceBySourceId(1L);

        assertThat(source.getsourceId(), is(1L));
        assertThat(source.getCode(), is("SOURCE_CODE_1"));
        assertThat(source.getDescription(), is("SRC_CODE_DSC_1"));
    }

    @Test
    public void shouldAddASource() throws Exception {
        Source source = new Source("test", "test");

        sourceService.addSource(source);

        Source insertedSource = sourceService.findSourceBySourceId(source.getsourceId());

        assertThat(insertedSource.equals(source), is(true));
    }

}