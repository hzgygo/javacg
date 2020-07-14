package ${relativeProjectPath}.${servieSimpleName}.service;

import org.springframework.cloud.openfeign.FeignClient;
import ${relativeProjectPath}.${servieSimpleName}.service.base.IBase${entity}Client;

@FeignClient(name="<#noparse>${</#noparse>service.prefix<#noparse>}</#noparse>-${servieName}-service")
public interface I${entity}Client extends IBase${entity}Client{


}
