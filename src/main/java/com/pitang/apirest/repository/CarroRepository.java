package com.pitang.apirest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pitang.apirest.models.Carro;


/**
*  Repositorio de Carro
*
*/
public interface CarroRepository extends JpaRepository<Carro, Long> {
	
	Carro findById(long id);

}
