package com.hzgy.user.modules.permission.base;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import javax.annotation.PostConstruct;
import com.hzgy.core.util.SnowflakeIdWorker;

import com.hzgy.core.service.BaseService;
import com.hzgy.db.redis.RedisService;
import com.hzgy.core.common.ReturnCode;
import com.hzgy.core.entity.ResultData;
import com.hzgy.core.common.converter.InputToPo;
import com.hzgy.core.common.converter.PoToOutput;
import com.hzgy.core.exception.base.BaseException;
import com.hzgy.core.exception.CodeException;
import com.hzgy.db.annotation.datasource.ReadDataSource;
import com.hzgy.db.annotation.datasource.WriteDataSource;
import com.hzgy.user.modules.permission.entity.PermissionInput;
import com.hzgy.user.modules.permission.entity.PermissionOutput;
import com.hzgy.user.modules.permission.entity.PermissionPo;
import com.hzgy.user.modules.permission.IPermissionDao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public abstract class BasePermissionService extends BaseService implements IBasePermissionService<PermissionInput,PermissionOutput>{

    private static Logger logger = LoggerFactory.getLogger(BasePermissionService.class);
	//业务返回码
	protected final Integer MC_1 = 100501;
	protected final Integer MC_2 = 100502;
	protected final Integer MC_3 = 100503;
	protected final Integer MC_4 = 100504;
	protected final Integer MC_5 = 100505;
	protected final Integer MC_6 = 100506;
	protected final Integer MC_7 = 100507;
	protected final Integer MC_8 = 100508;
	protected final Integer MC_9 = 100509;
	protected final Integer MC_10 = 100510;
	protected final Integer MC_11 = 100511;
	protected final Integer MC_12 = 100512;
	protected final Integer MC_13 = 100513;
	protected final Integer MC_14 = 100514;
	protected final Integer MC_15 = 100515;
	protected final Integer MC_16 = 100516;
	protected final Integer MC_17 = 100517;
	protected final Integer MC_18 = 100518;
	protected final Integer MC_19 = 100519;
	protected final Integer MC_20 = 100520;



	@Resource
	protected RedisService<PermissionPo> redisService;
	
	@Resource
    protected IPermissionDao permissionDao;

	@PostConstruct
	private void addService(){
		if (autoDataProcessor != null){
			autoDataProcessor.addService(this);
		}
	}

	/**
	 * 保存
	 * @param permissionInput Input参数对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
    @Override
	public ResultData<Integer> create(PermissionInput permissionInput) throws BaseException{
        //返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
    	InputToPo<PermissionInput,PermissionPo> inputToPo = new InputToPo<>();
		PermissionPo permissionPo = inputToPo.toPo(permissionInput,PermissionPo.class);
		//通过SnowflakeId生成数据库主键id
		long id = SnowflakeIdWorker.nextId();
		permissionPo.setId(id);
		int rval = permissionDao.save(permissionPo);
		if (rval == 0){
            throw new CodeException(ReturnCode.FAIL.getCode());
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(rval);
		return resultData;
	}

	/**
	 * 根据id修改
	 * @param permissionInput input对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
	@Override
	public ResultData<Integer> modifyById(PermissionInput permissionInput) throws BaseException{
        //返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<PermissionInput,PermissionPo> inputToPo = new InputToPo<>();
		PermissionPo permissionPo = inputToPo.toPo(permissionInput,PermissionPo.class);
		int rval = permissionDao.updateById(permissionPo);
        if (rval == 0){
        	throw new CodeException(ReturnCode.FAIL.getCode());
        }
        resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
        resultData.setData(rval);
        return resultData;
	}

	/**
	 * 动态条件删除
	 * @param permissionInput input对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
    @Override
	public ResultData<Integer> deleteCriteria(PermissionInput permissionInput) throws BaseException{
		//返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
    	InputToPo<PermissionInput,PermissionPo> inputToPo = new InputToPo<>();
		PermissionPo permissionPo = inputToPo.toPo(permissionInput,PermissionPo.class);
		int rval = permissionDao.removeCriteria(permissionPo);
		if (rval == 0){
			throw new CodeException(ReturnCode.FAIL.getCode());
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(rval);
		return resultData;
	}

	/**
	 * 根据Id删除数据
	 * @param id 主键id
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
    @Override
	public ResultData<Integer> deleteById(Long id) throws BaseException{
        //返回业务结果对象
        ResultData<Integer> resultData = new ResultData<>();
		int rval = permissionDao.removeById(id);
        if (rval == 0){
        	throw new CodeException(ReturnCode.FAIL.getCode());
        }
        resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
        resultData.setData(rval);
        return resultData;
	}

	/**
	 * 删除所有数据
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
    @Override
	public ResultData<Integer> deleteAll() throws BaseException{
		//返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		int rval = permissionDao.removeAll();
		if (rval == 0){
			throw new CodeException(ReturnCode.FAIL.getCode());
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(rval);
		return resultData;
	}

	/**
	 * 动态条件查询列表数据
	 * @param permissionInput input对象
	 * @return ResultData<List<PermissionOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<PermissionOutput>> listCriteria(PermissionInput permissionInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<PermissionOutput>> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<PermissionInput,PermissionPo> inputToPo = new InputToPo<>();
		PermissionPo permissionPo = inputToPo.toPo(permissionInput,PermissionPo.class);
		List<PermissionOutput> listOutput = null;
		List<PermissionPo> list = permissionDao.listCriteria(permissionPo);
		if (list != null){
			//对象转换工具
			PoToOutput<PermissionPo,PermissionOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,PermissionOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}
	/**
	 * 动态条件查询主键Id数据
	 * @param permissionInput Input对象
	 * @return ResultData<List<Long>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<Long>> listIdsCriteria(PermissionInput permissionInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<Long>> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<PermissionInput,PermissionPo> inputToPo = new InputToPo<>();
		PermissionPo permissionPo = inputToPo.toPo(permissionInput,PermissionPo.class);
        List<Long> list = permissionDao.listIdsCriteria(permissionPo);
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(list);
		return resultData;
	}

	/**
	 * 根据id串，in的方式查询数据
	 * @param permissionInput Input对象
	 * @return ResultData<List<PermissionOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<PermissionOutput>> listInIds(PermissionInput permissionInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<PermissionOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<PermissionInput,PermissionPo> inputToPo = new InputToPo<>();
		PermissionPo permissionPo = inputToPo.toPo(permissionInput,PermissionPo.class);
		List<PermissionOutput> listOutput = null;
		List<PermissionPo> list = permissionDao.listByInIds(permissionPo);
		if (list != null){
			//对象转换工具
			PoToOutput<PermissionPo,PermissionOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,PermissionOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}
	/**
	 * 查询所有数据
	 * @return ResultData<List<PermissionOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<PermissionOutput>> listAll() throws BaseException{
		//返回业务结果对象
		ResultData<List<PermissionOutput>> resultData = new ResultData<>();
        List<PermissionOutput> listOutput = null;
		List<PermissionPo> list = permissionDao.listAll();
		if (list != null){
			//对象转换工具
			PoToOutput<PermissionPo,PermissionOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,PermissionOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}

	/**
	 * 根据id，获取单条数据
	 * @param id 主键id
	 * @return ResultData<PermissionOutput>
	 */
    @ReadDataSource
	@Override
	public ResultData<PermissionOutput> getOneById(Long id) throws BaseException{
        //返回业务结果对象
        ResultData<PermissionOutput> resultData = new ResultData<>();
		PermissionOutput permissionOutput = null;
		PermissionPo permissionPo = permissionDao.getOneById(id);
		if (permissionPo != null){
			//对象转换工具
			PoToOutput<PermissionPo,PermissionOutput> poToOutput = new PoToOutput<>();
			permissionOutput = poToOutput.toOutput(permissionPo,PermissionOutput.class);
		}
        resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
        resultData.setData(permissionOutput);
        return resultData;
	}

	/**
	 * 动态条件，获取单条数据
	 * @param permissionInput input对象
	 * @return ResultData<PermissionOutput>
	 */
    @ReadDataSource
    @Override
	public ResultData<PermissionOutput> getOne(PermissionInput permissionInput) throws BaseException{
		//返回业务结果对象
		ResultData<PermissionOutput> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<PermissionInput,PermissionPo> inputToPo = new InputToPo<>();
		PermissionPo permissionPo = inputToPo.toPo(permissionInput,PermissionPo.class);
		PermissionOutput permissionResultOutput	= null;
		PermissionPo permissionResultPo = permissionDao.getOneCriteria(permissionPo);
		if (permissionResultPo != null){
			//对象转换工具
			PoToOutput<PermissionPo,PermissionOutput> poToOutput = new PoToOutput<>();
			permissionResultOutput = poToOutput.toOutput(permissionResultPo,PermissionOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(permissionResultOutput);
		return resultData;
	}

	/**
	 * 动态条件，查询记录总数
	 * @param permissionInput Input对象
	 * @return ResultData<Long>
	 */
    @ReadDataSource
    @Override
	public ResultData<Long> count(PermissionInput permissionInput) throws BaseException{
		//返回业务结果对象
		ResultData<Long> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<PermissionInput,PermissionPo> inputToPo = new InputToPo<>();
		PermissionPo permissionPo = inputToPo.toPo(permissionInput,PermissionPo.class);
		Long count = permissionDao.count(permissionPo);
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(count == null?0:count);
		return resultData;
	}

	/**
	 * 动态条件，自动分页查询列表数据
	 * @param  permissionInput Input对象
	 * @return PageInfo
	 */
    @ReadDataSource
    @Override
	public ResultData<PageInfo> listPaginated(PermissionInput permissionInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo> resultData = new ResultData<>();
		int page = permissionInput.getPage() == null?1 : permissionInput.getPage();
		int rows = permissionInput.getRows() == null?10 : permissionInput.getRows();
		PageHelper.startPage(page, rows);
		//Input to po 转换
        InputToPo<PermissionInput,PermissionPo> inputToPo = new InputToPo<>();
		PermissionPo permissionPo = inputToPo.toPo(permissionInput,PermissionPo.class);
        PageInfo pageInfo = null;
		List<PermissionPo> list = permissionDao.listPaginated(permissionPo);
		if (list != null){
            pageInfo = new PageInfo<>(list);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(pageInfo == null? new PageInfo<>():pageInfo);
		return resultData;
	}

	/**
	 * 动态条件，手动分页查询列表数据
	 * @param permissionInput Input对象
	 * @return ResultData<PageInfo<PermissionOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<PageInfo<PermissionOutput>> listPaginatedManual(PermissionInput  permissionInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo<PermissionOutput>> resultData = new ResultData<>();
		int page = permissionInput.getPage() == null?1: permissionInput.getPage();
		int rows = permissionInput.getRows() == null?10: permissionInput.getRows();
		//Input to po 转换
        InputToPo<PermissionInput,PermissionPo> inputToPo = new InputToPo<>();
		PermissionPo permissionPo = inputToPo.toPo(permissionInput,PermissionPo.class);
		//计算手动分页参数
		int start = (page - 1) * rows;
		permissionPo.setStart(start);
		permissionPo.setOffset(rows);
        PageInfo<PermissionOutput> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(page);
		pageInfo.setPageSize(rows);
		Long countRecords = permissionDao.countPaginatedManual(permissionPo);
		List<PermissionPo> list = permissionDao.listPaginatedManual(permissionPo);
		//对象转换工具
		PoToOutput<PermissionPo,PermissionOutput> poToOutput = new PoToOutput<>();
		List<PermissionOutput> listOutput = poToOutput.toListOutput(list,PermissionOutput.class);
		int pageTotal = (int) Math.ceil(countRecords / (double) rows);
		pageInfo.setPages(pageTotal);
		pageInfo.setList(listOutput);
		pageInfo.setTotal(countRecords);
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(pageInfo);
		return resultData;
	}

    /**
    * 动态条件关联查询列表数据
    * @param permissionInput input对象
    * @return ResultData<List<PermissionOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<PermissionOutput>> listCriteriaLeftRolePermitRelation(PermissionInput permissionInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<PermissionOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<PermissionInput,PermissionPo> inputToPo = new InputToPo<>();
		PermissionPo permissionPo = inputToPo.toPo(permissionInput,PermissionPo.class);
		List<PermissionOutput> listOutput = null;
		List<PermissionPo> list = permissionDao.listCriteriaLeftRolePermitRelation(permissionPo);
		if (list != null){
			//对象转换工具
			PoToOutput<PermissionPo,PermissionOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,PermissionOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }

    /**
    * 根据id串，in的方式查询数据
    * @param permissionInput Input对象
    * @return ResultData<List<PermissionOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<PermissionOutput>> listInIdsLeftRolePermitRelation(PermissionInput permissionInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<PermissionOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<PermissionInput,PermissionPo> inputToPo = new InputToPo<>();
		PermissionPo permissionPo = inputToPo.toPo(permissionInput,PermissionPo.class);
		List<PermissionOutput> listOutput = null;
		List<PermissionPo> list = permissionDao.listInIdsLeftRolePermitRelation(permissionPo);
		if (list != null){
			//对象转换工具
			PoToOutput<PermissionPo,PermissionOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,PermissionOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }
    /**
    * 查询所有数据
    * @return ResultData<List<PermissionOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<PermissionOutput>> listAllLeftRolePermitRelation() throws BaseException{
		//返回业务结果对象
		ResultData<List<PermissionOutput>> resultData = new ResultData<>();
		List<PermissionOutput> listOutput = null;
		List<PermissionPo> list = permissionDao.listAllLeftRolePermitRelation();
		if (list != null){
			//对象转换工具
			PoToOutput<PermissionPo,PermissionOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,PermissionOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }

	/**
	* 根据id，获取单条数据
	* @param id 主键id
	* @return ResultData<PermissionOutput>
	*/
	@ReadDataSource
	@Override
	public ResultData<PermissionOutput> getOneByIdLeftRolePermitRelation(Long id) throws BaseException{
		//返回业务结果对象
		ResultData<PermissionOutput> resultData = new ResultData<>();
		PermissionOutput permissionOutput = null;
		PermissionPo permissionPo = permissionDao.getOneByIdLeftRolePermitRelation(id);
		if (permissionPo != null){
			//对象转换工具
			PoToOutput<PermissionPo,PermissionOutput> poToOutput = new PoToOutput<>();
			permissionOutput = poToOutput.jsonOutput(permissionPo,PermissionOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(permissionOutput);
		return resultData;
	}

    /**
    * 动态条件，获取单条数据
    * @param permissionInput input对象
    * @return ResultData<PermissionOutput>
    */
    @ReadDataSource
    @Override
    public ResultData<PermissionOutput> getOneLeftRolePermitRelation(PermissionInput permissionInput) throws BaseException{
		//返回业务结果对象
		ResultData<PermissionOutput> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<PermissionInput,PermissionPo> inputToPo = new InputToPo<>();
		PermissionPo permissionPo = inputToPo.toPo(permissionInput,PermissionPo.class);
		PermissionOutput permissionResultOutput	= null;
		PermissionPo permissionResultPo = permissionDao.getOneCriteriaLeftRolePermitRelation(permissionPo);
		if (permissionResultPo != null){
			//对象转换工具
			PoToOutput<PermissionPo,PermissionOutput> poToOutput = new PoToOutput<>();
			permissionResultOutput = poToOutput.jsonOutput(permissionResultPo,PermissionOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(permissionResultOutput);
		return resultData;
    }

    /**
    * 动态条件，自动分页查询列表数据
    * @param  permissionInput Input对象
    * @return PageInfo
    */
    @ReadDataSource
    @Override
    public ResultData<PageInfo> listPaginatedLeftRolePermitRelation(PermissionInput permissionInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo> resultData = new ResultData<>();
		int page = permissionInput.getPage() == null?1 : permissionInput.getPage();
		int rows = permissionInput.getRows() == null?10 : permissionInput.getRows();
		PageHelper.startPage(page, rows);
		//Input to po 转换
		InputToPo<PermissionInput,PermissionPo> inputToPo = new InputToPo<>();
		PermissionPo permissionPo = inputToPo.toPo(permissionInput,PermissionPo.class);
		PageInfo pageInfo = null;
		List<PermissionPo> list = permissionDao.listPaginatedLeftRolePermitRelation(permissionPo);
		if (list != null){
    		pageInfo = new PageInfo<>(list);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(pageInfo == null? new PageInfo<>():pageInfo);
		return resultData;
    }

}
