package ${relativeProjectPath}.${entityPath};
<#assign idIsExist="false"/>
<#list columns as column>
<#assign property="result"/>
<#if column.code?lower_case == 'id' >
	<#assign property="id"/>
	<#assign idIsExist="true"/>
</#if>
</#list>
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
import ${path_jbt_interceptor}.valid.group.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.annotation.Resource;

import ${path_core}.entity.ResultData;
import ${relativeProjectPath}.${entityPath}.entity.${entity}Input;
import ${relativeProjectPath}.${entityPath}.entity.${entity}Output;
import ${relativeProjectPath}.${entityPath}.base.Base${entity}Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.github.pagehelper.PageInfo;

//@Api(value = "/${apiPerfix}/${entity?lower_case}", description = "${table.comment}接口")
//@RestController
//@EnableAutoConfiguration
//@RequestMapping(value="/${apiPerfix}/${entity?lower_case}")
public class ${entity}Controller extends Base${entity}Controller{

	private static Logger logger = LoggerFactory.getLogger(${entity}Controller.class);

    /**
	@Resource
	private I${entity}Service ${entity?uncap_first}Service;

	@ApiOperation(value = "创建数据")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResultData<Integer> create(
			@ApiParam(name = "创建数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
			@RequestBody @Validated ${entity}Input ${entity?uncap_first}Input,
			BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {
		return ${entity?uncap_first}Service.create(${entity?uncap_first}Input);
	}

	<#if idIsExist == 'true'>
    @ApiOperation(value = "数据修改")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResultData<Integer> modify(
            @ApiParam(name = "修改数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
            @RequestBody @Validated ${entity}Input ${entity?uncap_first}Input,
            BindingResult bindingResult,
            HttpServletRequest request,
            HttpServletResponse response) {
        return ${entity?uncap_first}Service.modifyById(${entity?uncap_first}Input);
    }

	@ApiOperation(value = "根据主键删除数据")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public ResultData<Integer> deleteById(
			@ApiParam(name = "删除数据主键", value = "数据库唯一主键")
			@PathVariable("id") @NotNull Long id,
			HttpServletRequest request, 
			HttpServletResponse response) {
    	return ${entity?uncap_first}Service.deleteById(id);
	}

	@ApiOperation(value = "根据主键查询数据")
	@RequestMapping(value = "/one/{id}", method = RequestMethod.POST)
	public ResultData<${entity}Output> oneById(
			@ApiParam(name = "查询数据主键", value = "数据库唯一主键")
			@PathVariable("id") @NotNull Long id,
			HttpServletRequest request, 
			HttpServletResponse response) {
    	return ${entity?uncap_first}Service.getOneById(id);
	}
	</#if>

	@ApiOperation(value = "动态条件获取删除数据")
	@RequestMapping(value = "/delete/criteria", method = RequestMethod.POST)
	public ResultData<Integer> deleteCriteria(
			@ApiParam(name = "删除数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
			@RequestBody @Validated ${entity}Input ${entity?uncap_first}Input,
    		BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {
		return ${entity?uncap_first}Service.deleteCriteria(${entity?uncap_first}Input);
	}

	@ApiOperation(value = "动态条件查询单条数据")
	@RequestMapping(value = "/one/criteria", method = RequestMethod.POST)
	public ResultData<${entity}Output> oneCriteria(
			@ApiParam(name = "查询单条数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
			@RequestBody ${entity}Input ${entity?uncap_first}Input,
			BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {
			return ${entity?uncap_first}Service.getOne(${entity?uncap_first}Input);
    }

	@ApiOperation(value = "动态条件获取列表数据")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResultData<List<${entity}Output>> list(
			@ApiParam(name = "获取列表数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
			@RequestBody @Validated ${entity}Input ${entity?uncap_first}Input,
			BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {
		return ${entity?uncap_first}Service.listCriteria(${entity?uncap_first}Input);
	}

	@ApiOperation(value = "查询所有列表数据")
	@RequestMapping(value = "/listall", method = RequestMethod.POST)
	public ResultData<List<${entity}Output>> listAll(
		HttpServletRequest request,
		HttpServletResponse response) {
		return ${entity?uncap_first}Service.listAll();
	}

	@ApiOperation(value = "动态条件获取分页列表数据")
	@RequestMapping(value = "/paginate", method = RequestMethod.POST)
	public ResultData<PageInfo> paginate(
			@ApiParam(name = "获取分页列表数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
			@RequestBody @Validated ${entity}Input ${entity?uncap_first}Input,
			BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {
		return ${entity?uncap_first}Service.listPaginated(${entity?uncap_first}Input);
	}
	**/
}
