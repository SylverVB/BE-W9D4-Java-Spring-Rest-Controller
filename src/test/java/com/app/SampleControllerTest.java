package com.app;

import com.app.Controller.SampleController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Unit tests for {@link SampleController}.
 */
@ExtendWith({MockitoExtension.class, SpringExtension.class})
@WebMvcTest(SampleController.class)
public class SampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Test the GET endpoint with a path variable.
     *
     * @throws Exception if a request processing error occurs
     */
    @Test
    public void testGetPathVariable() throws Exception {
        long expectedPathVariable = 1;
        String url = "/long/{id}";

        mockMvc.perform(MockMvcRequestBuilders.get(url, expectedPathVariable))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedPathVariable)));
    }

    /**
     * Test the POST endpoint with a Sample request body.
     *
     * @throws Exception if a request processing error occurs
     */
    @Test
    public void testPostSample() throws Exception {
        String requestBody = "{\"id\": 3, \"text\": \"This is a sample text\"}";

        String url = "/requestbody";

        mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.ALL)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("{\"id\": 3, \"text\": \"This is a sample text\"}"));
    }
}