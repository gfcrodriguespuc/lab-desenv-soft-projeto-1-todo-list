import axios from "axios";

const TASKS_RESOURCE = "tasks";

const apiClient = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  timeout: 120_000,
});

apiClient.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    // Aqui é onde todos os erros HTTP passam antes de virarem erros nas rotas.
    // É um intercetor Global, atinge todas as rotas.
    // Se esta função não lançar um erro a rota receberá um sucesso, mas caso
    // ela lançar chegará na rota como um erro.

    let apiErrorDetail;
    if (error.response) {
      // Request failed
      // Aqui podemos testar se é um erro de autenticação
      // e fazer um redirect para o login
      console.debug("Request failed", error.response);
      apiErrorDetail = error.response.data?.detail || error.message;
    } else {
      // Network Error
      // Situações em que o servidor está inativo
      // ou problemas de conexão do lado do cliente
      // Aqui podemos usar a API navigator.online:
      // https://developer.mozilla.org/en-US/docs/Web/API/Navigator/Online_and_offline_events
      // e checar se o cliente está offline ou se o servidor que está offline
      console.debug("Network Error", error);
      apiErrorDetail = "Erro na conexão com o servidor";
    }

    // É necessário lançar o erro para ele chegar na rota como um erro
    // Na rota que ele será tratado de forma especifica
    // throw error;
    const apiError = new Error(apiErrorDetail, {
      cause: error,
    });
    apiError.name = "ApiError";
    throw apiError;
  }
);

export async function getAllTasks() {
  return (await apiClient.get(TASKS_RESOURCE)).data || [];
}

export async function postTask(taskToCreate) {
  const response = await apiClient.post(TASKS_RESOURCE, taskToCreate);
  const taskId = response.headers.location?.split("/").pop() ?? null;
  return taskId;
}

export async function getTask(id) {
  return (await apiClient.get(`${TASKS_RESOURCE}/${id}`)).data;
}

export async function putTask(id, task) {
  await apiClient.put(`${TASKS_RESOURCE}/${id}`, task);
}

export async function deleteTask(id) {
  await apiClient.delete(`${TASKS_RESOURCE}/${id}`);
}
