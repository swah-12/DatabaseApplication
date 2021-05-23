package database;

/**
 * Provides SQL statements to create, drop, and fill a Film table
 * and to access data from the Film table.
 * 
 * @authors Jonas Knochelmann, Shel Wah, and Irelan Bailey
 *
 */
public class Film {
	/**
	 * Creates a SQL table named Film with the columns
	 * ID, Director, and Language.
	 * 
	 * @return createTable
	 */
	public static String createTable() {
		return "CREATE TABLE Film (" 
				+ "ID int not null primary key " 
				+ "GENERATED ALWAYS AS IDENTITY "
				+ "(START WITH 10, INCREMENT BY 1), "
				+ "Director varchar(150), "
				+ "Language varchar(50) "
				+ ")";
	}

	/**
	 * Drops the Film table.
	 * 
	 * @return dropTable
	 */
	public static String dropTable() {
		return "DROP TABLE Film";
	}

	/**
	 * Inserts data into the Film table.
	 * 
	 * @return insertData
	 */
	public static String insertData() {
		return "INSERT INTO Film" 
				+ "(Director, Language) "
				+ "VALUES ('Rod Lurie', 'English'), "
				+ "('Irvin Kershner', 'English'), "
				+ "('Martin Scorsese', 'English'), "
				+ "('Bong Joon Ho', 'Korean')";
	}

	/**
	 * Selects all the data in the Film table.
	 * 
	 * @return allData
	 */
	public static String allData() {
		return "SELECT * FROM Film";
	}
	
	/**
	 * @author Jonas K
	 * @param director
	 * @param language
	 * @return SQL code to insert given data into table
	 */
	public static String addData(String director, String language) {
		return "INSERT INTO Film"
			+ "(Director, Language) "
			+ "VALUES ('"+director+"', '"+language+"')";
	}
}
