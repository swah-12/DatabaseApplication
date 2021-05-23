package database;

/**
 * Provides SQL statements to create, drop, and fill a Rating table
 * and to access data from the Rating table.
 * 
 * @authors Jonas Knochelmann, Shel Wah, and Irelan Bailey
 *
 */
public class Rating {
	/**
	 * Creates a SQL table named Rating with the columns
	 * ID, MovieID, Rating, and AmountOfRatings.
	 * 
	 * @return createTable
	 */
	public static String createTable() {
		return "CREATE TABLE Rating ("
			+"ID int not null primary key " 
	        + "GENERATED ALWAYS AS IDENTITY " 
	    	+ "(START WITH 1000, INCREMENT BY 1),"
	        + "MovieID int," 
	        + "Rating Decimal(10,1)," 
	        + "AmountOfRatings int"
	        + ")";
	}    
    
	/**
	 * Drops the Rating table.
	 * 
	 * @return dropTable
	 */
    public static String dropTable() {
    	return "DROP TABLE Rating";
    }
    
    /**
	 * Inserts data into the Rating table.
	 * 
	 * @return insertData
	 */
    public static String insertData() {
    	return "INSERT INTO Rating "
    		+ "(MovieID, Rating, AmountOfRatings) "
            + "VALUES (10, 6.8, 19888), "
            + "(11, 4.8, 1443), "
            + "(12, 8.2, 1192789), "
            + "(13, 8.6, 559372)";
    }
        
    /**
	 * Selects all the data in the Rating table.
	 * 
	 * @return allData
	 */
    public static String allData() {
    	return "SELECT * FROM Rating";
    }
    
    /**
	 * @author Jonas K
	 * @param movieID
	 * @param rating
	 * @param amountOfRatings
	 * @return SQL code to insert given data into table
	 */
	public static String addData(int movieID, float rating, int amountOfRatings) {
		return "INSERT INTO Rating"
			+ "(MovieID, Rating, AmountOfRatings) "
			+ "VALUES ("+movieID+", "+rating+", "+amountOfRatings+")";
	}
}
