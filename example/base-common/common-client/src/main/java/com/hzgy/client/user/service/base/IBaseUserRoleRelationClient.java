package com.hzgy.client.user.service.base;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.hzgy.client.user.vo.UserRoleRelationInput;
import com.hzgy.client.user.vo.UserRoleRelationOutput;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.hzgy.core.entity.ResultData;

public interface IBaseUserRoleRelationClient{

	@PostMapping(value = "/api/userrolerelation/create")
	ResultData<Integer> create(@RequestBody UserRoleRelationInput userRoleRelationInput);


	@PostMapping(value = "/api/userrolerelation/one/criteria")
	ResultData<UserRoleRelationOutput> getOne(@RequestBody UserRoleRelationInput userRoleRelationInput);

	@PostMapping(value = "/api/userrolerelation/list")
    ResultData<List<UserRoleRelationOutput>> list(@RequestBody UserRoleRelationInput userRoleRelationInput);

	@PostMapping(value = "/api/userrolerelation/listall")
	ResultData<List<UserRoleRelationOutput>> listAll();

	@PostMapping(value = "/api/userrolerelation/paginate")
    ResultData<PageInfo<List<UserRoleRelationOutput>>> paginate(@RequestBody UserRoleRelationInput userRoleRelationInput);
}
