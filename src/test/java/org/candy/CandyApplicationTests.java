package org.candy;

import lombok.extern.slf4j.Slf4j;
import org.candy.controller.CandyController;
import org.candy.service.CandyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@Slf4j
class CandyApplicationTests {

    @Autowired
    private CandyController CandyController;

    @Autowired
    private CandyService myService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(CandyController).isNotNull();
        assertThat(myService).isNotNull();
    }

}
