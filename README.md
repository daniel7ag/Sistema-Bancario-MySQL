# Banco de Dados - Sistema Bancário

Este projeto simula uma aplicação bancária utilizando **Java**, **JDBC** e um **banco de dados relacional MySQL**. A aplicação permite criar contas bancárias, listar contas existentes, excluir contas e realizar operações bancárias como depósitos, saques e transferências.

## Funcionalidades

- **Criar Conta**: Permite cadastrar uma nova conta bancária com um nome de titular e um número de conta gerado automaticamente.
- **Listar Contas**: Exibe todas as contas cadastradas no sistema.
- **Excluir Conta**: Permite excluir uma conta bancária do sistema.
- **Depositar**: Realiza um depósito em uma conta existente.
- **Sacar**: Permite retirar um valor de uma conta, desde que haja saldo suficiente.
- **Transferir**: Realiza uma transferência de valores entre duas contas bancárias.

## Estrutura do Projeto

O projeto foi estruturado da seguinte forma:

```bash
├── src
│   ├── controller
│   │   └── AplicacaoBancaria.java       # Classe principal com menu e interações
│   ├── dao
│   │   └── ContaBancariaDAO.java        # Classe de acesso ao banco de dados (CRUD)
│   ├── model
│   │   └── ContaBancaria.java           # Modelo de dados da conta bancária
│   └── utils
│       └── ConexaoDB.java               # Classe de conexão com o banco de dados
├── lib
│   └── mysql-connector-java-8.0.33.jar  # Conector JDBC para MySQL
├── Banco.sql                           # Script SQL para criação do banco de dados e tabelas
└── pom.xml                             # Arquivo de configuração do Maven (caso esteja usando Maven)

```

## Requisitos

- **Java** 8 ou superior
- **MySQL** 5.7 ou superior
- **Maven** (caso utilize Maven para gerenciamento de dependências)
- **Conector JDBC do MySQL** (mysql-connector-java)

## Instalação e Execução

### 1. Instalar o MySQL

Certifique-se de que o MySQL está instalado e em execução. Caso não tenha, baixe e instale a versão mais recente do MySQL [aqui](https://dev.mysql.com/downloads/).

### 2. Configurar o Banco de Dados

- Abra o **MySQL Workbench** e crie o banco de dados e a tabela necessária utilizando o script SQL fornecido. O arquivo `Banco.sql` contém o código SQL para criar a tabela `conta`:

```sql
CREATE DATABASE Banco;
USE Banco;

CREATE TABLE conta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    saldo DECIMAL(10, 2) DEFAULT 0
);
```

### 3. Configurar a Conexão com o Banco de Dados

Abra o arquivo ConexaoDB.java e ajuste os parâmetros de conexão conforme a configuração do seu banco de dados local:
```
public class ConexaoDB {
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/Banco", "root", "senha");
        } catch (SQLException e) {
            throw new SQLException("Erro ao conectar ao banco de dados", e);
        }
    }
}
```
Substitua "root" e "senha" pelo seu nome de usuário e senha do MySQL.

### 4. Compilar e Executar o Projeto
Se estiver usando Maven, basta rodar o comando a seguir para compilar o projeto:
```
mvn clean install
```
Para executar a aplicação:
```
mvn exec:java -Dexec.mainClass="controller.AplicacaoBancaria"
```
Se não estiver utilizando Maven, compile e execute manualmente:

1. Compile o código:
```
javac -d . src/controller/*.java src/dao/*.java src/model/*.java src/utils/*.java
```
2. Execute o projeto:
```
java -cp .:lib/mysql-connector-java-8.0.33.jar controller.AplicacaoBancaria
```
### 5. Interagindo com o Menu
Após a execução, o menu será exibido no terminal. As opções incluem:

1. Cadastrar Conta: Crie uma conta bancária inserindo o nome do titular.

2. Listar Contas: Exibe todas as contas cadastradas.

3. Excluir Conta: Exclui uma conta existente através do ID.

4. Depositar: Realize um depósito em uma conta.

5. Sacar: Realize um saque de uma conta (necessário saldo suficiente).

6. Transferir: Transfira valores entre duas contas.
Sair: Encerra a aplicação.

## Dependências

JDBC MySQL: O conector JDBC do MySQL (mysql-connector-java) é necessário para a comunicação com o banco de dados. Está incluído na pasta lib.

### Exemplo de dependência do Maven para o MySQL Connector (caso você esteja usando Maven):
```
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
```

## Contribuindo
Sinta-se à vontade para contribuir com melhorias! Se você encontrar algum erro ou tiver sugestões de novas funcionalidades, crie um issue ou envie um pull request.
