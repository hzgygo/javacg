package com.hzgy.generator.entity;

import java.util.ArrayList;

public class PhysicalDiagram extends Entity{
	private static final long serialVersionUID = -3886651454205624705L;
	private String attributeId;
	private String name;
	private String code;
	private ArrayList<PdmTable> tables = new ArrayList<PdmTable>();
	private String creationDate ;
	private String creator ;
	private String modificationDate ;
	

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

	public ArrayList<PdmTable> getTables() {
		return tables;
	}

	public void setTables(ArrayList<PdmTable> tables) {
		this.tables = tables;
	}

	public void addTable(PdmTable table) {
		tables.add(table);
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

}
