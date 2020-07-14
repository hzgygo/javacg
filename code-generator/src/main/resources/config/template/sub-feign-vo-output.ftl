package ${relativeProjectPath}.${servieSimpleName}.vo;

import ${relativeProjectPath}.${servieSimpleName}.vo.base.Base${entity};
<#assign isJoin="false"/>
<#list references as reference>
<#if table.code == reference.parentTable.code>
    <#assign isJoin="true"/>
</#if>
</#list>
<#if isJoin == 'true'>
import java.util.List;
</#if>

public class ${entity}Output extends Base${entity}{

<#list references as reference>
<#assign join=""/>
<#assign joinTableName=""/>
<#assign isJoin="false"/>
<#assign isOne="false"/>
<#if table.code == reference.parentTable.code>
    <#assign join="left"/>
    <#assign isJoin="true"/>
    <#assign ctOriginalCode="${reference.childTable.originalCode}"/>
    <#assign ctCode="${reference.childTable.code}"/>
    <#assign ctIndex="${ctCode?index_of('_')}"/>
    <#assign ctLength="${ctCode?length}"/>
    <#assign joinTableName="${ctCode?substring(ctIndex?number+1,ctLength?number)}"/>
    <#list reference.joins as joins>
        <#if joins.parentTableColumn.originalCode == joins.childTableColumn.originalCode>
            <#assign isOne ="true"/>
        </#if>
    </#list>
    <#if isOne == 'true'>
    /**${reference.parentTable.comment}**/
    private ${joinTableName}Output ${joinTableName?uncap_first}Output;
    <#else >
    /**${reference.childTable.comment}**/
    private List<${joinTableName}Output> list${joinTableName}Output;
    </#if>

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
    private ${joinTableName}Output ${joinTableName?uncap_first}Output;
</#if>
</#list>

<#list references as reference>
<#assign join=""/>
<#assign joinTableName=""/>
<#assign isJoin="false"/>
<#assign isOne="false"/>
<#if table.code == reference.parentTable.code>
    <#assign join="left"/>
    <#assign isJoin="true"/>
    <#assign ctOriginalCode="${reference.childTable.originalCode}"/>
    <#assign ctCode="${reference.childTable.code}"/>
    <#assign ctIndex="${ctCode?index_of('_')}"/>
    <#assign ctLength="${ctCode?length}"/>
    <#assign joinTableName="${ctCode?substring(ctIndex?number+1,ctLength?number)}"/>
    <#list reference.joins as joins>
        <#if joins.parentTableColumn.originalCode == joins.childTableColumn.originalCode>
            <#assign isOne ="true"/>
        </#if>
    </#list>
    <#if isOne == 'true'>
    public ${joinTableName}Output get${joinTableName}Output() {
        return ${joinTableName?uncap_first}Output;
    }

    public void set${joinTableName}Output(${joinTableName}Output ${joinTableName?uncap_first}Output) {
        this.${joinTableName?uncap_first}Output = ${joinTableName?uncap_first}Output;
    }
    <#else >
    public List<${joinTableName}Output> getList${joinTableName}Output() {
        return list${joinTableName}Output;
    }

    public void setList${joinTableName}Output(List<${joinTableName}Output> list${joinTableName}Output) {
        this.list${joinTableName}Output = list${joinTableName}Output;
    }
    </#if>
</#if>
<#if table.code == reference.childTable.code>
    <#assign join="inner"/>
    <#assign isJoin="true"/>
    <#assign ctOriginalCode="${reference.parentTable.originalCode}"/>
    <#assign ctCode="${reference.parentTable.code}"/>
    <#assign ctIndex="${ctCode?index_of('_')}"/>
    <#assign ctLength="${ctCode?length}"/>
    <#assign joinTableName="${ctCode?substring(ctIndex?number+1,ctLength?number)}"/>

    public ${joinTableName}Output get${joinTableName}Output() {
        return ${joinTableName?uncap_first}Output;
    }

    public void set${joinTableName}Output(${joinTableName}Output ${joinTableName?uncap_first}Output) {
        this.${joinTableName?uncap_first}Output = ${joinTableName?uncap_first}Output;
    }
</#if>
</#list>
}
