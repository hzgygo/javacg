package com.hzgy.client.user.service.base;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.hzgy.client.user.vo.RoleInput;
import com.hzgy.client.user.vo.RoleOutput;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.hzgy.core.entity.ResultData;

public interface IBaseRoleClient{

	@PostMapping(value = "/api/role/create")
	ResultData<Integer> create(@RequestBody RoleInput roleInput);

	@PostMapping(value = "/api/role/modify")
    ResultData<Integer> modify(@RequestBody RoleInput roleInput);

	@PostMapping(value = "/api/role/delete/{id}")
    ResultData<Integer> delete(@PathVariable("id") Long id);
	
	@PostMapping(value = "/api/role/delete")
    ResultData<Integer> delete(@RequestBody RoleInput roleInput);

	@PostMapping(value = "/api/role/one/{id}")
	ResultData<RoleOutput> getOne(@PathVariable("id") Long id);

	@PostMapping(value = "/api/role/one/criteria")
	ResultData<RoleOutput> getOne(@RequestBody RoleInput roleInput);

	@PostMapping(value = "/api/role/list")
    ResultData<List<RoleOutput>> list(@RequestBody RoleInput roleInput);

	@PostMapping(value = "/api/role/listall")
	ResultData<List<RoleOutput>> listAll();

	@PostMapping(value = "/api/role/paginate")
    ResultData<PageInfo<List<RoleOutput>>> paginate(@RequestBody RoleInput roleInput);
}
