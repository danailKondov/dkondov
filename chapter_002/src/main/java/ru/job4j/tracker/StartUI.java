package ru.job4j.tracker;


import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
* Class is start point with main method.
* @since 13/11/2017
* @version 2
*/
public class StartUI {

	/**
	* Input operations.
	**/
	private Input input;
	
	/**
	* Tracker.
	**/
	private Tracker tracker;

	/**
	* Exit constant.
	**/
	private static final int EXIT = 6;

	/**
	 * Array of menu options.
	 */
	private ArrayList<Integer> menuOptions;

	/**
	* Constructor with parameters.
	**/
	public StartUI(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}

	/**
	* Main method.
	**/
	public static void main(String[] args) {

		String log4jConfPath = "chapter_002\\src\\main\\resourses\\log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);

		final Logger log = LoggerFactory.getLogger("Tracker");
		Properties properties = new Properties();
		Connection connection = null;
		InputStream inputStream = null;

		try {
			inputStream = Files.newInputStream(Paths.get("chapter_002\\src\\main\\resourses\\database.properties"));
			properties.load(inputStream);
			connection = DriverManager.getConnection(properties.getProperty("url"), properties);
			Tracker tracker = new Tracker(connection, log);

			Input consoleInput = new ValidateInput();
			new StartUI(consoleInput, tracker).init();

		} catch (IOException | SQLException e) {
			log.error(e.getMessage(), e);
		} finally {
			Utils.closeQuietly(inputStream, log);
		}
	}

	/**
	* General loop
	**/
	public void init() {
		MenuTracker menu = new MenuTracker(input, tracker);
		menu.fillActions();
		menuOptions = menu.keys();
		while(true) {
			menu.show();
			System.out.println(
			"6. Exit Program\n" + 
			"Select:"
			);
			int key = Integer.valueOf(input.ask("", menuOptions));
			if (key == EXIT) {

				// закрываем связь с базой данных перед выходом
				Utils.closeQuietly(tracker.getConnection(), tracker.getLog());
				break;
			}
			menu.select(key);
		}
	}
}