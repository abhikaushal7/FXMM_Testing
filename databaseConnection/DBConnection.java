package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

	public static void main(String[] args) throws SQLException {
		
		System.out.println("-------- Oracle JDBC Connection Testing ------");

        try {

            Class.forName("oracle.jdbc.OracleDriver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
            return;

        }

        System.out.println("Oracle JDBC Driver Registered!");

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@lil-ux-dbdbga1:1536/fsguxd.world", "UAPSING", "Glad375871");

        } catch (SQLException e) {

            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return;

        }

        if (conn != null) {
       
             conn.clearWarnings();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select * from FX_OBJECT.DEAL_OUTRIGHT_FORWARD");
             System.out.println("DEAL_ID     DEAL_ID     CURRENCY1       CURRENCY2       VALUE_DATE      SPOT_RATE         FORWARD_RATE       FORWARD_POINTS");
             System.out.println("");
             System.out.println("====     ==========      ========        =======        =======          =======           =======              =======");
                          
             System.out.println("");
             while(rs.next())
             {
                 System.out.print(rs.getString("DEAL_ID"));
                 System.out.print("      ");
                 System.out.print(rs.getString("DEAL_ID"));
                 System.out.print("      ");
                 System.out.print(rs.getString("CURRENCY1"));
                 System.out.print("      ");
                 System.out.print(rs.getString("CURRENCY2"));
                 System.out.print("      ");
                 System.out.print(rs.getString("VALUE_DATE"));
                 System.out.print("      ");
                 System.out.print(rs.getString("SPOT_RATE"));
                 System.out.print("      ");
                 System.out.print(rs.getString("FORWARD_RATE"));
                 System.out.print("      ");
                 System.out.print(rs.getString("FORWARD_POINTS"));
                 System.out.println("");
             }
             rs.close();
        
        
        
        
        
        
        } else {
            System.out.println("Failed to make connection!");
        }
    
	}

}
