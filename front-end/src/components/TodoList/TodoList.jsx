import stylesButton from "../../styles/button.module.css";
import styles from "./TodoList.module.css";

import { faTrash } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { clsx } from "clsx";

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
            stylesButton["button"],
            stylesButton["button--danger"],
            stylesButton["button--icon"],
            styles["todo-list__button"]
          )}
        >
          <FontAwesomeIcon icon={faTrash} />
        </button>
      </li>
    </ul>
  );
};

export default TodoList;
