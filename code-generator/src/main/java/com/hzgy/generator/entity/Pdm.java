package com.hzgy.generator.entity;

import java.util.ArrayList;

public class Pdm extends Entity{
	private static final long serialVersionUID = 4922003313923752820L;
	private String pdmFilaPath;
	private String attributeId;
	private String name;
	private String code;
	private String dBMSCode;
	private String dBMSName;
	private ArrayList<PhysicalDiagram> physicalDiagrams = new ArrayList<PhysicalDiagram>();
	private ArrayList<PdmUser> users = new ArrayList<PdmUser>();
	private ArrayList<PdmTable> tables = new ArrayList<PdmTable>();
	private ArrayList<PdmReference> references = new ArrayList<PdmReference>();
	
	private String creationDate;
	private String creator ;
	private String modificationDate ;

	public String getPdmFilaPath() {
		return pdmFilaPath;
	}

	public void setPdmFilaPath(String pdmFilaPath) {
		this.pdmFilaPath = pdmFilaPath;
	}

	public String getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDBMSCode() {
		return dBMSCode;
	}

	public void setDBMSCode(String code) {
		dBMSCode = code;
	}

	public String getDBMSName() {
		return dBMSName;
	}

	public void setDBMSName(String name) {
		dBMSName = name;
	}

	public ArrayList<PhysicalDiagram> getPhysicalDiagrams() {
		return physicalDiagrams;
	}

	public void setPhysicalDiagrams(
			ArrayList<PhysicalDiagram> physicalDiagrams) {
		this.physicalDiagrams = physicalDiagrams;
	}

	public ArrayList<PdmUser> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<PdmUser> users) {
		this.users = users;
	}

	public ArrayList<PdmTable> getTables() {
		return tables;
	}

	public void setTables(ArrayList<PdmTable> tables) {
		this.tables = tables;
	}

	public ArrayList<PdmReference> getReferences() {
		return references;
	}

	public void setReferences(ArrayList<PdmReference> references) {
		this.references = references;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}

	public PdmUser getUser(String id) throws Exception {
		for (PdmUser user : users) {
			if (id.equals(user.getAttributeId())) {
				return user;
			}
		}
		throw new Exception("Id为：" + id + "对应的User不存在！");
	}

	public PdmTable getTable(String id) throws Exception {
		for (PdmTable table : tables) {
			if (id.equals(table.getAttributeId())) {
				return table;
			}
		}
		throw new Exception("Id为：" + id + "对应的Table不存在！");
	}

	public PdmReference getReference(String id) throws Exception {
		for (PdmReference reference : references) {
			if (id.equals(reference.getAttributeId())) {
				return reference;
			}
		}
		throw new Exception("Id为：" + id + "对应的Reference不存在！");
	}
}
