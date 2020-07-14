package com.hzgy.generator.entity;

import java.io.Serializable;

public class Entity implements Serializable {

	private static final long serialVersionUID = 5574468630643899188L;

	protected Long id;
	
	protected Long generatedKey;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGeneratedKey() {
		return generatedKey;
	}

	public void setGeneratedKey(Long generatedKey) {
		this.generatedKey = generatedKey;
	}
}
