package com.hzgy.client.user.service.base;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.hzgy.client.user.vo.PermissionInput;
import com.hzgy.client.user.vo.PermissionOutput;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.hzgy.core.entity.ResultData;

public interface IBasePermissionClient{

	@PostMapping(value = "/api/permission/create")
	ResultData<Integer> create(@RequestBody PermissionInput permissionInput);

	@PostMapping(value = "/api/permission/modify")
    ResultData<Integer> modify(@RequestBody PermissionInput permissionInput);

	@PostMapping(value = "/api/permission/delete/{id}")
    ResultData<Integer> delete(@PathVariable("id") Long id);
	
	@PostMapping(value = "/api/permission/delete")
    ResultData<Integer> delete(@RequestBody PermissionInput permissionInput);

	@PostMapping(value = "/api/permission/one/{id}")
	ResultData<PermissionOutput> getOne(@PathVariable("id") Long id);

	@PostMapping(value = "/api/permission/one/criteria")
	ResultData<PermissionOutput> getOne(@RequestBody PermissionInput permissionInput);

	@PostMapping(value = "/api/permission/list")
    ResultData<List<PermissionOutput>> list(@RequestBody PermissionInput permissionInput);

	@PostMapping(value = "/api/permission/listall")
	ResultData<List<PermissionOutput>> listAll();

	@PostMapping(value = "/api/permission/paginate")
    ResultData<PageInfo<List<PermissionOutput>>> paginate(@RequestBody PermissionInput permissionInput);
}
