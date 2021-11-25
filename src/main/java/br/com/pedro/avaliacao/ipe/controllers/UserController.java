package br.com.pedro.avaliacao.ipe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedro.avaliacao.ipe.models.User;
import br.com.pedro.avaliacao.ipe.services.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/users")
	public ResponseEntity<String> users(@RequestBody User user) {
		boolean result = service.save(user);
		return result ? ResponseEntity.ok("User saved at " + user.pathToData() + " !")
				: ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("User not saved " + user.toString() + " !");
	}

	@GetMapping("/users/{name}")
	@ResponseBody
	public ResponseEntity<List<User>> getUser(@PathVariable("name") String name) {
		List<User> users = service.getOne(name);
		return users == null || users.size() == 0 ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) : ResponseEntity.ok(users);
	}
}
