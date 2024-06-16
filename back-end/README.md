# Back-end

## Environment Variables

### `PORT`

Esta variável de ambiente define a porta em que o servidor irá rodar. Quando não definida, o servidor irá rodar na porta `8080`.

### `SPRING_DATASOURCE_URL`

Esta variável de ambiente define a URL do banco de dados. Quando não definida, o banco de dados será criado em memória.
O Formato da URL para o banco de dados PostgreSQL é `jdbc:postgresql://<host>:<port>/<database>`, exemplo: `jdbc:postgresql://dpg-cpn1o95ds78s73ap6hlg-a.oregon-postgres.render.com/todolistdb_z7wo`.

### `SPRING_DATASOURCE_USERNAME`

Esta variável de ambiente define o usuário do banco de dados. Quando não definida, o usuário será `sa`.

### `SPRING_DATASOURCE_PASSWORD`

Esta variável de ambiente define a senha do banco de dados. Quando não definida, a senha será vazia.

### `SPRING_DATASOURCE_DRIVER_CLASS_NAME`

Esta variável de ambiente define o nome da classe do driver do banco de dados. Quando não definida, o driver será `org.h2.Driver`.
Para o banco de dados PostgreSQL, o driver é `org.postgresql.Driver`.
