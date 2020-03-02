package com.worldpay.offerprocessor.controllers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * IOfferControllerTests
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IOfferControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private String stubJsonBody = "{\"price\": \"USD 12.23\", \"description\": \"For 1 billion rupees\", \"expiry_date\": \"2018-05-05T11:50:55\"}";

    @Test
    public void testCreateOffer_WithValidAttributes() throws Exception {

        // create offer
        this.mockMvc.perform(post("/offer").contentType(MediaType.APPLICATION_JSON_VALUE).content(stubJsonBody))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void testCreateOffer_WithInvalidDate() throws Exception {

        final String invalidJsonBody = "{\"price\": \"USD 12.23\", \"description\": \"For 1 billion rupees\", \"expiry_date\": \"THIS IS AN INVALID DATE\"}";

        // create offer
        this.mockMvc.perform(post("/offer").contentType(MediaType.APPLICATION_JSON_VALUE).content(invalidJsonBody))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testCreateOffer_WithInvalidPrice() throws Exception {

        final String invalidJsonBody = "{\"price\": \"THIS PRICE IS NOT VALID\", \"description\": \"For 1 billion rupees\", \"expiry_date\": \"2018-05-05T11:50:55\"}";

        // create offer
        this.mockMvc.perform(post("/offer").contentType(MediaType.APPLICATION_JSON_VALUE).content(invalidJsonBody))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testCreateOffer_WithInvalidDescription() throws Exception {

        final String invalidJsonBody = "{\"price\": \"USD 31\", \"expiry_date\": \"2018-05-05T11:50:55\"}";

        // create offer
        this.mockMvc.perform(post("/offer").contentType(MediaType.APPLICATION_JSON_VALUE).content(invalidJsonBody))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void testGetOffer_WhenNotExists() throws Exception {

        this.mockMvc.perform(get("/offer/999")).andDo(print()).andExpect(status().isNotFound());

    }

    @Test
    public void testCreate_ThenRetrieveOffer() throws Exception {

        // create offer
        MvcResult result = this.mockMvc
                .perform(post("/offer").contentType(MediaType.APPLICATION_JSON_VALUE).content(stubJsonBody))
                .andExpect(status().is2xxSuccessful()).andExpect(content().string("1")).andReturn();

        String id = result.getResponse().getContentAsString();
        final String expectedResponse = "{\"id\":" + id
                + ",\"price\":\"USD 12.23\",\"cancelled\":false,\"description\":\"For 1 billion rupees\",\"expiry_date\":\"2018-05-05T11:50:55\"}";

        // Retrieve previously created offer
        this.mockMvc.perform(get("/offer/" + id)).andDo(print()).andExpect(status().is2xxSuccessful())
                .andExpect(content().json(expectedResponse));

    }

    @Test
    public void testCancelOffer_WhenNotExists() throws Exception {

        // attempt to cancel offer that doens't exist
        this.mockMvc.perform(patch("/offer/999/cancel")).andDo(print()).andExpect(status().isNotFound());

    }

    @Test
    public void testCancelOffer_WhenExists() throws Exception {

        // create offer
        MvcResult createOfferResult = this.mockMvc
                .perform(post("/offer").contentType(MediaType.APPLICATION_JSON_VALUE).content(stubJsonBody))
                .andExpect(status().is2xxSuccessful()).andReturn();

        String id = createOfferResult.getResponse().getContentAsString();

        // mark as cancelled
        this.mockMvc.perform(patch("/offer/" + id + "/cancel")).andDo(print()).andExpect(status().isOk());

        // Get the off offer from API 
        MvcResult cancelOfferResult = this.mockMvc.perform(get("/offer/" + id)).andDo(print())
                .andExpect(status().is2xxSuccessful()).andReturn();

        String response = cancelOfferResult.getResponse().getContentAsString();

        JsonElement jsonElem = new JsonParser().parse(response);
        JsonObject actualResponse = jsonElem.getAsJsonObject();

        // Check cancelled field is updated
        assertTrue(actualResponse.get("cancelled").getAsBoolean());

    }

}