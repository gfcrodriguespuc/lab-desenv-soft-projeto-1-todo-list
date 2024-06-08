import { TodoForm } from "../TodoForm";
import { TodoList } from "../TodoList";

export const TodoWrapper = () => {
  return (
    <article>
      <h1>Lista de Tarefas</h1>
      <TodoForm />
      <TodoList />
    </article>
  );
};

export default TodoWrapper;
