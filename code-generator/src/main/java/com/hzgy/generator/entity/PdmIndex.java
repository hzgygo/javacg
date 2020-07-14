package com.hzgy.generator.entity;

import java.util.ArrayList;

public class PdmIndex extends Entity{
	private static final long serialVersionUID = 4648519731280755959L;
	private String attributeId;
	private String name;
	private String code;
	private PdmTable table;
	private ArrayList<PdmTableColumn> columns = new ArrayList<PdmTableColumn>();
	private String creationDate ;
	private String creator ;
	private String modificationDate ;
	private String createTime;
	private String modifyTime;
	
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

	public PdmTable getTable() {
		return table;
	}

	public void setTable(PdmTable table) {
		this.table = table;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
}
