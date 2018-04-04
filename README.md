# JSF

Este projeto está estruturado com os assuntos relacionados ao JSF.

**Aulas**

*[Aula 1 - Conceitos Básicos ](https://github.com/ifpb-disciplinas-2017-2/dac-jsf/commit/a1a3dbf7c98399c82ee42dcfe6a00632a64d6f50)*

*[Aula 2 - HTML 5 e conversores ](https://github.com/ifpb-disciplinas-2017-2/dac-jsf/commit/20232ed4246313acf48df330554f1ec6709dedb1)*

*[Aula 3 - Validadores e template ](https://github.com/ifpb-disciplinas-2017-2/dac-jsf/commit/296b3a5eba2a9d847596d402767467a3024bd168)*

## Criar o arquivo `Dockerfile` do banco PostgreSQL
```
FROM postgres
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD 12345
ENV POSTGRES_DB dac-album
COPY create.sql /docker-entrypoint-initdb.d/
COPY insert.sql /docker-entrypoint-initdb.d/
```
## Criar uma imagem do banco PostgreSQL
`docker build -t ricardojob/banco ./postgres`:  
*`-t`: qual a tag que vamos atribuir a essa imagem*  
*`./postgres`: caminho relativo (ou absoluto) para o arquivo Dockerfile*  


## Criar o arquivo `Dockerfile` da aplicação
```
FROM payara/server-web
ENV DOMAIN domain1
ENV LIB /opt/payara41/glassfish/domains/${DOMAIN}/lib/
ENV DEPLOY ${PAYARA_PATH}/glassfish/domains/${DOMAIN}/autodeploy/
ADD target/dac-jsf/WEB-INF/lib/ ${LIB}
ENTRYPOINT $PAYARA_PATH/bin/asadmin start-domain --verbose ${DOMAIN}
ADD target/dac-jsf.war  ${DEPLOY}

```

## Criar uma imagem da aplicação

`docker build -t ricardojob/jsf .`:  
*`-t`: qual a tag que vamos atribuir a essa imagem*  
*`.`: caminho relativo (ou absoluto) para o arquivo Dockerfile*  

## Executar o container  
`docker run -p 5433:5432 -d --name banco ricardojob/banco` e
`docker run -p 8081:8080 -d --name app --link banco:host-banco ricardojob/jsf`:   
*`-p`: o bind entre a porta do host local com a porta do container*  
*`-d`: o container seja executar em background*  
*`--link`: o bind entre os containers, para pertimir que o container da aplicação tenha acesso ao container do banco*  
*`--name`: o nome do container*  


## Listar as imagens

`docker image ls`

## Listar os containers

`docker container ls`

## Parar o container

`docker stop <container_id | container_name>`

## Executar comandos no container  
Para executarmos comandos necessitamos de executar o comando `docker exec -it <container_id | container_name> <command>`.
Por exemplo, para termos acesso ao container do banco que configuramos podemos fazer:

`docker exec -it banco /bin/bash`:  
*`-it`: para termos acesso iterativo ao TTY*  
*`banco`: o nome do container que desejamos seja executar determinado comando*  
*`/bin/bash`: o comando que vamos executar no container*  

Após esses passos, teremos acesso ao terminal do container. Podemos acessar o _database_ que definimos no arquivo `Dockerfile` que configura o banco de dados, neste exemplo `dac-album`.

`psql -U postgres dac-album`:  
*`-U`: usuário configurado*  
*`dac-album`: o _database_ que desejamos acessar*

Alguns comando úteis no `psql`:  
*`\dt`: lista as tabelas do _database_*    
*`select * from album;`: seleciona todos os albuns*  
*`INSERT INTO album(nome, cpf) VALUES ('RC Always','12/10/1966');`: insere um novo album*    
*`\q`: sair do _database_*  
