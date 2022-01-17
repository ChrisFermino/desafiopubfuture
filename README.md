# DesafioPubFuture

### Prompt de Comando(cmd)

Necessita de [Maven](https://maven.apache.org/install.html) instalado para usar o comando `mvn clean install`.

Necessita de [Java 11](https://jdk.java.net/archive/) nas variavéis de ambiente do sistema.

localize a pasta do projeto pelo cmd e execute o comando `mvn clean install` em seguida execute `mvn spring-boot:run`

### Banco de dados

Necessita ter uma base no postgres, eu usei docker para isso.

`docker run --name desafiopubfuture -p 5432:5432 -e POSTGRES_PASSWORD=123456789 -d postgres`

### Documentação Swagger

 Com a aplicação iniciada, use a porta 8080/swagger-ui.html
