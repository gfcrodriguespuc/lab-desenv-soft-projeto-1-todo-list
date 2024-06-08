import buttonStyles from "../../styles/button.module.css";
import inputStyles from "../../styles/input.module.css";
import styles from "./TodoForm.module.css";

import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { clsx } from "clsx";

export const TodoForm = () => {
  return (
    <form className={styles["todo-form"]}>
      <input
        className={clsx(
          inputStyles["input-text"],
          inputStyles["input-text--primary"],
          inputStyles["input-text--full"]
        )}
        type="text"
        placeholder="Qual sua nova tarefa?"
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
