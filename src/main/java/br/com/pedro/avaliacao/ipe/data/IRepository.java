package br.com.pedro.avaliacao.ipe.data;

import java.io.Serializable;
import java.util.List;

public interface IRepository<T extends Serializable> {

	List<T> get(String id);
	
	boolean save(T entity);

}