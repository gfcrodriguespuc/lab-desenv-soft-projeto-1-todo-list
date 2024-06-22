import styles from "./TodoWrapper.module.css";

import { useEffect, useState } from "react";
import * as todoApi from "../../services/api";
import { TodoForm } from "../TodoForm";
import { TodoList } from "../TodoList";

export const TodoWrapper = () => {
  const [loadings, setLoadings] = useState(0);
  const [todos, setTodos] = useState([]);

  const isLoading = loadings > 0;

  const addLoading = () => {
    setLoadings((l) => l + 1);
  };

  const removeLoading = () => {
    setLoadings((l) => l - 1);
  };

  const refreshTodos = async () => {
    try {
      addLoading();
      const savedTodos = await todoApi.getAllTasks();
      setTodos(savedTodos);
    } catch (error) {
      console.error("Erro ao buscar as tarefas", error);
      alert(`Erro ao buscar as tarefas: ${error.message}`);
    } finally {
      removeLoading();
    }
  };

  const addTodo = async (todoForm) => {
    try {
      addLoading();
      const newTodo = {
        ...todoForm,
        completed: false,
      };
      await todoApi.postTask(newTodo);
      refreshTodos();
    } catch (error) {
      console.error("Erro ao adicionar a tarefa", error);
      alert(`Erro ao adicionar a tarefa: ${error.message}`);
    } finally {
      removeLoading();
    }
  };

  const onClickCompleteTodo = async (id) => {
    try {
      addLoading();

      // todoApi.completeTodoById(id);

      const todoToUpdate = todos.find((todo) => todo.id === id);
      if (!todoToUpdate) {
        return;
      }

      const todoUpdated = {
        ...todoToUpdate,
        completed: !todoToUpdate.completed,
      };
      await todoApi.putTask(id, todoUpdated);
      refreshTodos();
    } catch (error) {
      console.error("Erro ao completar a tarefa", error);
      alert(`Erro ao completar a tarefa: ${error.message}`);
    } finally {
      removeLoading();
    }
  };

  const onClickDeleteTodo = async (id) => {
    try {
      addLoading();
      await todoApi.deleteTask(id);
      refreshTodos();
    } catch (error) {
      console.error("Erro ao deletar a tarefa", error);
      alert(`Erro ao deletar a tarefa: ${error.message}`);
    } finally {
      removeLoading();
    }
  };

  useEffect(() => {
    refreshTodos();
  }, []);

  return (
    <article className={styles["todo-wrapper"]}>
      <h1 className={styles["todo-wrapper__title"]}>Lista de Tarefas</h1>
      <div className={styles["todo-wrapper__form"]}>
        <TodoForm addTodo={addTodo} />
      </div>

      {isLoading && <p>Carregando...</p>}

      <TodoList
        todos={todos}
        onClickDeleteTodo={onClickDeleteTodo}
        onClickCompleteTodo={onClickCompleteTodo}
      />
    </article>
  );
};

export default TodoWrapper;
