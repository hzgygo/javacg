package com.hzgy.generator.entity;

public class FieldAttribute extends Entity{
	
	private static final long serialVersionUID = 1L;
	/**名称**/
	private String name;
	/**类型**/
	private Class<?> type;
	
	public FieldAttribute(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}
}

