package com.hibernate.resource;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CodeGroup")
public class CodeGroup 
{
	@Id
	//@SequenceGenerator(name="seq_codeGroup",sequenceName="SEQ_CODEGROUP",initialValue=999)
	//@GeneratedValue(strategy=GenerationType.IDENTITY,generator="seq_codeGroup")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="groupID")
	private int groupID;
	private String groupCode;
	private String groupDesc;
	private boolean groupStatus;
	
	public CodeGroup()
	{
		
	}
	public CodeGroup(int groupID,String groupCode,String groupDesc,Boolean groupStatus)
	{
		this.groupID=groupID;
		this.groupCode=groupCode;
		this.groupDesc=groupDesc;
		this.groupStatus=groupStatus;
	}
	public int getGroupID() {
		return groupID;
	}
	public void setGroupID(int groupID) {
		this.groupID = groupID;
	}
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getGroupDesc() {
		return groupDesc;
	}
	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
	public boolean isGroupStatus() {
		return groupStatus;
	}
	public void setGroupStatus(boolean groupStatus) {
		this.groupStatus = groupStatus;
	}
	
}
