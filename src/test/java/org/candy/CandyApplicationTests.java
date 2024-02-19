package org.candy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = CandyApplication.class)
class CandyApplicationTests {

    @Test
    void contextLoads() {
    }

}
