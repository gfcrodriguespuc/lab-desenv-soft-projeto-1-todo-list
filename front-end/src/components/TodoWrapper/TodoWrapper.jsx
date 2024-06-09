import styles from "./TodoWrapper.module.css";

import { TodoForm } from "../TodoForm";
import { TodoList } from "../TodoList";

export const TodoWrapper = () => {
  return (
    <article className={styles["todo-wrapper"]}>
      <h1 className={styles["todo-wrapper__title"]}>Lista de Tarefas</h1>
      <div className={styles["todo-wrapper__form"]}>
        <TodoForm />
      </div>
      <TodoList />
    </article>
  );
};

export default TodoWrapper;
