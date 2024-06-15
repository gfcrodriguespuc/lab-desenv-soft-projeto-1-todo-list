import { TodoStatus, TodoType } from "../../models/todo";

const TODOS_KEY = "todos";

function computeStatus(todo) {
  // Prevista, X dias de atraso ou Concluída
  // Prevista ou Concluída

  if (todo.completed) {
    return {
      state: TodoStatus.COMPLETED,
    };
  }

  if (todo.type === TodoType.DUE_DATE) {
    const dueDate = new Date(todo.dueDate + "T23:59:59.999");
    const todayAtEndOfDay = new Date();
    todayAtEndOfDay.setHours(23, 59, 59, 999);

    const diffTime = Math.abs(todayAtEndOfDay - dueDate);
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
    if (dueDate < todayAtEndOfDay) {
      return {
        state: TodoStatus.LATE,
        days: diffDays,
        dueDate: dueDate,
      };
    }

    return {
      state: TodoStatus.EXPECTED,
      days: diffDays,
      dueDate: dueDate,
    };
  }

  if (todo.type === TodoType.DEADLINE) {
    const dueDate = new Date(todo.createdAt);
    dueDate.setDate(dueDate.getDate() + todo.deadline);
    dueDate.setHours(23, 59, 59, 999);
    const todayAtEndOfDay = new Date();
    todayAtEndOfDay.setHours(23, 59, 59, 999);

    const diffTime = Math.abs(todayAtEndOfDay - dueDate);
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
    if (dueDate < todayAtEndOfDay) {
      return {
        state: TodoStatus.LATE,
        days: diffDays,
      };
    }

    return {
      state: TodoStatus.EXPECTED,
      days: diffDays,
    };
  }

  return {
    state: TodoStatus.EXPECTED,
  };
}

export function getAllTodos() {
  const todosJSON = localStorage.getItem(TODOS_KEY);
  if (!todosJSON) {
    return [];
  }

  const todos = JSON.parse(todosJSON);
  for (const todo of todos) {
    todo.status = computeStatus(todo);
  }

  return todos;
}

export function saveTodos(todos) {
  localStorage.setItem(TODOS_KEY, JSON.stringify(todos));
}

export function saveTodo(todo) {
  const todos = getAllTodos();
  todo.createdAt = new Date().toISOString();
  todos.push(todo);
  saveTodos(todos);
}

export function updateTodoById(id, updatedTodo) {
  const todos = getAllTodos();
  const updatedTodos = todos.map((todo) => {
    if (todo.id === id) {
      return updatedTodo;
    }
    return todo;
  });
  saveTodos(updatedTodos);
}

export function deleteTodoById(id) {
  const todos = getAllTodos();
  const updatedTodos = todos.filter((todo) => todo.id !== id);
  saveTodos(updatedTodos);
}
