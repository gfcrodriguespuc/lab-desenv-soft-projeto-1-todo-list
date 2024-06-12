import buttonStyles from "../../styles/button.module.css";
import inputStyles from "../../styles/input.module.css";
import styles from "./TodoForm.module.css";

import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { clsx } from "clsx";
import { useState } from "react";

export const TodoForm = ({ addTodo }) => {
  const [todoDescription, setTodoDescription] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();

    const todoDescriptionTrim = todoDescription.trim();
    if (!todoDescriptionTrim) {
      return;
    }

    addTodo(todoDescriptionTrim);
    setTodoDescription("");
  };

  return (
    <form className={styles["todo-form"]} onSubmit={handleSubmit}>
      <input
        className={clsx(
          inputStyles["input-text"],
          inputStyles["input-text--primary"],
          inputStyles["input-text--full"]
        )}
        type="text"
        placeholder="Qual sua nova tarefa?"
        value={todoDescription}
        onChange={(event) => setTodoDescription(event.target.value)}
      />
      <button
        className={clsx(
          buttonStyles["button"],
          buttonStyles["button--primary"],
          buttonStyles["button--icon"]
        )}
        type="submit"
      >
        <FontAwesomeIcon icon={faPlus} />
      </button>
    </form>
  );
};

export default TodoForm;
