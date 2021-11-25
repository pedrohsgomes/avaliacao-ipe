package br.com.pedro.avaliacao.ipe.models;

import java.io.Serializable;
import java.nio.file.Paths;
import java.time.LocalDate;

import com.google.gson.annotations.JsonAdapter;

import br.com.pedro.avaliacao.ipe.converters.LocalDateGSONConverter;
import lombok.Data;

@Data
public class User implements Serializable {
	
	private static final long serialVersionUID = -6163670131670775419L;
	private String name;
	private String lastName;
	@JsonAdapter(value = LocalDateGSONConverter.class)
	private LocalDate birthDate;
	private int age;
	
	public String pathToData() {
		String dataBasePath = Paths.get("").toAbsolutePath().toString();
		dataBasePath += "/" + name + "/" + lastName + ".dat";
		
		return dataBasePath;
	}
}
