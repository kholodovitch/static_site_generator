package com.kholodovitch.static_site_generator.processor;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class App {
	private static Options options;

	private static String inputFolder;
	private static String outputFolder;

	static {
		options = new Options();
		options.addOption("i", true, "input folder");
		options.addOption("o", true, "output folder");
	}

	public static void main(String[] args) throws ParseException {
		if (args.length == 0)
			help();
		
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse(options, args);
		parseOptions(cmd);

		Processor proc = new Processor();
		proc.start(inputFolder, outputFolder);
	}

	private static void parseOptions(CommandLine cmd) {
		inputFolder = cmd.getOptionValue("i");
		outputFolder = cmd.getOptionValue("o", "build");
	}

	private static void help() {
		HelpFormatter formater = new HelpFormatter();
		formater.printHelp("App", options);
		System.exit(0);
	}
}
