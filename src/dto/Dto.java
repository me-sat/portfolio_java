package dto;

import java.sql.Date;

//�f�[�^�x�[�X����󂯎��l���i�[����
public class Dto {

	//�f�[�^�x�[�X�̃e�[�u���̃J����
	private String DO;
	private Date  DEADLINE;
	private boolean YET;
	private int id;
	
	
	

	//�Q�b�^�[�ƃZ�b�^�[

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
