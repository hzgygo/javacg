package com.hzgy.generator.entity;

import java.util.ArrayList;

public class PdmReference extends Entity {
	private static final long serialVersionUID = -8801792870836202645L;
	private String attributeId;
	private String objectId;
	private String name;
	private String originalCode;
	private String code;
	private String constraintName;
	private PdmTable parentTable;
	private PdmTable childTable;
	private Integer updateConstraint = 1;
	private Integer deleteConstraint = 1;
	private String implementationType;
	private ArrayList<PdmReferenceJoin> joins = new ArrayList<PdmReferenceJoin>();
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

	public PdmTable getParentTable() {
		return parentTable;
	}

	public void setParentTable(PdmTable parentTable) {
		this.parentTable = parentTable;
	}

	public PdmTable getChildTable() {
		return childTable;
	}

	public void setChildTable(PdmTable childTable) {
		this.childTable = childTable;
	}

	public Integer getUpdateConstraint() {
		return updateConstraint;
	}

	public void setUpdateConstraint(Integer updateConstraint) {
		this.updateConstraint = updateConstraint;
	}

	public Integer getDeleteConstraint() {
		return deleteConstraint;
	}

	public void setDeleteConstraint(Integer deleteConstraint) {
		this.deleteConstraint = deleteConstraint;
	}

	public String getImplementationType() {
		return implementationType;
	}

	public void setImplementationType(String implementationType) {
		this.implementationType = implementationType;
	}

	public ArrayList<PdmReferenceJoin> getJoins() {
		return joins;
	}

	public void setJoins(ArrayList<PdmReferenceJoin> joins) {
		this.joins = joins;
	}

	public void addReferenceJoin(PdmReferenceJoin pdmReferenceJoin) {
		joins.add(pdmReferenceJoin);
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
