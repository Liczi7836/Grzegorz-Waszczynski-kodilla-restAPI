/*
package com.crud.tasks.trello.task;

import com.crud.tasks.controller.TaskController;
import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TaskTestSuit {

    @InjectMocks
    private TaskMapper taskMapper;

    @Mock
    private TaskController taskController;

    @Mock
    private DbService dbService;

    @Test
    public void generateMoreMethodCoverageWithTasks(){
        //Given
        Task task = new Task(1L, "First task", "First task description");

        //When
        TaskDto taskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(task.getId(), taskDto.getId());
        assertEquals(task.getTitle(), taskDto.getTitle());
        assertEquals(task.getContent(), taskDto.getContent());
    }

    @Test
    public void generateMoreMethodCoverageWithTasksDto(){
        //Given
        TaskDto taskDto = new TaskDto(2L, "Second task", "Second task description");

        //When
        Task task = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(taskDto.getId(), task.getId());
        assertEquals(taskDto.getTitle(), task.getTitle());
        assertEquals(taskDto.getContent(), task.getContent());
    }

    @Test
    public void saveTaskAndGetByIdTest() throws TaskNotFoundException {
        //Given
        TaskDto taskDto = new TaskDto(1L, "First task", "First task description");
        taskController.createTask(taskDto);

        //When
        ResponseEntity<TaskDto> anotherTaskDto = taskController.getTask(taskDto.getId());

        //Then
        assertEquals(taskDto.getTitle(), anotherTaskDto.getBody().getTitle());

    }



}
*/
