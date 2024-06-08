import styles from "./TodoList.module.css";

import { clsx } from "clsx";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faTrash } from "@fortawesome/free-solid-svg-icons";

export const TodoList = () => {
  return (
    <ul className={styles["todo-list"]}>
      <li
        className={clsx(
          styles["todo-list__item"],
          styles["todo-list__item--completed"]
        )}
      >
        <span>Tarefa 1</span>
        <button
          className={clsx(
            styles["todo-list__icon-button"],
            styles["todo-list__icon-button--delete"]
          )}
        >
          <FontAwesomeIcon icon={faTrash} />
        </button>
      </li>
    </ul>
  );
};

export default TodoList;
