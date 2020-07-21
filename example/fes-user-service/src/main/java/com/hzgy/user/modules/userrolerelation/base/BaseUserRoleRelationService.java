package com.hzgy.user.modules.userrolerelation.base;

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
import com.hzgy.user.modules.userrolerelation.entity.UserRoleRelationInput;
import com.hzgy.user.modules.userrolerelation.entity.UserRoleRelationOutput;
import com.hzgy.user.modules.userrolerelation.entity.UserRoleRelationPo;
import com.hzgy.user.modules.userrolerelation.IUserRoleRelationDao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public abstract class BaseUserRoleRelationService extends BaseService implements IBaseUserRoleRelationService<UserRoleRelationInput,UserRoleRelationOutput>{

    private static Logger logger = LoggerFactory.getLogger(BaseUserRoleRelationService.class);
	//业务返回码
	protected final Integer MC_1 = 100201;
	protected final Integer MC_2 = 100202;
	protected final Integer MC_3 = 100203;
	protected final Integer MC_4 = 100204;
	protected final Integer MC_5 = 100205;
	protected final Integer MC_6 = 100206;
	protected final Integer MC_7 = 100207;
	protected final Integer MC_8 = 100208;
	protected final Integer MC_9 = 100209;
	protected final Integer MC_10 = 100210;
	protected final Integer MC_11 = 100211;
	protected final Integer MC_12 = 100212;
	protected final Integer MC_13 = 100213;
	protected final Integer MC_14 = 100214;
	protected final Integer MC_15 = 100215;
	protected final Integer MC_16 = 100216;
	protected final Integer MC_17 = 100217;
	protected final Integer MC_18 = 100218;
	protected final Integer MC_19 = 100219;
	protected final Integer MC_20 = 100220;



	@Resource
	protected RedisService<UserRoleRelationPo> redisService;
	
	@Resource
    protected IUserRoleRelationDao userRoleRelationDao;

	@PostConstruct
	private void addService(){
		if (autoDataProcessor != null){
			autoDataProcessor.addService(this);
		}
	}

	/**
	 * 保存
	 * @param userRoleRelationInput Input参数对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
    @Override
	public ResultData<Integer> create(UserRoleRelationInput userRoleRelationInput) throws BaseException{
        //返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
    	InputToPo<UserRoleRelationInput,UserRoleRelationPo> inputToPo = new InputToPo<>();
		UserRoleRelationPo userRoleRelationPo = inputToPo.toPo(userRoleRelationInput,UserRoleRelationPo.class);
		//通过SnowflakeId生成数据库主键id
		long id = SnowflakeIdWorker.nextId();
		userRoleRelationPo.setId(id);
		int rval = userRoleRelationDao.save(userRoleRelationPo);
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
	 * @param userRoleRelationInput input对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
    @Override
	public ResultData<Integer> deleteCriteria(UserRoleRelationInput userRoleRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
    	InputToPo<UserRoleRelationInput,UserRoleRelationPo> inputToPo = new InputToPo<>();
		UserRoleRelationPo userRoleRelationPo = inputToPo.toPo(userRoleRelationInput,UserRoleRelationPo.class);
		int rval = userRoleRelationDao.removeCriteria(userRoleRelationPo);
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
		int rval = userRoleRelationDao.removeAll();
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
	 * @param userRoleRelationInput input对象
	 * @return ResultData<List<UserRoleRelationOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<UserRoleRelationOutput>> listCriteria(UserRoleRelationInput userRoleRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<UserRoleRelationOutput>> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<UserRoleRelationInput,UserRoleRelationPo> inputToPo = new InputToPo<>();
		UserRoleRelationPo userRoleRelationPo = inputToPo.toPo(userRoleRelationInput,UserRoleRelationPo.class);
		List<UserRoleRelationOutput> listOutput = null;
		List<UserRoleRelationPo> list = userRoleRelationDao.listCriteria(userRoleRelationPo);
		if (list != null){
			//对象转换工具
			PoToOutput<UserRoleRelationPo,UserRoleRelationOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,UserRoleRelationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}
	/**
	 * 查询所有数据
	 * @return ResultData<List<UserRoleRelationOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<UserRoleRelationOutput>> listAll() throws BaseException{
		//返回业务结果对象
		ResultData<List<UserRoleRelationOutput>> resultData = new ResultData<>();
        List<UserRoleRelationOutput> listOutput = null;
		List<UserRoleRelationPo> list = userRoleRelationDao.listAll();
		if (list != null){
			//对象转换工具
			PoToOutput<UserRoleRelationPo,UserRoleRelationOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,UserRoleRelationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}


	/**
	 * 动态条件，获取单条数据
	 * @param userRoleRelationInput input对象
	 * @return ResultData<UserRoleRelationOutput>
	 */
    @ReadDataSource
    @Override
	public ResultData<UserRoleRelationOutput> getOne(UserRoleRelationInput userRoleRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<UserRoleRelationOutput> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<UserRoleRelationInput,UserRoleRelationPo> inputToPo = new InputToPo<>();
		UserRoleRelationPo userRoleRelationPo = inputToPo.toPo(userRoleRelationInput,UserRoleRelationPo.class);
		UserRoleRelationOutput userRoleRelationResultOutput	= null;
		UserRoleRelationPo userRoleRelationResultPo = userRoleRelationDao.getOneCriteria(userRoleRelationPo);
		if (userRoleRelationResultPo != null){
			//对象转换工具
			PoToOutput<UserRoleRelationPo,UserRoleRelationOutput> poToOutput = new PoToOutput<>();
			userRoleRelationResultOutput = poToOutput.toOutput(userRoleRelationResultPo,UserRoleRelationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(userRoleRelationResultOutput);
		return resultData;
	}

	/**
	 * 动态条件，查询记录总数
	 * @param userRoleRelationInput Input对象
	 * @return ResultData<Long>
	 */
    @ReadDataSource
    @Override
	public ResultData<Long> count(UserRoleRelationInput userRoleRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<Long> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<UserRoleRelationInput,UserRoleRelationPo> inputToPo = new InputToPo<>();
		UserRoleRelationPo userRoleRelationPo = inputToPo.toPo(userRoleRelationInput,UserRoleRelationPo.class);
		Long count = userRoleRelationDao.count(userRoleRelationPo);
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(count == null?0:count);
		return resultData;
	}

	/**
	 * 动态条件，自动分页查询列表数据
	 * @param  userRoleRelationInput Input对象
	 * @return PageInfo
	 */
    @ReadDataSource
    @Override
	public ResultData<PageInfo> listPaginated(UserRoleRelationInput userRoleRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo> resultData = new ResultData<>();
		int page = userRoleRelationInput.getPage() == null?1 : userRoleRelationInput.getPage();
		int rows = userRoleRelationInput.getRows() == null?10 : userRoleRelationInput.getRows();
		PageHelper.startPage(page, rows);
		//Input to po 转换
        InputToPo<UserRoleRelationInput,UserRoleRelationPo> inputToPo = new InputToPo<>();
		UserRoleRelationPo userRoleRelationPo = inputToPo.toPo(userRoleRelationInput,UserRoleRelationPo.class);
        PageInfo pageInfo = null;
		List<UserRoleRelationPo> list = userRoleRelationDao.listPaginated(userRoleRelationPo);
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
	 * @param userRoleRelationInput Input对象
	 * @return ResultData<PageInfo<UserRoleRelationOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<PageInfo<UserRoleRelationOutput>> listPaginatedManual(UserRoleRelationInput  userRoleRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo<UserRoleRelationOutput>> resultData = new ResultData<>();
		int page = userRoleRelationInput.getPage() == null?1: userRoleRelationInput.getPage();
		int rows = userRoleRelationInput.getRows() == null?10: userRoleRelationInput.getRows();
		//Input to po 转换
        InputToPo<UserRoleRelationInput,UserRoleRelationPo> inputToPo = new InputToPo<>();
		UserRoleRelationPo userRoleRelationPo = inputToPo.toPo(userRoleRelationInput,UserRoleRelationPo.class);
		//计算手动分页参数
		int start = (page - 1) * rows;
		userRoleRelationPo.setStart(start);
		userRoleRelationPo.setOffset(rows);
        PageInfo<UserRoleRelationOutput> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(page);
		pageInfo.setPageSize(rows);
		Long countRecords = userRoleRelationDao.countPaginatedManual(userRoleRelationPo);
		List<UserRoleRelationPo> list = userRoleRelationDao.listPaginatedManual(userRoleRelationPo);
		//对象转换工具
		PoToOutput<UserRoleRelationPo,UserRoleRelationOutput> poToOutput = new PoToOutput<>();
		List<UserRoleRelationOutput> listOutput = poToOutput.toListOutput(list,UserRoleRelationOutput.class);
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
    * @param userRoleRelationInput input对象
    * @return ResultData<List<UserRoleRelationOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<UserRoleRelationOutput>> listCriteriaInnerRole(UserRoleRelationInput userRoleRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<UserRoleRelationOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<UserRoleRelationInput,UserRoleRelationPo> inputToPo = new InputToPo<>();
		UserRoleRelationPo userRoleRelationPo = inputToPo.toPo(userRoleRelationInput,UserRoleRelationPo.class);
		List<UserRoleRelationOutput> listOutput = null;
		List<UserRoleRelationPo> list = userRoleRelationDao.listCriteriaInnerRole(userRoleRelationPo);
		if (list != null){
			//对象转换工具
			PoToOutput<UserRoleRelationPo,UserRoleRelationOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,UserRoleRelationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }

    /**
    * 查询所有数据
    * @return ResultData<List<UserRoleRelationOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<UserRoleRelationOutput>> listAllInnerRole() throws BaseException{
		//返回业务结果对象
		ResultData<List<UserRoleRelationOutput>> resultData = new ResultData<>();
		List<UserRoleRelationOutput> listOutput = null;
		List<UserRoleRelationPo> list = userRoleRelationDao.listAllInnerRole();
		if (list != null){
			//对象转换工具
			PoToOutput<UserRoleRelationPo,UserRoleRelationOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,UserRoleRelationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }


    /**
    * 动态条件，获取单条数据
    * @param userRoleRelationInput input对象
    * @return ResultData<UserRoleRelationOutput>
    */
    @ReadDataSource
    @Override
    public ResultData<UserRoleRelationOutput> getOneInnerRole(UserRoleRelationInput userRoleRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<UserRoleRelationOutput> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<UserRoleRelationInput,UserRoleRelationPo> inputToPo = new InputToPo<>();
		UserRoleRelationPo userRoleRelationPo = inputToPo.toPo(userRoleRelationInput,UserRoleRelationPo.class);
		UserRoleRelationOutput userRoleRelationResultOutput	= null;
		UserRoleRelationPo userRoleRelationResultPo = userRoleRelationDao.getOneCriteriaInnerRole(userRoleRelationPo);
		if (userRoleRelationResultPo != null){
			//对象转换工具
			PoToOutput<UserRoleRelationPo,UserRoleRelationOutput> poToOutput = new PoToOutput<>();
			userRoleRelationResultOutput = poToOutput.jsonOutput(userRoleRelationResultPo,UserRoleRelationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(userRoleRelationResultOutput);
		return resultData;
    }

    /**
    * 动态条件，自动分页查询列表数据
    * @param  userRoleRelationInput Input对象
    * @return PageInfo
    */
    @ReadDataSource
    @Override
    public ResultData<PageInfo> listPaginatedInnerRole(UserRoleRelationInput userRoleRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo> resultData = new ResultData<>();
		int page = userRoleRelationInput.getPage() == null?1 : userRoleRelationInput.getPage();
		int rows = userRoleRelationInput.getRows() == null?10 : userRoleRelationInput.getRows();
		PageHelper.startPage(page, rows);
		//Input to po 转换
		InputToPo<UserRoleRelationInput,UserRoleRelationPo> inputToPo = new InputToPo<>();
		UserRoleRelationPo userRoleRelationPo = inputToPo.toPo(userRoleRelationInput,UserRoleRelationPo.class);
		PageInfo pageInfo = null;
		List<UserRoleRelationPo> list = userRoleRelationDao.listPaginatedInnerRole(userRoleRelationPo);
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
    * @param userRoleRelationInput input对象
    * @return ResultData<List<UserRoleRelationOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<UserRoleRelationOutput>> listCriteriaInnerUser(UserRoleRelationInput userRoleRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<UserRoleRelationOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<UserRoleRelationInput,UserRoleRelationPo> inputToPo = new InputToPo<>();
		UserRoleRelationPo userRoleRelationPo = inputToPo.toPo(userRoleRelationInput,UserRoleRelationPo.class);
		List<UserRoleRelationOutput> listOutput = null;
		List<UserRoleRelationPo> list = userRoleRelationDao.listCriteriaInnerUser(userRoleRelationPo);
		if (list != null){
			//对象转换工具
			PoToOutput<UserRoleRelationPo,UserRoleRelationOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,UserRoleRelationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }

    /**
    * 查询所有数据
    * @return ResultData<List<UserRoleRelationOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<UserRoleRelationOutput>> listAllInnerUser() throws BaseException{
		//返回业务结果对象
		ResultData<List<UserRoleRelationOutput>> resultData = new ResultData<>();
		List<UserRoleRelationOutput> listOutput = null;
		List<UserRoleRelationPo> list = userRoleRelationDao.listAllInnerUser();
		if (list != null){
			//对象转换工具
			PoToOutput<UserRoleRelationPo,UserRoleRelationOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,UserRoleRelationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }


    /**
    * 动态条件，获取单条数据
    * @param userRoleRelationInput input对象
    * @return ResultData<UserRoleRelationOutput>
    */
    @ReadDataSource
    @Override
    public ResultData<UserRoleRelationOutput> getOneInnerUser(UserRoleRelationInput userRoleRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<UserRoleRelationOutput> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<UserRoleRelationInput,UserRoleRelationPo> inputToPo = new InputToPo<>();
		UserRoleRelationPo userRoleRelationPo = inputToPo.toPo(userRoleRelationInput,UserRoleRelationPo.class);
		UserRoleRelationOutput userRoleRelationResultOutput	= null;
		UserRoleRelationPo userRoleRelationResultPo = userRoleRelationDao.getOneCriteriaInnerUser(userRoleRelationPo);
		if (userRoleRelationResultPo != null){
			//对象转换工具
			PoToOutput<UserRoleRelationPo,UserRoleRelationOutput> poToOutput = new PoToOutput<>();
			userRoleRelationResultOutput = poToOutput.jsonOutput(userRoleRelationResultPo,UserRoleRelationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(userRoleRelationResultOutput);
		return resultData;
    }

    /**
    * 动态条件，自动分页查询列表数据
    * @param  userRoleRelationInput Input对象
    * @return PageInfo
    */
    @ReadDataSource
    @Override
    public ResultData<PageInfo> listPaginatedInnerUser(UserRoleRelationInput userRoleRelationInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo> resultData = new ResultData<>();
		int page = userRoleRelationInput.getPage() == null?1 : userRoleRelationInput.getPage();
		int rows = userRoleRelationInput.getRows() == null?10 : userRoleRelationInput.getRows();
		PageHelper.startPage(page, rows);
		//Input to po 转换
		InputToPo<UserRoleRelationInput,UserRoleRelationPo> inputToPo = new InputToPo<>();
		UserRoleRelationPo userRoleRelationPo = inputToPo.toPo(userRoleRelationInput,UserRoleRelationPo.class);
		PageInfo pageInfo = null;
		List<UserRoleRelationPo> list = userRoleRelationDao.listPaginatedInnerUser(userRoleRelationPo);
		if (list != null){
    		pageInfo = new PageInfo<>(list);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
        resultData.setData(pageInfo == null?new PageInfo<>():pageInfo);
		return resultData;
    }

}
