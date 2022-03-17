package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskController taskController;


    @Test
    public void shouldGetEmptyTaskList() throws Exception {
        //Given
        when(taskController.getTasks()).thenReturn(ResponseEntity.of(Optional.of(List.of())));

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("http://test.com/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void shouldGetAllTasks() throws Exception {
        //Given
        List<Task> specialTasks = List.of(new Task(1L, "First task", "First task content"));
        List<TaskDto> specialDtoTasks = List.of(new TaskDto(2L, "Second task", "Second task content"));

        when(taskController.getTasks()).thenReturn(ResponseEntity.of(Optional.of(specialDtoTasks)));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(specialTasks);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("http://test.com/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("Second task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].content", Matchers.is("Second task content")));
    }

    @Test
    public void shouldGetOneTask() throws Exception {
        //Given
        TaskDto firstTask = new TaskDto(1L, "First task", "First task content");

        when(taskController.getTask(firstTask.getId())).thenReturn(ResponseEntity.of(Optional.of(firstTask)));

        Gson gson = new Gson();
        String jsonContent = gson.toJson(firstTask);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("http://test.com/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("First task")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.is("First task content")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        //Given
        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete("http://test.com/v1/tasks/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(taskController, times(1)).deleteTask(1L);
    }

    @Test
    public void shouldCreateTask() throws Exception{
        //Given
        TaskDto testTask = new TaskDto(1L, "First task", "First task content");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(testTask);
        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("http://test.com/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(taskController, times(1)).createTask(testTask);
    }




}