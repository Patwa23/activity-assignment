package com.tacx.assignment.controller;

import com.tacx.assignment.services.ActivityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Activity Controller Unit Test")
class ActivityControllerTest {

    private static final String URL = "http://host:port/api/v1/records/upload";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActivityService activityService;

    @InjectMocks
    private ActivityController activityController;

    @Test
    @DisplayName("Upload file - Unit Test")
    public void testUploadFile() throws Exception {

    }

}