package ${relativeProjectPath}.${entityPath}.base;

import ${com_jbt_bms}.entity.BaseVo;

public abstract class Base${entity}Criteria extends BaseVo{

<#list columns as cols>
	<#if cols.dataType?contains('varchar')>

    private String ${cols.code?uncap_first}AsLike;
	</#if>
</#list>

<#list columns as cols>
	<#if cols.dataType?contains('varchar')>

    public String get${cols.code}AsLike() {
    return ${cols.code?uncap_first}AsLike;
    }

    public void set${cols.code}AsLike(String ${cols.code?uncap_first}AsLike) {
    this.${cols.code?uncap_first}AsLike = ${cols.code?uncap_first}AsLike;
    }
	</#if>
</#list>

}
