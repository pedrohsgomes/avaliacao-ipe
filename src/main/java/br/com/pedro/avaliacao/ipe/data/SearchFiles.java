package br.com.pedro.avaliacao.ipe.data;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SearchFiles {

	private File root;
	private String startFilter;
	private final String dataBasePath = Paths.get("").toAbsolutePath().toString();
	
	private Set<String> files = new HashSet<String>();

	public SearchFiles(String path, String startFilter) {
		this.root = new File(dataBasePath + "/" + path + "/");
		this.startFilter = startFilter;
	}

	/**
	 * List eligible files on current path
	 * 
	 * @param directory The directory to be searched
	 * @return Eligible files
	 */
	private String[] getTargetFiles(File directory) {
		if (directory == null) {
			return null;
		}

		File[] files = directory.listFiles(new FilenameFilter() {			

			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return name.startsWith(startFilter) && name.endsWith(".dat");
			}

		});

		return Arrays.asList(files).stream().map(File::getAbsolutePath).collect(Collectors.toList()).toArray(new String[files.length]);
	}

	/**
	 * Print all eligible files
	 */
	private void printFiles(String[] targets) {
		for (String target : targets) {
			System.out.println(target);
		}
		
		files.addAll(Arrays.asList(targets));
	}

	private void recursive(File path) {

		printFiles(getTargetFiles(path));
		for (File file : path.listFiles()) {
			if (file.isDirectory()) {
				recursive(file);
			}
		}
		if (path.isDirectory()) {
			printFiles(getTargetFiles(path));
		}
	}
	
	public List<String> find() {
		files.clear();
		recursive(root);
		return files.stream().toList();
	}
}
