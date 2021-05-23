package database;

/**
 * Provides SQL statements to create, drop, and fill a Ip table
 * and to access data from the Ip table.
 * 
 * @authors Jonas Knochelmann, Shel Wah, and Irelan Bailey
 *
 */
public class Ip {
	/**
	 * Creates a SQL table named Ip with the columns
	 * Top, MovieName, Episodes, and Country.
	 * 
	 * @return createTable
	 */
	public static String createTable() {
		return "CREATE TABLE IP(" 
				+ "Top int, "
				+ "MovieName varchar(150), "
				+ "Episodes int, "
				+ "Country varchar(150)"
				+ ")";
	}

	/**
	 * Drops the Ip table.
	 * 
	 * @return dropTable
	 */
	public static String dropTable() {
		return "DROP TABLE IP";
	}

	/**
	 * Inserts data into the Ip table.
	 * 
	 * @return insertData
	 */
	public static String insertData() {
		return "INSERT INTO IP" 
				+ "(Top, MovieName, Episodes, Country) "
				+ "VALUES (1, 'The OutPost', 0, 'America'), "
				+ "(2, 'The Empire Strikes Back', 0, 'Norway'), "
				+ "(3, 'The Wolf of Wall Street', 0, 'America'), "
				+ "(4, 'Parasite', 0, 'South Korea')";
	}

	/**
	 * Selects all the data in the Ip table.
	 * 
	 * @return allData
	 */
	public static String allData() {
		return "SELECT * FROM IP";
	}
	
	/**
	 * @author Jonas K
	 * @param top
	 * @param movieName
	 * @param episodes
	 * @param country
	 * @return SQL code to insert given data into table
	 */
	public static String addData(int top, String movieName, int episodes, String country) {
		return "INSERT INTO IP"
			+ "(Top, MovieName, Episodes, Country) "
			+ "VALUES ("+top+", '"+movieName+"', "+episodes+", '"+country+"')";
	}
}
