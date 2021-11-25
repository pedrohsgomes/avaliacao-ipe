package br.com.pedro.avaliacao.ipe.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import br.com.pedro.avaliacao.ipe.TestConfig;
import br.com.pedro.avaliacao.ipe.data.DataBaseManager;
import br.com.pedro.avaliacao.ipe.models.User;
import br.com.pedro.avaliacao.ipe.repositories.UserRepository;
import br.com.pedro.avaliacao.ipe.services.UserService;

@SpringBootTest(classes= TestConfig.class)
@TestPropertySource({"classpath:application.properties"})
//@RunWith(PowerMockRunner.class)
//@PrepareForTest(User.class)
public class UserTestController {

	@Autowired
	UserController controller;

	@Autowired
	UserService serviceMock;
	
	@Autowired
	UserRepository repositoryMock;
	
	@Autowired
	DataBaseManager<Serializable> managerMock;
	
	private User createJoao() {
		User user = new User();
		user.setName("joao");
		user.setLastName("silva");
		user.setBirthDate(LocalDate.now().minusYears((long) (16L + Math.random() * (107L - 16L))));
		int age = LocalDate.now().minusYears(user.getBirthDate().getYear()).getYear();
		user.setAge(age);
		return user;
	}
	
	@Test
    @DisplayName("Salvar usuário e retornar status 200")
	void saveSuccess() {
		User user = createJoao();
		
		ResponseEntity<String> response = controller.users(user);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User saved at " + user.pathToData() + " !", response.getBody());
	}
	
	@Test
    @DisplayName("Salvar usuário e retornar status 422")
	void saveNotFound() {
		User user = createJoao();
		user.setName("");
		
		ResponseEntity<String> response = controller.users(user);
		
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals("User not saved " + user.toString() + " !", response.getBody());
	}
	
	@Test
    @DisplayName("Recuperar usuário e retornar status 200")
	void getSuccess() {
		User user = createJoao();
		serviceMock.save(user);
		ResponseEntity<List<User>> response = controller.getUser("joao");
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(user, response.getBody().get(0));
	}
	
	@Test
    @DisplayName("Recuperar usuário e retornar status 404")
	void getFail() {
		User user = createJoao();
		serviceMock.save(user);
		ResponseEntity<List<User>> response = controller.getUser("jose");
		
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNull(response.getBody());
	}
}
