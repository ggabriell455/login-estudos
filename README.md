# login-estudos

Esse pequeno projeto de estudos é um micro serviço para autenticação com Token JWT.

### Tecnologias

- Java 18
- Spring Boot 3
- Maven
- Swagger
- Docker
- Docker-Compose

Passo a passo para subir a aplicação como docker:

1 - Após instalar todo setup para docker e/ou dockercompose no PC, abrir terminal na pasta raiz do projeto.

2 -  Realizar Build do projeto para gerar o Jar.

```shell
  mvn package 
```
3 - Realizar Build da image com o comando: 

Realizar Build da image com o comando:

```shell
docker build -t <user-do-dockerhub>/login-estudos:1.0 .
```

4 - Subir a imagem: 

```shell
docker run -p 8080:8080 <user-do-dockerhub>/login-estudos:1.0
```
Para levantar a aplicação (Com possibilidade de debug):

```shell
- docker run -p 8080:8082 -p 5005:5005 -e JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,address=*:5005,server=y,suspend=n" <user-do-dockerhub>/login-estudos:1.0
```

