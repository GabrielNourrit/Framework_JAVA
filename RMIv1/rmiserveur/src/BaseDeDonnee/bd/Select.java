package BaseDeDonnee.bd;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Select {

	private Object table;
	private String[] condition;
	private List<String> champs;

	/**
	 * @param pTable le nom de la table ou selectionner
	 * @param pChamps la liste des champs a selectionner
	 * @param pCondition la liste des conditions (ex: id<10)
	 */
	public Select(String pTable, Object[] pChamps, String[] pCondition){
		this.table=pTable;
		List<String> l = new ArrayList<String>();
		for(Object t : pChamps)
			l.add((String)t);
		this.champs=l;
		this.condition=pCondition;
	}
	
	/**
	 * Execute la requete SELECT et renvoie le r�sultat sous forme de liste
	 * @param conn
	 * @return une liste d'objet contenant le r�sultat de la requ�te
	 * Resultat avec N colonnes et M lignes
	 * de la forme [[nom colonne1,...,nom colonneN],[valeurLigne11,...,valeurLigne1N],[valeurLigneM1,...,valeurLigneMN]]
	 * @throws SQLException
	 */
	public ResultSet select(Connection conn) throws SQLException{
		Statement stmt = conn.createStatement() ;
		String tmpQuery = "SELECT ";
		tmpQuery+=this.champs.size()==0?"*":String.join(",", this.champs);
		tmpQuery+=" FROM "+this.table;
		String tmpCondition = "";
		for(int i = 0; i< condition.length; i++) {
			if (i == condition.length-1) tmpCondition+=condition[i];
			else tmpCondition+=condition[i]+" AND ";
		}
		if (condition.length!=0) tmpQuery+=" WHERE "+tmpCondition;
		System.out.println(tmpQuery);
		ResultSet rs = stmt.executeQuery(tmpQuery);
		return rs;
	}
}
