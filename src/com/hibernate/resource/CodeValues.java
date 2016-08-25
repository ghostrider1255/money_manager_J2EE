package com.hibernate.resource;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CodeValues")
public class CodeValues 
{
	@Id
	//@SequenceGenerator(name="seq_codeValue",sequenceName="SEQ_CODEVALUE",initialValue=999)
	//@GeneratedValue(strategy=GenerationType.IDENTITY,generator="seq_codeValue")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="codeID")
	private int codeID;
	private String codeName;
	private String codeDesc;
	@OneToOne(cascade = CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name="groupID")
	private CodeGroup codeGroup;
	
	public CodeValues()
	{
		
	}
	public int getCodeID() {
		return codeID;
	}
	public void setCodeID(int codeID) {
		this.codeID = codeID;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getCodeDesc() {
		return codeDesc;
	}
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
	public CodeGroup getCodeGroup() {
		return codeGroup;
	}
	public void setCodeGroup(CodeGroup codeGroup) {
		this.codeGroup = codeGroup;
	}
}
