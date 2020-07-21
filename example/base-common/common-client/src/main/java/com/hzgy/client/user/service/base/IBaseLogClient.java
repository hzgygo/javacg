package com.hzgy.client.user.service.base;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.hzgy.client.user.vo.LogInput;
import com.hzgy.client.user.vo.LogOutput;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.hzgy.core.entity.ResultData;

public interface IBaseLogClient{

	@PostMapping(value = "/api/log/create")
	ResultData<Integer> create(@RequestBody LogInput logInput);

	@PostMapping(value = "/api/log/modify")
    ResultData<Integer> modify(@RequestBody LogInput logInput);

	@PostMapping(value = "/api/log/delete/{id}")
    ResultData<Integer> delete(@PathVariable("id") Long id);
	
	@PostMapping(value = "/api/log/delete")
    ResultData<Integer> delete(@RequestBody LogInput logInput);

	@PostMapping(value = "/api/log/one/{id}")
	ResultData<LogOutput> getOne(@PathVariable("id") Long id);

	@PostMapping(value = "/api/log/one/criteria")
	ResultData<LogOutput> getOne(@RequestBody LogInput logInput);

	@PostMapping(value = "/api/log/list")
    ResultData<List<LogOutput>> list(@RequestBody LogInput logInput);

	@PostMapping(value = "/api/log/listall")
	ResultData<List<LogOutput>> listAll();

	@PostMapping(value = "/api/log/paginate")
    ResultData<PageInfo<List<LogOutput>>> paginate(@RequestBody LogInput logInput);
}
