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
