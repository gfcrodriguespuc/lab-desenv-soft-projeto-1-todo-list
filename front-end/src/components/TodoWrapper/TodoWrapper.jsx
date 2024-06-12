import styles from "./TodoWrapper.module.css";

import { useEffect, useState } from "react";
import { v4 as uuidv4 } from "uuid";
import * as todoApi from "../../services/api";
import { TodoForm } from "../TodoForm";
import { TodoList } from "../TodoList";

export const TodoWrapper = () => {
  const [todos, setTodos] = useState([]);

  const refreshTodos = () => {
    const savedTodos = todoApi.getAllTodos();
    setTodos(savedTodos);
  };

  const addTodo = (description) => {
    const newTodo = {
      id: uuidv4(),
      description,
      completed: false,
    };
    todoApi.saveTodo(newTodo);
    refreshTodos();
  };

  const onClickCompleteTodo = (id) => {
    // todoApi.completeTodoById(id);

    const todoToUpdate = todos.find((todo) => todo.id === id);
    if (!todoToUpdate) {
      return;
    }

    const todoUpdated = {
      ...todoToUpdate,
      completed: !todoToUpdate.completed,
    };
    todoApi.updateTodoById(id, todoUpdated);
    refreshTodos();
  };

  const onClickDeleteTodo = (id) => {
    todoApi.deleteTodoById(id);
    refreshTodos();
  };

  useEffect(() => {
    refreshTodos();
  }, []);

  return (
    <article className={styles["todo-wrapper"]}>
      <h1 className={styles["todo-wrapper__title"]}>Lista de Tarefas</h1>
      <div className={styles["todo-wrapper__form"]}>
        <TodoForm addTodo={addTodo} />
      </div>
      <TodoList
        todos={todos}
        onClickDeleteTodo={onClickDeleteTodo}
        onClickCompleteTodo={onClickCompleteTodo}
      />
    </article>
  );
};

export default TodoWrapper;
