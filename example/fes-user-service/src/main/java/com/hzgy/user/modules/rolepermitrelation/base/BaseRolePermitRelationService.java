package com.hzgy.user.modules.rolepermitrelation.base;

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
import com.hzgy.user.modules.rolepermitrelation.entity.RolePermitRelationInput;
import com.hzgy.user.modules.rolepermitrelation.entity.RolePermitRelationOutput;
import com.hzgy.user.modules.rolepermitrelation.entity.RolePermitRelationPo;
import com.hzgy.user.modules.rolepermitrelation.IRolePermitRelationDao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public abstract class BaseRolePermitRelationService extends BaseService implements IBaseRolePermitRelationService<RolePermitRelationInput,RolePermitRelationOutput>{

    private static Logger logger = LoggerFactory.getLogger(BaseRolePermitRelationService.class);
	//业务返回码
	protected final Integer MC_1 = 100401;
	protected final Integer MC_2 = 100402;
	protected final Integer MC_3 = 100403;
	protected final Integer MC_4 = 100404;
	protected final Integer MC_5 = 100405;
	protected final Integer MC_6 = 100406;
	protected final Integer MC_7 = 100407;
	protected final Integer MC_8 = 100408;
	protected final Integer MC_9 = 100409;
	protected final Integer MC_10 = 100410;
	protected final Integer MC_11 = 100411;
	protected final Integer MC_12 = 100412;
	protected final Integer MC_13 = 100413;
	protected final Integer MC_14 = 100414;
	protected final Integer MC_15 = 100415;
	protected final Integer MC_16 = 100416;
	protected final Integer MC_17 = 100417;
	protected final Integer MC_18 = 100418;
	protected final Integer MC_19 = 100419;
	protected final Integer MC_20 = 100420;



	@Resource
	protected RedisService<RolePermitRelationPo> redisService;
	
	@Resource
    protected IRolePermitRelationDao rolePermitRelationDao;

	@PostConstruct
	private void addService(){
		if (autoDataProcessor != null){
			autoDataProcessor.addService(this);
		}
	}

	/**
	 * 保存
	 * @param rolePermitRelationInput Input参数对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
    @Override
	public ResultData<Integer> create(RolePermitRelationInput rolePermitRelationInput) throws BaseException{
        //返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
    	InputToPo<RolePermitRelationInput,RolePermitRelationPo> inputToPo = new InputToPo<>();
		RolePermitRelationPo rolePermitRelationPo = inputToPo.toPo(rolePermitRelationInput,RolePermitRelationPo.class);
		//通过SnowflakeId生成数据库主键id
		long id = SnowflakeIdWorker.nextId();
		rolePermitRelationPo.setId(id);
		int rval = rolePermitRelationDao.save(rolePermitRelationPo);
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
	 * @param rolePermitRelationInput input对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
    @Override
	public ResultData<Integer> deleteCriteria(RolePermitRelationInput rolePermitRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
    	InputToPo<RolePermitRelationInput,RolePermitRelationPo> inputToPo = new InputToPo<>();
		RolePermitRelationPo rolePermitRelationPo = inputToPo.toPo(rolePermitRelationInput,RolePermitRelationPo.class);
		int rval = rolePermitRelationDao.removeCriteria(rolePermitRelationPo);
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
		int rval = rolePermitRelationDao.removeAll();
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
	 * @param rolePermitRelationInput input对象
	 * @return ResultData<List<RolePermitRelationOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<RolePermitRelationOutput>> listCriteria(RolePermitRelationInput rolePermitRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<RolePermitRelationOutput>> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<RolePermitRelationInput,RolePermitRelationPo> inputToPo = new InputToPo<>();
		RolePermitRelationPo rolePermitRelationPo = inputToPo.toPo(rolePermitRelationInput,RolePermitRelationPo.class);
		List<RolePermitRelationOutput> listOutput = null;
		List<RolePermitRelationPo> list = rolePermitRelationDao.listCriteria(rolePermitRelationPo);
		if (list != null){
			//对象转换工具
			PoToOutput<RolePermitRelationPo,RolePermitRelationOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,RolePermitRelationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}
	/**
	 * 查询所有数据
	 * @return ResultData<List<RolePermitRelationOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<RolePermitRelationOutput>> listAll() throws BaseException{
		//返回业务结果对象
		ResultData<List<RolePermitRelationOutput>> resultData = new ResultData<>();
        List<RolePermitRelationOutput> listOutput = null;
		List<RolePermitRelationPo> list = rolePermitRelationDao.listAll();
		if (list != null){
			//对象转换工具
			PoToOutput<RolePermitRelationPo,RolePermitRelationOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,RolePermitRelationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}


	/**
	 * 动态条件，获取单条数据
	 * @param rolePermitRelationInput input对象
	 * @return ResultData<RolePermitRelationOutput>
	 */
    @ReadDataSource
    @Override
	public ResultData<RolePermitRelationOutput> getOne(RolePermitRelationInput rolePermitRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<RolePermitRelationOutput> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<RolePermitRelationInput,RolePermitRelationPo> inputToPo = new InputToPo<>();
		RolePermitRelationPo rolePermitRelationPo = inputToPo.toPo(rolePermitRelationInput,RolePermitRelationPo.class);
		RolePermitRelationOutput rolePermitRelationResultOutput	= null;
		RolePermitRelationPo rolePermitRelationResultPo = rolePermitRelationDao.getOneCriteria(rolePermitRelationPo);
		if (rolePermitRelationResultPo != null){
			//对象转换工具
			PoToOutput<RolePermitRelationPo,RolePermitRelationOutput> poToOutput = new PoToOutput<>();
			rolePermitRelationResultOutput = poToOutput.toOutput(rolePermitRelationResultPo,RolePermitRelationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(rolePermitRelationResultOutput);
		return resultData;
	}

	/**
	 * 动态条件，查询记录总数
	 * @param rolePermitRelationInput Input对象
	 * @return ResultData<Long>
	 */
    @ReadDataSource
    @Override
	public ResultData<Long> count(RolePermitRelationInput rolePermitRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<Long> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<RolePermitRelationInput,RolePermitRelationPo> inputToPo = new InputToPo<>();
		RolePermitRelationPo rolePermitRelationPo = inputToPo.toPo(rolePermitRelationInput,RolePermitRelationPo.class);
		Long count = rolePermitRelationDao.count(rolePermitRelationPo);
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(count == null?0:count);
		return resultData;
	}

	/**
	 * 动态条件，自动分页查询列表数据
	 * @param  rolePermitRelationInput Input对象
	 * @return PageInfo
	 */
    @ReadDataSource
    @Override
	public ResultData<PageInfo> listPaginated(RolePermitRelationInput rolePermitRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo> resultData = new ResultData<>();
		int page = rolePermitRelationInput.getPage() == null?1 : rolePermitRelationInput.getPage();
		int rows = rolePermitRelationInput.getRows() == null?10 : rolePermitRelationInput.getRows();
		PageHelper.startPage(page, rows);
		//Input to po 转换
        InputToPo<RolePermitRelationInput,RolePermitRelationPo> inputToPo = new InputToPo<>();
		RolePermitRelationPo rolePermitRelationPo = inputToPo.toPo(rolePermitRelationInput,RolePermitRelationPo.class);
        PageInfo pageInfo = null;
		List<RolePermitRelationPo> list = rolePermitRelationDao.listPaginated(rolePermitRelationPo);
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
	 * @param rolePermitRelationInput Input对象
	 * @return ResultData<PageInfo<RolePermitRelationOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<PageInfo<RolePermitRelationOutput>> listPaginatedManual(RolePermitRelationInput  rolePermitRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo<RolePermitRelationOutput>> resultData = new ResultData<>();
		int page = rolePermitRelationInput.getPage() == null?1: rolePermitRelationInput.getPage();
		int rows = rolePermitRelationInput.getRows() == null?10: rolePermitRelationInput.getRows();
		//Input to po 转换
        InputToPo<RolePermitRelationInput,RolePermitRelationPo> inputToPo = new InputToPo<>();
		RolePermitRelationPo rolePermitRelationPo = inputToPo.toPo(rolePermitRelationInput,RolePermitRelationPo.class);
		//计算手动分页参数
		int start = (page - 1) * rows;
		rolePermitRelationPo.setStart(start);
		rolePermitRelationPo.setOffset(rows);
        PageInfo<RolePermitRelationOutput> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(page);
		pageInfo.setPageSize(rows);
		Long countRecords = rolePermitRelationDao.countPaginatedManual(rolePermitRelationPo);
		List<RolePermitRelationPo> list = rolePermitRelationDao.listPaginatedManual(rolePermitRelationPo);
		//对象转换工具
		PoToOutput<RolePermitRelationPo,RolePermitRelationOutput> poToOutput = new PoToOutput<>();
		List<RolePermitRelationOutput> listOutput = poToOutput.toListOutput(list,RolePermitRelationOutput.class);
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
    * @param rolePermitRelationInput input对象
    * @return ResultData<List<RolePermitRelationOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<RolePermitRelationOutput>> listCriteriaInnerRole(RolePermitRelationInput rolePermitRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<RolePermitRelationOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<RolePermitRelationInput,RolePermitRelationPo> inputToPo = new InputToPo<>();
		RolePermitRelationPo rolePermitRelationPo = inputToPo.toPo(rolePermitRelationInput,RolePermitRelationPo.class);
		List<RolePermitRelationOutput> listOutput = null;
		List<RolePermitRelationPo> list = rolePermitRelationDao.listCriteriaInnerRole(rolePermitRelationPo);
		if (list != null){
			//对象转换工具
			PoToOutput<RolePermitRelationPo,RolePermitRelationOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,RolePermitRelationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }

    /**
    * 查询所有数据
    * @return ResultData<List<RolePermitRelationOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<RolePermitRelationOutput>> listAllInnerRole() throws BaseException{
		//返回业务结果对象
		ResultData<List<RolePermitRelationOutput>> resultData = new ResultData<>();
		List<RolePermitRelationOutput> listOutput = null;
		List<RolePermitRelationPo> list = rolePermitRelationDao.listAllInnerRole();
		if (list != null){
			//对象转换工具
			PoToOutput<RolePermitRelationPo,RolePermitRelationOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,RolePermitRelationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }


    /**
    * 动态条件，获取单条数据
    * @param rolePermitRelationInput input对象
    * @return ResultData<RolePermitRelationOutput>
    */
    @ReadDataSource
    @Override
    public ResultData<RolePermitRelationOutput> getOneInnerRole(RolePermitRelationInput rolePermitRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<RolePermitRelationOutput> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<RolePermitRelationInput,RolePermitRelationPo> inputToPo = new InputToPo<>();
		RolePermitRelationPo rolePermitRelationPo = inputToPo.toPo(rolePermitRelationInput,RolePermitRelationPo.class);
		RolePermitRelationOutput rolePermitRelationResultOutput	= null;
		RolePermitRelationPo rolePermitRelationResultPo = rolePermitRelationDao.getOneCriteriaInnerRole(rolePermitRelationPo);
		if (rolePermitRelationResultPo != null){
			//对象转换工具
			PoToOutput<RolePermitRelationPo,RolePermitRelationOutput> poToOutput = new PoToOutput<>();
			rolePermitRelationResultOutput = poToOutput.jsonOutput(rolePermitRelationResultPo,RolePermitRelationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(rolePermitRelationResultOutput);
		return resultData;
    }

    /**
    * 动态条件，自动分页查询列表数据
    * @param  rolePermitRelationInput Input对象
    * @return PageInfo
    */
    @ReadDataSource
    @Override
    public ResultData<PageInfo> listPaginatedInnerRole(RolePermitRelationInput rolePermitRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo> resultData = new ResultData<>();
		int page = rolePermitRelationInput.getPage() == null?1 : rolePermitRelationInput.getPage();
		int rows = rolePermitRelationInput.getRows() == null?10 : rolePermitRelationInput.getRows();
		PageHelper.startPage(page, rows);
		//Input to po 转换
		InputToPo<RolePermitRelationInput,RolePermitRelationPo> inputToPo = new InputToPo<>();
		RolePermitRelationPo rolePermitRelationPo = inputToPo.toPo(rolePermitRelationInput,RolePermitRelationPo.class);
		PageInfo pageInfo = null;
		List<RolePermitRelationPo> list = rolePermitRelationDao.listPaginatedInnerRole(rolePermitRelationPo);
		if (list != null){
    		pageInfo = new PageInfo<>(list);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
        resultData.setData(pageInfo == null?new PageInfo<>():pageInfo);
		return resultData;
    }
    /**
    * 动态条件关联查询列表数据
    * @param rolePermitRelationInput input对象
    * @return ResultData<List<RolePermitRelationOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<RolePermitRelationOutput>> listCriteriaInnerPermission(RolePermitRelationInput rolePermitRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<RolePermitRelationOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<RolePermitRelationInput,RolePermitRelationPo> inputToPo = new InputToPo<>();
		RolePermitRelationPo rolePermitRelationPo = inputToPo.toPo(rolePermitRelationInput,RolePermitRelationPo.class);
		List<RolePermitRelationOutput> listOutput = null;
		List<RolePermitRelationPo> list = rolePermitRelationDao.listCriteriaInnerPermission(rolePermitRelationPo);
		if (list != null){
			//对象转换工具
			PoToOutput<RolePermitRelationPo,RolePermitRelationOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,RolePermitRelationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }

    /**
    * 查询所有数据
    * @return ResultData<List<RolePermitRelationOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<RolePermitRelationOutput>> listAllInnerPermission() throws BaseException{
		//返回业务结果对象
		ResultData<List<RolePermitRelationOutput>> resultData = new ResultData<>();
		List<RolePermitRelationOutput> listOutput = null;
		List<RolePermitRelationPo> list = rolePermitRelationDao.listAllInnerPermission();
		if (list != null){
			//对象转换工具
			PoToOutput<RolePermitRelationPo,RolePermitRelationOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,RolePermitRelationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }


    /**
    * 动态条件，获取单条数据
    * @param rolePermitRelationInput input对象
    * @return ResultData<RolePermitRelationOutput>
    */
    @ReadDataSource
    @Override
    public ResultData<RolePermitRelationOutput> getOneInnerPermission(RolePermitRelationInput rolePermitRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<RolePermitRelationOutput> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<RolePermitRelationInput,RolePermitRelationPo> inputToPo = new InputToPo<>();
		RolePermitRelationPo rolePermitRelationPo = inputToPo.toPo(rolePermitRelationInput,RolePermitRelationPo.class);
		RolePermitRelationOutput rolePermitRelationResultOutput	= null;
		RolePermitRelationPo rolePermitRelationResultPo = rolePermitRelationDao.getOneCriteriaInnerPermission(rolePermitRelationPo);
		if (rolePermitRelationResultPo != null){
			//对象转换工具
			PoToOutput<RolePermitRelationPo,RolePermitRelationOutput> poToOutput = new PoToOutput<>();
			rolePermitRelationResultOutput = poToOutput.jsonOutput(rolePermitRelationResultPo,RolePermitRelationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(rolePermitRelationResultOutput);
		return resultData;
    }

    /**
    * 动态条件，自动分页查询列表数据
    * @param  rolePermitRelationInput Input对象
    * @return PageInfo
    */
    @ReadDataSource
    @Override
    public ResultData<PageInfo> listPaginatedInnerPermission(RolePermitRelationInput rolePermitRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo> resultData = new ResultData<>();
		int page = rolePermitRelationInput.getPage() == null?1 : rolePermitRelationInput.getPage();
		int rows = rolePermitRelationInput.getRows() == null?10 : rolePermitRelationInput.getRows();
		PageHelper.startPage(page, rows);
		//Input to po 转换
		InputToPo<RolePermitRelationInput,RolePermitRelationPo> inputToPo = new InputToPo<>();
		RolePermitRelationPo rolePermitRelationPo = inputToPo.toPo(rolePermitRelationInput,RolePermitRelationPo.class);
		PageInfo pageInfo = null;
		List<RolePermitRelationPo> list = rolePermitRelationDao.listPaginatedInnerPermission(rolePermitRelationPo);
		if (list != null){
    		pageInfo = new PageInfo<>(list);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
        resultData.setData(pageInfo == null?new PageInfo<>():pageInfo);
		return resultData;
    }

}
