# Projeto Altbank API Credit Card

Este projeto é uma API REST desenvolvida com Quarkus para gerenciar contas, cartões e webhooks relacionados a cartões de crédito.

## Funcionalidades Principais

* Criação de contas com cartões físicos associados.
* Cancelamento de contas por documento do cliente.
* ~~ Emissão de cartões virtuais para contas existentes.~~ 
* ~~ Validação e reemissão de cartões.~~
* ~~ Atualização de CVV de cartões.~~
* Recebimento de webhooks para status de entrega.
* ~~ Recebimento de webhooks para atualizações de CVV.~~
* Documentação da API com Swagger UI.

## Tecnologias Utilizadas

* Quarkus
* Java
* JPA (Hibernate Panache)
* JUnit 5
* Mockito
* RestAssured
* Swagger UI

## Como Executar

1.  Clone o repositório.
2.  Execute `mvn clean compile quarkus:dev` para iniciar o Quarkus Dev Mode.
3.  Acesse a documentação da API em `http://localhost:8080/swagger/`.

## Testes

* Testes unitários para os controllers e serviços.
* Execução dos testes com `mvn test`.

## Webhooks

* Endpoints para receber webhooks de status de entrega e atualizações de CVV.

## Configuração

* Configurações no arquivo `application.properties`.

## Dependências

* As dependências estão definidas no arquivo `pom.xml`.

## Parâmetros

* ApiKey para endpoint de atualização do status de entrega do cartão
tracking.api.key=26372ce8-48c3-4924-9ea5-6561c954
