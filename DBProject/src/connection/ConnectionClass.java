package connection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

//import javax.swing.JOptionPane;

public class ConnectionClass {
	
	String connDetails;
	String username;
	String password;
	String envData;
	Connection con;
	
	static Logger log = Logger.getLogger(ConnectionClass.class); 
	
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
			readFileAsString();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	try {
    		log.info("UserId-"+getUserid());
    		log.info("Pwd-"+getPwd());
con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
		getUserid(),getPwd());
    		//("jdbc:oracle:thin:@localhost:1521:xe","hr","password23#@");
		} catch (SQLException e) {
			e.printStackTrace(); System.getProperty("system.UserId");
		}
    	
    }
    
    public void readFileAsString()throws Exception 
    { 
    	String fileName=System.getProperty("user.dir")+"/User_Details.txt";
    	envData = new String(Files.readAllBytes(Paths.get(fileName)));
    }
    
    public String getUserid(){
    	String envDetails[]=envData.split("\n");
    	String userDetails[]=envDetails[0].split(":");
    	return userDetails[1].trim();
    }
    
    public String getPwd(){
    	String envDetails[]=envData.split("\n");
    	String pwdDetails[]=envDetails[1].split(":");
    	return pwdDetails[1].trim();
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
    		log.info("Number of records - "+rs.getFetchSize());
    		System.out.println("Number of records - "+rs.getFetchSize());
    		//JOptionPane.showMessageDialog(null,"Number of records - "+rs.getFetchSize());
			while(rs.next()){
				log.info(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
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
