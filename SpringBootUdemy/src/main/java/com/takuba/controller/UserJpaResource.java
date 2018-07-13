package com.takuba.controller;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.takuba.exceptions.UserNotFoundException;
import com.takuba.repository.UserRepository;
import com.takuba.user.Post;
import com.takuba.user.User;
import com.takuba.user.UserDaoService;

@RestController
public class UserJpaResource {
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping("/jpa/users")
	public List<User> retriveAllUsers(){
		return repository.findAll();
	}
	@GetMapping("/jpa/users/{id}")
	public Resource<User> retriveUser(@PathVariable int id) {
		Optional<User> user =repository.findById(id);
		if(!user.isPresent()){
			throw new UserNotFoundException("id-"+id);
		}
		Resource<User> resource =new Resource<User>(user.get());
		ControllerLinkBuilder linkTo = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retriveAllUsers());
		resource.add(linkTo.withRel("all-user"));
		return resource;
	}
	//se agrego la etiqueta @Valid para agregar validaciones, las validaciones se implementan en el bean User
	
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> saveUser(@Valid @RequestBody User user) {
		User savedUser= repository.save(user);
		//se crea la locacion del recurso creado y se envia un codigo 201 que indica que el recurso fue creado
		URI location  = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	@DeleteMapping("/jpa/users/{id}")
	public void deleteById(@PathVariable int id) {
		repository.deleteById(id);
	}
	@GetMapping("/jpa/users/{id}/post")
	public List<Post> retriveAllUsers(@PathVariable int id){
		Optional<User> user= repository.findById(id);
		if(!user.isPresent()){
			throw new UserNotFoundException("id-"+id);
		}
		return user.get().getPosts();
	}
	
}