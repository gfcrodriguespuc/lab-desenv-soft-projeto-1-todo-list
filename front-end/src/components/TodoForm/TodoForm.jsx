import buttonStyles from "../../styles/button.module.css";
import inputStyles from "../../styles/input.module.css";
import styles from "./TodoForm.module.css";

import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { clsx } from "clsx";
import { useState } from "react";
import { TodoPriority, TodoType } from "../../models/todo";

export const TodoForm = ({ addTodo }) => {
  const [todoDescription, setTodoDescription] = useState("");
  const [todoType, setTodoType] = useState(TodoType.FREE);
  const [todoPriority, setTodoPriority] = useState(TodoPriority.LOW);
  const [todoDueDate, setTodoDueDate] = useState("");
  const [todoDeadline, setTodoDeadline] = useState("");

  const handleTypeChange = (event) => {
    console.debug("handleTypeChange", event.target.value);
    setTodoType(event.target.value);
  };

  const handlePriorityChange = (event) => {
    console.debug("handlePriorityChange", event.target.value);
    setTodoPriority(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    const todoDescriptionTrim = todoDescription.trim();
    if (!todoDescriptionTrim) {
      return;
    }

    addTodo({
      type: todoType,
      priority: todoPriority,
      dueDate: todoType === TodoType.DUE_DATE ? todoDueDate : null,
      deadline: todoType === TodoType.DEADLINE ? +todoDeadline : null,
      description: todoDescriptionTrim,
    });
    setTodoDescription("");
  };

  return (
    <form className={styles["todo-form"]} onSubmit={handleSubmit}>
      {/*
        Tipos possiveis para a tarefa:
        A ToDo List deverá possuir três tipos de tarefas:
          o 1. Data (com data prevista de conclusão)
          o 2. Prazo (com prazo previsto de conclusão informado em dias)
          o 3. Livre (Sem data ou prazo previsto de conclusão)
        As tarefas deverão ter três níveis de prioridades:
          o 1. Alta
          o 2. Média
          o 3. Baixa
      */}
      <div className={clsx(styles["todo-form__radio-group-wrapper"])}>
        <b>Tipo de tarefa</b>

        <div className={clsx(styles["todo-form__radio-group"])}>
          <label>
            <input
              type="radio"
              value={TodoType.DUE_DATE}
              onChange={handleTypeChange}
              checked={todoType === TodoType.DUE_DATE}
            />{" "}
            Data prevista
          </label>
          <label>
            <input
              type="radio"
              value={TodoType.DEADLINE}
              onChange={handleTypeChange}
              checked={todoType === TodoType.DEADLINE}
            />{" "}
            Prazo previsto
          </label>
          <label>
            <input
              type="radio"
              value={TodoType.FREE}
              onChange={handleTypeChange}
              checked={todoType === TodoType.FREE}
            />{" "}
            Livre
          </label>
        </div>
      </div>

      <div>
        {todoType === TodoType.DUE_DATE && (
          <input
            className={clsx(
              inputStyles["input-text"],
              inputStyles["input-text--primary"],
              inputStyles["input-text--full"]
            )}
            type="date"
            min={new Date().toISOString().split("T")[0]}
            value={todoDueDate}
            onChange={(event) => setTodoDueDate(event.target.value)}
          />
        )}

        {todoType === TodoType.DEADLINE && (
          <input
            className={clsx(
              inputStyles["input-text"],
              inputStyles["input-text--primary"],
              inputStyles["input-text--full"]
            )}
            type="number"
            min="1"
            placeholder="Prazo em dias"
            value={todoDeadline}
            onChange={(event) => setTodoDeadline(event.target.value)}
          />
        )}
      </div>

      <div className={clsx(styles["todo-form__radio-group-wrapper"])}>
        <b>Prioridade</b>

        <div className={clsx(styles["todo-form__radio-group"])}>
          <label>
            <input
              type="radio"
              value={TodoPriority.LOW}
              onChange={handlePriorityChange}
              checked={todoPriority === TodoPriority.LOW}
            />{" "}
            Baixa
          </label>
          <label>
            <input
              type="radio"
              value={TodoPriority.MEDIUM}
              onChange={handlePriorityChange}
              checked={todoPriority === TodoPriority.MEDIUM}
            />{" "}
            Média
          </label>
          <label>
            <input
              type="radio"
              value={TodoPriority.HIGH}
              onChange={handlePriorityChange}
              checked={todoPriority === TodoPriority.HIGH}
            />{" "}
            Alta
          </label>
        </div>
      </div>

      <div className={clsx(styles["todo-form__submit-group"])}>
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
      </div>
    </form>
  );
};

export default TodoForm;
