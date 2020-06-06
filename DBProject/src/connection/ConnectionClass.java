package connection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import javax.swing.JOptionPane;

public class ConnectionClass {
	
	String connDetails;
	String username;
	String password;
	Connection con;
	File file = new File(System.getProperty("user.dir")+"/log.txt");
	BufferedWriter  output = null;
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
("jdbc:oracle:thin:@localhost:1521:xe",System.getProperty("system.UserId")
		,System.getProperty("system.Password"));
    		//("jdbc:oracle:thin:@localhost:1521:xe","hr","password23#@");
		} catch (SQLException e) {
			e.printStackTrace(); System.getProperty("system.UserId");
		}
    	
    }
    
    public void runQuery(){
    	Statement stmt = null;
    	try {
			output = new BufferedWriter(new FileWriter(file));
		} catch (IOException e4) {
			// TODO Auto-generated catch block
			e4.printStackTrace();
		}
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
    		//JOptionPane.showMessageDialog(null,"Number of records - "+rs.getFetchSize());
			while(rs.next()){
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
			try {
				output.write("Hi");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			//JOptionPane.showMessageDialog(null,rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
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
