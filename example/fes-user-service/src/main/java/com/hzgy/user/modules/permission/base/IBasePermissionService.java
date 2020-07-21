package com.hzgy.user.modules.permission.base;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.hzgy.core.entity.BaseInput;
import com.hzgy.core.entity.BaseOutput;
import com.hzgy.core.entity.ResultData;
import com.hzgy.core.service.IService;
import com.hzgy.core.exception.base.BaseException;

public interface IBasePermissionService<Input extends BaseInput,Output extends BaseOutput> extends IService{

	/**
	 * 保存
	 * @param input input参数对象
	 * @return ResultData<Integer>
	 */
	ResultData<Integer> create(Input input) throws BaseException;

	/**
	 * 根据id修改
	 * @param input input参数对象
	 * @return ResultData<Integer>
	 */
	ResultData<Integer> modifyById(Input input) throws BaseException;
	
	/**
	 * 动态条件删除
	 * @param input input参数对象
	 * @return ResultData<Integer>
	 */
	ResultData<Integer> deleteCriteria(Input input) throws BaseException;
	
	/**
	 * 根据Id删除数据
	 * @param id 主键id
	 * @return ResultData<Integer>
	 */
	ResultData<Integer> deleteById(Long id) throws BaseException;

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
	 * 动态条件查询主键Id数据
	 * @param input input参数对象
	 * @return ResultData<List<Long>>
	 */
	ResultData<List<Long>> listIdsCriteria(Input input) throws BaseException;

	/**
	 * 根据id串，in的方式查询数据
	 * @param input input参数对象
	 * @return ResultData<List<Output>>
	 */
	ResultData<List<Output>> listInIds(Input input) throws BaseException;
	/**
	 * 查询所有数据
	 * @return ResultData<List<Output>>
	 */
	ResultData<List<Output>> listAll() throws BaseException;
	
	/**
	 * 根据id，获取单条数据
	 * @param id 主键id
	 * @return ResultData<Output>
	 */
	ResultData<Output> getOneById(Long id) throws BaseException;

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
	ResultData<List<Output>> listCriteriaLeftRolePermitRelation(Input input) throws BaseException;
	/**
	* 根据id串，in的方式关联查询数据
	* @param input input参数对象
	* @return ResultData<List<Output>>
	*/
	ResultData<List<Output>> listInIdsLeftRolePermitRelation(Input input) throws BaseException;
	/**
	* 关联查询所有数据
	* @return ResultData<List<Output>>
	*/
	ResultData<List<Output>> listAllLeftRolePermitRelation() throws BaseException;

	/**
	* 根据id，关联获取单条数据
	* @param id 主键id
	* @return ResultData<Output>
	*/
	ResultData<Output> getOneByIdLeftRolePermitRelation(Long id) throws BaseException;

	/**
	* 动态条件，关联获取单条数据
	* @param input input对象
	* @return ResultData<Output>
	*/
	ResultData<Output> getOneLeftRolePermitRelation(Input input) throws BaseException;

	/**
	* 动态条件，自动分页关联查询列表数据
	* @param  input input参数对象
	* @return ResultData<PageInfo<Output>>
	*/
	ResultData<PageInfo> listPaginatedLeftRolePermitRelation(Input input) throws BaseException;




}
