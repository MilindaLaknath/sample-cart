package com.milinda.assessment;

import com.milinda.assessment.controller.ItemController;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AssessmentApplicationTests {
    @Autowired
    private ItemController controller;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

}
