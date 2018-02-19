package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection_FolderGroup {

	public String DBConnection(String query, String ColumnName) throws SQLException  {
		
		System.out.println("-------- Oracle JDBC Connection Testing ------");

        try {

            Class.forName("oracle.jdbc.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
           

        }

        System.out.println("Oracle JDBC Driver Registered!");

        Connection conn = null;
        String Deal_Id = null;

        try {

            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@lil-ux-dbdbga1:1536/fsguxd.world", "UAPSING", "Bunge123");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            

        }

        if (conn != null) {
       
             conn.clearWarnings();
             Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery(query);
            
                          
           System.out.println("");
             while(rs.next())
             {
            	 Deal_Id = rs.getString(ColumnName);
//                 System.out.print(Deal_Id);
             }
             rs.close();
        
             
        } else {
            System.out.println("Failed to make connection!");
        }
		return Deal_Id;
	
    
	}

}
