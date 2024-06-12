import styles from "./TodoWrapper.module.css";

import { useState } from "react";
import { v4 as uuidv4 } from "uuid";
import { TodoForm } from "../TodoForm";
import { TodoList } from "../TodoList";

export const TodoWrapper = () => {
  const [todos, setTodos] = useState([
    { id: 1, description: "Estudar React", completed: false },
    { id: 2, description: "Estudar Node", completed: false },
    { id: 3, description: "Estudar CSS", completed: true },
  ]);

  const addTodo = (description) => {
    setTodos((prevTodos) => [
      ...prevTodos,
      {
        id: uuidv4(),
        description,
        completed: false,
      },
    ]);
  };

  return (
    <article className={styles["todo-wrapper"]}>
      <h1 className={styles["todo-wrapper__title"]}>Lista de Tarefas</h1>
      <div className={styles["todo-wrapper__form"]}>
        <TodoForm addTodo={addTodo} />
      </div>
      <TodoList todos={todos} />
    </article>
  );
};

export default TodoWrapper;
