package database;

/**
 * Creating different queries, JOIN, AND, OR, LIKE from multiple tables.
 * 
 * @authors Jonas Knochelmann, Shel Wah, and Irelan Bailey
 */
public class MultipleQueries {

	/**
	 * PrintS the Movie Name, Publication Year, and Country from Movie and Ip tables
	 * where country is America.
	 * 
	 * @return the value from Move and Ip tables.
	 */
	public static String Publication() {
		return "SELECT m.MovieName, m.PublicationYear, i.Country " 
				+ "FROM Movie m, Ip i "
				+ "WHERE m.MovieName = i.MovieName " 
				+ "AND Country = 'America' ";
	}

	/**
     * Prints the MovieID, Genre, BasedOn, Subtitles, and Rating from Genre and Rating tables
     * where Subtitles is Yes.
     * 
     * @return the value from Genre and Rating tables.
     */
    public static String MoreDetails() {
        return "SELECT m.MovieID, m.Genre, m.BasedOn, m.Subtitles, i.Rating " 
                + "FROM Genre m, Rating i "
                + "WHERE m.MovieID = i.MovieID " 
                + "AND Subtitles = 'Yes' ";
    }
    
	/**
	 * Using query LIKE to return data if tables contain specific word lines.
	 * 
	 * @return the value where language is Korean and Director is 'Rod'.
	 */
	public static String OrQuery() {
		return "SELECT ID, Director " 
				+ "FROM Film " 
				+ "WHERE Language = 'Korean' " 
				+ "OR Director LIKE '%Rod%' ";
	}
	
	public static String Sorting() {
		return "SELECT * "
				+ "FROM IP"
				+ " ORDER BY Top DESC ";
		
	}
}
