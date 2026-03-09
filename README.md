# 🏨 Hotel API - Spring Boot

Uma API RESTful para gerenciamento de reservas em hotéis, desenvolvida com **Spring Boot 3.5.9** e **Java 21**.

## 📋 Visão Geral

Esta API fornece funcionalidades completas para:
- 🏢 Gerenciar hotéis e seus dados
- 🛏️ Administrar quartos e disponibilidade
- 📝 Controlar reservas de hóspedes
- 👥 Gerenciar usuários com autenticação e autorização

## 🚀 Tecnologias Utilizadas

| Tecnologia | Descrição |
|-----------|-----------|
| **Java 21** | Linguagem de programação |
| **Spring Boot 3.5.9** | Framework principal |
| **Spring Data JPA** | ORM e persistência |
| **Spring Security** | Autenticação e autorização |
| **PostgreSQL** | Banco de dados relacional |
| **MapStruct 1.6.3** | Mapeamento de DTOs |
| **Lombok** | Redução de boilerplate |
| **JUnit 5** | Testes unitários |
| **Mockito** | Mocking para testes |
| **AssertJ** | Assertions fluentes |

## 📦 Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- **Java 21** ou superior
- **Maven 3.8+**
- **PostgreSQL 12+**
- **Git**

## ⚙️ Configuração do Ambiente

### 1. Clone o repositório

```bash
git clone https://github.com/vitor440/hotel-api-springboot.git
cd hotel-api-springboot
```

### 2. Configure o banco de dados

Crie um banco de dados PostgreSQL:

```sql
CREATE DATABASE hotelapi;
```

### 3. Configure as variáveis de ambiente

Edite o arquivo `hotelapi_v2/src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hotelapi
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

## 🏃 Como Executar

### Desenvolvimento

```bash
cd hotelapi_v2
mvn spring-boot:run
```

A API estará disponível em: `http://localhost:8080`

### Build para Produção

```bash
cd hotelapi_v2
mvn clean package
java -jar target/hotelapi-0.0.1-SNAPSHOT.jar
```

## 🧪 Testes

Execute todos os testes:

```bash
cd hotelapi_v2
mvn test
```

Execute testes de uma classe específica:

```bash
mvn test -Dtest=HotelServiceTest
```

## 📚 Estrutura do Projeto

```
hotel-api-springboot/
├── hotelapi_v2/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/hotelapi/hotelapi/
│   │   │   │   ├── controller/           # Controllers REST
│   │   │   │   ├── service/              # Lógica de negócio
│   │   │   │   ├── repository/           # Acesso a dados (JPA)
│   │   │   │   ├── model/                # Entidades JPA
│   │   │   │   ├── dto/                  # Data Transfer Objects
│   │   │   │   ├── mapper/               # Mapeamento de DTOs (MapStruct)
│   │   │   │   ├── validation/           # Validadores customizados
│   │   │   │   ├── exception/            # Exceções customizadas
│   │   │   │   ├── security/             # Configuração de segurança
│   │   │   │   └── configuration/        # Configurações gerais
│   │   │   └── resources/
│   │   │       └── application.properties # Configurações da aplicação
│   │   └── test/
│   │       ├── java/com/hotelapi/hotelapi/
│   │       │   └── unittest/
│   │       │       ├── service/          # Testes de serviço
│   │       │       ├── validator/        # Testes de validador
│   │       │       ├── mapper/           # Testes de mapper
│   │       │       └── mock/             # Objetos mock
│   ├── pom.xml                           # Dependências Maven
│   └── comandos_sql.txt                  # Scripts SQL
└── README.md
```

## 🔌 Endpoints Principais

### Hotéis

| Método | Endpoint | Descrição | Autenticação |
|--------|----------|-----------|--------------|
| **POST** | `/hoteis` | Criar novo hotel | ADMIN |
| **GET** | `/hoteis/{id}` | Obter hotel por ID | ADMIN, USER |
| **GET** | `/hoteis` | Listar hotéis (com filtros) | ADMIN, USER |
| **PUT** | `/hoteis/{id}` | Atualizar hotel | ADMIN |
| **DELETE** | `/hoteis/{id}` | Deletar hotel | ADMIN |

**Parâmetros de filtro (GET /hoteis):**
- `nome` (opcional): Filtrar por nome
- `estado` (opcional): Filtrar por estado
- `num-pagina` (padrão: 0): Número da página
- `tamanho-pagina` (padrão: 3): Quantidade por página

### Quartos

| Método | Endpoint | Descrição | Autenticação |
|--------|----------|-----------|--------------|
| **POST** | `/quartos` | Criar novo quarto | ADMIN |
| **GET** | `/quartos/{id}` | Obter quarto por ID | ADMIN, USER |
| **GET** | `/quartos` | Listar quartos (com filtros) | ADMIN, USER |
| **PUT** | `/quartos/{id}` | Atualizar quarto | ADMIN |
| **DELETE** | `/quartos/{id}` | Deletar quarto | ADMIN |
| **GET** | `/quartos/disponiveis` | Listar quartos disponíveis | ADMIN, USER |

### Reservas

| Método | Endpoint | Descrição | Autenticação |
|--------|----------|-----------|--------------|
| **POST** | `/reservas` | Criar nova reserva | ADMIN, USER |
| **GET** | `/reservas/{id}` | Obter reserva por ID | ADMIN, USER |
| **GET** | `/reservas` | Listar reservas (com filtros) | ADMIN, USER |
| **PUT** | `/reservas/{id}` | Atualizar reserva | ADMIN, USER |
| **DELETE** | `/reservas/{id}` | Cancelar reserva | ADMIN, USER |

### Usuários

| Método | Endpoint | Descrição | Autenticação |
|--------|----------|-----------|--------------|
| **POST** | `/usuarios` | Criar novo usuário | PUBLIC |
| **GET** | `/usuarios/{id}` | Obter usuário por ID | PUBLIC |
| **GET** | `/usuarios` | Listar usuários | PUBLIC |
| **PUT** | `/usuarios/{id}` | Atualizar usuário | PUBLIC |
| **DELETE** | `/usuarios/{id}` | Deletar usuário | PUBLIC |

## 🔐 Autenticação

A API utiliza **Spring Security com HTTP Basic Authentication**.

### Exemplo de Requisição Autenticada

```bash
curl -X GET http://localhost:8080/hoteis \
  -H "Authorization: Basic dXNlcjpwYXNz"
```

> **Nota:** O header `Authorization` deve conter `Basic` seguido das credenciais codificadas em Base64.

### Roles Disponíveis

- **ADMIN**: Acesso total (criar, editar, deletar hotéis e quartos)
- **USER**: Acesso de leitura (listar hotéis e quartos)

## 📄 Exemplo de Requisições

### Criar um Hotel

```bash
curl -X POST http://localhost:8080/hoteis \
  -H "Content-Type: application/json" \
  -H "Authorization: Basic YWRtaW46YWRtaW4=" \
  -d '{
    "nome": "Hotel Paradise",
    "estado": "SP",
    "cidade": "São Paulo",
    "telefone": "(11) 99999-9999",
    "andares": 10
  }'
```

### Listar Hotéis com Filtros

```bash
curl -X GET "http://localhost:8080/hoteis?nome=Paradise&estado=SP&num-pagina=0&tamanho-pagina=10" \
  -H "Authorization: Basic dXNlcjpwYXNz"
```

### Criar uma Reserva

```bash
curl -X POST http://localhost:8080/reservas \
  -H "Content-Type: application/json" \
  -d '{
    "checkIn": "2026-03-20",
    "checkOut": "2026-03-25",
    "nomeHospede": "João Silva",
    "email": "joao@email.com",
    "telefone": "(11) 98888-8888",
    "quantidadeAdultos": 2,
    "quantidadeCriancas": 1,
    "idQuarto": 1
  }'
```

## 🏗️ Padrões e Arquitetura

### Padrão de Camadas

```
Controller → Service → Repository → Database
    ↑           ↓
    └─→ Validator
    
DTOs ←→ Mapper ←→ Entities
```

### Principais Padrões Utilizados

- **Repository Pattern**: Acesso a dados abstraído
- **Service Pattern**: Lógica de negócio centralizada
- **DTO Pattern**: Transferência segura de dados
- **Specification Pattern**: Buscas dinâmicas e complexas
- **Exception Handling**: Exceções customizadas


## 🗄️ Modelo de Dados

```
┌─────────────┐
│   HOTEL     │
├─────────────┤
│ id (PK)     │
│ nome        │
│ estado      │
│ cidade      │
│ telefone    │
│ andares     │
│ data_cadastro│
│ data_atualizacao│
└─────────────┘
       │ 1:N
       │
┌─────────────┐
│   QUARTO    │
├─────────────┤
│ id (PK)     │
│ tipo        │
│ preco       │
│ capacidade  │
│ disponivel  │
│ tamanho_m²  │
│ id_hotel(FK)│
│ data_cadastro│
│ data_atualizacao│
└─────────────┘
       │ 1:N
       │
┌─────────────┐
│  RESERVA    │
├─────────────┤
│ id (PK)     │
│ check_in    │
│ check_out   │
│ nome_hospede│
│ email       │
│ telefone    │
│ num_adultos │
│ num_criancas│
│ total       │
│ cod_confirmacao│
│ id_quarto(FK)│
│ data_cadastro│
│ data_atualizacao│
└─────────────┘

┌─────────────┐
│  USUARIO    │
├─────────────┤
│ id (PK)     │
│ login       │
│ email       │
│ senha       │
│ roles       │
└─────────────┘
```

## 🔄 Fluxo de Reserva

```
1. Cliente consulta quartos disponíveis
   GET /quartos/disponiveis?tipo=suite&check-in=2026-03-20&check-out=2026-03-25

2. Cliente cria uma reserva
   POST /reservas (com dados do hóspede e ID do quarto)

3. Sistema gera código de confirmação único (UUID)

4. Sistema decrementa vagas disponíveis no quarto

5. Reserva é persistida no banco de dados
```

## 🐛 Troubleshooting

### Erro: "Database connection refused"
- Verifique se PostgreSQL está rodando
- Confirme as credenciais em `application.properties`
- Verifique a porta (padrão: 5432)

### Erro: "Unauthorized - 401"
- Verifique se está enviando o header `Authorization`
- Confirme se o usuário e senha estão corretos
- Verifique se o usuário tem a role correta

### Erro: "Registro Duplicado"
- A validação está funcionando corretamente
- Não é possível cadastrar dois hotéis com mesmo nome no mesmo estado
- Não é possível cadastrar dois quartos do mesmo tipo no mesmo hotel

## 📚 Recursos Adicionais

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Security](https://spring.io/projects/spring-security)
- [MapStruct Documentation](https://mapstruct.org/)


## 👤 Autor

**Vitor** - [GitHub](https://github.com/vitor440)

---

<div align="center">

</div>
