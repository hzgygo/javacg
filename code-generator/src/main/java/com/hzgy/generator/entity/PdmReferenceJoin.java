package com.hzgy.generator.entity;

public class PdmReferenceJoin extends Entity {
	private static final long serialVersionUID = 5424269012341104947L;
	private String attributeId;
	private String objectId ;
	private PdmTableColumn parentTableColumn;
	private PdmTableColumn childTableColumn;
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

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public PdmTableColumn getParentTableColumn() {
		return parentTableColumn;
	}

	public void setParentTableColumn(PdmTableColumn parentTableColumn) {
		this.parentTableColumn = parentTableColumn;
	}

	public PdmTableColumn getChildTableColumn() {
		return childTableColumn;
	}

	public void setChildTableColumn(PdmTableColumn childTableColumn) {
		this.childTableColumn = childTableColumn;
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
