package sondage;

import java.io.Serializable;
import java.util.ArrayList;

public class SondageObj implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String question,date,owner;
	private ArrayList<String> reponses;
	private boolean multiple;
	private int total,id;
	
	public SondageObj(int id, String owner, String question, String reponses,boolean multiple, String date, int total) {
		this.question=question;
		this.owner=owner;
		
		this.reponses = new ArrayList<String>();
		for(String a : reponses.split(";")) {
			this.reponses.add(a);
		}
		
		this.date = date;
		this.multiple = multiple;
		this.total = total;
		this.id = id;
	}
	
	public String getQuestion() {return this.question;}
	public ArrayList<String> getReponses() {return this.reponses;}
	public String getDate() {return this.date;}
	public int getTotal() {return this.total;}
	public boolean isMultiple() {return this.multiple;}
	public String getOwner() {return this.owner;}
	public int getId() {return this.id;}
	
}
