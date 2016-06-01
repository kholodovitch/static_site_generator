package com.kholodovitch.static_site_generator.processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.kholodovitch.static_site_generator.data.global.Settings;

public class Processor {

	public void start(String inputFolder, String outputFolder) throws Exception {
		Settings settings = loadObject(FileUtils.getFile(inputFolder, "global", "settings.json"), Settings.class);
		processMainData(inputFolder, settings);
	}

	private <T> T loadObject(File file, Class<T> clazz) throws FileNotFoundException, IOException {
		try (Reader reader = new FileReader(file)) {
			return new Gson().fromJson(reader, clazz);
		}
	}

	private void processMainData(String inputFolder, Settings settings) {
		ExecutorService executor = Executors.newFixedThreadPool(32);
		Collection<File> listFiles = FileUtils.listFiles(new File(inputFolder, "main"), new String[] { "json" }, true);

		for (final File file : listFiles) {
			Runnable worker = new Runnable() {
				@Override
				public void run() {
					try {
						processFile(file);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			};
			executor.execute(worker);
		}

		executor.shutdown();
		while (!executor.isTerminated()) {
		}
	}

	protected void processFile(File file) {
		System.out.println(file.getAbsolutePath());
	}

}
