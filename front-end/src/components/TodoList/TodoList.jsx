import stylesButton from "../../styles/button.module.css";
import styles from "./TodoList.module.css";

import { faTrash } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { clsx } from "clsx";

export const TodoList = ({ todos, onClickDeleteTodo }) => {
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
            styles["todo-list__item--completed"]
          )}
        >
          <span>{todo.description}</span>
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
