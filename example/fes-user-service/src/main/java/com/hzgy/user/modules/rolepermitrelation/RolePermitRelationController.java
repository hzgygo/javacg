package com.hzgy.user.modules.rolepermitrelation;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;
import com.hzgy.interceptor.valid.group.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.annotation.Resource;

import com.hzgy.core.entity.ResultData;
import com.hzgy.user.modules.rolepermitrelation.entity.RolePermitRelationInput;
import com.hzgy.user.modules.rolepermitrelation.entity.RolePermitRelationOutput;
import com.hzgy.user.modules.rolepermitrelation.base.BaseRolePermitRelationController;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.github.pagehelper.PageInfo;

//@Api(value = "/api/rolepermitrelation", description = "用户角色关系映射表接口")
//@RestController
//@EnableAutoConfiguration
//@RequestMapping(value="/api/rolepermitrelation")
public class RolePermitRelationController extends BaseRolePermitRelationController{

	private static Logger logger = LoggerFactory.getLogger(RolePermitRelationController.class);

    /**
	@Resource
	private IRolePermitRelationService rolePermitRelationService;

	@ApiOperation(value = "创建数据")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResultData<Integer> create(
			@ApiParam(name = "创建数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
			@RequestBody @Validated RolePermitRelationInput rolePermitRelationInput,
			BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {
		return rolePermitRelationService.create(rolePermitRelationInput);
	}


	@ApiOperation(value = "动态条件获取删除数据")
	@RequestMapping(value = "/delete/criteria", method = RequestMethod.POST)
	public ResultData<Integer> deleteCriteria(
			@ApiParam(name = "删除数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
			@RequestBody @Validated RolePermitRelationInput rolePermitRelationInput,
    		BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {
		return rolePermitRelationService.deleteCriteria(rolePermitRelationInput);
	}

	@ApiOperation(value = "动态条件查询单条数据")
	@RequestMapping(value = "/one/criteria", method = RequestMethod.POST)
	public ResultData<RolePermitRelationOutput> oneCriteria(
			@ApiParam(name = "查询单条数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
			@RequestBody RolePermitRelationInput rolePermitRelationInput,
			BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {
			return rolePermitRelationService.getOne(rolePermitRelationInput);
    }

	@ApiOperation(value = "动态条件获取列表数据")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResultData<List<RolePermitRelationOutput>> list(
			@ApiParam(name = "获取列表数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
			@RequestBody @Validated RolePermitRelationInput rolePermitRelationInput,
			BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {
		return rolePermitRelationService.listCriteria(rolePermitRelationInput);
	}

	@ApiOperation(value = "查询所有列表数据")
	@RequestMapping(value = "/listall", method = RequestMethod.POST)
	public ResultData<List<RolePermitRelationOutput>> listAll(
		HttpServletRequest request,
		HttpServletResponse response) {
		return rolePermitRelationService.listAll();
	}

	@ApiOperation(value = "动态条件获取分页列表数据")
	@RequestMapping(value = "/paginate", method = RequestMethod.POST)
	public ResultData<PageInfo> paginate(
			@ApiParam(name = "获取分页列表数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
			@RequestBody @Validated RolePermitRelationInput rolePermitRelationInput,
			BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {
		return rolePermitRelationService.listPaginated(rolePermitRelationInput);
	}
	**/
}
