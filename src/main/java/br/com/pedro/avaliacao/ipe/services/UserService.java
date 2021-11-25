package br.com.pedro.avaliacao.ipe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import br.com.pedro.avaliacao.ipe.models.User;
import br.com.pedro.avaliacao.ipe.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private Gson gson;
	
	public List<User> getOne(String name) {
		
		if (name == null || name.isBlank() || name.isEmpty()) {
			return null;
		}
		
		List<User> users = repository.get(name);
		return users;
	}
	
	public boolean save(String json) {
		if (json == null || json.isBlank() || json.isEmpty()) {
			return false;
		}
		
		User user = gson.fromJson(json, User.class);
		
		if (!validateUser(user)) {
			return false;
		}
		
		boolean result = save(user);
		return result;
	}
	
	public boolean save(User user) {
		
		if (!validateUser(user)) {
			return false;
		}
		
		boolean result = repository.save(user);
		return result;
	}

	private boolean validateUser(User user) {
		if (user == null || user.getAge() <= 0 || user.getBirthDate() == null 
				|| user.getLastName().isBlank() || user.getLastName().isEmpty()
				|| user.getName().isBlank() || user.getName().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
}
