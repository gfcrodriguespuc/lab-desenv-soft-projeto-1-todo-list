import stylesButton from "../../styles/button.module.css";
import styles from "./TodoList.module.css";

import { faCheck, faTrash } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { clsx } from "clsx";

export const TodoList = ({ todos, onClickDeleteTodo, onClickCompleteTodo }) => {
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
          <span className={styles["todo-list__description"]}>
            {todo.description}
          </span>
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
