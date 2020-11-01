package com.pitang.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.apirest.models.Usuario;
import com.pitang.apirest.security.LoginResource;

/**
* Repositorio de Usuarios
*/
public interface  UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findById(long id);
	Usuario findByLogin(String login);


}
