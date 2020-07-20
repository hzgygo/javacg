package ${relativeProjectPath}.${servieSimpleName}.service.base;
<#assign idIsExist="false"/>
<#list columns as column>
<#assign property="result"/>
<#if column.code?lower_case == 'id' >
	<#assign property="id"/>
	<#assign idIsExist="true"/>
</#if>
</#list>

import java.util.List;
import com.github.pagehelper.PageInfo;
import ${relativeProjectPath}.${servieSimpleName}.vo.${entity}Input;
import ${relativeProjectPath}.${servieSimpleName}.vo.${entity}Output;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ${path_core}.entity.ResultData;

public interface IBase${entity}Client{

	@PostMapping(value = "/${apiPerfix}/${entity?lower_case}/create")
	ResultData<Integer> create(@RequestBody ${entity}Input ${entity?uncap_first}Input);

	<#if idIsExist == 'true'>
	@PostMapping(value = "/${apiPerfix}/${entity?lower_case}/modify")
    ResultData<Integer> modify(@RequestBody ${entity}Input ${entity?uncap_first}Input);

	@PostMapping(value = "/${apiPerfix}/${entity?lower_case}/delete/{id}")
    ResultData<Integer> delete(@PathVariable("id") Long id);
	
	@PostMapping(value = "/${apiPerfix}/${entity?lower_case}/delete")
    ResultData<Integer> delete(@RequestBody ${entity}Input ${entity?uncap_first}Input);

	@PostMapping(value = "/${apiPerfix}/${entity?lower_case}/one/{id}")
	ResultData<${entity}Output> getOne(@PathVariable("id") Long id);
	</#if>

	@PostMapping(value = "/${apiPerfix}/${entity?lower_case}/one/criteria")
	ResultData<${entity}Output> getOne(@RequestBody ${entity}Input ${entity?uncap_first}Input);

	@PostMapping(value = "/${apiPerfix}/${entity?lower_case}/list")
    ResultData<List<${entity}Output>> list(@RequestBody ${entity}Input ${entity?uncap_first}Input);

	@PostMapping(value = "/${apiPerfix}/${entity?lower_case}/listall")
	ResultData<List<${entity}Output>> listAll();

	@PostMapping(value = "/${apiPerfix}/${entity?lower_case}/paginate")
    ResultData<PageInfo<List<${entity}Output>>> paginate(@RequestBody ${entity}Input ${entity?uncap_first}Input);
}
