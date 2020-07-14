package com.hzgy.generator.entity;

import java.util.ArrayList;

public class PdmKey extends Entity{
	private static final long serialVersionUID = 6308521372481026800L;
	private String attributeId;
	private String objectId ;
	private String name;
	private String originalCode;
	private String code;
	private String constraintName;
	private String extendedAttributesText;
	private ArrayList<PdmTableColumn> columns = new ArrayList<PdmTableColumn>();
	private String creationDate ;
	private String creator ;
	private String modificationDate ;
	

	public String getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOriginalCode() {
		return originalCode;
	}

	public void setOriginalCode(String originalCode) {
		this.originalCode = originalCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getConstraintName() {
		return constraintName;
	}

	public void setConstraintName(String constraintName) {
		this.constraintName = constraintName;
	}

	public ArrayList<PdmTableColumn> getColumns() {
		return columns;
	}

	public void setColumns(ArrayList<PdmTableColumn> columns) {
		this.columns = columns;
	}

	public void addColumn(PdmTableColumn column) {
		columns.add(column);
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

	public String getExtendedAttributesText() {
		return extendedAttributesText;
	}

	public void setExtendedAttributesText(String extendedAttributesText) {
		this.extendedAttributesText = extendedAttributesText;
	}
	
	
}
