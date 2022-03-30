package com.crud.tasks.controller;

import java.util.List;

import com.crud.tasks.domain.Task;
import com.crud.tasks.service.SimpleEmailService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@AutoConfigureMockMvc
public class TrelloControllerIT {

    private static final Gson GSON = new Gson();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SimpleEmailService emailService;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void shouldGetAllTasks() throws Exception {
        //Given
        List<Task> specialTasks = List.of(new Task(1L, "First task", "First task content"));
        String jsonContent = GSON.toJson(specialTasks);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/trello/boards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

}
