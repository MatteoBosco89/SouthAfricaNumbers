package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class TelNum {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id private int id;
	private String originalNum;
	private String num;
	private boolean incorrect = false;
	private boolean modified = false;
	private String edit;
	protected final int lenghtConstraint = 11;
	
	
	public TelNum() { }
	
	public void checkNum() {
		if(this.originalNum.length() != this.lenghtConstraint) {
			this.num = this.originalNum.replaceAll("[^\\d.]", "");
			this.edit = this.originalNum.replaceAll(this.num, "");
			if(!this.num.equals(this.originalNum)) this.modified = true;
			if(this.num.length() != this.lenghtConstraint) this.incorrect = true;
		}else this.num = this.originalNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getOriginalNum() {
		return originalNum;
	}

	public void setOriginalNum(String originalNum) {
		this.originalNum = originalNum;
	}

	public boolean isIncorrect() {
		return incorrect;
	}

	public void setIncorrect(boolean incorrect) {
		this.incorrect = incorrect;
	}

	public boolean isModified() {
		return modified;
	}

	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public String getEdit() {
		return edit;
	}

	public void setEdit(String edit) {
		this.edit = edit;
	}
	
}
