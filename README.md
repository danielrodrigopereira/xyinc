# xyinc
Projeto Desenvolvedor III Zup

# Dependências.

* [Maven versão 4.0.0];
* Java 8;
* MongoDb;
* Spring Boot;
* Swagger-ui;
* PostMan;
* JUnit
* GSon

# Passos para realizar o START na aplicação.

* Instalação do MongoDb com as configurações Default(localhost:27017);
* Baixar o zip do projeto;
* Extrair;
* Abrir o prompt de comando, acessar `{PROJECT_PATH}`, executar: `mvn clean package`;
* Executar o comando `java -jar target/xyinc-0.0.1.jar`;

# Testes da Aplicação

## Disponibilizado teste dentro da aplicação com JUnit
### EntityControllerTest
Possibilita testar todos os serviços disponíveis do `entity` sendo necessário alterar o parametro `entity` para uma entidade não existente na base e o `entityId` para ter 100% do resultado.
### ApiControllerTest
Possibilita persistir os dados de um `entity` sendo necessário alterar o parametro `entity` para uma entidade existente na base e o `entityRecordId` para um registro existente para ter 100% do resultado.

## Disponibilizado arquivo testesApiDynamic.json ou pela url abaixo para teste no PostMan
`https://www.getpostman.com/collections/9fdf9950ee1e9de7a379`
## Disponibilizado a pagina de teste por meio do swagger-ui pela url abaixo
`http://localhost:8080/swagger-ui.html`

## Manutenção de uma entidade

### Criando uma entidade.
Dados: Requisição `POST`, URL `http://localhost:8080/entity`, JSON:
```
{
	"name": "Pessoa",
	"attributes": {
		"nome": "texto",
		"idade": "numero",
		"saldo": "valor",
		"aniversario": "data",
		"ativo": "ativo"
	}
}
```

### Listando as entidades.
Dados: requisição `GET`, URL `http://localhost:8080/entity`;

### Listando uma entidade.
Dados:  requisição `GET`, URL `http://localhost:8080/entity`, parametro `ID`, EX `http://localhost:8080/entity/5d5191f8dbe56e384c60d531`;

### Removendo uma entidade
Dados:  requisição `DELETE`, URL `http://localhost:8080/entity`, parametro `ID`, EX `http://localhost:8080/entity/5d5191f8dbe56e384c60d531`;

### Alterando uma entidade: Método não implementado pois impacta nos registro existentes, sendo necessário remover a mesma e criar novamente.


## Persistindo uma entidade

### Criando um registro de uma entidade.
Dados: requisição `POST`, URL `http://localhost:8080/api`, parametro `Entidade`, EX `http://localhost:8080/api/Pessoa`, JSON:
```
{
	"nome": "Joao",
	"idade": 5,
	"saldo": 110.5,
	"aniversario": "10/10/2012",
	"ativo": true
}
```

### Listando registros de uma entidade.
Dados: requisição `GET`, URL `http://localhost:8080/api`, parametro `Entidade`, EX `http://localhost:8080/api/Pessoa`;

### Listando um registro de uma entidade.
Dados: requisição `GET`, URL `http://localhost:8080/api`, parametro `Entidade` e `ID`, EX `http://localhost:8080/api/Pessoa`;

### Removendo um registro de uma entidade.
Dados: requisição `DELETE`, URL `http://localhost:8080/api`, parametro `Entidade` e `ID`, EX `http://localhost:8080/api/Pessoa/99d9639a-9f20-4579-95e6-cd26303fbf0d`;

### Alterando um registro de uma entidade.
Dados:  requisição `PUT`, URL `http://localhost:8080/api`, parametro `Entidade` e `ID`, EX `http://localhost:8080/api/Pessoa/99d9639a-9f20-4579-95e6-cd26303fbf0d`, JSON:
```
{
	"nome": "Joao Roberto",
	"idade": 5,
	"saldo": 110.5,
	"aniversario": "10/10/2012",
	"ativo": true
}
```
