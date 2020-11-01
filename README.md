## API RESTFUL + JPA + JWT
Desafio Pitang 

###  ESTÓRIAS DE USUÁRIO 
* Como  usuario do serviço devo ser capas de me cadastrar e cadastrar uma lista com os meus carros, devo conseguir listar todos os usuarios do serviço. 
* Como um usuario cadastrado devo ter um maneira de cadastrar novos carros na minha lista e contabilizar a quantidade de vezes que eu uso meus carros.
* Como um usuário cadastrado preciso me autenticar para editar ou apagar os dados da minha lista de carros.


### SOLUÇÃO
Serviço  em questo foi  construido usando Sprint Boot, que é um projeto da Spring que tem como objetivo principal ganhar produtividade na questão de configuração e publicação da aplicações, foi usando para Segurança o Spring Security, que é um framework muito customizavel para controle de acesso e segurança, e Java Web Token(jwt) que atualmente ja é um padrão da Internet para a criação de dados com assinatura.  


### Guia de acesso da API 
### Usuario
* [/api/signin](http://google.com) Esta rota espera um objeto com os campos login e password, e deve retornar o token de acesso da API (JWT) com as informações do usuário logado. 
* [/api/users](http://google.com) Listar todos os usuários
* [/api/users](http://google.com) Cadastrar um novo usuário 
* [/api/users/{id}](http://google.com) Buscar um usuário pelo id
* [/api/users/{id}](http://google.com) Remover um usuário pelo id
* [/api/users/{id}](http://google.com) Atualizar um usuário pelo id

### Carro
* [/api/me](#) Retornar as informações do usuário logado. (To do)
* [/api/cars](http://google.com) Listar todos os carros do usuário logado 
* [/api/cars](http://google.com) Cadastrar um novo carro para o usuário logado 
* [/api/cars/{id}](http://google.com) Buscar um carro do usuário logado pelo id 
* [/api/cars/{id}](http://google.com) Remover um carro do usuário logado pelo id 
* [/api/cars/{id}](http://google.com) Atualizar um carro do usuário logado pelo id



