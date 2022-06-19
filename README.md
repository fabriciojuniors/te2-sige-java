
# SIGE

Trabalho desenvolvido para a matéria de Tópicos Especiais II.




## Instalação


Clone o repositório do Github
```bash
  git clone https://github.com/fabriciojuniors/te2-sige-java
```

Acesse a pasta do projeto
```
  cd te2-sige-java
```

Instale as dependências do projeto 
```
  mvn clean install
```

Crie um database Postgres nomeado sige-teii

Configure o DataSource no servidor Wildfly
```
<datasource jta="true" jndi-name="java:jboss/datasources/SigeDS" pool-name="SigeDSPOOL" enabled="true" use-ccm="false">
    <connection-url>jdbc:postgresql://localhost:5432/sige-teii</connection-url>
    <driver-class>org.postgresql.Driver</driver-class>
    <driver>postgresql</driver>
    <security>
        <user-name>postgres</user-name>
        <password>postgres</password>
    </security>
</datasource>
```

Execute a aplicação (play na IDE Netbeans)

    
