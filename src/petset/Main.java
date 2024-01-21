package petset;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
	/**
	 * Launch the application.
	 * @throws SQLException 
	 */	
	public static void main(String[] args)throws SQLException{
		// TODO Auto-generated method stub

		String user, pass;
        user = "postgres";
        pass = "12345";

        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/VT_PetSet", user, pass);
        
        login login1 = new login (conn);
        login1.setVisible(true);
        
	}
}
