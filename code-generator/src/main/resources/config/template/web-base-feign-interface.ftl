package ${relativeProjectPath}.${entityPath}.base;
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
import ${relativeProjectPath}.${entityPath}.${entity};
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import ${path_jbt_bms}.entity.ResultData;


public interface IBase${entity}Client{

	@RequestMapping(value = "/${apiPerfix}/${entity?lower_case}/create", method = RequestMethod.POST)
	ResultData<Integer> create(@RequestParam(value = "access_token") String accessToken,@RequestBody ${entity} ${entity?uncap_first});

	@RequestMapping(value = "/${apiPerfix}/${entity?lower_case}/modify", method = RequestMethod.POST)
    ResultData<Integer> modify(@RequestParam(value = "access_token") String accessToken,@RequestBody ${entity} ${entity?uncap_first});

	@RequestMapping(value = "/${apiPerfix}/${entity?lower_case}/delete", method = RequestMethod.POST)
    ResultData<Integer> delete(@RequestParam(value = "access_token") String accessToken,@RequestBody ${entity} ${entity?uncap_first});

	@RequestMapping(value = "/${apiPerfix}/${entity?lower_case}/one/criteria", method = RequestMethod.POST)
	ResultData<${entity}> one(@RequestParam(value = "access_token") String accessToken,@RequestBody ${entity} ${entity?uncap_first});

	@RequestMapping(value = "/${apiPerfix}/${entity?lower_case}/list", method = RequestMethod.POST)
    ResultData<List<${entity}>> list(@RequestParam(value = "access_token") String accessToken,@RequestBody ${entity} ${entity?uncap_first});

	@RequestMapping(value = "/${apiPerfix}/${entity?lower_case}/listall", method = RequestMethod.POST)
	ResultData<List<${entity}>> listall(@RequestParam(value = "access_token") String accessToken);

	@RequestMapping(value = "/${apiPerfix}/${entity?lower_case}/paginate", method = RequestMethod.POST)
    ResultData<PageInfo> paginate(@RequestParam(value = "access_token") String accessToken,@RequestBody ${entity} ${entity?uncap_first});
}
