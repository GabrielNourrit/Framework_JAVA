package BD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Insertion {
	private String table;
	private Object[] intoValues;
	private List<String> values;

	/**
	 * @param table le nom de la table ou selectionner
	 * @param values la list des noms des valeurs ‡ inserer (ex:id)
	 * @param intoValues la liste des valeurs (10 pour un int, '10' pour une chaine)
	 */
	public Insertion (String table, Object[] values,Object[] intoValues) {
		this.table = table;
		this.intoValues = intoValues;
		List<String> l = new ArrayList<String>();
		for(Object t : values)
			l.add((String)t);
		this.values = l;
	}
	
	/**
	 * execute la requete INSERT
	 * @param conn la connexion
	 * @throws SQLException
	 */
	public void inserer(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement() ;
		String tmpValues = values.size()==0?"":String.join(",", values);
		String requete = "insert into " + this.table+"("+tmpValues+")" + " values(";
		for (int i = 0; i < intoValues.length; i++) {
			System.out.println(intoValues[i]);
			if (i == intoValues.length-1) {
				if (intoValues[i] instanceof Integer || intoValues[i] instanceof Double) requete+= intoValues[i]+")";
				if (intoValues[i] instanceof String){
					if(intoValues[i].toString().indexOf(".nextval")!=-1 || intoValues[i].toString().indexOf(".lastval")!=-1 || intoValues[i].toString().indexOf("TO_DATE")!=-1)

					if(intoValues[i].toString().indexOf(".nextval")!=-1 || intoValues[i].toString().indexOf("TO_DATE")!=-1)

						requete+=intoValues[i]+")";
					else
						requete+="'"+intoValues[i]+"')";
				}
			}
			else {
				if (intoValues[i] instanceof Integer || intoValues[i] instanceof Double) requete+= intoValues[i]+",";
				if (intoValues[i] instanceof String) {
					if(intoValues[i].toString().indexOf(".nextval")!=-1 ||intoValues[i].toString().indexOf(".lastval")!=-1 || intoValues[i].toString().indexOf("TO_DATE")!=-1)

					if(intoValues[i].toString().indexOf(".nextval")!=-1 || intoValues[i].toString().indexOf("TO_DATE")!=-1)
					requete+=intoValues[i]+",";
					else
						requete+="'"+intoValues[i]+"',";
				}
			}	
		}
		System.out.println(requete);
		int rs2 = stmt.executeUpdate(requete);
		System.out.println(rs2 + " lignes inserees\n");
	}
	
	
}
