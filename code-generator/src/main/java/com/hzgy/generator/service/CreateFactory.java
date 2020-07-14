package com.hzgy.generator.service;

public class CreateFactory{
	
	public static CreateDatabaseService getCreateDatabaseInstance(String databaseName) {
		return new CreateDatabaseService(databaseName);
	}
	
	public static CreateClassService getCreaterClassInstance() {
		return new CreateClassService();
	}
}