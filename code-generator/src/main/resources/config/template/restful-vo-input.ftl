package ${relativeProjectPath}.${entityPath}.entity;

import ${path_core}.entity.BaseInput;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.BindingResult;
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

@ApiModel(value = "${table.comment}-请求参数实体")
public class ${entity}Input extends BaseInput{

	@Override
	public BindingResult _validated(Class<?>[] groups, Object[] args) {
		return null;
	}

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

	/**
    * ${cols.name}-<@compress single_line=true>${cols.comment}</@compress>
	*/
    @ApiModelProperty(value = "${cols.name}")
	<#if cols.originalCode?contains('id') >
	@JSONField(serializeUsing = ToStringSerializer.class)
	</#if>
	private ${dataType} ${cols.code?uncap_first};
	</#list>

	public ${entity}Input() {

	}
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

	public ${dataType} get${cols.code}() {
		return ${cols.code?uncap_first};
	}

	public void set${cols.code}(${dataType} ${cols.code?uncap_first}) {
		this.${cols.code?uncap_first} = ${cols.code?uncap_first};
	}
	</#list>
}
