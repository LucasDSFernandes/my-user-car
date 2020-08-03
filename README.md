# my-user-car

Aplicação em spring boot para cadastro de usuário utilizando spring security, spring jpa, swagger, postgres, h2, eureka.

### Api Sistema de cadastro de usuario e carro com login

Essa aplicação contem os seguintes endpoints:
Para usuario: 

		- (POST) http://localhost:8083/api/me (Endpoint Retornar as informações do usuário logado) 
		- (POST) http://localhost:8083/api/users (Endpoint Listar todos os usuários)
		- (POST) http://localhost:8083/api/users (Endpoint Cadastrar um novo usuário)
		- (POST) http://localhost:8083//api/users/{id}  (Endpoint Buscar um usuário pelo id)
		- (POST) http://localhost:8083//api/users/{id}  (Endpoint Remover um usuário pelo id)
		- (POST) http://localhost:8083//api/users/{id}  (Endpoint Atualizar um usuário pelo id)
		
Para utilizacao de caros:

		- (POST) http://localhost:8083/api/cars (Endpoint Listar todos os usuários)
		- (POST) http://localhost:8083/api/cars (Endpoint Cadastrar um carro do usuario logado)
		- (POST) http://localhost:8083//api/cars/{id} (Endpoint Endpoint Buscar um carro do usuario logado)
		- (POST) http://localhost:8083//api/cars/{id} (Endpoint Remover um carro do usuario logado)
		- (POST) http://localhost:8083//api/cars/{id} (Endpoint Atualizar um carro do usuario logado)
Swagger na heroku:

<a href="http://app-userservice.herokuapp.com/swagger-ui.html" >Clique aqui para acessar a Heroku Swagger</a>
 

### Configuração da api

Para poder startar aplicação, colocar nas configurações o seguinte:

 ```sh
	$ Program arguments: --spring.config.location=src/main/resources/application.yml --spring.config.name=application.yml
	$ VM Arguments: -Dspring.profiles.active=test (Para utilizar banco em memoria) ou -Dspring.profiles.active=dev (Heroku) 
 ````
 
 ### Cobertura de Teste local com Junit
Utilizando plugin **Code Coverage** do eclipse

Cobertura de **81,5%** de codigo
<img width="575" alt="Coverage-81-code" src="https://user-images.githubusercontent.com/33759918/89132484-e6edc780-d4ea-11ea-89f2-fa55d9ee19e9.png">
