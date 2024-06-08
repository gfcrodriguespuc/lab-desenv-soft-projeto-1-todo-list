import styles from "./TodoForm.module.css";

export const TodoForm = () => {
  return (
    <form className={styles["todo-form"]}>
      <input
        className={styles["todo-form__input"]}
        type="text"
        placeholder="Qual sua nova tarefa?"
      />
      <button className={styles["todo-form__button"]} type="submit">
        +
      </button>
    </form>
  );
};

export default TodoForm;
