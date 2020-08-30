package connection;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class MasterClass{
	
	static Logger log = Logger.getLogger(MasterClass.class); 
	public static void main(String[] args) throws IOException {
		//BasicConfigurator.configure();
		//PropertiesConfigurator is used to configure logger from properties file
        PropertyConfigurator.configure("log4j.properties");
        
		log.info("Execution started!");
		ConnectionClass cc = new ConnectionClass("BAC","HAT","CAT");
		cc.makeConnection();
		cc.runQuery();
		log.info("Execution ended!");
	}

}
