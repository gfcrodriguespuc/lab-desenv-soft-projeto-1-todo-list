import stylesButton from "../../styles/button.module.css";
import styles from "./TodoList.module.css";

import { faCheck, faTrash } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { clsx } from "clsx";
import { TodoStatus, TodoType } from "../../models/todo";

export const TodoList = ({ todos, onClickDeleteTodo, onClickCompleteTodo }) => {
  const formatTodoStatus = (todo) => {
    // Prevista, X dias de atraso ou Concluída
    // Prevista ou Concluída

    if (todo.status.state === TodoStatus.COMPLETED) {
      return "Concluída";
    }

    if (todo.type === TodoType.DUE_DATE) {
      const diffDays = todo.status.days;
      if (todo.status.state === TodoStatus.LATE) {
        return diffDays === 1
          ? `${diffDays} dia de atraso`
          : `${diffDays} dias de atraso`;
      }

      return diffDays === 0
        ? "Prevista para hoje"
        : diffDays === 1
        ? "Prevista para amanhã"
        : `Prevista para ${todo.status.dueDate.toLocaleDateString()}`;
    }

    if (todo.type === TodoType.DEADLINE) {
      const diffDays = todo.status.days;
      if (todo.status.state === TodoStatus.LATE) {
        return diffDays === 1
          ? `${diffDays} dia de atraso`
          : `${diffDays} dias de atraso`;
      }

      return diffDays === 0
        ? "Prevista para hoje"
        : diffDays === 1
        ? `${diffDays} dia restante`
        : `${diffDays} dias restantes`;
    }

    return "Prevista";
  };

  const handleClickCompleteTodo = (id) => {
    onClickCompleteTodo(id);
  };

  const handleClickDeleteTodo = (id) => {
    onClickDeleteTodo(id);
  };

  return (
    <ul className={styles["todo-list"]}>
      {todos.map((todo) => (
        <li
          key={todo.id}
          className={clsx(
            styles["todo-list__item"],
            todo.completed && styles["todo-list__item--completed"]
          )}
        >
          <div>
            <span className={styles["todo-list__description"]}>
              {todo.description}
            </span>
            <small>{formatTodoStatus(todo)}</small>
          </div>
          <button
            className={clsx(
              stylesButton["button"],
              stylesButton["button--primary"],
              stylesButton["button--icon"],
              styles["todo-list__button"]
            )}
            disabled={todo.completed}
            onClick={() => handleClickCompleteTodo(todo.id)}
          >
            <FontAwesomeIcon icon={faCheck} />
          </button>
          <button
            className={clsx(
              stylesButton["button"],
              stylesButton["button--danger"],
              stylesButton["button--icon"],
              styles["todo-list__button"]
            )}
            onClick={() => handleClickDeleteTodo(todo.id)}
          >
            <FontAwesomeIcon icon={faTrash} />
          </button>
        </li>
      ))}
    </ul>
  );
};

export default TodoList;
