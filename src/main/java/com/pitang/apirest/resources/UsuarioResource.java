package com.pitang.apirest.resources;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.json.HTTP;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pitang.apirest.models.Usuario;
import com.pitang.apirest.repository.CarroRepository;
import com.pitang.apirest.repository.UsuarioRepository;
import com.pitang.apirest.security.LoginResource;



import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.Response;



/**
* Recursos de usuario   
*
*/
@RestController
@RequestMapping(value="/api")
@Api(value="API REST Usuarios")
@CrossOrigin(origins="*")
public class UsuarioResource {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	CarroRepository carroRepository;
	
	
	/**
	* Lista todos os usuarios cadastrados 
	* @return List 
	*/
	@GetMapping("/Users")
	@ApiOperation(value="Retorna uma lista de Usuarios")
	public List <Usuario> listaUsuarios(){
		return usuarioRepository.findAll(); 
	}
	
	
	/**
	* Retorna um usuario  para o id informado  
	* @param  id (long)
	* @return Usuario
	*/
	@GetMapping("/Users/{id}")
	@ApiOperation(value="Retorna um usuario para o id informado")
	public Usuario listaUsuariosUnico(@PathVariable(value="id") long id){
		return  usuarioRepository.findById(id);
	}
	
	
	/**
	* apaga um usuario e todos os carros associados 
	* @param usuario (Usuario)
	* 
	*/
	@DeleteMapping("/Users")
	@ApiOperation(value="Apaga um usuario")
	public void deletaUsuario(@RequestBody Usuario usuario) {
		 usuarioRepository.delete(usuario);
	}
	
	
	/**
	* Incluir um novo usuario
	* @param usuario (Usuario)
	* @param erros (Errors)
	* @return JsonObject
	*/
	@PostMapping("/Users")
	@ApiOperation(value="Inclui um usuario")
	public ResponseEntity<String> salvaUsuario(@Valid @RequestBody Usuario usuario, Errors erros){
		JSONObject jsonObject = new JSONObject();
		//erros de preenchimento  
		if(erros.hasErrors()) {
			String erroCode = "";
			if (erros.getFieldError().getCode().equals("NotBlank")) {
				erroCode =  "5";
			}
			
			
			jsonObject.put("errorCode", erroCode);
			jsonObject.put("message", erros.getFieldError().getDefaultMessage());
			
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.BAD_REQUEST);
		}
		
		//Validando login 	
		if(usuarioRepository.findByLogin(usuario.getLogin()) != null) {				
			jsonObject.put("errorCode", 401);
			jsonObject.put("message", "Login already exists");			
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.BAD_REQUEST);
		}
		
		//validando email 
		if(usuarioRepository.findByLogin(usuario.getEmail()) != null) {	
			jsonObject.put("errorCode", 401);
			jsonObject.put("message", "Email already exists");
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.BAD_REQUEST);
		}
		
		
		
		
		Usuario UsuarioRetorno =  usuarioRepository.save(usuario);
		
		if(UsuarioRetorno == null) {	
			
			jsonObject.put("errorCode", 401);
			jsonObject.put("message", "Invalid Login");
			
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.BAD_REQUEST);
		}
		
		jsonObject.put("Code", 200);
		jsonObject.put("message", "Usuario incluido:"+ usuario.getFirstName());
		return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
	}
	
	
	/**
	* atualiza os dados de um usuario cadastrado  
	* @param usuario (Usuario)
	* @param erros (Errors)
	* @return ResponseEntity 
	*/
	@PutMapping("/Users")
	@ApiOperation(value="Atualiza um Usuarios")
	public ResponseEntity<String> atualizaUsuario(@Valid @RequestBody Usuario usuario, Errors erros) {
		
		
		
		JSONObject jsonObject = new JSONObject();
		//erros de preenchimento  
		if(erros.hasErrors()) {
			String erroCode = "";
			if (erros.getFieldError().getCode().equals("NotBlank")) {
				erroCode =  "5";
			}
			
			
			jsonObject.put("errorCode", erroCode);
			jsonObject.put("message", erros.getFieldError().getDefaultMessage());
			
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.BAD_REQUEST);
		}
		
		//Validando login
		
		Usuario usuarioBase =  usuarioRepository.findById(usuario.getId());
		
		if(!usuarioBase.getLogin().equals(usuario.getLogin())) {
		
			if(usuarioRepository.findByLogin(usuario.getLogin()) != null) {						
				jsonObject.put("errorCode", 400);
				jsonObject.put("message", "Login already exists");			
				return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.BAD_REQUEST);
			}
		}
		
		
		//validando email 
		if(!usuarioBase.getEmail().equals(usuario.getEmail())) {
			if(usuarioRepository.findByLogin(usuario.getEmail()) != null) {	
				jsonObject.put("errorCode", 400);
				jsonObject.put("message", "Email already exists");
				return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.BAD_REQUEST);
			}
		}
		
		Usuario UsuarioRetorno =  usuarioRepository.save(usuario);
		
		if(UsuarioRetorno == null) {	
			
			jsonObject.put("errorCode", 401);
			jsonObject.put("message", "Invalid Login");
			
			return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.BAD_REQUEST);
		}
		
		jsonObject.put("Code", 200);
		jsonObject.put("message", "Usuario Alterado: "+ usuario.getFirstName());
		return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.OK);
	
	}

	
	/**
	* Autenticaçao de usuario,  retorna um tokem de acesso quando usuario é identificado  
	* @param login (LoginResource)
	* @return ResponseEntity
	*/
	@PostMapping("/sigin")
	@ApiOperation(value="Logar na API")
	public ResponseEntity<String> logarUsuario(@RequestBody LoginResource login) {
		
		Usuario usuario = usuarioRepository.findByLogin(login.getLogin());
		
		
		if (usuario != null  && new BCryptPasswordEncoder().matches(login.getSenha(), usuario.getPassword())) {	
			//criando token JWT
			String jwtToken = Jwts.builder()
			        .claim("name", usuario.getFirstName())
			        .claim("email", usuario.getEmail())
			        .setSubject(usuario.getFirstName())
			        .setId(UUID.randomUUID().toString())
			        .compact();

				JSONObject jsonObject = new JSONObject();
				jsonObject.put("token", jwtToken);
						
				return new ResponseEntity<String>( jsonObject.toString(), HttpStatus.OK);
       
			
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("errorCode", "401");
		jsonObject.put("messager", "Usuario não Autorizado");
		
		
		return new ResponseEntity<String>(jsonObject.toString(), HttpStatus.UNAUTHORIZED);
	}
	
	
}
