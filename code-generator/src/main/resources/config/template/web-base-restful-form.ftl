package ${relativeProjectPath}.${entityPath}.base;

<#list columns as cols>
<#if cols.dataType?contains('decimal')>
import java.math.BigDecimal;
<#break>
</#if>
<#if cols.originalCode?contains('id')>
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
<#break>
<#break>
</#if>
</#list>
<#assign isList="false"/>
<#list references as reference>
<#assign join=""/>
<#assign joinTableName=""/>
<#assign isJoin="false"/>
<#if table.code == reference.parentTable.code>
	<#assign isList="true"/>
	<#assign join="left"/>
	<#assign isJoin="true"/>
	<#assign ctOriginalCode="${reference.childTable.originalCode}"/>
	<#assign ctCode="${reference.childTable.code}"/>
	<#assign ctIndex="${ctCode?index_of('_')}"/>
	<#assign ctLength="${ctCode?length}"/>
	<#assign joinTableName="${ctCode?substring(ctIndex?number+1,ctLength?number)}"/>
import ${relativeProjectPath}.${joinTableName?lower_case}.${joinTableName};
</#if>
<#if table.code == reference.childTable.code>
	<#assign join="inner"/>
	<#assign isJoin="true"/>
	<#assign ctOriginalCode="${reference.parentTable.originalCode}"/>
	<#assign ctCode="${reference.parentTable.code}"/>
	<#assign ctIndex="${ctCode?index_of('_')}"/>
	<#assign ctLength="${ctCode?length}"/>
	<#assign joinTableName="${ctCode?substring(ctIndex?number+1,ctLength?number)}"/>
import ${relativeProjectPath}.${joinTableName?lower_case}.${joinTableName};
</#if>
</#list>
<#if isList == 'true'>
import java.util.List;
</#if>

public abstract class Base${entity} extends Base${entity}Criteria{

	<#list columns as cols>
	<#assign dataType="String" />
	<#if cols.dataType?contains('bigint')>
	<#assign dataType="Long" />
	<#elseif cols.dataType?contains('integer')>
	<#assign dataType="Long" />
	<#elseif cols.dataType?contains('tinyint')>
	<#assign dataType="Integer" />
	<#elseif cols.dataType?contains('smallint')>
	<#assign dataType="Integer" />
	<#elseif cols.dataType?contains('double')>
	<#assign dataType="Double" />
	<#elseif cols.dataType?contains('decimal')>
	<#assign dataType="BigDecimal" />
	<#elseif cols.dataType?contains('int')>
	<#assign dataType="Integer" />
	<#elseif cols.dataType?contains('byte')>
		<#assign dataType="Integer" />
	<#else>
	<#assign dataType="String" />
	</#if>
	
	/**${cols.name}-<@compress single_line=true>${cols.comment}</@compress>**/
	<#if cols.originalCode?contains('id') >
	@JSONField(serializeUsing = ToStringSerializer.class)
	</#if>
	private ${dataType} ${cols.code?uncap_first};
	</#list>
<#list references as reference>
	<#assign join=""/>
	<#assign joinTableName=""/>
	<#assign isJoin="false"/>
	<#if table.code == reference.parentTable.code>
		<#assign join="left"/>
		<#assign isJoin="true"/>
		<#assign ctOriginalCode="${reference.childTable.originalCode}"/>
		<#assign ctCode="${reference.childTable.code}"/>
		<#assign ctIndex="${ctCode?index_of('_')}"/>
		<#assign ctLength="${ctCode?length}"/>
		<#assign joinTableName="${ctCode?substring(ctIndex?number+1,ctLength?number)}"/>

    /**${reference.childTable.comment}**/
    private List<${joinTableName}> list${joinTableName};
	</#if>
	<#if table.code == reference.childTable.code>
		<#assign join="inner"/>
		<#assign isJoin="true"/>
		<#assign ctOriginalCode="${reference.parentTable.originalCode}"/>
		<#assign ctCode="${reference.parentTable.code}"/>
		<#assign ctIndex="${ctCode?index_of('_')}"/>
		<#assign ctLength="${ctCode?length}"/>
		<#assign joinTableName="${ctCode?substring(ctIndex?number+1,ctLength?number)}"/>

	/**${reference.parentTable.comment}**/
	private ${joinTableName} ${joinTableName?uncap_first};
	</#if>
</#list>

<#list columns as cols>
<#assign dataType="String" />
<#if cols.dataType?contains('bigint')>
<#assign dataType="Long" />
<#elseif cols.dataType?contains('integer')>
<#assign dataType="Long" />
<#elseif cols.dataType?contains('tinyint')>
<#assign dataType="Integer" />
<#elseif cols.dataType?contains('smallint')>
<#assign dataType="Integer" />
<#elseif cols.dataType?contains('double')>
<#assign dataType="Double" />
<#elseif cols.dataType?contains('decimal')>
<#assign dataType="BigDecimal" />
<#elseif cols.dataType?contains('int')>
<#assign dataType="Integer" />
<#else>
<#assign dataType="String" />
</#if>

	public ${dataType} get${cols.code}() {
		return ${cols.code?uncap_first};
	}

	public void set${cols.code}(${dataType} ${cols.code?uncap_first}) {
		this.${cols.code?uncap_first} = ${cols.code?uncap_first};
	}
</#list>
<#list references as reference>
<#assign join=""/>
<#assign joinTableName=""/>
<#assign isJoin="false"/>
<#if table.code == reference.parentTable.code>
	<#assign join="left"/>
	<#assign isJoin="true"/>
	<#assign ctOriginalCode="${reference.childTable.originalCode}"/>
	<#assign ctCode="${reference.childTable.code}"/>
	<#assign ctIndex="${ctCode?index_of('_')}"/>
	<#assign ctLength="${ctCode?length}"/>
	<#assign joinTableName="${ctCode?substring(ctIndex?number+1,ctLength?number)}"/>

	public List<${joinTableName}> getList${joinTableName}() {
		return list${joinTableName};
	}

	public void setList${joinTableName}(List<${joinTableName}> list${joinTableName}) {
		this.list${joinTableName} = list${joinTableName};
	}
</#if>
<#if table.code == reference.childTable.code>
	<#assign join="inner"/>
	<#assign isJoin="true"/>
	<#assign ctOriginalCode="${reference.parentTable.originalCode}"/>
	<#assign ctCode="${reference.parentTable.code}"/>
	<#assign ctIndex="${ctCode?index_of('_')}"/>
	<#assign ctLength="${ctCode?length}"/>
	<#assign joinTableName="${ctCode?substring(ctIndex?number+1,ctLength?number)}"/>

	public ${joinTableName} get${joinTableName}() {
		return ${joinTableName?uncap_first};
	}

	public void set${joinTableName}(${joinTableName} ${joinTableName?uncap_first}) {
		this.${joinTableName?uncap_first} = ${joinTableName?uncap_first};
	}
</#if>
</#list>
}
