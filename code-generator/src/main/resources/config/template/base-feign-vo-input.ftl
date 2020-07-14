package ${relativeProjectPath}.${servieSimpleName}.vo.base;

<#list columns as cols>
<#if cols.dataType?contains('decimal')>
import java.math.BigDecimal;
<#break>
</#if>
<#if cols.originalCode?contains('id')>
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
<#break>
</#if>
</#list>

public abstract class Base${entity}Input implements java.io.Serializable{

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
}
