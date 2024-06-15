package com.labdesenvsoft.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labdesenvsoft.todolist.entity.Task;

public interface ITaskRepository extends JpaRepository<Task, Long> {

}
