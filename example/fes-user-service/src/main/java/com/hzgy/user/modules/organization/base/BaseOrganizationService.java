package com.hzgy.user.modules.organization.base;

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
import com.hzgy.user.modules.organization.entity.OrganizationInput;
import com.hzgy.user.modules.organization.entity.OrganizationOutput;
import com.hzgy.user.modules.organization.entity.OrganizationPo;
import com.hzgy.user.modules.organization.IOrganizationDao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public abstract class BaseOrganizationService extends BaseService implements IBaseOrganizationService<OrganizationInput,OrganizationOutput>{

    private static Logger logger = LoggerFactory.getLogger(BaseOrganizationService.class);
	//业务返回码
	protected final Integer MC_1 = 100601;
	protected final Integer MC_2 = 100602;
	protected final Integer MC_3 = 100603;
	protected final Integer MC_4 = 100604;
	protected final Integer MC_5 = 100605;
	protected final Integer MC_6 = 100606;
	protected final Integer MC_7 = 100607;
	protected final Integer MC_8 = 100608;
	protected final Integer MC_9 = 100609;
	protected final Integer MC_10 = 100610;
	protected final Integer MC_11 = 100611;
	protected final Integer MC_12 = 100612;
	protected final Integer MC_13 = 100613;
	protected final Integer MC_14 = 100614;
	protected final Integer MC_15 = 100615;
	protected final Integer MC_16 = 100616;
	protected final Integer MC_17 = 100617;
	protected final Integer MC_18 = 100618;
	protected final Integer MC_19 = 100619;
	protected final Integer MC_20 = 100620;



	@Resource
	protected RedisService<OrganizationPo> redisService;
	
	@Resource
    protected IOrganizationDao organizationDao;

	@PostConstruct
	private void addService(){
		if (autoDataProcessor != null){
			autoDataProcessor.addService(this);
		}
	}

	/**
	 * 保存
	 * @param organizationInput Input参数对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
    @Override
	public ResultData<Integer> create(OrganizationInput organizationInput) throws BaseException{
        //返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
    	InputToPo<OrganizationInput,OrganizationPo> inputToPo = new InputToPo<>();
		OrganizationPo organizationPo = inputToPo.toPo(organizationInput,OrganizationPo.class);
		//通过SnowflakeId生成数据库主键id
		long id = SnowflakeIdWorker.nextId();
		organizationPo.setId(id);
		int rval = organizationDao.save(organizationPo);
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
	 * @param organizationInput input对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
	@Override
	public ResultData<Integer> modifyById(OrganizationInput organizationInput) throws BaseException{
        //返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<OrganizationInput,OrganizationPo> inputToPo = new InputToPo<>();
		OrganizationPo organizationPo = inputToPo.toPo(organizationInput,OrganizationPo.class);
		int rval = organizationDao.updateById(organizationPo);
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
	 * @param organizationInput input对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
    @Override
	public ResultData<Integer> deleteCriteria(OrganizationInput organizationInput) throws BaseException{
		//返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
    	InputToPo<OrganizationInput,OrganizationPo> inputToPo = new InputToPo<>();
		OrganizationPo organizationPo = inputToPo.toPo(organizationInput,OrganizationPo.class);
		int rval = organizationDao.removeCriteria(organizationPo);
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
		int rval = organizationDao.removeById(id);
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
		int rval = organizationDao.removeAll();
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
	 * @param organizationInput input对象
	 * @return ResultData<List<OrganizationOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<OrganizationOutput>> listCriteria(OrganizationInput organizationInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<OrganizationOutput>> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<OrganizationInput,OrganizationPo> inputToPo = new InputToPo<>();
		OrganizationPo organizationPo = inputToPo.toPo(organizationInput,OrganizationPo.class);
		List<OrganizationOutput> listOutput = null;
		List<OrganizationPo> list = organizationDao.listCriteria(organizationPo);
		if (list != null){
			//对象转换工具
			PoToOutput<OrganizationPo,OrganizationOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,OrganizationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}
	/**
	 * 动态条件查询主键Id数据
	 * @param organizationInput Input对象
	 * @return ResultData<List<Long>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<Long>> listIdsCriteria(OrganizationInput organizationInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<Long>> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<OrganizationInput,OrganizationPo> inputToPo = new InputToPo<>();
		OrganizationPo organizationPo = inputToPo.toPo(organizationInput,OrganizationPo.class);
        List<Long> list = organizationDao.listIdsCriteria(organizationPo);
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(list);
		return resultData;
	}

	/**
	 * 根据id串，in的方式查询数据
	 * @param organizationInput Input对象
	 * @return ResultData<List<OrganizationOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<OrganizationOutput>> listInIds(OrganizationInput organizationInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<OrganizationOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<OrganizationInput,OrganizationPo> inputToPo = new InputToPo<>();
		OrganizationPo organizationPo = inputToPo.toPo(organizationInput,OrganizationPo.class);
		List<OrganizationOutput> listOutput = null;
		List<OrganizationPo> list = organizationDao.listByInIds(organizationPo);
		if (list != null){
			//对象转换工具
			PoToOutput<OrganizationPo,OrganizationOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,OrganizationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}
	/**
	 * 查询所有数据
	 * @return ResultData<List<OrganizationOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<OrganizationOutput>> listAll() throws BaseException{
		//返回业务结果对象
		ResultData<List<OrganizationOutput>> resultData = new ResultData<>();
        List<OrganizationOutput> listOutput = null;
		List<OrganizationPo> list = organizationDao.listAll();
		if (list != null){
			//对象转换工具
			PoToOutput<OrganizationPo,OrganizationOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,OrganizationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}

	/**
	 * 根据id，获取单条数据
	 * @param id 主键id
	 * @return ResultData<OrganizationOutput>
	 */
    @ReadDataSource
	@Override
	public ResultData<OrganizationOutput> getOneById(Long id) throws BaseException{
        //返回业务结果对象
        ResultData<OrganizationOutput> resultData = new ResultData<>();
		OrganizationOutput organizationOutput = null;
		OrganizationPo organizationPo = organizationDao.getOneById(id);
		if (organizationPo != null){
			//对象转换工具
			PoToOutput<OrganizationPo,OrganizationOutput> poToOutput = new PoToOutput<>();
			organizationOutput = poToOutput.toOutput(organizationPo,OrganizationOutput.class);
		}
        resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
        resultData.setData(organizationOutput);
        return resultData;
	}

	/**
	 * 动态条件，获取单条数据
	 * @param organizationInput input对象
	 * @return ResultData<OrganizationOutput>
	 */
    @ReadDataSource
    @Override
	public ResultData<OrganizationOutput> getOne(OrganizationInput organizationInput) throws BaseException{
		//返回业务结果对象
		ResultData<OrganizationOutput> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<OrganizationInput,OrganizationPo> inputToPo = new InputToPo<>();
		OrganizationPo organizationPo = inputToPo.toPo(organizationInput,OrganizationPo.class);
		OrganizationOutput organizationResultOutput	= null;
		OrganizationPo organizationResultPo = organizationDao.getOneCriteria(organizationPo);
		if (organizationResultPo != null){
			//对象转换工具
			PoToOutput<OrganizationPo,OrganizationOutput> poToOutput = new PoToOutput<>();
			organizationResultOutput = poToOutput.toOutput(organizationResultPo,OrganizationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(organizationResultOutput);
		return resultData;
	}

	/**
	 * 动态条件，查询记录总数
	 * @param organizationInput Input对象
	 * @return ResultData<Long>
	 */
    @ReadDataSource
    @Override
	public ResultData<Long> count(OrganizationInput organizationInput) throws BaseException{
		//返回业务结果对象
		ResultData<Long> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<OrganizationInput,OrganizationPo> inputToPo = new InputToPo<>();
		OrganizationPo organizationPo = inputToPo.toPo(organizationInput,OrganizationPo.class);
		Long count = organizationDao.count(organizationPo);
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(count == null?0:count);
		return resultData;
	}

	/**
	 * 动态条件，自动分页查询列表数据
	 * @param  organizationInput Input对象
	 * @return PageInfo
	 */
    @ReadDataSource
    @Override
	public ResultData<PageInfo> listPaginated(OrganizationInput organizationInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo> resultData = new ResultData<>();
		int page = organizationInput.getPage() == null?1 : organizationInput.getPage();
		int rows = organizationInput.getRows() == null?10 : organizationInput.getRows();
		PageHelper.startPage(page, rows);
		//Input to po 转换
        InputToPo<OrganizationInput,OrganizationPo> inputToPo = new InputToPo<>();
		OrganizationPo organizationPo = inputToPo.toPo(organizationInput,OrganizationPo.class);
        PageInfo pageInfo = null;
		List<OrganizationPo> list = organizationDao.listPaginated(organizationPo);
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
	 * @param organizationInput Input对象
	 * @return ResultData<PageInfo<OrganizationOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<PageInfo<OrganizationOutput>> listPaginatedManual(OrganizationInput  organizationInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo<OrganizationOutput>> resultData = new ResultData<>();
		int page = organizationInput.getPage() == null?1: organizationInput.getPage();
		int rows = organizationInput.getRows() == null?10: organizationInput.getRows();
		//Input to po 转换
        InputToPo<OrganizationInput,OrganizationPo> inputToPo = new InputToPo<>();
		OrganizationPo organizationPo = inputToPo.toPo(organizationInput,OrganizationPo.class);
		//计算手动分页参数
		int start = (page - 1) * rows;
		organizationPo.setStart(start);
		organizationPo.setOffset(rows);
        PageInfo<OrganizationOutput> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(page);
		pageInfo.setPageSize(rows);
		Long countRecords = organizationDao.countPaginatedManual(organizationPo);
		List<OrganizationPo> list = organizationDao.listPaginatedManual(organizationPo);
		//对象转换工具
		PoToOutput<OrganizationPo,OrganizationOutput> poToOutput = new PoToOutput<>();
		List<OrganizationOutput> listOutput = poToOutput.toListOutput(list,OrganizationOutput.class);
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
    * @param organizationInput input对象
    * @return ResultData<List<OrganizationOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<OrganizationOutput>> listCriteriaLeftUser(OrganizationInput organizationInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<OrganizationOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<OrganizationInput,OrganizationPo> inputToPo = new InputToPo<>();
		OrganizationPo organizationPo = inputToPo.toPo(organizationInput,OrganizationPo.class);
		List<OrganizationOutput> listOutput = null;
		List<OrganizationPo> list = organizationDao.listCriteriaLeftUser(organizationPo);
		if (list != null){
			//对象转换工具
			PoToOutput<OrganizationPo,OrganizationOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,OrganizationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }

    /**
    * 根据id串，in的方式查询数据
    * @param organizationInput Input对象
    * @return ResultData<List<OrganizationOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<OrganizationOutput>> listInIdsLeftUser(OrganizationInput organizationInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<OrganizationOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<OrganizationInput,OrganizationPo> inputToPo = new InputToPo<>();
		OrganizationPo organizationPo = inputToPo.toPo(organizationInput,OrganizationPo.class);
		List<OrganizationOutput> listOutput = null;
		List<OrganizationPo> list = organizationDao.listInIdsLeftUser(organizationPo);
		if (list != null){
			//对象转换工具
			PoToOutput<OrganizationPo,OrganizationOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,OrganizationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }
    /**
    * 查询所有数据
    * @return ResultData<List<OrganizationOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<OrganizationOutput>> listAllLeftUser() throws BaseException{
		//返回业务结果对象
		ResultData<List<OrganizationOutput>> resultData = new ResultData<>();
		List<OrganizationOutput> listOutput = null;
		List<OrganizationPo> list = organizationDao.listAllLeftUser();
		if (list != null){
			//对象转换工具
			PoToOutput<OrganizationPo,OrganizationOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,OrganizationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }

	/**
	* 根据id，获取单条数据
	* @param id 主键id
	* @return ResultData<OrganizationOutput>
	*/
	@ReadDataSource
	@Override
	public ResultData<OrganizationOutput> getOneByIdLeftUser(Long id) throws BaseException{
		//返回业务结果对象
		ResultData<OrganizationOutput> resultData = new ResultData<>();
		OrganizationOutput organizationOutput = null;
		OrganizationPo organizationPo = organizationDao.getOneByIdLeftUser(id);
		if (organizationPo != null){
			//对象转换工具
			PoToOutput<OrganizationPo,OrganizationOutput> poToOutput = new PoToOutput<>();
			organizationOutput = poToOutput.jsonOutput(organizationPo,OrganizationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(organizationOutput);
		return resultData;
	}

    /**
    * 动态条件，获取单条数据
    * @param organizationInput input对象
    * @return ResultData<OrganizationOutput>
    */
    @ReadDataSource
    @Override
    public ResultData<OrganizationOutput> getOneLeftUser(OrganizationInput organizationInput) throws BaseException{
		//返回业务结果对象
		ResultData<OrganizationOutput> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<OrganizationInput,OrganizationPo> inputToPo = new InputToPo<>();
		OrganizationPo organizationPo = inputToPo.toPo(organizationInput,OrganizationPo.class);
		OrganizationOutput organizationResultOutput	= null;
		OrganizationPo organizationResultPo = organizationDao.getOneCriteriaLeftUser(organizationPo);
		if (organizationResultPo != null){
			//对象转换工具
			PoToOutput<OrganizationPo,OrganizationOutput> poToOutput = new PoToOutput<>();
			organizationResultOutput = poToOutput.jsonOutput(organizationResultPo,OrganizationOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(organizationResultOutput);
		return resultData;
    }

    /**
    * 动态条件，自动分页查询列表数据
    * @param  organizationInput Input对象
    * @return PageInfo
    */
    @ReadDataSource
    @Override
    public ResultData<PageInfo> listPaginatedLeftUser(OrganizationInput organizationInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo> resultData = new ResultData<>();
		int page = organizationInput.getPage() == null?1 : organizationInput.getPage();
		int rows = organizationInput.getRows() == null?10 : organizationInput.getRows();
		PageHelper.startPage(page, rows);
		//Input to po 转换
		InputToPo<OrganizationInput,OrganizationPo> inputToPo = new InputToPo<>();
		OrganizationPo organizationPo = inputToPo.toPo(organizationInput,OrganizationPo.class);
		PageInfo pageInfo = null;
		List<OrganizationPo> list = organizationDao.listPaginatedLeftUser(organizationPo);
		if (list != null){
    		pageInfo = new PageInfo<>(list);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(pageInfo == null? new PageInfo<>():pageInfo);
		return resultData;
    }

}
