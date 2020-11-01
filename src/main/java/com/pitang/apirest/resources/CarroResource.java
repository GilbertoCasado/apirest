package com.pitang.apirest.resources;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pitang.apirest.models.Carro;
import com.pitang.apirest.repository.CarroRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;




/**
* Recursos de Carro
*/
@RestController
@RequestMapping(value="/api")
@Api(value="API REST carros")
@CrossOrigin(origins="*")
public class CarroResource {
	
	@Autowired
	CarroRepository carroRepository;
	
	@GetMapping("/Cars")
	@ApiOperation(value="Retorna uma lista de carros")
	/**
	* Retorna uma lista de carros  
	*
	*/
	public List <Carro> listaCarros(){
		return carroRepository.findAll(); 
	}
	
	
	/**
	* Retorna um unico Carro  
	*
	*/
	@GetMapping("/Cars/{id}")
	@ApiOperation(value="Retorna um unico carro")
	public Carro listaCarroUnico(@PathVariable(value="id") long id){
		return carroRepository.findById(id);
	}
	/**
	* Cadastra um novo carro para  o usuario logado  
	*
	*/
	@PostMapping("/Cars")
	@ApiOperation(value="inclui um novo carro")
	public Carro salvaCarro(@RequestBody Carro carro) {
		return carroRepository.save(carro);
	}
	
	/**
	* Deleta um Carro
	*
	*/
	@DeleteMapping("/Cars")
	@ApiOperation(value="apaga um carro")
	public void deletaCarro(@RequestBody Carro carro) {
		carroRepository.delete(carro);
	}
	
	
	/**
	* Atualiza as informações de um carro
	*
	*/
	@PutMapping("/Cars")
	@ApiOperation(value="atualiza as informações de um carro")
	public Carro atualizaCarro(@RequestBody Carro carro) {
		return carroRepository.save(carro);
	}
}
