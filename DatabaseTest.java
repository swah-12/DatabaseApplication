package database;
/**
 * This class is intend to test all queries we create
 * 
 * @authors Jonas Knochelmann, Shel Wah, and Irelan Bailey
 *
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Demonstrates how to create a database, drop and insert tables,
 * and access data from the database
 * @param args
 */
public class DatabaseTest {
	private static String databaseURL = "jdbc:derby:FirstDatabase;create=true";


	public static void main(String[] args) {
		Connection connection;
		Statement statement;
		try {
			connection = DriverManager.getConnection(databaseURL);
			statement = connection.createStatement();
			
			// Movie table
			statement.execute(Movie.dropTable());
			statement.execute(Movie.createTable());
			statement.execute(Movie.insertData());
			
			ResultSet movieResults = statement.executeQuery(Movie.allData());

			// Film table
			statement.execute(Film.dropTable());
			statement.execute(Film.createTable());
			statement.execute(Film.insertData());
			
			statement.execute(Film.addData("Nobody", "English"));
			
			ResultSet filmResults = statement.executeQuery(Film.allData());
			// Ip table
			statement.execute(Ip.dropTable());
			statement.execute(Ip.createTable());
			statement.execute(Ip.insertData());
			
			statement.execute(Ip.addData(4, "Test Move", 0, "MehBrain"));
			
			ResultSet ipResults = statement.executeQuery(Ip.allData());

			// Rating table
			statement.execute(Rating.dropTable());
			statement.execute(Rating.createTable());
			statement.execute(Rating.insertData());
			
			statement.execute(Rating.addData(14, (float) 0.1, 1));
			
			ResultSet ratingResults = statement.executeQuery(Rating.allData());

			// Genre table
			statement.execute(Genre.dropTable());
			statement.execute(Genre.createTable());
			statement.execute(Genre.insertData());
			
			statement.execute(Genre.addData(14, "Action", "Nothing", "English"));
			
			ResultSet genreResults = statement.executeQuery(Genre.allData());
			
			// MultipleQueries table
			//ResultSet publicationResults = statement.executeQuery(Genre.allData());

			ResultSet orQueryResults = statement.executeQuery(MultipleQueries.OrQuery());
			
			//GUI testing
			GUI gui = new GUI(statement, new String[] {"Tables", "Queries"},
									   new String[][] {{"Movie", "Film", "Genre", "Ip", "Rating"}, {"Or Query", "Publication", "Sorting", "MoreDetails"}},
									   new String[][] {{Movie.allData(), Film.allData(), Genre.allData(), Ip.allData(), Rating.allData()},
										   {MultipleQueries.OrQuery(), MultipleQueries.Publication(), MultipleQueries.Sorting(), MultipleQueries.MoreDetails()}});

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Prints a table and creates the size of columns.
	 * 
	 * @param results
	 * @param columnWidth
	 */
	static void printTable(ResultSet results, int columnWidth) {
		try {
			ResultSetMetaData meta = results.getMetaData();
			int columnCount = meta.getColumnCount();
			StringBuilder sb = new StringBuilder();

			for (int i = 1; i <= columnCount; i++) {
				sb.append(stringForceLength(meta.getColumnName(i), columnWidth));

				if (i != columnCount) {
					sb.append("|");
				}
			}
			System.out.println(sb);

			sb.setLength(0);
			for (int i = 1; i <= columnCount * columnWidth + columnCount - 1; i++) {
				sb.append("-");
			}
			System.out.println(sb);

			while (results.next()) {
				sb.setLength(0);
				for (int i = 1; i <= columnCount; i++) {
					sb.append(stringForceLength(results.getString(i), columnWidth));

					if (i != columnCount) {
						sb.append("|");
					}
				}

				System.out.println(sb);
			}

			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Forces strings that are printed to cut off at a certain length.
	 * 
	 * @param string
	 * @param force
	 * @return
	 */
	private static String stringForceLength(String string, int force) {
		StringBuilder sb = new StringBuilder();
		if (string != null) {
			sb.append(string);
		} else {
			sb.append("null");
		}

		int oLen = sb.length();
		if (oLen < force) {
			for (int i = 0; i < force - oLen; i++) {
				sb.append(" ");
			}
			return sb.toString();
		} else {
			return sb.substring(0, force);
		}
	}

	/**
	 * Prints the results of the Movie table.
	 * 
	 * @throws SQLException
	 */
	private static void printMovieData(ResultSet results) throws SQLException {

		System.out.printf("%-5s %-30s %-30s %-20s %-20s%n", "ID", "Movie Name", "Description", "Publication Year",
				"Source");
		System.out.println(
				"----------------------------------------------------------------------------------------------------");
		while (results.next()) {

			int id = results.getInt("ID");
			String movieName = results.getString("MovieName");
			String description = results.getString("Description");
			int pubYear = results.getInt("PublicationYear");
			String source = results.getString("Source");

			System.out.printf("%-5d %-30s %-30s %-20d %-20s%n", id, movieName, description, pubYear, source);
		}
		System.out.println("");
	}

	/**
	 * Prints the results of the Film table.
	 * 
	 * @throws SQLException
	 */
	private static void printFilmData(ResultSet results) throws SQLException {

		System.out.printf("%-5s %-20s %-20s%n", "ID", "Director", "Language");
		System.out.println("------------------------------------");
		while (results.next()) {
			int id = results.getInt("ID");
			String director = results.getString("Director");
			String language = results.getString("Language");

			System.out.printf("%-5d %-20s %-20s%n", id, director, language);
		}
		System.out.println("");
	}

	/**
	 * Prints the results of the Ip table.
	 * 
	 * @throws SQLException
	 */
	private static void printIpData(ResultSet results) throws SQLException {

		System.out.printf("%-5s %-30s %-10s %-15s%n", "Top#", "Movie Name", "Episodes", "Country");
		System.out.println("-------------------------------------------------------");
		while (results.next()) {
			int top = results.getInt("Top");
			String movieName = results.getString("MovieName");
			int episode = results.getInt("Episodes");
			String country = results.getString("Country");

			System.out.printf("%-5d %-30s %-10d %-15s%n", top, movieName, episode, country);
		}
		System.out.println("");
	}

	/**
	 * Prints the results of the Genre table.
	 * 
	 * @throws SQLException
	 */
	private static void printGenreData(ResultSet results) throws SQLException {
		System.out.printf("%-4s %-8s %-10s %-12s %-8s%n", "ID", "MoveID", "Genre", "BasedOn", "Subtitles");
		System.out.println("-----------------------------------------------");
		while (results.next()) {
			int id = results.getInt("ID");
			int movieID = results.getInt("MovieID");
			String genre = results.getString("Genre");
			String basedOn = results.getString("BasedOn");
			String subtitles = results.getString("Subtitles");

			System.out.printf("%-4s %-8s %-10s %-12s %-8s%n", id, movieID, genre, basedOn, subtitles);
		}
		System.out.println();
	}

	/**
	 * Prints the results of the Rating table.
	 * 
	 * @throws SQLException
	 */
	private static void printPublicationData(ResultSet results) throws SQLException {
		System.out.printf("%-30s %-10s %-20s %n", "Movie Name", "Publication Year", "Country");
		System.out.println("--------------------------------------------------------");
		while (results.next()) {
			String name = results.getString("MovieName");
			int pubYear = results.getInt("PublicationYear");
			String country = results.getString("Country");

			System.out.printf("%-30s %-10d %-20s %n", name, pubYear, country);
		}
		System.out.println();
	}

	/**
	 * Prints the results of the Rating table.
	 * 
	 * @throws SQLException
	 */
	private static void printRatingData(ResultSet results) throws SQLException {
		System.out.printf("%-5s %-8s %-7s %-7s%n", "ID", "MovieID", "Rating", "AmountOfRatings");
		System.out.println("--------------------------------------");
		while (results.next()) {
			int id = results.getInt("ID");
			int movieID = results.getInt("MovieID");
			Double rating = results.getDouble("Rating");
			int amountOfRatings = results.getInt("AmountOfRatings");

			System.out.printf("%-5s %-8s %-7s %-,7d%n", id, movieID, rating, amountOfRatings);
		}
		System.out.println();
	}

	/**
	 * Prints the results of the OrQuery method. Using WHERE clause, & OR query
	 * 
	 * @throws SQLException
	 */
	private static void printOrQueryData(ResultSet results) throws SQLException {
		System.out.printf("%-5s %-15s%n", "ID", "Director");
		System.out.println("-----------------------");
		while (results.next()) {
			int id = results.getInt("ID");
			String director = results.getString("Director");

			System.out.printf("%-5d %-15s%n", id, director);
		}
		System.out.println();
	}
	
	private static void printMoreDetailsQueryData(ResultSet results) throws SQLException {
		System.out.printf("%-5s %-8s %-7s %-7s%n", "MovieID", "Genre", "BasedOn", "Subtitles", "Rating");
		System.out.println("-----------------------");
		while (results.next()) {
			int id = results.getInt("MovieID");
			String genre = results.getString("Genre");
			String basedOn = results.getString("BasedOn");
			String subtitles = results.getString("Subtitles");
			Double rating = results.getDouble("Rating");

			System.out.printf("%-5s %-8s %-7s %-7s%n", id, genre, basedOn, subtitles, rating);
		}
		System.out.println();
	}

}

