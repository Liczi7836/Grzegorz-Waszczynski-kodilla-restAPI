package com.crud.tasks.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private DbService dbService;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskController taskController;

    @Test
    void getTasks() {
        //Given
        when(dbService.getAllTasks()).thenReturn(List.of());

        //When
        taskController.getTasks();

        //Then
        verify(dbService, times(1)).getAllTasks();
        verify(taskMapper, times(1)).mapToTaskDtoList(List.of());
    }
}