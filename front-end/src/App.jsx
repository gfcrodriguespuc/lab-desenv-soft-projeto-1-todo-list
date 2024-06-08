import styles from "./App.module.css";

import { TodoWrapper } from "./components/TodoWrapper";

export const App = () => {
  return (
    <div className={styles["app"]}>
      <TodoWrapper />
    </div>
  );
};

export default App;
