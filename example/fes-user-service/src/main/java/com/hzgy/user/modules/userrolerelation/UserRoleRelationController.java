package com.hzgy.user.modules.userrolerelation;
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
import com.hzgy.user.modules.userrolerelation.entity.UserRoleRelationInput;
import com.hzgy.user.modules.userrolerelation.entity.UserRoleRelationOutput;
import com.hzgy.user.modules.userrolerelation.base.BaseUserRoleRelationController;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.github.pagehelper.PageInfo;

//@Api(value = "/api/userrolerelation", description = "用户角色关系映射表接口")
//@RestController
//@EnableAutoConfiguration
//@RequestMapping(value="/api/userrolerelation")
public class UserRoleRelationController extends BaseUserRoleRelationController{

	private static Logger logger = LoggerFactory.getLogger(UserRoleRelationController.class);

    /**
	@Resource
	private IUserRoleRelationService userRoleRelationService;

	@ApiOperation(value = "创建数据")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResultData<Integer> create(
			@ApiParam(name = "创建数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
			@RequestBody @Validated UserRoleRelationInput userRoleRelationInput,
			BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {
		return userRoleRelationService.create(userRoleRelationInput);
	}


	@ApiOperation(value = "动态条件获取删除数据")
	@RequestMapping(value = "/delete/criteria", method = RequestMethod.POST)
	public ResultData<Integer> deleteCriteria(
			@ApiParam(name = "删除数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
			@RequestBody @Validated UserRoleRelationInput userRoleRelationInput,
    		BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {
		return userRoleRelationService.deleteCriteria(userRoleRelationInput);
	}

	@ApiOperation(value = "动态条件查询单条数据")
	@RequestMapping(value = "/one/criteria", method = RequestMethod.POST)
	public ResultData<UserRoleRelationOutput> oneCriteria(
			@ApiParam(name = "查询单条数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
			@RequestBody UserRoleRelationInput userRoleRelationInput,
			BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {
			return userRoleRelationService.getOne(userRoleRelationInput);
    }

	@ApiOperation(value = "动态条件获取列表数据")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResultData<List<UserRoleRelationOutput>> list(
			@ApiParam(name = "获取列表数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
			@RequestBody @Validated UserRoleRelationInput userRoleRelationInput,
			BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {
		return userRoleRelationService.listCriteria(userRoleRelationInput);
	}

	@ApiOperation(value = "查询所有列表数据")
	@RequestMapping(value = "/listall", method = RequestMethod.POST)
	public ResultData<List<UserRoleRelationOutput>> listAll(
		HttpServletRequest request,
		HttpServletResponse response) {
		return userRoleRelationService.listAll();
	}

	@ApiOperation(value = "动态条件获取分页列表数据")
	@RequestMapping(value = "/paginate", method = RequestMethod.POST)
	public ResultData<PageInfo> paginate(
			@ApiParam(name = "获取分页列表数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
			@RequestBody @Validated UserRoleRelationInput userRoleRelationInput,
			BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {
		return userRoleRelationService.listPaginated(userRoleRelationInput);
	}
	**/
}
