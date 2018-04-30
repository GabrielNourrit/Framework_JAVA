package BD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Update {

	private Object table;
	private String[] condition;
	private List<String> champs;

	/**
	 * @param pTable le nom de la table � modifier
	 * @param pChamps la liste des champs a modifier
	 * @param pCondition la liste des conditions (ex: id<10)
	 */
	public Update(String pTable, Object[] pChamps, String[] pCondition){
		this.table=pTable;
		List<String> l = new ArrayList<String>();
		for(Object t : pChamps)	l.add((String)t);
		this.champs=l;
		this.condition=pCondition;
	}
	
	/**
	 * execute la requete UPDATE
	 * @param conn la connexion
	 * @throws SQLException
	 */
	public void update(Connection conn) throws SQLException{
		Statement stmt = conn.createStatement() ;
		String tmpQuery = "UPDATE " + this.table + " SET ";
		tmpQuery+=this.champs.size()==0?"*":String.join(",", this.champs);
		String tmpCondition = "";
		for(int i = 0; i< condition.length; i++) {
			if (i == condition.length-1) tmpCondition+=condition[i];
			else tmpCondition+=condition[i]+" AND ";
		}
		if (condition.length!=0) tmpQuery+=" WHERE "+tmpCondition;
		System.out.println(tmpQuery);
		int rs = stmt.executeUpdate(tmpQuery);
		System.out.println(rs + " lignes modifiées");
	}
	
}
