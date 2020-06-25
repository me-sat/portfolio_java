package dto;

import java.sql.Date;

//データベースから受け取る値を格納する
public class Dto {

	//データベースのテーブルのカラム
	private String DO;
	private Date  DEADLINE;
	private boolean YET;
	private int id;
	
	
	

	//ゲッターとセッター

	public String getDO() {
		return DO;
	}
	public void setDO(String dO) {
		DO = dO;	
	}


	public Date getDEADLINE() {
		return DEADLINE;
	}
	public void setDEADLINE(Date dEADLINE) {
		DEADLINE = dEADLINE;
	}
	
	

	public boolean isYET() {
		return YET;
	}
	public void setYET(boolean yET) {
		YET = yET;
	}
	
	
	
	
	public int getId() {
		return id;
	}
	
	public void setID(int Id) {
		id = Id;
		
	}
	
	
	
}
