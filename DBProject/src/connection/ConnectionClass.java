package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConnectionClass {
	
	String connDetails;
	String username;
	String password;
	Connection con;
	public ConnectionClass(String connDetails, String username, String password) {
		//super();
		this.connDetails = connDetails;
		this.username = username;
		this.password = password;
	}
	
    public void makeConnection()
    {
    	try {
			con = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1521:xe","hr","password23");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }
    
    public void runQuery(){
    	Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

    	ResultSet rs = null;
		try {
			rs = stmt.executeQuery("select * from employees where rownum<=5");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
    	try {
    		System.out.println("Number of records - "+rs.getFetchSize());
    		JOptionPane.showMessageDialog(null,"Number of records - "+rs.getFetchSize());
			while(rs.next())
			JOptionPane.showMessageDialog(null,rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    	try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
	

}
