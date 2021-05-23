package database;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.LinkedList;

import javax.swing.*;

/**
 * Displays the database's GUI, and provides limited ability to edit table
 * 
 * @author Jonas K
 */
public class GUI { 
	private static int WIDTH = 800;
	private static int HEIGHT = 600;
	private static String SQL_ALL_DATA = "SELECT * FROM ";
	
    JFrame frame; 
    
    //Menu
    JMenuBar menuBar;
    JMenu[] queryMenus;
    QueryMenuItem[][] queryMenuItems;
    JMenu editMenu;
    
    //Table Stuff
    DisplayTable table; 
    JScrollPane scrollPane;
    
    //SQL Stuff
    Statement statement;
    String[][] queries;
    
    //State
    String currentTable;
    QueryTable tableArr;
    
    GUI(Statement statement, String[] menuNames, String[][] queryMenuNames, String[][] queries) { 
    	this.statement = statement;
    	this.queries = queries;
    	
        frame = new JFrame(); 
        frame.setTitle("Movie Table"); 
        frame.setSize(WIDTH, HEIGHT);
        frame.setResizable(false); 
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        scrollPane = new JScrollPane();
        frame.add(scrollPane);
        try {
        	currentTable = queries[0][0];
			displayQuery(new QueryTable(statement.executeQuery(queries[0][0])));
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        addMenu(menuNames, queryMenuNames);
        
        frame.setVisible(true); 
        
    }
    
    private void addMovieDialog() {
    	try {
	    	String movieName = JOptionPane.showInputDialog("Movie Name: ");
	    	String description = JOptionPane.showInputDialog("Description: "); 
	    	int publicationYear = Integer.parseInt(JOptionPane.showInputDialog("Publication Year: ")); 
	    	String source = JOptionPane.showInputDialog("Source: "); 
	    	
	    	try {
	    		statement.execute(Movie.addData(movieName, description, publicationYear, source));
				displayQuery(new QueryTable(statement.executeQuery(Movie.allData())));
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		} finally {
			return;
		}
    }
    
    private void editMovieDialog() {
    	try {
	    	int movieID = Integer.parseInt(JOptionPane.showInputDialog("Edit Movie ID: "));
	    	String movieName = JOptionPane.showInputDialog("New Movie Name: ");
	    	String description = JOptionPane.showInputDialog("New Description: "); 
	    	int publicationYear = Integer.parseInt(JOptionPane.showInputDialog("New Publication Year: ")); 
	    	String source = JOptionPane.showInputDialog("New Source: "); 
	    	
	    	try {
	    		statement.executeUpdate(Movie.editData(movieID, movieName, description, publicationYear, source));
				displayQuery(new QueryTable(statement.executeQuery(Movie.allData())));
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		} finally {
			return;
		}
    }
    
    private void deleteMovieDialog() {
    	try {
			int movieID = Integer.parseInt(JOptionPane.showInputDialog("Delete Movie ID: "));
	    	
	    	try {
	    		statement.executeUpdate(Movie.deleteData(movieID));
				displayQuery(new QueryTable(statement.executeQuery(Movie.allData())));
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	} finally {
    		return;
    	}
    }
    
    private void addMenu(String[] menuNames, String[][] queryMenuNames) {
    	menuBar = new JMenuBar();

    	queryMenus = new JMenu[menuNames.length];
    	queryMenuItems = new QueryMenuItem[menuNames.length][];
    	for(int i = 0; i < menuNames.length; i++) {
    		queryMenus[i] = new JMenu(menuNames[i]);
    		queryMenuItems[i] = new QueryMenuItem[queryMenuNames[i].length];
    		for(int j = 0; j < queryMenuNames[i].length; j++) {
    			queryMenuItems[i][j] = new QueryMenuItem(queryMenuNames[i][j], i, j);
    			queryMenuItems[i][j].addActionListener(queryMenuItems[i][j]);
    			queryMenus[i].add(queryMenuItems[i][j]);
    		}
    		
    		menuBar.add(queryMenus[i]);
    	}
    	
    	editMenu = new JMenu("Edit");
    	EditMenuItem item;
		
    	item = new EditMenuItem("Add Movie", "Add");
    	item.addActionListener(item);
    	editMenu.add(item);
    			
    	item = new EditMenuItem("Edit Movie", "Edit");
    	item.addActionListener(item);
    	editMenu.add(item);
    	
    	item = new EditMenuItem("Delete Movie", "Delete");
    	item.addActionListener(item);
    	editMenu.add(item);
    	
    	menuBar.add(editMenu);
    	
    	frame.setJMenuBar(menuBar);
    }
    
    public void displayQuery(QueryTable tableArr) {
    	this.tableArr = tableArr;
    	
    	frame.remove(scrollPane);
    	
        table = new DisplayTable(tableArr.data, tableArr.columnNames); 
        table.setBounds(0, 0, WIDTH, HEIGHT); 
  
        scrollPane = new JScrollPane(table);
        
        frame.add(scrollPane);
        frame.revalidate();
    }
    
    //Private classes
    private class DisplayTable extends JTable {
    	 private static final long serialVersionUID = 1L;
    	 
    	 DisplayTable(String[][] data, String[] columnNames) {
    		 super(data, columnNames);
    		 this.getTableHeader().setReorderingAllowed(false);
    	 }
    	 
    	 @Override
         public boolean isCellEditable(int row, int column) {                
                 return false;
         };
    }
    
    private class EditMenuItem extends JMenuItem implements ActionListener {
		private static final long serialVersionUID = 1L;
    	private String item;
		
    	EditMenuItem(String title, String item) {
			super(title);
			this.item = item;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			switch(item) {
			case "Add":
				addMovieDialog();
				break;
			case "Edit":
				editMovieDialog();
				break;
				
			case "Delete":
				deleteMovieDialog();
				break;
				
			}
		}
    }
    
    private class QueryMenuItem extends JMenuItem implements ActionListener {
		private static final long serialVersionUID = 1L;
    	private int menu;
    	private int menuItem;
		
		QueryMenuItem(String title, int menu, int menuItem) {
			super(title);
			this.menu = menu;
			this.menuItem = menuItem;
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				displayQuery(new QueryTable(statement.executeQuery(queries[menu][menuItem])));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
    }
    
    private class QueryTable {
    	String[] columnNames;
    	String[][] data;
    	
    	public QueryTable(ResultSet results) {
	    	try {
				ResultSetMetaData meta = results.getMetaData();
				int columnCount = meta.getColumnCount();
				
				String[] columnNames = new String[columnCount];
				
				for (int i = 1; i <= columnCount; i++) {
					columnNames[i - 1] = meta.getColumnName(i);
				}
				
	        	LinkedList<String[]> dataList = new LinkedList<String[]>();
	        	while (results.next()) {
	        		String[] row = new String[columnCount];
					for (int i = 1; i <= columnCount; i++) {
						row[i - 1] = results.getString(i);
					}
					dataList.add(row);
					
				}
	        	String[][] data = new String[dataList.size()][columnCount];
	        	
	        	for(int i = 0; i < data.length; i++) {
	        		String[] row = dataList.pop();
	        		
	        		for(int j = 0; j < columnCount; j++) {
	        			data[i][j] = row[j];
	        		}
	        	}
	            
	        	this.columnNames = columnNames;
	        	this.data = data;
	
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	}
    }
}
