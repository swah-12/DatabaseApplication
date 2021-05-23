package database;

/**
 * Provides SQL statements to create, drop, and fill a Genre table
 * and to access data from the Genre table.
 * 
 * @authors Jonas Knochelmann, Shel Wah, and Irelan Bailey
 *
 */
public class Genre {
	/**
	 * Creates a SQL table named Genre with the columns
	 * ID, MovieID, Genre, BasedOn, and Subtitles.
	 * 
	 * @return createTable
	 */
	public static String createTable() {
		return "CREATE TABLE Genre ("
			+"ID int not null primary key " 
	        + "GENERATED ALWAYS AS IDENTITY " 
	    	+ "(START WITH 123, INCREMENT BY 1),"
	        + "MovieID int," 
	        + "Genre varchar(255)," 
	        + "BasedOn varchar(255)," 
	        + "Subtitles varchar(255)"
	        + ")";
	}    
    
	/**
	 * Drops the Genre table.
	 * 
	 * @return dropTable
	 */
    public static String dropTable() {
    	return "DROP TABLE Genre";
    }
    
    /**
	 * Inserts data into the Genre table.
	 * 
	 * @return insertData
	 */
    public static String insertData() {
    	return "INSERT INTO Genre "
    		+ "(MovieID, Genre, BasedOn, Subtitles) "
            + "VALUES (10, 'War', 'Book', 'Yes'), "
            + "(11, 'Series', 'Film', 'Yes'), "
            + "(12, 'Biography', 'Person', 'Yes'), "
            + "(13, 'Thriller', 'Life Events', 'Yes')";
    }
       
    /**
	 * Selects all the data in the Genre table.
	 * 
	 * @return allData
	 */
    public static String allData() {
    	return "SELECT * FROM Genre";
    }
    
    /**
	 * @author Jonas K
	 * @param movieName
	 * @param description
	 * @param publicationYear
	 * @param source
	 * @return SQL code to insert given data into table
	 */
	public static String addData(int movieID, String genre, String basedOn, String subtitles) {
		return "INSERT INTO Genre"
			+ "(MovieID, Genre, BasedOn, Subtitles) "
			+ "VALUES ("+movieID+", '"+genre+"', '"+basedOn+"', '"+subtitles+"')";
	}
}

