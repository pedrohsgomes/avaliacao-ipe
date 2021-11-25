package br.com.pedro.avaliacao.ipe.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.gson.Gson;

@Component
public class DataBaseManager<E extends Serializable> implements IDatabaseRepository<E> {
	
//	private static final IRepository<E> instance = new DataBaseManager<E>();
	
    private final String dataBasePath = Paths.get("").toAbsolutePath().toString();
    @Autowired
    private Gson gson;

	private FileWriter fw;

	
	private DataBaseManager() {
	}
	
//    public static IRepository<?> getInstance() {
//        return instance;
//    }
   
	@Override
	public List<String> get(String context, String id) {
		
		SearchFiles search = new SearchFiles(context, id);
		List<String> files = search.find();
		
		List<String> data = new ArrayList<String>();
		
		for (String file : files) {
			Path path = Paths.get(file);

		    try {
				String read = Files.readAllLines(path).get(0);
				data.add(read);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return data;
	}

	@Override
	public boolean save(E data, String path, String fileName) {
		String json = gson.toJson(data);

		try {
			File file = new File(dataBasePath + "/" + path + "/" + fileName + ".dat");
			new File(file.getParent()).mkdirs();
			file.createNewFile();
			fw = new FileWriter(file, Charsets.UTF_8);
			fw.write(json);
			fw.close();    
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
}
