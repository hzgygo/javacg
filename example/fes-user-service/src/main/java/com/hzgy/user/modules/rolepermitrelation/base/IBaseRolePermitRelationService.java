package com.hzgy.user.modules.rolepermitrelation.base;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hzgy.core.entity.BaseInput;
import com.hzgy.core.entity.BaseOutput;
import com.hzgy.core.entity.ResultData;
import com.hzgy.core.service.IService;
import com.hzgy.core.exception.base.BaseException;

public interface IBaseRolePermitRelationService<Input extends BaseInput,Output extends BaseOutput> extends IService{

	/**
	 * 保存
	 * @param input input参数对象
	 * @return ResultData<Integer>
	 */
	ResultData<Integer> create(Input input) throws BaseException;

	
	/**
	 * 动态条件删除
	 * @param input input参数对象
	 * @return ResultData<Integer>
	 */
	ResultData<Integer> deleteCriteria(Input input) throws BaseException;
	

	/**
	 * 删除所有数据
	 * @return ResultData<Integer>
	 */
	ResultData<Integer> deleteAll() throws BaseException;
	
	/**
	 * 动态条件查询列表数据
	 * @param input input参数对象
	 * @return ResultData<List<Output>>
	 */
	ResultData<List<Output>> listCriteria(Input input) throws BaseException;
	/**
	 * 查询所有数据
	 * @return ResultData<List<Output>>
	 */
	ResultData<List<Output>> listAll() throws BaseException;
	

	/**
	 * 动态条件，获取单条数据
	 * @param input input对象
	 * @return ResultData<Output>
	 */
	ResultData<Output> getOne(Input input) throws BaseException;

	/**
	 * 动态条件，查询记录总数
	 * @param input input参数对象
	 * @return ResultData<Long>
	 */
	ResultData<Long> count(Input input) throws BaseException;

	/**
	 * 动态条件，自动分页查询列表数据
	 * @param  input input参数对象
	 * @return ResultData<PageInfo<Output>>
	 */
	ResultData<PageInfo> listPaginated(Input input) throws BaseException;

	/**
	 * 动态条件，手动分页查询列表数据
	 * @param input input参数对象
	 * @return ResultData<PageInfo<Output>>
	 */
	ResultData<PageInfo<Output>> listPaginatedManual(Input input) throws BaseException;


    /**
    * 动态条件关联查询列表数据
    * @param input input参数对象
    * @return ResultData<List<Output>>
    */
    ResultData<List<Output>> listCriteriaInnerRole(Input input) throws BaseException;
    /**
    * 关联查询所有数据
    * @return ResultData<List<Output>>
    */
    ResultData<List<Output>> listAllInnerRole() throws BaseException;


    /**
    * 动态条件，关联获取单条数据
    * @param input input对象
    * @return ResultData<Output>
    */
    ResultData<Output> getOneInnerRole(Input input) throws BaseException;

    /**
    * 动态条件，自动分页关联查询列表数据
    * @param  input input参数对象
    * @return ResultData<PageInfo<Output>>
    */
    ResultData<PageInfo> listPaginatedInnerRole(Input input) throws BaseException;

    /**
    * 动态条件关联查询列表数据
    * @param input input参数对象
    * @return ResultData<List<Output>>
    */
    ResultData<List<Output>> listCriteriaInnerPermission(Input input) throws BaseException;
    /**
    * 关联查询所有数据
    * @return ResultData<List<Output>>
    */
    ResultData<List<Output>> listAllInnerPermission() throws BaseException;


    /**
    * 动态条件，关联获取单条数据
    * @param input input对象
    * @return ResultData<Output>
    */
    ResultData<Output> getOneInnerPermission(Input input) throws BaseException;

    /**
    * 动态条件，自动分页关联查询列表数据
    * @param  input input参数对象
    * @return ResultData<PageInfo<Output>>
    */
    ResultData<PageInfo> listPaginatedInnerPermission(Input input) throws BaseException;




}
