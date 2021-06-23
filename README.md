## API RESTFUL + JPA + JWT

###  ESTÓRIAS DE USUÁRIO 
* Como usuario do serviço devo ser capas de me cadastrar e cadastrar uma lista com os meus carros, devo conseguir listar todos os usuarios do serviço. 
* Como um usuario cadastrado devo ter um maneira de cadastrar novos carros na minha lista e contabilizar a quantidade de vezes que eu uso meus carros.
* Como um usuário cadastrado preciso me autenticar para editar ou apagar os dados da minha lista de carros.


### SOLUÇÃO
Serviço em questo foi  construido usando Sprint Boot, que é um projeto da Spring que tem como objetivo principal ganhar produtividade na questão de configuração e publicação da aplicações, foi usando para Segurança o Spring Security, que é um framework muito customizavel para controle de acesso e segurança, e Java Web Token(jwt) que atualmente ja é um padrão da Internet para a criação de dados com assinatura.  


### Guia de acesso da API 
* Acesso via por interface grafica [Swagger](https://apirest-pitang.herokuapp.com/swagger-ui.html)

### Usuario
* [/api/signin](https://apirest-pitang.herokuapp.com/api/signin) Esta rota espera um objeto com os campos login e password, e deve retornar o token de acesso da API (JWT) com as informações do usuário logado. 
* [/api/users](https://apirest-pitang.herokuapp.com/api/Users) Listar todos os usuários
* [/api/users](https://apirest-pitang.herokuapp.com/api/Users) Cadastrar um novo usuário 
* [/api/users/{id}](https://apirest-pitang.herokuapp.com/api/Users/) Buscar um usuário pelo id
* [/api/users/{id}](https://apirest-pitang.herokuapp.com/api/Users) Remover um usuário pelo id
* [/api/users/{id}](https://apirest-pitang.herokuapp.com/api/Users) Atualizar um usuário pelo id

### Carro
* [/api/me](#) Retornar as informações do usuário logado. [To do]
* [/api/cars](https://apirest-pitang.herokuapp.com/api/Cars) Listar todos os carros do usuário logado 
* [/api/cars](https://apirest-pitang.herokuapp.com/api/Cars) Cadastrar um novo carro para o usuário logado 
* [/api/cars/{id}](https://apirest-pitang.herokuapp.com/api/Cars) Buscar um carro do usuário logado pelo id 
* [/api/cars/{id}](https://apirest-pitang.herokuapp.com/api/Cars) Remover um carro do usuário logado pelo id 
* [/api/cars/{id}](https://apirest-pitang.herokuapp.com/api/Cars) Atualizar um carro do usuário logado pelo id



