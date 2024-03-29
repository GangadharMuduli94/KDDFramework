package com.kdd.config;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

/**
 * @author Bheemarao.M
 *
 */
public class ReportDirectory {
	private static final Logger log = Logger.getLogger(ReportDirectory.class.getName());

	public boolean create(String folderPath) {
		Path path = Paths.get(folderPath);
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
				log.info("Directory was created\n" + folderPath);
				return true;
			} catch (IOException exp) {
				log.error(exp.getMessage(), exp);
				return false;
			}
		}
		return true;
	}

	public void delete(String folderPath) {
		File folder = new File(folderPath);
		if (folder.exists()) {
			for (String file : folder.list()) {
				File currentFile = new File(folder.getPath(), file);
				currentFile.delete();
			}
		}
	}

	public void clearFolder(String folderPath) {
		log.info("Clearing content in below folder: \n" + folderPath);
		delete(folderPath);
		create(folderPath);
	}
}
