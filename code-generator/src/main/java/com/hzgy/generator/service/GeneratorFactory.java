package com.hzgy.generator.service;

public class GeneratorFactory {
	
	public static GeneratorDatabaseService getCreateDatabaseInstance(String databaseName) {
		return new GeneratorDatabaseService(databaseName);
	}
	
	public static GeneratorClassService getCreaterClassInstance() {
		return new GeneratorClassService();
	}
}