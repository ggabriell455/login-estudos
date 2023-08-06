# login-estudos

Passo a passo para subir a aplicação como docker:

1 - Após instalar todo setup para docker no ps, abrir terminal na pasta raiz.

2 -  Realizar Build do projeto para gerar o Jar.

mvn package -DskipTests

3 - Realizar Build da image com o comando: 

docker build -t ggabriell4555/loginapp:1.0 .

docker build -t <nome do usuario no docker hub>/<nome para imagem> : <tag de versão> <local do docker file> .

docker build -t ggabriell4555/loginapp:1.0 .

4 - Subir a imagem: 

docker run -p 8080:8082  -p 5005:5005  --network docker-mysql_default -e JAVA_TOOL_OPTIONS="-agentlib:jdwp=transport=dt_socket,address=*:5005,server=y,suspend=n" ggabriell4555/loginapp:1.0

