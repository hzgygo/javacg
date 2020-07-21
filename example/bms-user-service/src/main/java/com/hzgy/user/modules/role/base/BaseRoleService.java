package com.hzgy.user.modules.role.base;

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
import com.hzgy.user.modules.role.entity.RoleInput;
import com.hzgy.user.modules.role.entity.RoleOutput;
import com.hzgy.user.modules.role.entity.RolePo;
import com.hzgy.user.modules.role.IRoleDao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public abstract class BaseRoleService extends BaseService implements IBaseRoleService<RoleInput,RoleOutput>{

    private static Logger logger = LoggerFactory.getLogger(BaseRoleService.class);
	//业务返回码
	protected final Integer MC_1 = 100101;
	protected final Integer MC_2 = 100102;
	protected final Integer MC_3 = 100103;
	protected final Integer MC_4 = 100104;
	protected final Integer MC_5 = 100105;
	protected final Integer MC_6 = 100106;
	protected final Integer MC_7 = 100107;
	protected final Integer MC_8 = 100108;
	protected final Integer MC_9 = 100109;
	protected final Integer MC_10 = 100110;
	protected final Integer MC_11 = 100111;
	protected final Integer MC_12 = 100112;
	protected final Integer MC_13 = 100113;
	protected final Integer MC_14 = 100114;
	protected final Integer MC_15 = 100115;
	protected final Integer MC_16 = 100116;
	protected final Integer MC_17 = 100117;
	protected final Integer MC_18 = 100118;
	protected final Integer MC_19 = 100119;
	protected final Integer MC_20 = 100120;



	@Resource
	protected RedisService<RolePo> redisService;
	
	@Resource
    protected IRoleDao roleDao;

	@PostConstruct
	private void addService(){
		if (autoDataProcessor != null){
			autoDataProcessor.addService(this);
		}
	}

	/**
	 * 保存
	 * @param roleInput Input参数对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
    @Override
	public ResultData<Integer> create(RoleInput roleInput) throws BaseException{
        //返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
    	InputToPo<RoleInput,RolePo> inputToPo = new InputToPo<>();
		RolePo rolePo = inputToPo.toPo(roleInput,RolePo.class);
		//通过SnowflakeId生成数据库主键id
		long id = SnowflakeIdWorker.nextId();
		rolePo.setId(id);
		int rval = roleDao.save(rolePo);
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
	 * @param roleInput input对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
	@Override
	public ResultData<Integer> modifyById(RoleInput roleInput) throws BaseException{
        //返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<RoleInput,RolePo> inputToPo = new InputToPo<>();
		RolePo rolePo = inputToPo.toPo(roleInput,RolePo.class);
		int rval = roleDao.updateById(rolePo);
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
	 * @param roleInput input对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
    @Override
	public ResultData<Integer> deleteCriteria(RoleInput roleInput) throws BaseException{
		//返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
    	InputToPo<RoleInput,RolePo> inputToPo = new InputToPo<>();
		RolePo rolePo = inputToPo.toPo(roleInput,RolePo.class);
		int rval = roleDao.removeCriteria(rolePo);
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
		int rval = roleDao.removeById(id);
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
		int rval = roleDao.removeAll();
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
	 * @param roleInput input对象
	 * @return ResultData<List<RoleOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<RoleOutput>> listCriteria(RoleInput roleInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<RoleOutput>> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<RoleInput,RolePo> inputToPo = new InputToPo<>();
		RolePo rolePo = inputToPo.toPo(roleInput,RolePo.class);
		List<RoleOutput> listOutput = null;
		List<RolePo> list = roleDao.listCriteria(rolePo);
		if (list != null){
			//对象转换工具
			PoToOutput<RolePo,RoleOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,RoleOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}
	/**
	 * 动态条件查询主键Id数据
	 * @param roleInput Input对象
	 * @return ResultData<List<Long>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<Long>> listIdsCriteria(RoleInput roleInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<Long>> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<RoleInput,RolePo> inputToPo = new InputToPo<>();
		RolePo rolePo = inputToPo.toPo(roleInput,RolePo.class);
        List<Long> list = roleDao.listIdsCriteria(rolePo);
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(list);
		return resultData;
	}

	/**
	 * 根据id串，in的方式查询数据
	 * @param roleInput Input对象
	 * @return ResultData<List<RoleOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<RoleOutput>> listInIds(RoleInput roleInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<RoleOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<RoleInput,RolePo> inputToPo = new InputToPo<>();
		RolePo rolePo = inputToPo.toPo(roleInput,RolePo.class);
		List<RoleOutput> listOutput = null;
		List<RolePo> list = roleDao.listByInIds(rolePo);
		if (list != null){
			//对象转换工具
			PoToOutput<RolePo,RoleOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,RoleOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}
	/**
	 * 查询所有数据
	 * @return ResultData<List<RoleOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<RoleOutput>> listAll() throws BaseException{
		//返回业务结果对象
		ResultData<List<RoleOutput>> resultData = new ResultData<>();
        List<RoleOutput> listOutput = null;
		List<RolePo> list = roleDao.listAll();
		if (list != null){
			//对象转换工具
			PoToOutput<RolePo,RoleOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,RoleOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}

	/**
	 * 根据id，获取单条数据
	 * @param id 主键id
	 * @return ResultData<RoleOutput>
	 */
    @ReadDataSource
	@Override
	public ResultData<RoleOutput> getOneById(Long id) throws BaseException{
        //返回业务结果对象
        ResultData<RoleOutput> resultData = new ResultData<>();
		RoleOutput roleOutput = null;
		RolePo rolePo = roleDao.getOneById(id);
		if (rolePo != null){
			//对象转换工具
			PoToOutput<RolePo,RoleOutput> poToOutput = new PoToOutput<>();
			roleOutput = poToOutput.toOutput(rolePo,RoleOutput.class);
		}
        resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
        resultData.setData(roleOutput);
        return resultData;
	}

	/**
	 * 动态条件，获取单条数据
	 * @param roleInput input对象
	 * @return ResultData<RoleOutput>
	 */
    @ReadDataSource
    @Override
	public ResultData<RoleOutput> getOne(RoleInput roleInput) throws BaseException{
		//返回业务结果对象
		ResultData<RoleOutput> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<RoleInput,RolePo> inputToPo = new InputToPo<>();
		RolePo rolePo = inputToPo.toPo(roleInput,RolePo.class);
		RoleOutput roleResultOutput	= null;
		RolePo roleResultPo = roleDao.getOneCriteria(rolePo);
		if (roleResultPo != null){
			//对象转换工具
			PoToOutput<RolePo,RoleOutput> poToOutput = new PoToOutput<>();
			roleResultOutput = poToOutput.toOutput(roleResultPo,RoleOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(roleResultOutput);
		return resultData;
	}

	/**
	 * 动态条件，查询记录总数
	 * @param roleInput Input对象
	 * @return ResultData<Long>
	 */
    @ReadDataSource
    @Override
	public ResultData<Long> count(RoleInput roleInput) throws BaseException{
		//返回业务结果对象
		ResultData<Long> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<RoleInput,RolePo> inputToPo = new InputToPo<>();
		RolePo rolePo = inputToPo.toPo(roleInput,RolePo.class);
		Long count = roleDao.count(rolePo);
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(count == null?0:count);
		return resultData;
	}

	/**
	 * 动态条件，自动分页查询列表数据
	 * @param  roleInput Input对象
	 * @return PageInfo
	 */
    @ReadDataSource
    @Override
	public ResultData<PageInfo> listPaginated(RoleInput roleInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo> resultData = new ResultData<>();
		int page = roleInput.getPage() == null?1 : roleInput.getPage();
		int rows = roleInput.getRows() == null?10 : roleInput.getRows();
		PageHelper.startPage(page, rows);
		//Input to po 转换
        InputToPo<RoleInput,RolePo> inputToPo = new InputToPo<>();
		RolePo rolePo = inputToPo.toPo(roleInput,RolePo.class);
        PageInfo pageInfo = null;
		List<RolePo> list = roleDao.listPaginated(rolePo);
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
	 * @param roleInput Input对象
	 * @return ResultData<PageInfo<RoleOutput>>
	 */
    @ReadDataSource
    @Override
	public ResultData<PageInfo<RoleOutput>> listPaginatedManual(RoleInput  roleInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo<RoleOutput>> resultData = new ResultData<>();
		int page = roleInput.getPage() == null?1: roleInput.getPage();
		int rows = roleInput.getRows() == null?10: roleInput.getRows();
		//Input to po 转换
        InputToPo<RoleInput,RolePo> inputToPo = new InputToPo<>();
		RolePo rolePo = inputToPo.toPo(roleInput,RolePo.class);
		//计算手动分页参数
		int start = (page - 1) * rows;
		rolePo.setStart(start);
		rolePo.setOffset(rows);
        PageInfo<RoleOutput> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(page);
		pageInfo.setPageSize(rows);
		Long countRecords = roleDao.countPaginatedManual(rolePo);
		List<RolePo> list = roleDao.listPaginatedManual(rolePo);
		//对象转换工具
		PoToOutput<RolePo,RoleOutput> poToOutput = new PoToOutput<>();
		List<RoleOutput> listOutput = poToOutput.toListOutput(list,RoleOutput.class);
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
    * @param roleInput input对象
    * @return ResultData<List<RoleOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<RoleOutput>> listCriteriaLeftRolePermitRelation(RoleInput roleInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<RoleOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<RoleInput,RolePo> inputToPo = new InputToPo<>();
		RolePo rolePo = inputToPo.toPo(roleInput,RolePo.class);
		List<RoleOutput> listOutput = null;
		List<RolePo> list = roleDao.listCriteriaLeftRolePermitRelation(rolePo);
		if (list != null){
			//对象转换工具
			PoToOutput<RolePo,RoleOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,RoleOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }

    /**
    * 根据id串，in的方式查询数据
    * @param roleInput Input对象
    * @return ResultData<List<RoleOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<RoleOutput>> listInIdsLeftRolePermitRelation(RoleInput roleInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<RoleOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<RoleInput,RolePo> inputToPo = new InputToPo<>();
		RolePo rolePo = inputToPo.toPo(roleInput,RolePo.class);
		List<RoleOutput> listOutput = null;
		List<RolePo> list = roleDao.listInIdsLeftRolePermitRelation(rolePo);
		if (list != null){
			//对象转换工具
			PoToOutput<RolePo,RoleOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,RoleOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }
    /**
    * 查询所有数据
    * @return ResultData<List<RoleOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<RoleOutput>> listAllLeftRolePermitRelation() throws BaseException{
		//返回业务结果对象
		ResultData<List<RoleOutput>> resultData = new ResultData<>();
		List<RoleOutput> listOutput = null;
		List<RolePo> list = roleDao.listAllLeftRolePermitRelation();
		if (list != null){
			//对象转换工具
			PoToOutput<RolePo,RoleOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,RoleOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }

	/**
	* 根据id，获取单条数据
	* @param id 主键id
	* @return ResultData<RoleOutput>
	*/
	@ReadDataSource
	@Override
	public ResultData<RoleOutput> getOneByIdLeftRolePermitRelation(Long id) throws BaseException{
		//返回业务结果对象
		ResultData<RoleOutput> resultData = new ResultData<>();
		RoleOutput roleOutput = null;
		RolePo rolePo = roleDao.getOneByIdLeftRolePermitRelation(id);
		if (rolePo != null){
			//对象转换工具
			PoToOutput<RolePo,RoleOutput> poToOutput = new PoToOutput<>();
			roleOutput = poToOutput.jsonOutput(rolePo,RoleOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(roleOutput);
		return resultData;
	}

    /**
    * 动态条件，获取单条数据
    * @param roleInput input对象
    * @return ResultData<RoleOutput>
    */
    @ReadDataSource
    @Override
    public ResultData<RoleOutput> getOneLeftRolePermitRelation(RoleInput roleInput) throws BaseException{
		//返回业务结果对象
		ResultData<RoleOutput> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<RoleInput,RolePo> inputToPo = new InputToPo<>();
		RolePo rolePo = inputToPo.toPo(roleInput,RolePo.class);
		RoleOutput roleResultOutput	= null;
		RolePo roleResultPo = roleDao.getOneCriteriaLeftRolePermitRelation(rolePo);
		if (roleResultPo != null){
			//对象转换工具
			PoToOutput<RolePo,RoleOutput> poToOutput = new PoToOutput<>();
			roleResultOutput = poToOutput.jsonOutput(roleResultPo,RoleOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(roleResultOutput);
		return resultData;
    }

    /**
    * 动态条件，自动分页查询列表数据
    * @param  roleInput Input对象
    * @return PageInfo
    */
    @ReadDataSource
    @Override
    public ResultData<PageInfo> listPaginatedLeftRolePermitRelation(RoleInput roleInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo> resultData = new ResultData<>();
		int page = roleInput.getPage() == null?1 : roleInput.getPage();
		int rows = roleInput.getRows() == null?10 : roleInput.getRows();
		PageHelper.startPage(page, rows);
		//Input to po 转换
		InputToPo<RoleInput,RolePo> inputToPo = new InputToPo<>();
		RolePo rolePo = inputToPo.toPo(roleInput,RolePo.class);
		PageInfo pageInfo = null;
		List<RolePo> list = roleDao.listPaginatedLeftRolePermitRelation(rolePo);
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
    * @param roleInput input对象
    * @return ResultData<List<RoleOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<RoleOutput>> listCriteriaLeftUserRoleRelation(RoleInput roleInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<RoleOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<RoleInput,RolePo> inputToPo = new InputToPo<>();
		RolePo rolePo = inputToPo.toPo(roleInput,RolePo.class);
		List<RoleOutput> listOutput = null;
		List<RolePo> list = roleDao.listCriteriaLeftUserRoleRelation(rolePo);
		if (list != null){
			//对象转换工具
			PoToOutput<RolePo,RoleOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,RoleOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }

    /**
    * 根据id串，in的方式查询数据
    * @param roleInput Input对象
    * @return ResultData<List<RoleOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<RoleOutput>> listInIdsLeftUserRoleRelation(RoleInput roleInput) throws BaseException{
		//返回业务结果对象
		ResultData<List<RoleOutput>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<RoleInput,RolePo> inputToPo = new InputToPo<>();
		RolePo rolePo = inputToPo.toPo(roleInput,RolePo.class);
		List<RoleOutput> listOutput = null;
		List<RolePo> list = roleDao.listInIdsLeftUserRoleRelation(rolePo);
		if (list != null){
			//对象转换工具
			PoToOutput<RolePo,RoleOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,RoleOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }
    /**
    * 查询所有数据
    * @return ResultData<List<RoleOutput>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<RoleOutput>> listAllLeftUserRoleRelation() throws BaseException{
		//返回业务结果对象
		ResultData<List<RoleOutput>> resultData = new ResultData<>();
		List<RoleOutput> listOutput = null;
		List<RolePo> list = roleDao.listAllLeftUserRoleRelation();
		if (list != null){
			//对象转换工具
			PoToOutput<RolePo,RoleOutput> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,RoleOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }

	/**
	* 根据id，获取单条数据
	* @param id 主键id
	* @return ResultData<RoleOutput>
	*/
	@ReadDataSource
	@Override
	public ResultData<RoleOutput> getOneByIdLeftUserRoleRelation(Long id) throws BaseException{
		//返回业务结果对象
		ResultData<RoleOutput> resultData = new ResultData<>();
		RoleOutput roleOutput = null;
		RolePo rolePo = roleDao.getOneByIdLeftUserRoleRelation(id);
		if (rolePo != null){
			//对象转换工具
			PoToOutput<RolePo,RoleOutput> poToOutput = new PoToOutput<>();
			roleOutput = poToOutput.jsonOutput(rolePo,RoleOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(roleOutput);
		return resultData;
	}

    /**
    * 动态条件，获取单条数据
    * @param roleInput input对象
    * @return ResultData<RoleOutput>
    */
    @ReadDataSource
    @Override
    public ResultData<RoleOutput> getOneLeftUserRoleRelation(RoleInput roleInput) throws BaseException{
		//返回业务结果对象
		ResultData<RoleOutput> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<RoleInput,RolePo> inputToPo = new InputToPo<>();
		RolePo rolePo = inputToPo.toPo(roleInput,RolePo.class);
		RoleOutput roleResultOutput	= null;
		RolePo roleResultPo = roleDao.getOneCriteriaLeftUserRoleRelation(rolePo);
		if (roleResultPo != null){
			//对象转换工具
			PoToOutput<RolePo,RoleOutput> poToOutput = new PoToOutput<>();
			roleResultOutput = poToOutput.jsonOutput(roleResultPo,RoleOutput.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(roleResultOutput);
		return resultData;
    }

    /**
    * 动态条件，自动分页查询列表数据
    * @param  roleInput Input对象
    * @return PageInfo
    */
    @ReadDataSource
    @Override
    public ResultData<PageInfo> listPaginatedLeftUserRoleRelation(RoleInput roleInput) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo> resultData = new ResultData<>();
		int page = roleInput.getPage() == null?1 : roleInput.getPage();
		int rows = roleInput.getRows() == null?10 : roleInput.getRows();
		PageHelper.startPage(page, rows);
		//Input to po 转换
		InputToPo<RoleInput,RolePo> inputToPo = new InputToPo<>();
		RolePo rolePo = inputToPo.toPo(roleInput,RolePo.class);
		PageInfo pageInfo = null;
		List<RolePo> list = roleDao.listPaginatedLeftUserRoleRelation(rolePo);
		if (list != null){
    		pageInfo = new PageInfo<>(list);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(pageInfo == null? new PageInfo<>():pageInfo);
		return resultData;
    }

}
