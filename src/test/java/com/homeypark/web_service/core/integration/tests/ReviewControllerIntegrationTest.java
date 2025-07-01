package com.homeypark.web_service.core.integration.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.homeypark.web_service.reservations.interfaces.rest.resources.CreateReviewResource;
import com.homeypark.web_service.reservations.interfaces.rest.resources.UpdateReviewResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ReviewControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetReviewByIdShouldReturn404WhenReviewNotFound() throws Exception {
        mockMvc.perform(get("/reviews/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetReviewsByHostIdShouldReturnEmptyListWhenNoReviews() throws Exception {
        mockMvc.perform(get("/reviews/host/999"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    public void testGetReviewsByGuestIdShouldReturnEmptyListWhenNoReviews() throws Exception {
        mockMvc.perform(get("/reviews/guest/999"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    public void testCreateReviewShouldReturn400WhenInvalidData() throws Exception {
        CreateReviewResource invalidReview = new CreateReviewResource(
                null, // missing reservationId
                5,
                "Great experience!",
                1L,
                null,
                null,
                2L
        );

        mockMvc.perform(post("/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidReview)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testUpdateReviewShouldReturn404WhenReviewNotFound() throws Exception {
        UpdateReviewResource updateReview = new UpdateReviewResource(
                4,
                "Updated comment"
        );

        mockMvc.perform(put("/reviews/999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateReview)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteReviewShouldReturn404WhenReviewNotFound() throws Exception {
        mockMvc.perform(delete("/reviews/999"))
                .andExpect(status().isNotFound());
    }
}
