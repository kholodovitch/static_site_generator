package com.kholodovitch.static_site_generator.processor;

import java.io.File;
import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.FileUtils;

public class Processor {

	public void start(String inputFolder, String outputFolder) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(32);
		Collection<File> listFiles = FileUtils.listFiles(new File(inputFolder), new String[] { "json" }, true);

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
