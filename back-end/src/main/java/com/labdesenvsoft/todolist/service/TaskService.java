package com.labdesenvsoft.todolist.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.labdesenvsoft.todolist.domain.entity.Task;
import com.labdesenvsoft.todolist.domain.exception.TaskNotFoundException;
import com.labdesenvsoft.todolist.repository.ITaskRepository;

@Service
public class TaskService {

    @Autowired
    ITaskRepository taskRepository;

    public Collection<Task> getAllTasks() {
        List<Task> tasks = new LinkedList<>();
        taskRepository.findAll().forEach(tasks::add);
        return tasks;
    }

    public long createTask(Task taskToCreate) {
        Task task = taskRepository.save(taskToCreate);
        return task.getId();
    }

    public Task getTaskById(long id) throws TaskNotFoundException {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new TaskNotFoundException("Tarefa não encontrada");
        }

        return optionalTask.get();
    }

    public void updateTask(long id, Task task) throws TaskNotFoundException {

        Task taskToUpdate = getTaskById(id);
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setCompleted(task.isCompleted());

        taskRepository.save(taskToUpdate);
    }

    public void deleteTask(long id) throws TaskNotFoundException {
        Task task = getTaskById(id);

        taskRepository.delete(task);
    }

}
