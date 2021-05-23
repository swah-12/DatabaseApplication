# DatabaseApplication
@authors Jonas Knochelmann, Shel Wah, and Irelan Bailey
This project allows the user to do the following: add a movie, remove a movie, and list all movies' records. It also allows the user to change a movie record (e.g. to allow a user to change the movie and/or to change the position) Users can query the data in at least three different ways: Or, AND, ORDER, and LIKE. All the queries in this project provide meaningful information in the movie domain.

## Rating.java
Rating.java class provides SQL statements to create, drop, and fill a Rating table and to access data from the Rating table. The Rating table include columns ID, MovieID, Rating, and AmountOfRatings.

## MultipleQueries.java
Creating different queries, JOIN, AND, OR, LIKE from multiple tables inside the MultipleQueries.java. MultipleQueries.java print the Movie Name, Publication Year, and Country from Movie and Ip tables where country is America.

## Movie.java
Movie.java include SQL statements to create, drop, and fill a Movie table and to access data from the Movie table. It's creates a SQL table named Movie with the columns, ID, MovieName, Description, PublicationYear, and Source.

## Ip.java
The class Ip.java provides SQL statements to create, drop, and fill a Ip table and to access data from the Ip table. It's creates a SQL table named Ip with the columns Top, MovieName, Episodes, and Country.

## GUI.java
@author Jonas K
The class GUI.java displays the database's GUI, and provides limited ability to edit table.

## Genre.java
Genre.java class provides SQL statements to create, drop, and fill a Genre table and to access data from the Genre table. It's creates a SQL table named Genre with the columns ID, MovieID, Genre, BasedOn, and Subtitles.

## Film.java
Provides SQL statements to create, drop, and fill a Film table and to access data from the Film table. It's creates a SQL table named Film with the columns ID, Director, and Language.

## DatabaseTest.java
This class is intend to test all queries we create. It demonstrates how to create a database, drop and insert tables, and access data from the database.

