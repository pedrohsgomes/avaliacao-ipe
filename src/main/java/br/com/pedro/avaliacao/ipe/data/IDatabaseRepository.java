package br.com.pedro.avaliacao.ipe.data;

import java.io.Serializable;
import java.util.List;

public interface IDatabaseRepository<T extends Serializable> {

	List<String> get(String context, String id);
	
	boolean save(T data, String path, String fileName);	

}