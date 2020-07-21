package com.hzgy.client.user.service.base;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.hzgy.client.user.vo.UserInput;
import com.hzgy.client.user.vo.UserOutput;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.hzgy.core.entity.ResultData;

public interface IBaseUserClient{

	@PostMapping(value = "/api/user/create")
	ResultData<Integer> create(@RequestBody UserInput userInput);

	@PostMapping(value = "/api/user/modify")
    ResultData<Integer> modify(@RequestBody UserInput userInput);

	@PostMapping(value = "/api/user/delete/{id}")
    ResultData<Integer> delete(@PathVariable("id") Long id);
	
	@PostMapping(value = "/api/user/delete")
    ResultData<Integer> delete(@RequestBody UserInput userInput);

	@PostMapping(value = "/api/user/one/{id}")
	ResultData<UserOutput> getOne(@PathVariable("id") Long id);

	@PostMapping(value = "/api/user/one/criteria")
	ResultData<UserOutput> getOne(@RequestBody UserInput userInput);

	@PostMapping(value = "/api/user/list")
    ResultData<List<UserOutput>> list(@RequestBody UserInput userInput);

	@PostMapping(value = "/api/user/listall")
	ResultData<List<UserOutput>> listAll();

	@PostMapping(value = "/api/user/paginate")
    ResultData<PageInfo<List<UserOutput>>> paginate(@RequestBody UserInput userInput);
}
