package connection;

public class MasterClass{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConnectionClass cc = new ConnectionClass("BAC","HAT","CAT");
		cc.makeConnection();
		cc.runQuery();
	}

}
