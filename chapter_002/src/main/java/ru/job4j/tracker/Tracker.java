package ru.job4j.tracker;

import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.*;

/**
* Class represents Tracker.
* @since 13/11/2017
* @version 2
*/
public class Tracker {

	/**
	 * Connection to SQL database.
	 */
	private Connection connection;

	/**
	 * Logger.
	 */
	private final Logger log;

	/**
	* variable represents list of items.
	**/
	private ArrayList<Item> items = new ArrayList<>();

	/**
	* variable for randomizer.
	**/
	private static final Random RN = new Random();

	/**
	* Default constructor.
	**/
	public Tracker(Connection connection, Logger log) {
		this.connection = connection;
		this.log = log;
		initiateDB();
	}

	/**
	 * Initiates database from outer sql script.
	 */
	private void initiateDB() {
		Statement statement = null;
		BufferedReader reader = null;
		try {
			statement = connection.createStatement();
			reader = new BufferedReader(
					new InputStreamReader(
							new FileInputStream("chapter_002\\src\\main\\resources\\tables_init.sql")));
			StringBuilder sb = new StringBuilder();
			String s;

			while ((s = reader.readLine()) != null) {
				sb.append(s);
				if (s.endsWith(";")) {
					statement.addBatch(sb.toString());
					sb = new StringBuilder();
				}
			}

			statement.executeBatch();
		} catch (IOException | SQLException e) {
			log.error(e.getMessage(), e);
		} finally {
			Utils.closeQuietly(reader, log);
			Utils.closeQuietly(statement, log);
		}
	}

	/**
	* Method adds new items to array.
	* @param item new item to add
	* @return item
	**/
	public Item add(Item item) {
		log.info("start adding item");

		if (item != null) {
			item.setID(generateID());

			PreparedStatement statement = null;
			try {
				String stmnt = "INSERT INTO items (item_id, item_name, item_desc, item_created)" +
						"VALUES (?, ?, ?, ?)";
				statement = connection.prepareStatement(stmnt);
				statement.setString(1, item.getID());
				statement.setString(2, item.getName());
				statement.setString(3, item.getDescription());
				statement.setLong(4, item.getCreated());
				statement.executeUpdate();

				if(item.getComments() != null && item.getComments().size() > 0) {
					String stmnt2 = "INSERT INTO items_comments (item_comment, item_id)" +
							"VALUES (?, ?)";
					statement = connection.prepareStatement(stmnt2);
					for (String comment : item.getComments()) {
						statement.setString(1, comment);
						statement.setString(2, item.getID());
						statement.addBatch();
					}
					statement.executeBatch();
				}
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
			} finally {
				Utils.closeQuietly(statement, log);
			}
		}
		return item;
	}

	/**
	* Method generates ID.
	* @return id
	**/
	String generateID() {
		return String.valueOf(System.currentTimeMillis() + RN.nextInt());
	}

	/**
	* Method updates item based on id.
	* @param item new item to update
	**/
	public void update(Item item) {

		if (item != null) {
			PreparedStatement statement = null;
			try {
                statement = connection.prepareStatement("UPDATE items " +
                        "SET item_name = ?, item_desc = ?, item_created = ?" +
                        "WHERE item_id = ?");
                statement.setString(1, item.getName());
                statement.setString(2, item.getDescription());
                statement.setLong(3, item.getCreated());
                statement.setString(4, item.getID());
                statement.executeUpdate();

                // проблема в том, что количество новых комментов может быть больше, или меньше,
				// или их может не быть вовсе - поэтому сначала стираем старые, потом пишем новые
				statement = connection.prepareStatement("DELETE FROM items_comments " +
						"WHERE item_id = ?");
				statement.setString(1, item.getID());
				statement.executeUpdate();

				if(item.getComments() != null && item.getComments().size() > 0) {
					statement = connection.prepareStatement("INSERT INTO items_comments (item_comment, item_id)" +
							"VALUES (?, ?)");
					for (String comment : item.getComments()) {
						statement.setString(1, comment);
						statement.setString(2, item.getID());
						statement.addBatch();
					}
					statement.executeBatch();
				}
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            } finally {
                Utils.closeQuietly(statement, log);
            }
		}
	}

	/**
	* Method removes item based on id.
	* @param item new item to remove
	**/
	public boolean delete(Item item) {
		boolean result = false;
		if (item != null) {
			PreparedStatement statement = null;

			try {
				statement = connection.prepareStatement("DELETE FROM items " +
                        "WHERE item_id = ?");
				statement.setString(1, item.getID());
				statement.executeUpdate();

				statement = connection.prepareStatement("DELETE FROM items_comments " +
						"WHERE item_id = ?");
				statement.setString(1, item.getID());
				int i = statement.executeUpdate();
				System.out.println("rows deleted: " + i);
				result = true;
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
			} finally {
				Utils.closeQuietly(statement, log);
			}
		}
		return result;
	}

	/**
	* Method returns no null array .
	* @return result array
	**/
	public ArrayList<Item> findAll() {
		items = new ArrayList<>();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("" +
					"SELECT it.item_id, it.item_name, it.item_desc, it.item_created, ic.item_comment " +
					"FROM items AS it LEFT OUTER JOIN items_comments AS ic " +
					"ON it.item_id = ic.item_id");
			ResultSet resultSet = statement.executeQuery();
			items = getItemsFromRs(resultSet);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		} finally {
			Utils.closeQuietly(statement, log);
		}
		return items;
	}

	/**
	 * Gets list of Items from standard ResultSet.
	 * @param rs ResultSet
	 * @return ArrayList of Items
	 */
	private ArrayList<Item> getItemsFromRs(ResultSet rs) {
		ArrayList<Item> itemsList = new ArrayList<>();
		Item previous = null;
		ArrayList<String> comments = new ArrayList<>();
		try {
			while(rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String desc = rs.getString(3);
                long created = rs.getLong(4);
                String comment = rs.getString(5);

                // добавляем из таблицы комментарии, добавленные
                // с помощью LEFT OUTER JOIN
                if (previous == null) {
                    previous = new Item(name, desc, created);
                    previous.setID(id);
                    if (comment != null) comments.add(comment);
                } else if (id.equals(previous.getID())) {
                    comments.add(comment);
                } else if (!id.equals(previous.getID())) {
                	// добавляем предыдущий Item
                    previous.setComments(comments);
                    itemsList.add(previous);

                    // создаем новый Item и если есть комментарии, то
					// начинаем их собирать
                    comments = new ArrayList<>();
					if (comment != null) comments.add(comment);
                    previous = new Item(name, desc, created);
                    previous.setID(id);
                }
            }
			// добавляем последний элемент
			if(comments.size() > 0 && previous != null) {
				previous.setComments(comments);
			}
			itemsList.add(previous);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		} finally {
			Utils.closeQuietly(rs, log);
		}
		return itemsList;
	}

	/**
	* Method returns array by name.
	* @return result array
	**/
	public ArrayList<Item> findByName(String key) {
		ArrayList<Item> result = null;
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("" +
					"SELECT it.item_id, it.item_name, it.item_desc, it.item_created, ic.item_comment " +
					"FROM items AS it LEFT OUTER JOIN items_comments AS ic " +
					"ON it.item_id = ic.item_id " +
					"WHERE it.item_name = ?");
			statement.setString(1, key);
			ResultSet rs = statement.executeQuery();
			result = getItemsFromRs(rs);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		} finally {
			Utils.closeQuietly(statement, log);
		}
		return result;
	}

	/**
	* Method returns array by id.
	* @return result
	**/
	public Item findByID(String id) {
		ArrayList<Item> result = null;
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement("" +
					"SELECT it.item_id, it.item_name, it.item_desc, it.item_created, ic.item_comment " +
					"FROM items AS it LEFT OUTER JOIN items_comments AS ic " +
					"ON it.item_id = ic.item_id " +
					"WHERE it.item_id = ?");
			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();
			result = getItemsFromRs(rs);
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		} finally {
			Utils.closeQuietly(statement, log);
		}
		return result.get(0);
	}

	public Connection getConnection() {
		return connection;
	}

	public Logger getLog() {
		return log;
	}
}