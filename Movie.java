package database;

/**
 * Provides SQL statements to create, drop, and fill a Movie table
 * and to access data from the Movie table.
 * 
 * @authors Jonas Knochelmann, Shel Wah, and Irelan Bailey
 *
 */
public class Movie {
	/**
	 * Creates a SQL table named Movie with the columns
	 * ID, MovieName, Description, PublicationYear, and Source.
	 * 
	 * @return createTable
	 */
	public static String createTable() {
		return "CREATE TABLE Movie (" 
				+ "ID int not null primary key " 
				+ "GENERATED ALWAYS AS IDENTITY "
				+ "(START WITH 10, INCREMENT BY 1), " 
				+ "MovieName varchar(200), " 
				+ "Description varchar(255), "
				+ "PublicationYear int, "
				+ "Source varchar(100) "
				+ ")";
	}
	
	/**
	 * Drops the Movie table.
	 * 
	 * @return dropTable
	 */
	public static String dropTable() {
		return "DROP TABLE Movie";
	}
	
	/**
	 * Inserts data into the Movie table.
	 * 
	 * @return insertData
	 */
	public static String insertData() {
		return "INSERT INTO Movie"
			+ "(MovieName, Description, PublicationYear, Source) "
			+ "VALUES ('The OutPost', 'U.S. soldiers at Afghanistan', 2020, 'Netflix'), "
			+ "('The Empire Strikes Back', 'Rebel Alliance is forced...', 1980, 'Disney+'), "
			+ "('The Wolf of Wall Street', 'Biography of fraudulent...', 2013, 'Amazon Prime Video'), "
			+ "('Parasite', 'Greed and class discrimination threaten the newly formed symbiotic relationship "
			+ "between the wealthy Park family and the destitute Kim clan.', 2019, 'Hulu')";
	}
	
	/**
	 * Selects all the data in the Movie table.
	 * 
	 * @return allData
	 */
	public static String allData() {
		return "SELECT * FROM Movie";
	}
	
	/**
	 * @author Jonas K
	 * @param movieName
	 * @param description
	 * @param publicationYear
	 * @param source
	 * @return SQL code to insert given data into table
	 */
	public static String addData(String movieName, String description, int publicationYear, String source) {
		return "INSERT INTO Movie"
			+ "(MovieName, Description, PublicationYear, Source) "
			+ "VALUES ('"+movieName+"', '"+description+"', "+publicationYear+", '"+source+"')";
	}
	
	public static String editData(int movieID, String movieName, String description, int publicationYear, String source) {
		return "UPDATE Movie " +
                "SET MovieName = '" + movieName + "', "
                + "Description = '" + description + "', "
                + "PublicationYear = " + publicationYear + ", "
                + "Source = '" + source + "'"
                + " WHERE ID = " + movieID;
	}
	
	public static String deleteData(int movieID) {
		return "DELETE FROM Movie "
                + " WHERE ID = " + movieID;
	}
}
