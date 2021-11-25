package br.com.pedro.avaliacao.ipe.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import br.com.pedro.avaliacao.ipe.data.IDatabaseRepository;
import br.com.pedro.avaliacao.ipe.data.IRepository;
import br.com.pedro.avaliacao.ipe.models.User;

@Repository
public class UserRepository implements IRepository<User> {
		
	@Autowired
	private IDatabaseRepository<User> manager;
	
	@Autowired
    private Gson gson;

	public List<User> get(String name) {
		
		List<String> jsons = (List<String>) manager.get("users", name);
		List<User> users = new ArrayList<User>();
		
		for (String json : jsons) {
			User user = gson.fromJson(json, User.class);
			users.add(user);
		}
		
		return users;
	}
	
	@Override
	public boolean save(User entity) {
		return manager.save(entity, "users", entity.getName().toLowerCase().concat("_").concat(entity.getLastName().replaceAll(" ", "").toLowerCase()));
	}
}
