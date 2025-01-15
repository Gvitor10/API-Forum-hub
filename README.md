# API - Forum Hub
<img src="src/assets/img/cover.jpg" alt="Imagem com fundo branco, contendo a logo do linguagem de progrmação Java, framework Spring Boot e a escrita REST entre colchetes." >

## 📚 Índice
1. [Descrição](#-descrição)
2. [Funcionalidades da API](#-funcionalidades-da-api)
3. [Requisitos](#-requisitos)
4. [Como Rodar a Aplicação](#-como-rodar-a-aplicação)
5. [Autenticação](#-autenticação)
6. [Endpoints](#-endpoints)
   - [Usuário](#usuario)
   - [Tópico](#tópico)
7. [Possíveis Erros](#-possíveis-erros)
8. [Créditos](#-créditos)

---

## 🔎 Descrição
O `Forum Hub` é uma API Restful construída em Java utilizando o framework Spring Boot. Ela permite que os usuários gerenciem informações sobre tópicos, possibilitando ações como:
- Listar todos os tópicos cadastrados.
- Buscar tópicos específicos.
- Cadastrar, alterar e excluir tópicos com base no `id` informado.

## 🔨 Funcionalidades da API
- **Cadastrar novo usuário**: Permite registrar um novo usuário no banco de dados.
- **Cadastrar novo tópico**: Permite registrar um novo tópico no banco de dados.
- **Listar tópicos cadastrados**: Retorna uma lista paginada com todos os tópicos.
- **Detalhar tópico**: Fornece informações detalhadas sobre um tópico específico com base no `id`.
- **Alterar tópico**: Permite atualizar as informações de um tópico com base no `id`.
- **Excluir tópico**: Permite remover um tópico com base no `id` informado.

## 📋 Requisitos
- **Linguagem**: Java 17+
- **Framework**: Spring Boot
- **Banco de Dados**: H2 ou qualquer banco de sua escolha (configuração pode ser necessária).
- **Ferramenta de Build**: Maven

## 🚀 Como Rodar a Aplicação
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-repositorio/forum-hub.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd forum-hub
   ```
3. Compile o projeto:
   ```bash
   mvn clean install
   ```
4. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```
5. Acesse a aplicação em `http://localhost:8080`.

## ✅ Autenticação
A API utiliza autenticação via Bearer Tokens para garantir que apenas usuários autorizados possam acessá-la.

### Obtendo um Token
- Endpoint de login:
  ```http
  POST /usuario/login
  ```
  - **Body da requisição**:
    ```json
    {
        "login": "usuario",
        "senha": "minhaSenha"
    }
    ```
  - **Resposta**:
    ```json
    {
        "token": "seu_token_aqui"
    }
    ```

### Usando o Token
Inclua o token no cabeçalho Authorization em todas as requisições protegidas:
```
Authorization: Bearer seu_token_aqui
```

## 💻 Endpoints
### Usuario
#### `POST /usuario/cadastrar`
Cadastra um novo usuário.
- **Body da requisição**:
  ```json
  {
      "login": "usuario",
      "senha": "minhaSenha"
  }
  ```

#### `POST /usuario/login`
Efetua o login de um usuário.
- **Body da requisição**:
  ```json
  {
      "login": "usuario",
      "senha": "minhaSenha"
  }
  ```

#### Resposta de Login:
```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJGb3J1bV9odWJfYXBpIiwic3ViIjoiQWx1bm8iLCJleHAiOjE3MzU2NjU4MDR9.RB7NGf5nHrQM1_q2JsC3SmUPheBi5etgbjJfZSEUJYg"
}
```

### Tópico
#### `POST /topicos`
Cadastra um novo tópico.
- **Cabeçalhos**:
  ```
  Authorization: Bearer seu_token_aqui
  Content-Type: application/json
  ```
- **Body da requisição**:
  ```json
  {
      "titulo": "titulo_do_topico",
      "mensagem": "mensagem_do_topico",
      "autor": "nome_do_autor",
      "curso": "nome_do_curso"
  }
  ```

#### `GET /topicos`
Retorna uma lista de tópicos cadastrados.
- **Cabeçalhos**:
  ```
  Authorization: Bearer seu_token_aqui
  ```

#### `GET /topicos/{id}`
Retorna detalhes de um tópico específico.
- **Cabeçalhos**:
  ```
  Authorization: Bearer seu_token_aqui
  ```

#### `PUT /topicos/{id}`
Atualiza informações de um tópico.
- **Cabeçalhos**:
  ```
  Authorization: Bearer seu_token_aqui
  Content-Type: application/json
  ```
- **Body da requisição**:
  ```json
  {
      "titulo": "novo_titulo",
      "mensagem": "nova_mensagem",
      "autor": "novo_autor"
  }
  ```

#### `DELETE /topicos/{id}`
Exclui um tópico com base no ID.
- **Cabeçalhos**:
  ```
  Authorization: Bearer seu_token_aqui
  ```

## 😵 Possíveis Erros
| Código do erro | Mensagem             | Descrição                          |
|----------------|----------------------|------------------------------------|
| 400            | Requisição inválida | Parâmetros ausentes ou inválidos   |
| 403            | Não autorizado      | Token inválido ou expirado         |
| 404            | Não encontrado      | Recurso inexistente no banco       |
| 500            | Erro interno        | Problema no servidor ou no código  |


---
Desenvolvido com ❤ por Gabriel Lobo.
