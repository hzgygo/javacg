package com.hzgy.client.user.service.base;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.hzgy.client.user.vo.OrganizationInput;
import com.hzgy.client.user.vo.OrganizationOutput;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.hzgy.core.entity.ResultData;

public interface IBaseOrganizationClient{

	@PostMapping(value = "/api/organization/create")
	ResultData<Integer> create(@RequestBody OrganizationInput organizationInput);

	@PostMapping(value = "/api/organization/modify")
    ResultData<Integer> modify(@RequestBody OrganizationInput organizationInput);

	@PostMapping(value = "/api/organization/delete/{id}")
    ResultData<Integer> delete(@PathVariable("id") Long id);
	
	@PostMapping(value = "/api/organization/delete")
    ResultData<Integer> delete(@RequestBody OrganizationInput organizationInput);

	@PostMapping(value = "/api/organization/one/{id}")
	ResultData<OrganizationOutput> getOne(@PathVariable("id") Long id);

	@PostMapping(value = "/api/organization/one/criteria")
	ResultData<OrganizationOutput> getOne(@RequestBody OrganizationInput organizationInput);

	@PostMapping(value = "/api/organization/list")
    ResultData<List<OrganizationOutput>> list(@RequestBody OrganizationInput organizationInput);

	@PostMapping(value = "/api/organization/listall")
	ResultData<List<OrganizationOutput>> listAll();

	@PostMapping(value = "/api/organization/paginate")
    ResultData<PageInfo<List<OrganizationOutput>>> paginate(@RequestBody OrganizationInput organizationInput);
}
