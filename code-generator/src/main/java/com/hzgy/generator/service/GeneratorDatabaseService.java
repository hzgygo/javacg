package com.hzgy.generator.service;

import com.hzgy.generator.dao.impl.ColumnDao;
import com.hzgy.generator.dao.impl.TableDao;
import com.hzgy.generator.entity.*;
import com.hzgy.generator.utils.DateUtil;
import com.hzgy.generator.utils.FileUtil;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneratorDatabaseService extends BaseGeneratorService {

	static Logger logger = Logger.getLogger(GeneratorDatabaseService.class);
	/** 表信息操作Dao **/
	private TableDao tableDao;
	/** 表字段信息操作Dao **/
	private ColumnDao columnDao;
	/**数据库名称**/
	private String databaseName;

	
	private Map<String,String> specialTableMap = new HashMap<String,String>(){
		{
			put("_table","_table");
			put("_table_column","_table_column");
			put("_table_index","_table_index");
			put("_table_reference","_table_reference");
		}
	};

	public GeneratorDatabaseService(String databaseName) {
		this.databaseName = databaseName;
		this.tableDao = new TableDao(databaseName);
		this.columnDao = new ColumnDao(databaseName);
	}

	/**
	 * 验证pdm文件是否有修改，如果无修改不进行任何生成创建操作
	 * @param generatorRootPath
	 * @param pdm
	 * @return
	 */
	private Boolean validPdmModify(String generatorRootPath,Pdm pdm){
		String tmpFilePath = generatorRootPath
				+ File.separator + "src"
				+ File.separator + "main"
				+ File.separator +  "resources"
				+ File.separator + "tmp";
		//获取pdm文件，并获取器修改时间
		String pdmFilePath = pdm.getPdmFilaPath();
		File file = new File(pdmFilePath);
		String fname = file.getName();
		logger.info("开始验证名称为["+fname+"]的数据库PDM设计文档是否有修改.");
		if (fname != null){
			fname = fname.replace(".pdm","");
		}
		Long modifyDate = file.lastModified();
		//时间戳文件名的临时文件，如果该时间戳的临时文件存在说明pdm文件并没有修改
		//否则认为已经修改，生成表，验证字段，生成服务信息将会执行
		tmpFilePath += File.separator + fname + "-" + modifyDate + ".tmp";
		File tmpFile = new File(tmpFilePath);
		if (tmpFile.exists()){
			logger.info("名称为["+fname+"]的数据库PDM设计文档无修改，无需生成和更新数据库.");
			return false;
		}
		else{
			try {
				//删除该文件的其他临时文件
				File parentTmpFile = tmpFile.getParentFile();
				File files[] = parentTmpFile.listFiles();
				if (files != null) {
					for (File fileTemp:files){
						String tempFileName = fileTemp.getName();
						if (tempFileName.contains(fname)){
							fileTemp.delete();
						}
					}
				}

				//创建信息的临时文件
				tmpFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			logger.info("名称为["+fname+"]的数据库PDM设计文档已经修改，需执行生成和更新数据库操作.");
			return true;
		}
	}

	/**
	 * 创建数据库
	 * @param generatorRootPath
	 * @param pdm
	 * @param isValidModify
	 * @param isGenerateScript
	 */
	public void createDatebase(String generatorRootPath,Pdm pdm,Boolean isValidModify,Boolean isGenerateScript) {

		if (isValidModify) {
			boolean isModify = this.validPdmModify(generatorRootPath, pdm);
			if (!isModify) {
				return;
			}
		}
		// 生成器根目录
//		String generatorRootPath = System.getProperty("user.dir");
		//建表脚本，包括建表，关系，索引
		StringBuffer createScript = new StringBuffer();
		//表结构修改脚本
		StringBuffer alterScript = new StringBuffer();
		// 创建数据库
		this.createTables(createScript,pdm);
		// 创建关系
		this.createReferences(createScript,pdm);
		// 创建索引
		this.createIndex(createScript,pdm);
		// 更新数据库
		this.updateDatabase(alterScript,pdm);
		if (isGenerateScript) {
			//生成整体建库脚本
			long times = System.currentTimeMillis();
			String createFileName = "create_" + this.databaseName + ".sql";
			this.generateScript(generatorRootPath, createFileName, createScript);
			//生成数据库修改脚本
			String alterFileName = "alter_" + this.databaseName + ".sql";
			this.generateScript(generatorRootPath, alterFileName, alterScript);
		}
	}
	
	private void generateScript(String generatorRootPath,String fileName,StringBuffer script) {
		// 表名称(名称结构必须为xx_xxxxxx)
		logger.info("开始进行数据库的脚本文件生成.");
		try{
			// 生成目录
			String scriptPath = generatorRootPath
					+ File.separator + "src"
					+ File.separator + "main"
					+ File.separator +  "resources"
					+ File.separator + "script";
			File scriptFile = new File(scriptPath);
			if(!scriptFile.exists()){
				// 生成目录
				scriptFile.mkdirs();
			}
			String filePath = scriptFile + File.separator + fileName;
			if (fileName.startsWith("alter_")) {
				if (script.length() > 0) {
					//修改脚本追加新的内容，并标注日期
					String oldFileContent = FileUtil.readFile(filePath);
					String updateDate = "/** " + DateUtil.dateToString(new Date(), DateUtil.DATETIME_PATTERN) + " 追加数据库更新脚本 */\r\n";
					oldFileContent += updateDate;
					oldFileContent += script.toString();
					FileUtil.writeFile(oldFileContent.getBytes(), filePath, "UTF-8");
				}
			}
			else {
				//生成脚本覆盖原始文件
				FileUtil.writeFile(script.toString().getBytes(), filePath, "UTF-8");
			}
			logger.info("数据库的脚本文件生成完成.");
		}
		catch(Exception e){
			e.printStackTrace();
			logger.info("数据库的脚本文件生成失败.");
		}
	}

	/**
	 * 生成建标脚本
	 * @param pdm
	 */
	private void createTables(StringBuffer createScript,Pdm pdm) {
		if (pdm == null) {
			return;
		}
		logger.info("/*********************************************************/");
		logger.info("/*************************创建数据库*************************/");
		logger.info("/*********************************************************/");
		ArrayList<PdmTable> tables = pdm.getTables();
		logger.info("开始生成数据脚本,并创建数据库表......");
		for (PdmTable tableTemp : tables) {
			if (tableTemp != null) {
				StringBuffer sqlScript = new StringBuffer();
				logger.info("/*********************************************************/");
				logger.info("生成表名称为:" + tableTemp.getName() + "的脚本");
				sqlScript.append("CREATE TABLE ").append("`" + tableTemp.getOriginalCode() + "`").append(" ( ").append("\n");
				ArrayList<PdmTableColumn> columns = tableTemp.getColumns();
				for (int c = 0; c < columns.size(); c++) {
					PdmTableColumn columnTemp = columns.get(c);
					if (columnTemp != null) {
						Integer length = columnTemp.getLength() == null?0:columnTemp.getLength();
						String lowValue = columnTemp.getLowValue();
						String highValue = columnTemp.getHighValue();
						String columnDataType = columnTemp.getDataType();
						String dtype = columnDataType;
						if (length > 0) {
							dtype = columnDataType + "(" + length + ")";
						}
						else if (lowValue != null && !lowValue.equals("") && highValue != null && !highValue.equals("")) {
							dtype = columnDataType + "(" + length + "," + highValue + ")";
						}
						sqlScript.append("\t`" + columnTemp.getOriginalCode() + "`");
						sqlScript.append(" " + dtype);

						// 是否为空
						if (columnTemp.getMandatory() != null && columnTemp.getMandatory() == 1) {
							sqlScript.append(" NOT NULL");
							String defval = columnTemp.getDefaultValue();
							if (defval != null && !defval.equals("")) {
								if (columnDataType != null
										&& columnDataType.contains("timestamp")) {
									if (!defval.equals("0000-00-00 00:00:00")){
										sqlScript.append(" DEFAULT " + defval);
									}
									else {
										sqlScript.append(" DEFAULT '" + defval + "'");
									}
								}
								else{
									sqlScript.append(" DEFAULT " + defval);
								}
							}
						}
						else {
							String defval = columnTemp.getDefaultValue();
							if (defval != null && !defval.equals("")) {
								if (columnDataType != null
										&& columnDataType.contains("timestamp")) {
									if (!defval.equals("0000-00-00 00:00:00")){
										sqlScript.append(" DEFAULT " + defval);
									}
									else {
										sqlScript.append(" DEFAULT '" + defval + "'");
									}
								}
								else{
									sqlScript.append(" DEFAULT " + defval);
								}
							}
							else {
								if(columnDataType != null
										&& !columnDataType.contains("date")
										&& !columnDataType.contains("time")){
									sqlScript.append(" DEFAULT NULL");
								}
							}
						}
						// 是否为自增
						if (columnTemp.getIdentity() != null && columnTemp.getIdentity() == 1) {
							sqlScript.append(" AUTO_INCREMENT");
						}
						if (columnTemp.getComment() != null && !columnTemp.getComment().equals("")) {
							String comment = columnTemp.getComment();
							Pattern p = Pattern.compile("\\s*|\t|\r|\n");
							Matcher m = p.matcher(comment);
							comment = m.replaceAll("");
							sqlScript.append(" COMMENT '" + comment + "'");
						}
						// 是否为最后一个字段
						if (c == (columns.size() - 1)) {
							ArrayList<PdmKey> keys = tableTemp.getKeys();
							// 判断是否有主键
							if (keys != null && keys.size() > 0) {
								// 生成主键和唯一键
								String pkeys = "", ukeys = "";
								int ukIndex = 0;
								for (PdmKey key : keys) {
									ArrayList<PdmTableColumn> keyColumns = key.getColumns();
									// 判断扩展属性是否存在
									String extendedAttributesText = key.getExtendedAttributesText();
									if (extendedAttributesText.toLowerCase().indexOf("unique") < 0) {
										for (PdmTableColumn column : keyColumns) {
											if (pkeys.equals("")) {
												pkeys = column.getOriginalCode();
											}
											else {
												pkeys += "," + column.getOriginalCode();
											}
										}
									}
									else {
										if (extendedAttributesText.toLowerCase().indexOf("unique") >= 0) {
											if (ukIndex > 0){
												ukeys += ", \n unique key AK_" + key.getOriginalCode() + " (";
											}
											else {
												ukeys += "\t unique key AK_" + key.getOriginalCode() + " (";
											}
											int index = 0;
											for (PdmTableColumn column : keyColumns) {
												if (index == 0) {
													ukeys += column.getOriginalCode();
												}
												else {
													ukeys += "," + column.getOriginalCode();
												}
												index++;
											}
											ukeys += ")";
											ukIndex++;
										}
									}
								}
								if (!pkeys.equals("")) {
									sqlScript.append(",").append("\n");
									sqlScript.append("\t primary key (" + pkeys + ")");
								}
								if (!ukeys.equals("")) {
									sqlScript.append(",").append("\n");
									sqlScript.append(ukeys);
								}
								sqlScript.append("\n);");
							}
							else {
								sqlScript.append("\n);");
							}
						}
						else {
							sqlScript.append(",").append("\n");
						}
					}
				}
				createScript.append(sqlScript + "\r\r");
				System.out.println("生成表脚本为:" + sqlScript.toString());
				logger.info("表名称为:" + tableTemp.getName() + "的脚本生成完成.");
				logger.info("判断表名称为:" + tableTemp.getOriginalCode() + "的数据表是否存在");
				// 判断数据库表是否存在
				boolean boo = tableDao.getTable(tableTemp.getOriginalCode());
				if (!boo) {
					logger.info("名称为:" + tableTemp.getOriginalCode() + "的数据库表不存在");
					// 如果表不存在，创建表
					tableDao.createTable(tableTemp.getOriginalCode(), sqlScript.toString());
				}
				else {
					logger.info("名称为:" + tableTemp.getOriginalCode() + "的数据库表已经存在");
				}
				logger.info("/*********************************************************/");
			}

		}
		logger.info("数据库创建完成.");
	}

	/**
	 * 创建数据库表关系
	 * @param pdm
	 */
	private void createReferences(StringBuffer createScript,Pdm pdm) {
		if (pdm == null) {
			return;
		}
		logger.info("/*********************************************************/");
		logger.info("/***********************创建数据库表关系************************/");
		logger.info("/*********************************************************/");
		logger.info("开始进行数据库表关系创建......");
		logger.info("开始生成数据表关系脚本,并创建数据库表关系......");
		// 获取所有关系数据
		ArrayList<PdmReference> referenceList = pdm.getReferences();
		for (PdmReference reference : referenceList) {
			if (reference != null) {
				// 关系脚本存贮对象
				StringBuffer referenceSqlScript = new StringBuffer();
				// 关系代码
				String referenceCode = reference.getCode();
				// 子表
				String childTableCode = reference.getChildTable().getOriginalCode();
				// 主表
				String parentTableCode = reference.getParentTable().getOriginalCode();
				referenceSqlScript.append(" alter table ").append(childTableCode).append(" add constraint ").append(" FK_" + referenceCode).append(" foreign key ");
				// 关系字段数组
				ArrayList<PdmReferenceJoin> referenceJoinList = reference.getJoins();
				String childTableColumns = null;
				String parentTableColumns = null;
				for (PdmReferenceJoin referenceJoin : referenceJoinList) {
					String childTableColumn = referenceJoin.getChildTableColumn().getCode();
					if (childTableColumns == null) {
						childTableColumns = childTableColumn;
					}
					else {
						childTableColumns += "," + childTableColumn;
					}
					String parentTableColumn = referenceJoin.getParentTableColumn().getCode();
					if (parentTableColumns == null) {
						parentTableColumns = parentTableColumn;
					}
					else {
						parentTableColumns += "," + parentTableColumn;
					}
				}
				referenceSqlScript.append(" (" + childTableColumns + ") ").append(" references ").append(parentTableCode).append(" (" + parentTableColumns + ") ")
						.append(" on delete restrict on update restrict;");
				createScript.append(referenceSqlScript + "\r\n");
				// 创建表关系
				tableDao.createTableReference(childTableCode, referenceCode, referenceSqlScript.toString());
			}
		}
		logger.info("数据库表关系创建完成");
	}

	/**
	 * 创建索引
	 * @param pdm
	 */
	private void createIndex(StringBuffer createScript,Pdm pdm) {
		if (pdm == null) {
			return;
		}
		logger.info("/*********************************************************/");
		logger.info("/***********************创建数据库索引************************/");
		logger.info("/*********************************************************/");
		logger.info("开始进行数据库表索引创建......");
		ArrayList<PdmTable> tables = pdm.getTables();
		for (PdmTable tableTemp : tables) {
			if (tableTemp != null) {
				// 表名称
				String tableName = tableTemp.getCode();
				ArrayList<PdmIndex> indexs = tableTemp.getIndexs();
				for (PdmIndex index : indexs) {
					// 索引脚本存储对象
					StringBuffer indexSqlScript = new StringBuffer();
					// 索引名称代码
					String indexCode = index.getCode();
					indexSqlScript.append(" create index ").append(indexCode).append(" on ").append(tableTemp.getCode());
					// 索引字段名称
					ArrayList<PdmTableColumn> columns = index.getColumns();
					String indexColumns = null;
					for (PdmTableColumn column : columns) {
						String columnCode = column.getCode();
						if (indexColumns == null) {
							indexColumns = columnCode;
						}
						else {
							indexColumns += "," + columnCode;
						}
					}
					indexSqlScript.append(" {" + indexColumns + "}; ");
					createScript.append(indexSqlScript + "\r\n");
					// 创建表索引
					tableDao.createTableIndex(tableName, indexCode, indexSqlScript.toString());
				}
			}
		}
		logger.info("数据库索引创建完成");
	}

	/**
	 * 更新数据库
	 * @param pdm
	 */
	private void updateDatabase(StringBuffer alterScript,Pdm pdm) {
		if (pdm == null) {
			return;
		}
		logger.info("/*********************************************************/");
		logger.info("/************************更新数据库**************************/");
		logger.info("/*********************************************************/");
		logger.info("开始进行数据库更新......");
		ArrayList<PdmTable> tables = pdm.getTables();
		for (PdmTable table : tables) {
			String tableCode = table.getOriginalCode();
			// 如果是系统特殊表（不记录表结构的信息表）不进行判断处理
			String tcode = specialTableMap.get(tableCode);
			if (tcode != null){
				logger.info("系统通用配置表，不进行更新判断处理：" + tableCode);
				continue;
			}
			String tableModificationDate = table.getModificationDate();
			// 根据objectid 查询表信息,进行数据库表信息的更新
			PdmTable tableOne = tableDao.selectOne("table.select_one_by_objid", table);
			// 如果表结构记录不存在直接添加
			if (tableOne == null) {
				// 记录表结构信息
				tableDao.insert("table.insert", table);
			}
			PdmTable tableTemp = tableDao.selectOne("table.select_one_by_objid", table);
			if (tableTemp != null) {
				String tableModificationDateTemp = tableTemp.getModificationDate();
				// 不相等，表结构有修改
				if (!tableModificationDate.equals(tableModificationDateTemp)) {
					logger.info("数据库表：" + tableCode + " 结构有修改.");
					try {
						boolean isUpdate = false;
    					String tableCodeTemp = tableTemp.getCode();
    					if (!tableCode.equals(tableCodeTemp)) {
    						logger.info("数据库表名称有修改，开始进行更新.");
    						String alterTableSql = "ALTER  TABLE " + tableCodeTemp + " RENAME TO " + tableCode + ";";
    						// 更新表信息
    						isUpdate = tableDao.alterTable(tableCodeTemp, alterTableSql);
    						if (isUpdate) {
    							alterScript.append(alterTableSql + "\r\n");
    						}
    					}
    					if (isUpdate) {
        					table.setId(tableTemp.getId());
        					// 更新表结构信息
        					tableDao.update("table.update", table);
    					}
					}
					catch(Exception e) {
						e.printStackTrace();
						logger.info("数据库表：" + tableCode + " 结构修改失败："+e.getMessage());
					}
				}
				else {
					logger.info("数据库表：" + tableCode + " 结构无修改.");
				}
				//查询表所有字段信息,进行无用字段的删除
				PdmTableColumn paramColumn = new PdmTableColumn();
				paramColumn.setTableId(tableTemp.getId());
				List<PdmTableColumn> columnsDB = columnDao.selectList("column.select_list_by_tid", paramColumn);
				for (PdmTableColumn pdmTableColumn:columnsDB) {
					String columnCode = pdmTableColumn.getCode();
					String objectId = pdmTableColumn.getObjectId();
					boolean isEqual = false;
					// 根据objectid 查询表字段信息,进行数据库表字段信息的更新
					ArrayList<PdmTableColumn> columns = table.getColumns();
					for (PdmTableColumn column : columns) {
						String objectCmpId = column.getObjectId();
						//比较是否有同样objid的数据表字段，比对字段是否存在
						//如果不存在，则删除该表字段
						if (objectId != null && objectCmpId != null && objectCmpId.equals(objectId)) {
							isEqual = true;
							break;
						}
					}
					//如果不存在，该字段从数据库表中删除，仅限开发环境
					if (!isEqual) {
						String dropSql = "alter table " + tableCode + " drop column " + columnCode;
						boolean isSucc = tableDao.alterTable(tableCode, dropSql);
						if (isSucc) {
							columnDao.deleteById("column.delete_by_id", pdmTableColumn);
							alterScript.append(dropSql + "\r\n");
						}
					}
				}
				// 根据objectid 查询表字段信息,进行数据库表字段信息的更新
				ArrayList<PdmTableColumn> columns = table.getColumns();
				int index = 0;
				for (PdmTableColumn column : columns) {
					String columnCode = column.getOriginalCode();
					String columnDataType = column.getDataType();
					Integer length = column.getLength() == null?0:column.getLength();
					String lowValue = column.getLowValue();
					String highValue = column.getHighValue();
					String columnModificationDate = column.getModificationDate();
					String comment = column.getComment();
					if (column == null || column.equals("")){
						comment = column.getName();
					}
					column.setComment(comment);
					column.setTableId(tableTemp.getId());// null
					PdmTableColumn columnOne = columnDao.selectOne("column.select_one_by_objid", column);
					if (columnOne == null) {
						// 更新表结构信息
						columnDao.insert("column.insert", column);
					}
					logger.info("验证数据库表：" + tableCode + " 结构是否有修改.");
					PdmTableColumn columnTemp = columnDao.selectOne("column.select_one_by_objid", column);
					if (columnTemp != null) {
						String columnModificationDateTemp = columnTemp.getModificationDate();
						String columnCodeTemp = columnTemp.getCode();
						String columnDataTypeTemp = columnTemp.getDataType();
						String dtype = columnDataType;
						logger.info("判断数据库表：" + tableCode + " 的" + columnCodeTemp + "字段是否有修改");
						// 不相等，表示字段有修改
						if (!columnModificationDate.equals(columnModificationDateTemp)) {
							logger.info("判断数据库表：" + tableCode + " 的" + columnCodeTemp + "字段有修改");
							try {
								boolean isColumn = false ,isDataType = false;
								if (length > 0) {
									dtype = columnDataType + "(" + length + ")";
								}
								else if (lowValue != null && !lowValue.equals("") && highValue != null && !highValue.equals("")) {
									dtype = columnDataType + "(" + length + "," + highValue + ")";
								}
								// 如果不等，更改字段名称
								if (!columnCode.equals(columnCodeTemp)) {
									logger.info("名称为：" + tableCode + " 的数据库表的" + columnCodeTemp + "字段名称有修改[改为：" +columnCode+ "].");
									String alterTableSql = "ALTER  TABLE " + tableCode + " CHANGE " + columnCodeTemp + " " + columnCode + " " + dtype + ";";
									logger.info("执行脚本：" + alterTableSql);
									// 更新表信息
									isColumn = tableDao.alterTable(tableCode, alterTableSql);
									if (isColumn) {
		    							alterScript.append(alterTableSql + "\r\n");
		    						}
								}
								// 如果不等，更新字段类型
								if (!columnDataType.equals(columnDataTypeTemp)) {
									logger.info("名称为：" + tableCode + " 的数据库表的" + columnCodeTemp + "字段类型有修改[改为：" +columnDataType+ " 类型].");
									String alterTableSql = null;
									//如果字段更换类型为datetime，需要删除原有字段
									//在添加新的字段，否则脚本执行出错
//									if (columnDataType != null && (columnDataType.contains("datetime")
//											|| columnDataType.contains("date")
//											|| columnDataType.contains("timestamp"))){
//										alterTableSql = "alter table " + tableCode + " drop column " + columnCode + ";\r\n";
//										alterTableSql += addColumnScript(index,tableCode, column,columns);
//									}
//									else{
										alterTableSql = "ALTER  TABLE " + tableCode + " MODIFY COLUMN " + columnCodeTemp + " " + dtype + ";";
//									}
									logger.info("执行脚本：" + alterTableSql);
									// 更新表信息
									isDataType = tableDao.alterTable(tableCode, alterTableSql);
									if (isDataType) {
		    							alterScript.append(alterTableSql + "\r\n");
		    						}
								}
								if (isColumn || isDataType) {
    								column.setId(columnTemp.getId());
    								// 更新表结构信息
    								columnDao.update("column.update", column);
								}
							}
							catch(Exception e) {
								e.printStackTrace();
								logger.info("名称为：" + tableCode + " 的数据库表的字段类型有修改失败:" + e.getMessage());
							}
						}
						else{
							logger.info("判断数据库表：" + tableCode + " 的" + columnCodeTemp + "字段无修改");
						}
						// 判断表字段是否存在，若字段不存在添加字段
						boolean boo = columnDao.getColumn(tableCode, columnCode);
						if (!boo) {
							logger.info("名称为：" + tableCode + " 的数据库表，新增名称为：" + columnCode + "字段.");
							//添加字段脚本
							String alterTableSql = addColumnScript(index,tableCode, column,columns);
							// 更新表信息
							boolean isSuccess = tableDao.alterTable(tableCode, alterTableSql);
							if (isSuccess) {
    							alterScript.append(alterTableSql + "\r\n");
    						}
						}
						else {
							logger.info("数据库表：" + tableCode + " 结构无修改.");
						}
					}
					//计数器累加
					index++;
				}
			}
		}
		logger.info("数据库更新完成");
	}


	private String addColumnScript(int index,String tableCode,PdmTableColumn column,ArrayList<PdmTableColumn> columns){
		Integer length = column.getLength() == null?0:column.getLength();
		String columnDataType = column.getDataType();
		String lowValue = column.getLowValue();
		String highValue = column.getHighValue();
		String columnCode = column.getOriginalCode();
		String dtype = "";
		if (length > 0) {
			dtype = columnDataType + "(" + length + ")";
		}
		else if (lowValue != null && !lowValue.equals("") && highValue != null && !highValue.equals("")) {
			dtype = columnDataType + "(" + length + "," + highValue + ")";
		}
		else{
			dtype = columnDataType;
		}
		String alterTableSql = "ALTER  TABLE " + tableCode + " ADD " + columnCode + " " + dtype;
		// 是否为空
		if (column.getMandatory() != null && column.getMandatory() == 1) {
			alterTableSql += " NOT NULL";
		}
		else {
			String defaulValue = column.getDefaultValue();
			if (defaulValue != null && !defaulValue.equals("")) {
				if  (dtype != null && dtype.toLowerCase().equals("timestamp")){
					alterTableSql += " DEFAULT '" + defaulValue + "'";
				}
				else{
					alterTableSql += " DEFAULT " + defaulValue;
				}
			}
		}
		// 是否为自增
		if (column.getIdentity() != null && column.getIdentity() == 1) {
			alterTableSql += " AUTO_INCREMENT";
		}
		// 增加注释
//		if (column.getComment() != null && !column.getComment().equals("")) {
//			alterTableSql += " COMMENT '" + column.getComment() + "'";
//		}
		//查找最近的一个字段信息
		if ((index-1) >= 0) {
			PdmTableColumn columnBefore = columns.get(index-1);
			if (columnBefore != null) {
				String beforeCode = columnBefore.getOriginalCode();
				if (beforeCode != null && !beforeCode.equals("")) {
					alterTableSql += " after " + beforeCode;
				}
			}
		}
		alterTableSql += ";";
		return alterTableSql;
	}
}
