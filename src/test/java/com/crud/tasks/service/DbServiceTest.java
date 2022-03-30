package com.crud.tasks.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DbServiceTest {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private DbService dbService;

    @Test
    void getAllTasks() {
        //Given
        List<Task> tasks = List.of(new Task());
        when(repository.findAll()).thenReturn(tasks);

        //When
        List<Task> allTasks = dbService.getAllTasks();

        //Then
        assertEquals(tasks, allTasks);
    }

    @Test
    void getTaskBySuccessPath() throws TaskNotFoundException {
        //Given
        long id = 1L;
        Task task = new Task();
        when(repository.findById(id)).thenReturn(Optional.of(task));

        //When
        Task response = dbService.getTaskBy(id);

        //Then
        assertEquals(task, response);
    }

    @Test
    void getTaskByFailurePath() {
        //Given
        long id = 1L;
        given(repository.findById(id)).willAnswer(invocation -> {
            throw new TaskNotFoundException();
        });

        //When & Then
        assertThrows(TaskNotFoundException.class, () -> dbService.getTaskBy(id));
    }

    @Test
    void saveTask() {
        //Given
        Task task = new Task();
        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);

        //When
        dbService.saveTask(task);

        //Then
        verify(repository, times(1)).save(captor.capture());
        Task value = captor.getValue();
        assertEquals(task, value);
    }

    @Test
    void deleteTask() {
        //Given
        long id = 1L;

        //When
        dbService.deleteTask(id);

        //Then
        verify(repository, times(1)).deleteById(id);
    }
}