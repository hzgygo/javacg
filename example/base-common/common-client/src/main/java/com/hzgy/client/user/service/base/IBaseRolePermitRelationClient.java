package com.hzgy.client.user.service.base;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.hzgy.client.user.vo.RolePermitRelationInput;
import com.hzgy.client.user.vo.RolePermitRelationOutput;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.hzgy.core.entity.ResultData;

public interface IBaseRolePermitRelationClient{

	@PostMapping(value = "/api/rolepermitrelation/create")
	ResultData<Integer> create(@RequestBody RolePermitRelationInput rolePermitRelationInput);


	@PostMapping(value = "/api/rolepermitrelation/one/criteria")
	ResultData<RolePermitRelationOutput> getOne(@RequestBody RolePermitRelationInput rolePermitRelationInput);

	@PostMapping(value = "/api/rolepermitrelation/list")
    ResultData<List<RolePermitRelationOutput>> list(@RequestBody RolePermitRelationInput rolePermitRelationInput);

	@PostMapping(value = "/api/rolepermitrelation/listall")
	ResultData<List<RolePermitRelationOutput>> listAll();

	@PostMapping(value = "/api/rolepermitrelation/paginate")
    ResultData<PageInfo<List<RolePermitRelationOutput>>> paginate(@RequestBody RolePermitRelationInput rolePermitRelationInput);
}
