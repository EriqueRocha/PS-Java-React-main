#### Autor
[Erique Rocha](https://github.com/EriqueRocha)

## Apresentação:
Esta aplicação tem como objetivo implementar uma API de gerenciamneto completo de contas e trasaçoes exibindo alimentando um extrato bancário.

### Tecnologias:
* Java 1.17+
* SpringBoot 2.6.4
* PostgreSQL
* SpringDataJpa
* Hibernate
* SpringWeb
* SpringTest
* Projeto Lombok
* Swagger OpenAPI
* Global Exception Handlers
* Flyway

 ### Dependências:
```XML
  <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-ui</artifactId>
            <version>1.6.4</version>
        </dependency>
        <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-core</artifactId>
        </dependency>
    </dependencies>
```

### SpringDataJpa:
```XML
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```
Implementa uma camada de acesso aos dados de forma facilitada, faça seus métodos personalizados e o Spring fornecerá a implementação

### Flyway:
```XML
<dependency>
	<groupId>org.flywaydb</groupId>
	<artifactId>flyway-core</artifactId>
</dependency>
```
ajuda com a migração de banco de dados, no caso, está sendo utilizado para que a aplicação inicie com um administrador e um usuário cadastrado

### PostgreSQL:
```XML
<dependency>
	<groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
	<scope>runtime</scope>
</dependency>
```
drive para a utilização do SGBD PostgreSQL

### Lombok:
```XML
<dependency>
	<groupId>org.projectlombok</groupId>
  <artifactId>lombok</artifactId>
	<optional>true</optional>
</dependency>
```
biblioteca voltada a produtividade e evitar boilerplate, com o uso de anotações criamos sem a necessidade de digitar de forma extensa, por exemplo, getters, setters e construtores

### Springdoc openapi ui:
```XML
<dependency>
	<groupId>org.springdoc</groupId>
	<artifactId>springdoc-openapi-ui</artifactId>
	<version>1.6.4</version>
</dependency>
```
biblioteca que ajuda a automatizar a geração de documentação da API usando projetos de inicialização do spring

## o Básico para começar a utilizar a aplicação pelo o swagger:
acessar a documentacão após rodar a API em local host: http://localhost:8080/swagger-ui/index.html

### End points:
<img src="https://github.com/EriqueRocha/PS-Java-React-main/blob/master/imagem/Captura%20de%20tela%202023-07-14%20151939.png" style="width: 50%;">

<img src="https://github.com/EriqueRocha/PS-Java-React-main/blob/master/imagem/Captura%20de%20tela%202023-07-14%20152008.png" style="width: 50%;">

| HTTP                                     | Descrição                                   |
|------------------------------------------|---------------------------------------------|
| PUT:/usuarios/{id}                       | Edita uma conta                             |
| POST:/cadastro                           | Cadastra uma conta                          |
| GET:/{id}                                | Busca uma conta                             |
| GET:/list                                |busca a lista de contas                      |
| DELETE:/deletar/{id}                     | deleta uma conta                            |
| PUT:/editar/{id}                         | Edita uma transação                         |
| POST:/cadastar                           | adiciona uma transação                      |
| GET:/                                    | lista de transação                          |
| GET:/userId/{id}                         | transações de uma conta                     |
| GET:/userId/{id}/operador/{operador}     | transações feita a uma pessoa especifica    |
| GET:/transferencias/{contaId}/{operador} | transações feita a uma pessoa em um período |
| GET:/transferencias/conta/{contaId}      | busca transação por periodo                 |
| GET:/escolher/{id}                       | busca uma transação especifica              |
| DELETE:/delete/{id}                      | deleta uma transação                        |
