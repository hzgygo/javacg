package ${relativeProjectPath}.${entityPath};
<#assign idIsExist="false"/>
<#list columns as column>
    <#assign property="result"/>
    <#if column.code?lower_case == 'id' >
        <#assign property="id"/>
        <#assign idIsExist="true"/>
    </#if>
</#list>

import org.springframework.cloud.openfeign.FeignClient;
import ${relativeProjectPath}.${entityPath}.base.IBase${entity}Client;

@FeignClient(name="${servieName}", url = "<#noparse>${</#noparse>service.api.domain<#noparse>}</#noparse>/bms-${servieName}")
public interface I${entity}Client extends IBase${entity}Client{

}
