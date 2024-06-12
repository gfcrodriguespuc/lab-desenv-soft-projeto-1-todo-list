const TODOS_KEY = "todos";

export function getAllTodos() {
  return JSON.parse(localStorage.getItem(TODOS_KEY)) || [];
}

export function saveTodos(todos) {
  localStorage.setItem(TODOS_KEY, JSON.stringify(todos));
}

export function saveTodo(todo) {
  const todos = getAllTodos();
  todos.push(todo);
  saveTodos(todos);
}
