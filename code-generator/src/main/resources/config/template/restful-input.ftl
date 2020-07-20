package ${relativeProjectPath}.${entityPath}.entity;

<#assign idIsExist="false"/>
<#list columns as column>
	<#assign property="result"/>
	<#if column.code?lower_case == 'id' >
		<#assign property="id"/>
		<#assign idIsExist="true"/>
	</#if>
</#list>
import ${path_core}.entity.BaseVo;
<#list columns as cols>
	<#if cols.dataType?contains('decimal')>
    import java.math.BigDecimal;
		<#break>
	</#if>
</#list>
import org.springframework.validation.BindingResult;

public class Base${entity}Vo extends BaseVo{

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
/**${cols.name}-<@compress single_line=true>${cols.comment}</@compress>**/
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

@Override
public BindingResult _validated(Class<?>[] classes, Object[] objects) {
return null;
}
}
