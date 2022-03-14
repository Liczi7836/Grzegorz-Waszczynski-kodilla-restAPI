package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldGetEmptyTaskList() throws Exception {
        //Given
        when(dbService.getAllTasks()).thenReturn(List.of());

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void shouldGetAllTasks() throws Exception {
        //Given
        List<Task> specialTasks = List.of(new Task(1L, "First task", "First task content"));

        when(dbService.getAllTasks()).thenReturn(specialTasks);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1L)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("First task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("First task content")));
    }

    @Test
    public void shouldGetOneTask() throws Exception {
        //Given
        Task firstTask = new Task(1L, "First task", "First task content");

        when(dbService.getTaskBy(1L)).thenReturn(firstTask);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1L)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("First task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("First task content")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        Task firstTask = new Task(1L, "First task", "First task content");

        doNothing().when(dbService).deleteTask(1L);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.nullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.nullValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.nullValue()));
    }

    @Test
    public void shouldCreateTask() throws Exception{
        Task task = new Task(1L, "First task", "First task content");

        doNothing().doThrow().when(dbService).saveTask(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1L)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("First task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("First task content")));
    }




}