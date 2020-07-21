package com.hzgy.user.modules.log.base;

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
import com.hzgy.user.modules.log.entity.LogInput;
import com.hzgy.user.modules.log.entity.LogOutput;
import com.hzgy.user.modules.log.entity.LogPo;
import com.hzgy.user.modules.log.ILogDao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public abstract class BaseLogService extends BaseService implements IBaseLogService<LogInput,LogOutput>{

    private static Logger logger = LoggerFactory.getLogger(BaseLogService.class);
	//业务返回码
	protected final Integer MC_1 = 100701;
	protected final Integer MC_2 = 100702;
	protected final Integer MC_3 = 100703;
	protected final Integer MC_4 = 100704;
	protected final Integer MC_5 = 100705;
	protected final Integer MC_6 = 100706;
	protected final Integer MC_7 = 100707;
	protected final Integer MC_8 = 100708;
	protected final Integer MC_9 = 100709;
	protected final Integer MC_10 = 100710;
	protected final Integer MC_11 = 100711;
	protected final Integer MC_12 = 100712;
	protected final Integer MC_13 = 100713;
	protected final Integer MC_14 = 100714;
	protected final Integer MC_15 = 100715;
	protected final Integer MC_16 = 100716;
	protected final Integer MC_17 = 100717;
	protected final Integer MC_18 = 100718;
	protected final Integer MC_19 = 100719;
	protected final Integer MC_20 = 100720;



	@Resource
	protected RedisService<LogPo> redisService;
	
	@Resource
    protected ILogDao logDao;

	@PostConstruct
	private void addService(){
		if (autoDataProcessor != null){
			autoDataProcessor.addService(this);
		}
	}

	/**
	 * 保存
	 * @param logInput Input参数对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
    @Override
	public ResultData<Integer> create(LogInput logInput) throws BaseException{
        //返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
    	InputToPo<LogInput,LogPo> inputToPo = new InputToPo<>();
		LogPo logPo = inputToPo.toPo(logInput,LogPo.class);
		//通过SnowflakeId生成数据库主键id
		long id = SnowflakeIdWorker.nextId();
		logPo.setId(id);
		int rval = logDao.save(logPo);
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
	 * @param logInput input对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
	@Override
	public ResultData<Integer> modifyById(LogInput logInput) throws BaseException{
        //返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<LogInput,LogPo> inputToPo = new InputToPo<>();
		LogPo logPo = inputToPo.toPo(logInput,LogPo.class);
		int rval = logDao.updateById(logPo);
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
	 * @param logInput input对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
    @Override
	public ResultData<Integer> deleteCriteria(LogInput logInput) throws BaseException{
		//返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
    	InputToPo<LogInput,LogPo> inputToPo = new InputToPo<>();
		LogPo logPo = inputToPo.toPo(logInput,LogPo.class);
		int rval = logDao.removeCriteria(logPo);
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
		int rval = logDao.removeById(id);
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
		int rval = logDao.removeAll();
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
	 * @param logInput input对象
	 * @return ResultData<List<LogOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<LogOutput>> listCriteria(LogInput logInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<LogOutput>> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<LogInput,LogPo> inputToPo = new InputToPo<>();
		LogPo logPo = inputToPo.toPo(logInput,LogPo.class);
		List<LogOutput> listOutput = null;
		List<LogPo> list = logDao.listCriteria(logPo);
		if (list != null){
			//对象转换工具
			PoToOutput<LogPo,LogOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,LogOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}
	/**
	 * 动态条件查询主键Id数据
	 * @param logInput Input对象
	 * @return ResultData<List<Long>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<Long>> listIdsCriteria(LogInput logInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<Long>> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<LogInput,LogPo> inputToPo = new InputToPo<>();
		LogPo logPo = inputToPo.toPo(logInput,LogPo.class);
        List<Long> list = logDao.listIdsCriteria(logPo);
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(list);
		return resultData;
	}

	/**
	 * 根据id串，in的方式查询数据
	 * @param logInput Input对象
	 * @return ResultData<List<LogOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<LogOutput>> listInIds(LogInput logInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<LogOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<LogInput,LogPo> inputToPo = new InputToPo<>();
		LogPo logPo = inputToPo.toPo(logInput,LogPo.class);
		List<LogOutput> listOutput = null;
		List<LogPo> list = logDao.listByInIds(logPo);
		if (list != null){
			//对象转换工具
			PoToOutput<LogPo,LogOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,LogOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}
	/**
	 * 查询所有数据
	 * @return ResultData<List<LogOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<LogOutput>> listAll() throws BaseException{
		//返回业务结果对象
		ResultData<List<LogOutput>> resultData = new ResultData<>();
        List<LogOutput> listOutput = null;
		List<LogPo> list = logDao.listAll();
		if (list != null){
			//对象转换工具
			PoToOutput<LogPo,LogOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,LogOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}

	/**
	 * 根据id，获取单条数据
	 * @param id 主键id
	 * @return ResultData<LogOutput>
	 */
    @ReadDataSource
	@Override
	public ResultData<LogOutput> getOneById(Long id) throws BaseException{
        //返回业务结果对象
        ResultData<LogOutput> resultData = new ResultData<>();
		LogOutput logOutput = null;
		LogPo logPo = logDao.getOneById(id);
		if (logPo != null){
			//对象转换工具
			PoToOutput<LogPo,LogOutput> poToOutput = new PoToOutput<>();
			logOutput = poToOutput.toOutput(logPo,LogOutput.class);
		}
        resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
        resultData.setData(logOutput);
        return resultData;
	}

	/**
	 * 动态条件，获取单条数据
	 * @param logInput input对象
	 * @return ResultData<LogOutput>
	 */
    @ReadDataSource
    @Override
	public ResultData<LogOutput> getOne(LogInput logInput) throws BaseException{
		//返回业务结果对象
		ResultData<LogOutput> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<LogInput,LogPo> inputToPo = new InputToPo<>();
		LogPo logPo = inputToPo.toPo(logInput,LogPo.class);
		LogOutput logResultOutput	= null;
		LogPo logResultPo = logDao.getOneCriteria(logPo);
		if (logResultPo != null){
			//对象转换工具
			PoToOutput<LogPo,LogOutput> poToOutput = new PoToOutput<>();
			logResultOutput = poToOutput.toOutput(logResultPo,LogOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(logResultOutput);
		return resultData;
	}

	/**
	 * 动态条件，查询记录总数
	 * @param logInput Input对象
	 * @return ResultData<Long>
	 */
    @ReadDataSource
    @Override
	public ResultData<Long> count(LogInput logInput) throws BaseException{
		//返回业务结果对象
		ResultData<Long> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<LogInput,LogPo> inputToPo = new InputToPo<>();
		LogPo logPo = inputToPo.toPo(logInput,LogPo.class);
		Long count = logDao.count(logPo);
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(count == null?0:count);
		return resultData;
	}

	/**
	 * 动态条件，自动分页查询列表数据
	 * @param  logInput Input对象
	 * @return PageInfo
	 */
    @ReadDataSource
    @Override
	public ResultData<PageInfo> listPaginated(LogInput logInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo> resultData = new ResultData<>();
		int page = logInput.getPage() == null?1 : logInput.getPage();
		int rows = logInput.getRows() == null?10 : logInput.getRows();
		PageHelper.startPage(page, rows);
		//Input to po 转换
        InputToPo<LogInput,LogPo> inputToPo = new InputToPo<>();
		LogPo logPo = inputToPo.toPo(logInput,LogPo.class);
        PageInfo pageInfo = null;
		List<LogPo> list = logDao.listPaginated(logPo);
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
	 * @param logInput Input对象
	 * @return ResultData<PageInfo<LogOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<PageInfo<LogOutput>> listPaginatedManual(LogInput  logInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo<LogOutput>> resultData = new ResultData<>();
		int page = logInput.getPage() == null?1: logInput.getPage();
		int rows = logInput.getRows() == null?10: logInput.getRows();
		//Input to po 转换
        InputToPo<LogInput,LogPo> inputToPo = new InputToPo<>();
		LogPo logPo = inputToPo.toPo(logInput,LogPo.class);
		//计算手动分页参数
		int start = (page - 1) * rows;
		logPo.setStart(start);
		logPo.setOffset(rows);
        PageInfo<LogOutput> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(page);
		pageInfo.setPageSize(rows);
		Long countRecords = logDao.countPaginatedManual(logPo);
		List<LogPo> list = logDao.listPaginatedManual(logPo);
		//对象转换工具
		PoToOutput<LogPo,LogOutput> poToOutput = new PoToOutput<>();
		List<LogOutput> listOutput = poToOutput.toListOutput(list,LogOutput.class);
		int pageTotal = (int) Math.ceil(countRecords / (double) rows);
		pageInfo.setPages(pageTotal);
		pageInfo.setList(listOutput);
		pageInfo.setTotal(countRecords);
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(pageInfo);
		return resultData;
	}


}
