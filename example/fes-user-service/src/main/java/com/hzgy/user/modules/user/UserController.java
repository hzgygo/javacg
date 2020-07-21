package com.hzgy.user.modules.user;
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
import com.hzgy.user.modules.user.entity.UserInput;
import com.hzgy.user.modules.user.entity.UserOutput;
import com.hzgy.user.modules.user.base.BaseUserController;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import com.github.pagehelper.PageInfo;

//@Api(value = "/api/user", description = "用户信息表接口")
//@RestController
//@EnableAutoConfiguration
//@RequestMapping(value="/api/user")
public class UserController extends BaseUserController{

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
	@Resource
	private IUserService userService;

	@ApiOperation(value = "创建数据")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResultData<Integer> create(
			@ApiParam(name = "创建数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
			@RequestBody @Validated UserInput userInput,
			BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {
		return userService.create(userInput);
	}

    @ApiOperation(value = "数据修改")
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResultData<Integer> modify(
            @ApiParam(name = "修改数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
            @RequestBody @Validated UserInput userInput,
            BindingResult bindingResult,
            HttpServletRequest request,
            HttpServletResponse response) {
        return userService.modifyById(userInput);
    }

	@ApiOperation(value = "根据主键删除数据")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public ResultData<Integer> deleteById(
			@ApiParam(name = "删除数据主键", value = "数据库唯一主键")
			@PathVariable("id") @NotNull Long id,
			HttpServletRequest request, 
			HttpServletResponse response) {
    	return userService.deleteById(id);
	}

	@ApiOperation(value = "根据主键查询数据")
	@RequestMapping(value = "/one/{id}", method = RequestMethod.POST)
	public ResultData<UserOutput> oneById(
			@ApiParam(name = "查询数据主键", value = "数据库唯一主键")
			@PathVariable("id") @NotNull Long id,
			HttpServletRequest request, 
			HttpServletResponse response) {
    	return userService.getOneById(id);
	}

	@ApiOperation(value = "动态条件获取删除数据")
	@RequestMapping(value = "/delete/criteria", method = RequestMethod.POST)
	public ResultData<Integer> deleteCriteria(
			@ApiParam(name = "删除数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
			@RequestBody @Validated UserInput userInput,
    		BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {
		return userService.deleteCriteria(userInput);
	}

	@ApiOperation(value = "动态条件查询单条数据")
	@RequestMapping(value = "/one/criteria", method = RequestMethod.POST)
	public ResultData<UserOutput> oneCriteria(
			@ApiParam(name = "查询单条数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
			@RequestBody UserInput userInput,
			BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {
			return userService.getOne(userInput);
    }

	@ApiOperation(value = "动态条件获取列表数据")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ResultData<List<UserOutput>> list(
			@ApiParam(name = "获取列表数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
			@RequestBody @Validated UserInput userInput,
			BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {
		return userService.listCriteria(userInput);
	}

	@ApiOperation(value = "查询所有列表数据")
	@RequestMapping(value = "/listall", method = RequestMethod.POST)
	public ResultData<List<UserOutput>> listAll(
		HttpServletRequest request,
		HttpServletResponse response) {
		return userService.listAll();
	}

	@ApiOperation(value = "动态条件获取分页列表数据")
	@RequestMapping(value = "/paginate", method = RequestMethod.POST)
	public ResultData<PageInfo> paginate(
			@ApiParam(name = "获取分页列表数据请求参数实体", value = "与请求实体匹配的JSON格式参数")
			@RequestBody @Validated UserInput userInput,
			BindingResult bindingResult,
			HttpServletRequest request,
			HttpServletResponse response) {
		return userService.listPaginated(userInput);
	}
	**/
}
