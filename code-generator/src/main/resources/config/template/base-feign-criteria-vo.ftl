package ${relativeProjectPath}.${servieSimpleName}.vo.base;

import ${com_jbt_core}.entity.BaseClientVo;

public abstract class Base${entity}Criteria extends BaseClientVo{

<#list columns as cols>
	<#if cols.dataType?contains('varchar')>

    private String ${cols.code?uncap_first}AsLike;
    <#else>

    private String ${cols.code?uncap_first}Operator;

    private String ${cols.code?uncap_first}Expression;
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
    <#else>

    public String get${cols.code}Operator() {
        return ${cols.code?uncap_first}Operator;
    }

    public void set${cols.code}Operator(String ${cols.code?uncap_first}Operator) {
        this.${cols.code?uncap_first}Operator = ${cols.code?uncap_first}Operator;
    }

    public String get${cols.code}Expression() {
        return ${cols.code?uncap_first}Expression;
    }

    public void set${cols.code}Expression(String ${cols.code?uncap_first}Expression) {
        this.${cols.code?uncap_first}Expression = ${cols.code?uncap_first}Expression;
    }
	</#if>
</#list>

}
