<#list columns as cols>
${entity?uncap_first}.${cols.code?uncap_first}=${cols.name}
</#list>
