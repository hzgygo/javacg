package com.hzgy.generator.entity;

import java.util.ArrayList;

public class PdmTable extends Entity{
	private static final long serialVersionUID = 4547407442411860629L;
	public String attributeId;
	private String objectId;
	private String name;
	private String originalCode;
	private String code;
	private PdmUser user;
	private String comment ;
	private ArrayList<PdmTableColumn> columns = new ArrayList<PdmTableColumn>();
	private ArrayList<PdmKey> keys = new ArrayList<PdmKey>();
	private PdmKey primaryKey;
	private ArrayList<PdmIndex> indexs = new ArrayList<PdmIndex>();
	private String creationDate;
	private String creator;
	private String modificationDate;
	private String createTime;
	private String modifyTime;

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

	public PdmUser getUser() {
		return user;
	}

	public void setUser(PdmUser user) {
		user.addTable(this);
		this.user = user;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ArrayList<PdmTableColumn> getColumns() {
		return columns;
	}

	public void setColumns(ArrayList<PdmTableColumn> columns) {
		this.columns = columns;
		for (PdmTableColumn column : columns) {
			column.setTable(this);
		}
	}

	public ArrayList<PdmKey> getKeys() {
		return keys;
	}

	public void setKeys(ArrayList<PdmKey> keys) {
		this.keys = keys;
	}

	public PdmKey getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(PdmKey primaryKey) {
		this.primaryKey = primaryKey;
	}

	public ArrayList<PdmIndex> getIndexs() {
		return indexs;
	}

	public void setIndexs(ArrayList<PdmIndex> indexs) {
		this.indexs = indexs;
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

	public void addColumn(PdmTableColumn column) {
		columns.add(column);
		column.setTable(this);
	}

	public void addKey(PdmKey key) {
		keys.add(key);
	}

	public void addIndex(PdmIndex index) {
		indexs.add(index);
	}

	public PdmTableColumn getColumn(String id) throws Exception {
		for (PdmTableColumn column : columns) {
			if (id.equals(column.getAttributeId())) {
				return column;
			}
		}
		throw new Exception("Id为：" + id + "对应的列不存在！");
	}

	public PdmKey getKey(String id) throws Exception {
		for (PdmKey key : keys) {
			if (id.equals(key.getAttributeId())) {
				return key;
			}
		}
		throw new Exception("Id为：" + id + "对应的Key不存在！");
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
