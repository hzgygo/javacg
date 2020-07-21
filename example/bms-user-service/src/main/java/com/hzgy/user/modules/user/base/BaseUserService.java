package com.hzgy.user.modules.user.base;

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
import com.hzgy.user.modules.user.entity.UserInput;
import com.hzgy.user.modules.user.entity.UserOutput;
import com.hzgy.user.modules.user.entity.UserPo;
import com.hzgy.user.modules.user.IUserDao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public abstract class BaseUserService extends BaseService implements IBaseUserService<UserInput,UserOutput>{

    private static Logger logger = LoggerFactory.getLogger(BaseUserService.class);
	//业务返回码
	protected final Integer MC_1 = 100301;
	protected final Integer MC_2 = 100302;
	protected final Integer MC_3 = 100303;
	protected final Integer MC_4 = 100304;
	protected final Integer MC_5 = 100305;
	protected final Integer MC_6 = 100306;
	protected final Integer MC_7 = 100307;
	protected final Integer MC_8 = 100308;
	protected final Integer MC_9 = 100309;
	protected final Integer MC_10 = 100310;
	protected final Integer MC_11 = 100311;
	protected final Integer MC_12 = 100312;
	protected final Integer MC_13 = 100313;
	protected final Integer MC_14 = 100314;
	protected final Integer MC_15 = 100315;
	protected final Integer MC_16 = 100316;
	protected final Integer MC_17 = 100317;
	protected final Integer MC_18 = 100318;
	protected final Integer MC_19 = 100319;
	protected final Integer MC_20 = 100320;



	@Resource
	protected RedisService<UserPo> redisService;
	
	@Resource
    protected IUserDao userDao;

	@PostConstruct
	private void addService(){
		if (autoDataProcessor != null){
			autoDataProcessor.addService(this);
		}
	}

	/**
	 * 保存
	 * @param userInput Input参数对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
    @Override
	public ResultData<Integer> create(UserInput userInput) throws BaseException{
        //返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
    	InputToPo<UserInput,UserPo> inputToPo = new InputToPo<>();
		UserPo userPo = inputToPo.toPo(userInput,UserPo.class);
		//通过SnowflakeId生成数据库主键id
		long id = SnowflakeIdWorker.nextId();
		userPo.setId(id);
		int rval = userDao.save(userPo);
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
	 * @param userInput input对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
	@Override
	public ResultData<Integer> modifyById(UserInput userInput) throws BaseException{
        //返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<UserInput,UserPo> inputToPo = new InputToPo<>();
		UserPo userPo = inputToPo.toPo(userInput,UserPo.class);
		int rval = userDao.updateById(userPo);
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
	 * @param userInput input对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
    @Override
	public ResultData<Integer> deleteCriteria(UserInput userInput) throws BaseException{
		//返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
    	InputToPo<UserInput,UserPo> inputToPo = new InputToPo<>();
		UserPo userPo = inputToPo.toPo(userInput,UserPo.class);
		int rval = userDao.removeCriteria(userPo);
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
		int rval = userDao.removeById(id);
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
		int rval = userDao.removeAll();
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
	 * @param userInput input对象
	 * @return ResultData<List<UserOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<UserOutput>> listCriteria(UserInput userInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<UserOutput>> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<UserInput,UserPo> inputToPo = new InputToPo<>();
		UserPo userPo = inputToPo.toPo(userInput,UserPo.class);
		List<UserOutput> listOutput = null;
		List<UserPo> list = userDao.listCriteria(userPo);
		if (list != null){
			//对象转换工具
			PoToOutput<UserPo,UserOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,UserOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}
	/**
	 * 动态条件查询主键Id数据
	 * @param userInput Input对象
	 * @return ResultData<List<Long>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<Long>> listIdsCriteria(UserInput userInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<Long>> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<UserInput,UserPo> inputToPo = new InputToPo<>();
		UserPo userPo = inputToPo.toPo(userInput,UserPo.class);
        List<Long> list = userDao.listIdsCriteria(userPo);
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(list);
		return resultData;
	}

	/**
	 * 根据id串，in的方式查询数据
	 * @param userInput Input对象
	 * @return ResultData<List<UserOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<UserOutput>> listInIds(UserInput userInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<UserOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<UserInput,UserPo> inputToPo = new InputToPo<>();
		UserPo userPo = inputToPo.toPo(userInput,UserPo.class);
		List<UserOutput> listOutput = null;
		List<UserPo> list = userDao.listByInIds(userPo);
		if (list != null){
			//对象转换工具
			PoToOutput<UserPo,UserOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,UserOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}
	/**
	 * 查询所有数据
	 * @return ResultData<List<UserOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<UserOutput>> listAll() throws BaseException{
		//返回业务结果对象
		ResultData<List<UserOutput>> resultData = new ResultData<>();
        List<UserOutput> listOutput = null;
		List<UserPo> list = userDao.listAll();
		if (list != null){
			//对象转换工具
			PoToOutput<UserPo,UserOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,UserOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}

	/**
	 * 根据id，获取单条数据
	 * @param id 主键id
	 * @return ResultData<UserOutput>
	 */
    @ReadDataSource
	@Override
	public ResultData<UserOutput> getOneById(Long id) throws BaseException{
        //返回业务结果对象
        ResultData<UserOutput> resultData = new ResultData<>();
		UserOutput userOutput = null;
		UserPo userPo = userDao.getOneById(id);
		if (userPo != null){
			//对象转换工具
			PoToOutput<UserPo,UserOutput> poToOutput = new PoToOutput<>();
			userOutput = poToOutput.toOutput(userPo,UserOutput.class);
		}
        resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
        resultData.setData(userOutput);
        return resultData;
	}

	/**
	 * 动态条件，获取单条数据
	 * @param userInput input对象
	 * @return ResultData<UserOutput>
	 */
    @ReadDataSource
    @Override
	public ResultData<UserOutput> getOne(UserInput userInput) throws BaseException{
		//返回业务结果对象
		ResultData<UserOutput> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<UserInput,UserPo> inputToPo = new InputToPo<>();
		UserPo userPo = inputToPo.toPo(userInput,UserPo.class);
		UserOutput userResultOutput	= null;
		UserPo userResultPo = userDao.getOneCriteria(userPo);
		if (userResultPo != null){
			//对象转换工具
			PoToOutput<UserPo,UserOutput> poToOutput = new PoToOutput<>();
			userResultOutput = poToOutput.toOutput(userResultPo,UserOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(userResultOutput);
		return resultData;
	}

	/**
	 * 动态条件，查询记录总数
	 * @param userInput Input对象
	 * @return ResultData<Long>
	 */
    @ReadDataSource
    @Override
	public ResultData<Long> count(UserInput userInput) throws BaseException{
		//返回业务结果对象
		ResultData<Long> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<UserInput,UserPo> inputToPo = new InputToPo<>();
		UserPo userPo = inputToPo.toPo(userInput,UserPo.class);
		Long count = userDao.count(userPo);
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(count == null?0:count);
		return resultData;
	}

	/**
	 * 动态条件，自动分页查询列表数据
	 * @param  userInput Input对象
	 * @return PageInfo
	 */
    @ReadDataSource
    @Override
	public ResultData<PageInfo> listPaginated(UserInput userInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo> resultData = new ResultData<>();
		int page = userInput.getPage() == null?1 : userInput.getPage();
		int rows = userInput.getRows() == null?10 : userInput.getRows();
		PageHelper.startPage(page, rows);
		//Input to po 转换
        InputToPo<UserInput,UserPo> inputToPo = new InputToPo<>();
		UserPo userPo = inputToPo.toPo(userInput,UserPo.class);
        PageInfo pageInfo = null;
		List<UserPo> list = userDao.listPaginated(userPo);
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
	 * @param userInput Input对象
	 * @return ResultData<PageInfo<UserOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<PageInfo<UserOutput>> listPaginatedManual(UserInput  userInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo<UserOutput>> resultData = new ResultData<>();
		int page = userInput.getPage() == null?1: userInput.getPage();
		int rows = userInput.getRows() == null?10: userInput.getRows();
		//Input to po 转换
        InputToPo<UserInput,UserPo> inputToPo = new InputToPo<>();
		UserPo userPo = inputToPo.toPo(userInput,UserPo.class);
		//计算手动分页参数
		int start = (page - 1) * rows;
		userPo.setStart(start);
		userPo.setOffset(rows);
        PageInfo<UserOutput> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(page);
		pageInfo.setPageSize(rows);
		Long countRecords = userDao.countPaginatedManual(userPo);
		List<UserPo> list = userDao.listPaginatedManual(userPo);
		//对象转换工具
		PoToOutput<UserPo,UserOutput> poToOutput = new PoToOutput<>();
		List<UserOutput> listOutput = poToOutput.toListOutput(list,UserOutput.class);
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
    * @param userInput input对象
    * @return ResultData<List<UserOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<UserOutput>> listCriteriaLeftUserRoleRelation(UserInput userInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<UserOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<UserInput,UserPo> inputToPo = new InputToPo<>();
		UserPo userPo = inputToPo.toPo(userInput,UserPo.class);
		List<UserOutput> listOutput = null;
		List<UserPo> list = userDao.listCriteriaLeftUserRoleRelation(userPo);
		if (list != null){
			//对象转换工具
			PoToOutput<UserPo,UserOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,UserOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }

    /**
    * 根据id串，in的方式查询数据
    * @param userInput Input对象
    * @return ResultData<List<UserOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<UserOutput>> listInIdsLeftUserRoleRelation(UserInput userInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<UserOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<UserInput,UserPo> inputToPo = new InputToPo<>();
		UserPo userPo = inputToPo.toPo(userInput,UserPo.class);
		List<UserOutput> listOutput = null;
		List<UserPo> list = userDao.listInIdsLeftUserRoleRelation(userPo);
		if (list != null){
			//对象转换工具
			PoToOutput<UserPo,UserOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,UserOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }
    /**
    * 查询所有数据
    * @return ResultData<List<UserOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<UserOutput>> listAllLeftUserRoleRelation() throws BaseException{
		//返回业务结果对象
		ResultData<List<UserOutput>> resultData = new ResultData<>();
		List<UserOutput> listOutput = null;
		List<UserPo> list = userDao.listAllLeftUserRoleRelation();
		if (list != null){
			//对象转换工具
			PoToOutput<UserPo,UserOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,UserOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }

	/**
	* 根据id，获取单条数据
	* @param id 主键id
	* @return ResultData<UserOutput>
	*/
	@ReadDataSource
	@Override
	public ResultData<UserOutput> getOneByIdLeftUserRoleRelation(Long id) throws BaseException{
		//返回业务结果对象
		ResultData<UserOutput> resultData = new ResultData<>();
		UserOutput userOutput = null;
		UserPo userPo = userDao.getOneByIdLeftUserRoleRelation(id);
		if (userPo != null){
			//对象转换工具
			PoToOutput<UserPo,UserOutput> poToOutput = new PoToOutput<>();
			userOutput = poToOutput.jsonOutput(userPo,UserOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(userOutput);
		return resultData;
	}

    /**
    * 动态条件，获取单条数据
    * @param userInput input对象
    * @return ResultData<UserOutput>
    */
    @ReadDataSource
    @Override
    public ResultData<UserOutput> getOneLeftUserRoleRelation(UserInput userInput) throws BaseException{
		//返回业务结果对象
		ResultData<UserOutput> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<UserInput,UserPo> inputToPo = new InputToPo<>();
		UserPo userPo = inputToPo.toPo(userInput,UserPo.class);
		UserOutput userResultOutput	= null;
		UserPo userResultPo = userDao.getOneCriteriaLeftUserRoleRelation(userPo);
		if (userResultPo != null){
			//对象转换工具
			PoToOutput<UserPo,UserOutput> poToOutput = new PoToOutput<>();
			userResultOutput = poToOutput.jsonOutput(userResultPo,UserOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(userResultOutput);
		return resultData;
    }

    /**
    * 动态条件，自动分页查询列表数据
    * @param  userInput Input对象
    * @return PageInfo
    */
    @ReadDataSource
    @Override
    public ResultData<PageInfo> listPaginatedLeftUserRoleRelation(UserInput userInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo> resultData = new ResultData<>();
		int page = userInput.getPage() == null?1 : userInput.getPage();
		int rows = userInput.getRows() == null?10 : userInput.getRows();
		PageHelper.startPage(page, rows);
		//Input to po 转换
		InputToPo<UserInput,UserPo> inputToPo = new InputToPo<>();
		UserPo userPo = inputToPo.toPo(userInput,UserPo.class);
		PageInfo pageInfo = null;
		List<UserPo> list = userDao.listPaginatedLeftUserRoleRelation(userPo);
		if (list != null){
    		pageInfo = new PageInfo<>(list);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(pageInfo == null? new PageInfo<>():pageInfo);
		return resultData;
    }
    /**
    * 动态条件关联查询列表数据
    * @param userInput input对象
    * @return ResultData<List<UserOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<UserOutput>> listCriteriaInnerOrganization(UserInput userInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<UserOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<UserInput,UserPo> inputToPo = new InputToPo<>();
		UserPo userPo = inputToPo.toPo(userInput,UserPo.class);
		List<UserOutput> listOutput = null;
		List<UserPo> list = userDao.listCriteriaInnerOrganization(userPo);
		if (list != null){
			//对象转换工具
			PoToOutput<UserPo,UserOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,UserOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }

    /**
    * 根据id串，in的方式查询数据
    * @param userInput Input对象
    * @return ResultData<List<UserOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<UserOutput>> listInIdsInnerOrganization(UserInput userInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<UserOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<UserInput,UserPo> inputToPo = new InputToPo<>();
		UserPo userPo = inputToPo.toPo(userInput,UserPo.class);
		List<UserOutput> listOutput = null;
		List<UserPo> list = userDao.listInIdsInnerOrganization(userPo);
		if (list != null){
			//对象转换工具
			PoToOutput<UserPo,UserOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,UserOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
   		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }
    /**
    * 查询所有数据
    * @return ResultData<List<UserOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<UserOutput>> listAllInnerOrganization() throws BaseException{
		//返回业务结果对象
		ResultData<List<UserOutput>> resultData = new ResultData<>();
		List<UserOutput> listOutput = null;
		List<UserPo> list = userDao.listAllInnerOrganization();
		if (list != null){
			//对象转换工具
			PoToOutput<UserPo,UserOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,UserOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }

	/**
	* 根据id，获取单条数据
	* @param id 主键id
	* @return ResultData<UserOutput>
	*/
	@ReadDataSource
	@Override
	public ResultData<UserOutput> getOneByIdInnerOrganization(Long id) throws BaseException{
		//返回业务结果对象
		ResultData<UserOutput> resultData = new ResultData<>();
		UserOutput userOutput = null;
		UserPo userPo = userDao.getOneByIdInnerOrganization(id);
		if (userPo != null){
			//对象转换工具
			PoToOutput<UserPo,UserOutput> poToOutput = new PoToOutput<>();
			userOutput = poToOutput.jsonOutput(userPo,UserOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(userOutput);
		return resultData;
	}

    /**
    * 动态条件，获取单条数据
    * @param userInput input对象
    * @return ResultData<UserOutput>
    */
    @ReadDataSource
    @Override
    public ResultData<UserOutput> getOneInnerOrganization(UserInput userInput) throws BaseException{
		//返回业务结果对象
		ResultData<UserOutput> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<UserInput,UserPo> inputToPo = new InputToPo<>();
		UserPo userPo = inputToPo.toPo(userInput,UserPo.class);
		UserOutput userResultOutput	= null;
		UserPo userResultPo = userDao.getOneCriteriaInnerOrganization(userPo);
		if (userResultPo != null){
			//对象转换工具
			PoToOutput<UserPo,UserOutput> poToOutput = new PoToOutput<>();
			userResultOutput = poToOutput.jsonOutput(userResultPo,UserOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(userResultOutput);
		return resultData;
    }

    /**
    * 动态条件，自动分页查询列表数据
    * @param  userInput Input对象
    * @return PageInfo
    */
    @ReadDataSource
    @Override
    public ResultData<PageInfo> listPaginatedInnerOrganization(UserInput userInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo> resultData = new ResultData<>();
		int page = userInput.getPage() == null?1 : userInput.getPage();
		int rows = userInput.getRows() == null?10 : userInput.getRows();
		PageHelper.startPage(page, rows);
		//Input to po 转换
		InputToPo<UserInput,UserPo> inputToPo = new InputToPo<>();
		UserPo userPo = inputToPo.toPo(userInput,UserPo.class);
		PageInfo pageInfo = null;
		List<UserPo> list = userDao.listPaginatedInnerOrganization(userPo);
		if (list != null){
    		pageInfo = new PageInfo<>(list);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
        resultData.setData(pageInfo == null?new PageInfo<>():pageInfo);
		return resultData;
    }

}
