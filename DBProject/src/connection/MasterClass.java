package connection;

import java.io.IOException;

public class MasterClass{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ConnectionClass cc = new ConnectionClass("BAC","HAT","CAT");
		cc.makeConnection();
		cc.runQuery();
	}

}
