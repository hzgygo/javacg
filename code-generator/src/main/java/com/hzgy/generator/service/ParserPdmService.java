package com.hzgy.generator.service;

import com.hzgy.generator.entity.*;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;

/**
 * 
 * 解析PDM文件
 *
 */
public class ParserPdmService{

	private Pdm pdm = new Pdm();

	static Logger logger = Logger.getLogger(ParserPdmService.class);

	public ParserPdmService(){
	}

	/**
	 * 解析pdm文件
	 * @param fileName 数据库设计文件名称
	 * @param isTablePerfix 表前缀
	 * @return
	 * @throws Exception
	 */
	public Pdm pdmParser(String generatorPath,String fileName,boolean isTablePerfix) throws Exception{
		String pdmFile = null;
		//生成类，资源文件，mybatis 配置文件
		pdmFile = generatorPath + File.separator + fileName;
		//设置pdm文件路径
		pdm.setPdmFilaPath(pdmFile);
		logger.info("开始解析数据库设计文件:" + pdmFile);
		SAXReader reader = new SAXReader();
		Document doc = reader.read(pdmFile);
		Node model = doc.selectSingleNode("//c:Children/o:Model");
		pdm.setAttributeId(((Element)model).attributeValue("Id"));
		pdm.setName(selectSingleNodeStringText(model,"a:Name"));
		pdm.setCode(selectSingleNodeStringText(model,"a:Code"));
		pdm.setCreationDate(selectSingleNodeStringText(model,"a:CreationDate"));
		pdm.setCreator(selectSingleNodeStringText(model,"a:Creator"));
		pdm.setModificationDate(selectSingleNodeStringText(model,"a:ModificationDate"));

		Node dbms = model.selectSingleNode("//o:Shortcut");
		pdm.setDBMSCode(selectSingleNodeStringText(dbms,"a:Code"));
		pdm.setDBMSName(selectSingleNodeStringText(dbms,"a:Name"));
		pdm.setCreationDate(selectSingleNodeStringText(dbms,"a:CreationDate"));
		pdm.setCreator(selectSingleNodeStringText(dbms,"a:Creator"));
		pdm.setModificationDate(selectSingleNodeStringText(dbms,"a:ModificationDate"));

		logger.info("数据库库设计文件数据模型名称为:" + pdm.getCode() + "(" + pdm.getName() + ")  数据库类型为:" + pdm.getDBMSCode() + "(" + pdm.getDBMSName() + ")");

		pdm.setUsers(userParser(model));
		pdm.setTables(tableParser(model,isTablePerfix));
		pdm.setPhysicalDiagrams(physicalDiagramParser(model));
		pdm.setReferences(referenceParser(model));

		return pdm;
	}

	public ArrayList<PhysicalDiagram> physicalDiagramParser(Node node) throws Exception{
		ArrayList<PhysicalDiagram> physicalList = new ArrayList<PhysicalDiagram>();
		for(Object o:node.selectNodes("c:PhysicalDiagrams/o:PhysicalDiagram")){
			Node physicalNode = (Node)o;
			PhysicalDiagram physicalDiagram = new PhysicalDiagram();
			physicalDiagram.setAttributeId(((Element)physicalNode).attributeValue("Id"));
			physicalDiagram.setName(selectSingleNodeStringText(physicalNode,"a:Name"));
			physicalDiagram.setCode(selectSingleNodeStringText(physicalNode,"a:Code"));
			physicalDiagram.setCreationDate(selectSingleNodeStringText(physicalNode,"a:CreationDate"));
			physicalDiagram.setCreator(selectSingleNodeStringText(physicalNode,"a:Creator"));
			physicalDiagram.setModificationDate(selectSingleNodeStringText(physicalNode,"a:ModificationDate"));
			// 添加Table
			for(Object table:physicalNode.selectNodes("c:Symbols/o:TableSymbol/c:Object/o:Table")){
				String id = ((Element)table).attributeValue("Ref");
				physicalDiagram.addTable(pdm.getTable(id));
			}
			physicalList.add(physicalDiagram);
		}
		return physicalList;
	}

	/**
	 * 遍历表
	 * 
	 * @param node
	 * @return
	 * @throws Exception
	 */
	public ArrayList<PdmTable> tableParser(Node node,boolean isTablePerfix) throws Exception{
		ArrayList<PdmTable> tableList = new ArrayList<PdmTable>();
		for(Object tableNodeObj:node.selectNodes("c:Tables/o:Table")){
			Node tableNode = (Node)tableNodeObj;
			String originalTableCode = selectSingleNodeStringText(tableNode,"a:Code");
			String tableName = selectSingleNodeStringText(tableNode,"a:Name");
			String code = null;
			//有表前缀，做表前缀处理
			if (isTablePerfix) {
				code = parseCode(1,originalTableCode);
				String newCode = null;
				int indexFP = tableName.indexOf("（");
				int indexFS = tableName.indexOf("）");
				int indexSP = tableName.indexOf("(");
				int indexSS = tableName.indexOf(")");
				if (indexFP >= 0 && indexFS >= 0) {
					newCode = tableName.substring(indexFP+1, indexFS);
				}
				else if (indexSP >= 0 && indexSS >= 0) {
					newCode = tableName.substring(indexSP+1, indexSS);
				}
				if (newCode != null) {
					code = parseCode(1,newCode);
				}
			}
			else {
				code = parseTableCode(originalTableCode);
			}
			PdmTable table = new PdmTable();
			table.setAttributeId(((Element)tableNode).attributeValue("Id"));
			table.setObjectId(selectSingleNodeStringText(tableNode,"a:ObjectID"));
			table.setName(tableName);
			table.setOriginalCode(originalTableCode);
			table.setCode(code);
			table.setCreationDate(selectSingleNodeStringText(tableNode,"a:CreationDate"));
			table.setCreator(selectSingleNodeStringText(tableNode,"a:Creator"));
			table.setModificationDate(selectSingleNodeStringText(tableNode,"a:ModificationDate"));
			table.setComment(selectSingleNodeStringText(tableNode,"a:Comment"));
			logger.info("/*********************************************************/");
			logger.info("数据库表名为：" + table.getName() + "--代码为:" + table.getCode());
			// 添加Columns
			table.setColumns(columnParser(tableNode));
			// 添加key
			for(Object keyNodeObj:tableNode.selectNodes("c:Keys/o:Key")){
				Node keyNode = (Node)keyNodeObj;
				PdmKey key = new PdmKey();
				key.setAttributeId(((Element)keyNode).attributeValue("Id"));
				key.setObjectId(selectSingleNodeStringText(keyNode,"a:ObjectID"));
				key.setName(selectSingleNodeStringText(keyNode,"a:Name"));
				String originalKeyCode = selectSingleNodeStringText(keyNode,"a:Code");
				key.setOriginalCode(originalKeyCode);
				key.setCode(originalKeyCode);
				key.setConstraintName(selectSingleNodeStringText(keyNode,"a:ConstraintName"));
				key.setExtendedAttributesText(selectSingleNodeStringText(keyNode,"a:ExtendedAttributesText"));
				key.setCreationDate(selectSingleNodeStringText(keyNode,"a:CreationDate"));
				key.setCreator(selectSingleNodeStringText(keyNode,"a:Creator"));
				key.setModificationDate(selectSingleNodeStringText(keyNode,"a:ModificationDate"));
				for(Object column:keyNode.selectNodes("c:Key.Columns/o:Column")){
					String id = ((Element)column).attributeValue("Ref");
					key.addColumn(table.getColumn(id));
				}
				table.addKey(key);
			}
			// 添加PrimaryKey
			String keyId = ((Element)tableNode.selectSingleNode("c:PrimaryKey/o:Key")).attributeValue("Ref");
			table.setPrimaryKey(table.getKey(keyId));
			// 添加Indexes
			for(Object indexNodeObj:tableNode.selectNodes("c:Indexes/o:Index")){
				Node indexNode = (Node)indexNodeObj;
				PdmIndex index = new PdmIndex();
				index.setAttributeId(((Element)indexNode).attributeValue("Id"));
				index.setName(selectSingleNodeStringText(indexNode,"a:Name"));
				String originalIndexCode = selectSingleNodeStringText(indexNode,"a:Code");
				index.setCode(originalIndexCode);
				index.setCreationDate(selectSingleNodeStringText(indexNode,"a:CreationDate"));
				index.setCreator(selectSingleNodeStringText(indexNode,"a:Creator"));
				index.setModificationDate(selectSingleNodeStringText(indexNode,"a:ModificationDate"));
				index.setTable(table);
				for(Object column:indexNode.selectNodes("//c:Column/o:Column")){
					String id = ((Element)column).attributeValue("Ref");
					index.addColumn(table.getColumn(id));
				}
				table.addIndex(index);
			}
			// 添加User
			Element userElement = (Element)tableNode.selectSingleNode("c:Owner/o:User");
			if(userElement != null){
				String userId = userElement.attributeValue("Ref");
				table.setUser(pdm.getUser(userId));
			}
			tableList.add(table);
		}
		return tableList;
	}

	/**
	 * 遍历字段
	 * 
	 * @param node
	 * @return
	 */
	public ArrayList<PdmTableColumn> columnParser(Node node){
		ArrayList<PdmTableColumn> columnList = new ArrayList<PdmTableColumn>();
		for(Object o:node.selectNodes("c:Columns/o:Column")){
			Node columnNode = (Node)o;
			PdmTableColumn column = new PdmTableColumn();
			column.setAttributeId(((Element)columnNode).attributeValue("Id"));
			column.setObjectId(selectSingleNodeStringText(columnNode,"a:ObjectID"));
			column.setName(selectSingleNodeStringText(columnNode,"a:Name"));
			column.setShowName(selectSingleNodeStringText(columnNode,"a:ShowName"));
			String originalColumnCode = selectSingleNodeStringText(columnNode,"a:Code");
			String code = this.parseCode(2, originalColumnCode);
			column.setOriginalCode(originalColumnCode);
			column.setCode(code);
			String dataType = selectSingleNodeStringText(columnNode,"a:DataType");
			int index = dataType.indexOf("(");
			if (index >= 0) {
				dataType = dataType.substring(0, index);
			}
			column.setDataType(dataType);
			column.setIdentity(selectSingleNodeIntText(columnNode,"a:Identity"));
			column.setLength(selectSingleNodeIntText(columnNode,"a:Length"));
			column.setPrecision(selectSingleNodeIntText(columnNode,"a:Precision"));
			column.setMandatory(selectSingleNodeIntText(columnNode,"a:Column.Mandatory"));
			column.setDefaultValue(selectSingleNodeStringText(columnNode,"a:DefaultValue"));
			column.setLowValue(selectSingleNodeStringText(columnNode,"a:LowValue"));
			column.setHighValue(selectSingleNodeStringText(columnNode,"a:HighValue"));
			column.setComment(selectSingleNodeStringText(columnNode,"a:Comment"));
			column.setCreationDate(selectSingleNodeStringText(columnNode,"a:CreationDate"));
			column.setCreator(selectSingleNodeStringText(columnNode,"a:Creator"));
			column.setModificationDate(selectSingleNodeStringText(columnNode,"a:ModificationDate"));
			columnList.add(column);
			logger.info("数据库字段名为 ：" + column.getName() + "--字段代码：" + column.getCode() + "--字段类型为:" + column.getDataType());
		}
		logger.info("/*********************************************************/");
		return columnList;
	}

	/**
	 * 遍历用户
	 * 
	 * @param node
	 * @return
	 */
	public ArrayList<PdmUser> userParser(Node node){
		ArrayList<PdmUser> userList = new ArrayList<PdmUser>();
		for(Object o:node.selectNodes("c:Users/o:User")){
			Node userNode = (Node)o;
			PdmUser user = new PdmUser();
			user.setAttributeId(((Element)userNode).attributeValue("Id"));
			user.setName(selectSingleNodeStringText(userNode,"a:Name"));
			user.setCode(selectSingleNodeStringText(userNode,"a:Code"));
			user.setCreationDate(selectSingleNodeStringText(userNode,"a:CreationDate"));
			user.setCreator(selectSingleNodeStringText(userNode,"a:Creator"));
			user.setModificationDate(selectSingleNodeStringText(userNode,"a:ModificationDate"));
			userList.add(user);
		}
		return userList;
	}

	/**
	 * 遍历外键
	 * 
	 * @param node
	 * @return
	 * @throws Exception
	 */
	public ArrayList<PdmReference> referenceParser(Node node) throws Exception{
		ArrayList<PdmReference> referenceList = new ArrayList<PdmReference>();
		for(Object referenceNodeObj:node.selectNodes("c:References/o:Reference")){
			Node referenceNode = (Node)referenceNodeObj;
			PdmReference reference = new PdmReference();
			reference.setAttributeId(((Element)referenceNode).attributeValue("Id"));
			reference.setObjectId(selectSingleNodeStringText(referenceNode,"a:ObjectID"));
			reference.setName(selectSingleNodeStringText(referenceNode,"a:Name"));
			reference.setCode(selectSingleNodeStringText(referenceNode,"a:Code"));
			reference.setConstraintName(selectSingleNodeStringText(referenceNode,"a:ForeignKeyConstraintName"));
			reference.setUpdateConstraint(selectSingleNodeIntText(referenceNode,"a:UpdateConstraint"));
			reference.setDeleteConstraint(selectSingleNodeIntText(referenceNode,"a:DeleteConstraint"));
			reference.setImplementationType(selectSingleNodeStringText(referenceNode,"a:ImplementationType"));
			reference.setCreationDate(selectSingleNodeStringText(referenceNode,"a:CreationDate"));
			reference.setCreator(selectSingleNodeStringText(referenceNode,"a:Creator"));
			reference.setModificationDate(selectSingleNodeStringText(referenceNode,"a:ModificationDate"));
			// 添加ParentTable
			String parentTableId = ((Element)referenceNode.selectSingleNode("c:ParentTable/o:Table")).attributeValue("Ref");
			reference.setParentTable(pdm.getTable(parentTableId));
			// 添加ChildTable
			String childTableId = ((Element)referenceNode.selectSingleNode("c:ChildTable/o:Table")).attributeValue("Ref");
			reference.setChildTable(pdm.getTable(childTableId));
			// 添加Joins
			for(Object referenceJoinNodeObj:referenceNode.selectNodes("c:Joins/o:ReferenceJoin")){
				Node referenceJoinNode = (Node)referenceJoinNodeObj;
				PdmReferenceJoin referenceJoin = new PdmReferenceJoin();
				referenceJoin.setAttributeId(((Element)referenceJoinNode).attributeValue("Id"));
				referenceJoin.setObjectId(selectSingleNodeStringText(referenceJoinNode,"a:ObjectID"));
				referenceJoin.setCreationDate(selectSingleNodeStringText(referenceJoinNode,"a:CreationDate"));
				referenceJoin.setCreator(selectSingleNodeStringText(referenceJoinNode,"a:Creator"));
				referenceJoin.setModificationDate(selectSingleNodeStringText(referenceJoinNode,"a:ModificationDate"));

				String id = ((Element)referenceJoinNode.selectSingleNode("c:Object1/o:Column")).attributeValue("Ref");
				referenceJoin.setParentTableColumn(reference.getParentTable().getColumn(id));

				id = ((Element)referenceJoinNode.selectSingleNode("c:Object2/o:Column")).attributeValue("Ref");
				referenceJoin.setChildTableColumn(reference.getChildTable().getColumn(id));
				reference.addReferenceJoin(referenceJoin);
			}
			referenceList.add(reference);
		}
		return referenceList;
	}

	private String selectSingleNodeStringText(Node parentNode,String childNodeName){
		Node childNode = parentNode.selectSingleNode(childNodeName);
		if(childNode != null){
			return childNode.getText();
		}
		else{
			return "";
		}
	}

	private Integer selectSingleNodeIntText(Node parentNode,String childNodeName){
		Node childNode = parentNode.selectSingleNode(childNodeName);
		if(childNode != null){
			return Integer.parseInt(childNode.getText());
		}
		else{
			return null;
		}
	}
	
	/**
	 * 处理字段
	 * @param type 1：表名称 2：表字段
	 * @param code
	 * @return
	 */
	private String parseCode(int type,String code) {
		if (code != null && !"".equals(code)) {
			String codeArr [] = code.trim().split("_");
			String returnCode = null;
			for (int i=0;i<codeArr.length; i++) {
				String splitCdoe = codeArr[i];
				if (splitCdoe == null || "".equals(splitCdoe)) {
					continue;
				}
				if(i == 0) {
					if (type == 1) {
						//表名称代码处理
						returnCode = splitCdoe.toUpperCase() + "_";
					}
					else {
						//表字段名称代码处理
						//首字符大写
						splitCdoe = splitCdoe.substring(0, 1).toUpperCase() + splitCdoe.substring(1, splitCdoe.length());
						returnCode = splitCdoe;
					}
				}
				else {
					//首字符大写
					splitCdoe = splitCdoe.substring(0, 1).toUpperCase() + splitCdoe.substring(1, splitCdoe.length());
					returnCode += splitCdoe;
				}
			}
			return returnCode;
		}
		return null;
	}
	
	/**
	 * 解析表名称
	 * @param code
	 * @return
	 */
	private String parseTableCode(String code) {
		if (code != null && !"".equals(code)) {
			String codeArr [] = code.trim().split("_");
			String returnCode = "";
			for (int i=0;i<codeArr.length; i++) {
				String splitCdoe = codeArr[i];
				if (splitCdoe == null || "".equals(splitCdoe)) {
					continue;
				}
				//首字符大写
				splitCdoe = splitCdoe.substring(0, 1).toUpperCase() + splitCdoe.substring(1, splitCdoe.length());
				returnCode += splitCdoe;
			}
			return returnCode;
		}
		return null;
	}
}
