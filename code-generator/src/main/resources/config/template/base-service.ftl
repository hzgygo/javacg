package ${relativeProjectPath}.${entityPath}.base;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import javax.annotation.PostConstruct;
import com.jbt.core.util.SnowflakeIdWorker;

import ${path_core}.service.BaseService;
import ${path_db}.redis.RedisService;
import ${path_core}.common.ReturnCode;
import ${path_core}.entity.ResultData;
import ${path_core}.common.converter.InputToPo;
import ${path_core}.common.converter.PoToOutput;
import ${path_core}.exception.base.BaseException;
import ${path_core}.exception.CodeException;
import ${path_db}.annotation.datasource.ReadDataSource;
import ${path_db}.annotation.datasource.WriteDataSource;
import ${relativeProjectPath}.${entityPath}.entity.${entity}Input;
import ${relativeProjectPath}.${entityPath}.entity.${entity}Output;
import ${relativeProjectPath}.${entityPath}.entity.${entity}Po;
import ${relativeProjectPath}.${entityPath}.I${entity}Dao;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public abstract class Base${entity}Service extends BaseService implements IBase${entity}Service<${entity}Input,${entity}Output>{
<#assign idIsExist="false"/>
<#list columns as column>
<#assign property="result"/>
<#if column.code?lower_case == 'id' >
	<#assign property="id"/>
	<#assign idIsExist="true"/>
</#if>
</#list>

    private static Logger logger = LoggerFactory.getLogger(Base${entity}Service.class);
	//业务返回码
<#assign tindexTmp="${tindex}"/>
<#if tindex < 10>
	<#assign tindexTmp="0${tindex}"/>
</#if>
	protected final Integer MC_1 = 10${tindexTmp}01;
	protected final Integer MC_2 = 10${tindexTmp}02;
	protected final Integer MC_3 = 10${tindexTmp}03;
	protected final Integer MC_4 = 10${tindexTmp}04;
	protected final Integer MC_5 = 10${tindexTmp}05;
	protected final Integer MC_6 = 10${tindexTmp}06;
	protected final Integer MC_7 = 10${tindexTmp}07;
	protected final Integer MC_8 = 10${tindexTmp}08;
	protected final Integer MC_9 = 10${tindexTmp}09;
	protected final Integer MC_10 = 10${tindexTmp}10;
	protected final Integer MC_11 = 10${tindexTmp}11;
	protected final Integer MC_12 = 10${tindexTmp}12;
	protected final Integer MC_13 = 10${tindexTmp}13;
	protected final Integer MC_14 = 10${tindexTmp}14;
	protected final Integer MC_15 = 10${tindexTmp}15;
	protected final Integer MC_16 = 10${tindexTmp}16;
	protected final Integer MC_17 = 10${tindexTmp}17;
	protected final Integer MC_18 = 10${tindexTmp}18;
	protected final Integer MC_19 = 10${tindexTmp}19;
	protected final Integer MC_20 = 10${tindexTmp}20;



	@Resource
	protected RedisService<${entity}Po> redisService;
	
	@Resource
    protected I${entity}Dao ${entity?uncap_first}Dao;

	@PostConstruct
	private void addService(){
		if (autoDataProcessor != null){
			autoDataProcessor.addService(this);
		}
	}

	/**
	 * 保存
	 * @param ${entity?uncap_first}Input Input参数对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
    @Override
	public ResultData<Integer> create(${entity}Input ${entity?uncap_first}Input) throws BaseException{
        //返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
    	InputToPo<${entity}Input,${entity}Po> inputToPo = new InputToPo<>();
		${entity}Po ${entity?uncap_first}Po = inputToPo.toPo(${entity?uncap_first}Input,${entity}Po.class);
		//通过SnowflakeId生成数据库主键id
		long id = SnowflakeIdWorker.nextId();
		${entity?uncap_first}Po.setId(id);
		int rval = ${entity?uncap_first}Dao.save(${entity?uncap_first}Po);
		if (rval == 0){
            throw new CodeException(ReturnCode.FAIL.getCode());
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(rval);
		return resultData;
	}

	<#if idIsExist == 'true'>
	/**
	 * 根据id修改
	 * @param ${entity?uncap_first}Input input对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
	@Override
	public ResultData<Integer> modifyById(${entity}Input ${entity?uncap_first}Input) throws BaseException{
        //返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<${entity}Input,${entity}Po> inputToPo = new InputToPo<>();
		${entity}Po ${entity?uncap_first}Po = inputToPo.toPo(${entity?uncap_first}Input,${entity}Po.class);
		int rval = ${entity?uncap_first}Dao.updateById(${entity?uncap_first}Po);
        if (rval == 0){
        	throw new CodeException(ReturnCode.FAIL.getCode());
        }
        resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
        resultData.setData(rval);
        return resultData;
	}
	</#if>

	/**
	 * 动态条件删除
	 * @param ${entity?uncap_first}Input input对象
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
    @Override
	public ResultData<Integer> deleteCriteria(${entity}Input ${entity?uncap_first}Input) throws BaseException{
		//返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		//Input to po 转换
    	InputToPo<${entity}Input,${entity}Po> inputToPo = new InputToPo<>();
		${entity}Po ${entity?uncap_first}Po = inputToPo.toPo(${entity?uncap_first}Input,${entity}Po.class);
		int rval = ${entity?uncap_first}Dao.removeCriteria(${entity?uncap_first}Po);
		if (rval == 0){
			throw new CodeException(ReturnCode.FAIL.getCode());
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(rval);
		return resultData;
	}

	<#if idIsExist == 'true'>
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
		int rval = ${entity?uncap_first}Dao.removeById(id);
        if (rval == 0){
        	throw new CodeException(ReturnCode.FAIL.getCode());
        }
        resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
        resultData.setData(rval);
        return resultData;
	}
	</#if>

	/**
	 * 删除所有数据
	 * @return ResultData<Integer>
	 */
    @WriteDataSource
    @Override
	public ResultData<Integer> deleteAll() throws BaseException{
		//返回业务结果对象
		ResultData<Integer> resultData = new ResultData<>();
		int rval = ${entity?uncap_first}Dao.removeAll();
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
	 * @param ${entity?uncap_first}Input input对象
	 * @return ResultData<List<${entity}Output>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<${entity}Output>> listCriteria(${entity}Input ${entity?uncap_first}Input) throws BaseException{
		//返回业务结果对象
		ResultData<List<${entity}Output>> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<${entity}Input,${entity}Po> inputToPo = new InputToPo<>();
		${entity}Po ${entity?uncap_first}Po = inputToPo.toPo(${entity?uncap_first}Input,${entity}Po.class);
		List<${entity}Output> listOutput = null;
		List<${entity}Po> list = ${entity?uncap_first}Dao.listCriteria(${entity?uncap_first}Po);
		if (list != null){
			//对象转换工具
			PoToOutput<${entity}Po,${entity}Output> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,${entity}Output.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}
    <#if idIsExist == 'true'>
	/**
	 * 动态条件查询主键Id数据
	 * @param ${entity?uncap_first}Input Input对象
	 * @return ResultData<List<Long>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<Long>> listIdsCriteria(${entity}Input ${entity?uncap_first}Input) throws BaseException{
		//返回业务结果对象
		ResultData<List<Long>> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<${entity}Input,${entity}Po> inputToPo = new InputToPo<>();
		${entity}Po ${entity?uncap_first}Po = inputToPo.toPo(${entity?uncap_first}Input,${entity}Po.class);
        List<Long> list = ${entity?uncap_first}Dao.listIdsCriteria(${entity?uncap_first}Po);
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(list);
		return resultData;
	}

	/**
	 * 根据id串，in的方式查询数据
	 * @param ${entity?uncap_first}Input Input对象
	 * @return ResultData<List<${entity}Output>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<${entity}Output>> listInIds(${entity}Input ${entity?uncap_first}Input) throws BaseException{
		//返回业务结果对象
		ResultData<List<${entity}Output>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<${entity}Input,${entity}Po> inputToPo = new InputToPo<>();
		${entity}Po ${entity?uncap_first}Po = inputToPo.toPo(${entity?uncap_first}Input,${entity}Po.class);
		List<${entity}Output> listOutput = null;
		List<${entity}Po> list = ${entity?uncap_first}Dao.listByInIds(${entity?uncap_first}Po);
		if (list != null){
			//对象转换工具
			PoToOutput<${entity}Po,${entity}Output> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,${entity}Output.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}
    </#if>
	/**
	 * 查询所有数据
	 * @return ResultData<List<${entity}Output>>
	 */
    @ReadDataSource
    @Override
	public ResultData<List<${entity}Output>> listAll() throws BaseException{
		//返回业务结果对象
		ResultData<List<${entity}Output>> resultData = new ResultData<>();
        List<${entity}Output> listOutput = null;
		List<${entity}Po> list = ${entity?uncap_first}Dao.listAll();
		if (list != null){
			//对象转换工具
			PoToOutput<${entity}Po,${entity}Output> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.toListOutput(list,${entity}Output.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
	}

	<#if idIsExist == 'true'>
	/**
	 * 根据id，获取单条数据
	 * @param id 主键id
	 * @return ResultData<${entity}Output>
	 */
    @ReadDataSource
	@Override
	public ResultData<${entity}Output> getOneById(Long id) throws BaseException{
        //返回业务结果对象
        ResultData<${entity}Output> resultData = new ResultData<>();
		${entity}Output ${entity?uncap_first}Output = null;
		${entity}Po ${entity?uncap_first}Po = ${entity?uncap_first}Dao.getOneById(id);
		if (${entity?uncap_first}Po != null){
			//对象转换工具
			PoToOutput<${entity}Po,${entity}Output> poToOutput = new PoToOutput<>();
			${entity?uncap_first}Output = poToOutput.toOutput(${entity?uncap_first}Po,${entity}Output.class);
		}
        resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
        resultData.setData(${entity?uncap_first}Output);
        return resultData;
	}
	</#if>

	/**
	 * 动态条件，获取单条数据
	 * @param ${entity?uncap_first}Input input对象
	 * @return ResultData<${entity}Output>
	 */
    @ReadDataSource
    @Override
	public ResultData<${entity}Output> getOne(${entity}Input ${entity?uncap_first}Input) throws BaseException{
		//返回业务结果对象
		ResultData<${entity}Output> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<${entity}Input,${entity}Po> inputToPo = new InputToPo<>();
		${entity}Po ${entity?uncap_first}Po = inputToPo.toPo(${entity?uncap_first}Input,${entity}Po.class);
		${entity}Output ${entity?uncap_first}ResultOutput	= null;
		${entity}Po ${entity?uncap_first}ResultPo = ${entity?uncap_first}Dao.getOneCriteria(${entity?uncap_first}Po);
		if (${entity?uncap_first}ResultPo != null){
			//对象转换工具
			PoToOutput<${entity}Po,${entity}Output> poToOutput = new PoToOutput<>();
			${entity?uncap_first}ResultOutput = poToOutput.toOutput(${entity?uncap_first}ResultPo,${entity}Output.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(${entity?uncap_first}ResultOutput);
		return resultData;
	}

	/**
	 * 动态条件，查询记录总数
	 * @param ${entity?uncap_first}Input Input对象
	 * @return ResultData<Long>
	 */
    @ReadDataSource
    @Override
	public ResultData<Long> count(${entity}Input ${entity?uncap_first}Input) throws BaseException{
		//返回业务结果对象
		ResultData<Long> resultData = new ResultData<>();
		//Input to po 转换
        InputToPo<${entity}Input,${entity}Po> inputToPo = new InputToPo<>();
		${entity}Po ${entity?uncap_first}Po = inputToPo.toPo(${entity?uncap_first}Input,${entity}Po.class);
		Long count = ${entity?uncap_first}Dao.count(${entity?uncap_first}Po);
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(count == null?0:count);
		return resultData;
	}

	/**
	 * 动态条件，自动分页查询列表数据
	 * @param  ${entity?uncap_first}Input Input对象
	 * @return PageInfo
	 */
    @ReadDataSource
    @Override
	public ResultData<PageInfo> listPaginated(${entity}Input ${entity?uncap_first}Input) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo> resultData = new ResultData<>();
		int page = ${entity?uncap_first}Input.getPage() == null?1 : ${entity?uncap_first}Input.getPage();
		int rows = ${entity?uncap_first}Input.getRows() == null?10 : ${entity?uncap_first}Input.getRows();
		PageHelper.startPage(page, rows);
		//Input to po 转换
        InputToPo<${entity}Input,${entity}Po> inputToPo = new InputToPo<>();
		${entity}Po ${entity?uncap_first}Po = inputToPo.toPo(${entity?uncap_first}Input,${entity}Po.class);
        PageInfo pageInfo = null;
		List<${entity}Po> list = ${entity?uncap_first}Dao.listPaginated(${entity?uncap_first}Po);
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
	 * @param ${entity?uncap_first}Input Input对象
	 * @return ResultData<PageInfo<${entity}Output>>
	 */
    @ReadDataSource
    @Override
	public ResultData<PageInfo<${entity}Output>> listPaginatedManual(${entity}Input  ${entity?uncap_first}Input) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo<${entity}Output>> resultData = new ResultData<>();
		int page = ${entity?uncap_first}Input.getPage() == null?1: ${entity?uncap_first}Input.getPage();
		int rows = ${entity?uncap_first}Input.getRows() == null?10: ${entity?uncap_first}Input.getRows();
		//Input to po 转换
        InputToPo<${entity}Input,${entity}Po> inputToPo = new InputToPo<>();
		${entity}Po ${entity?uncap_first}Po = inputToPo.toPo(${entity?uncap_first}Input,${entity}Po.class);
		//计算手动分页参数
		int start = (page - 1) * rows;
		${entity?uncap_first}Po.setStart(start);
		${entity?uncap_first}Po.setOffset(rows);
        PageInfo<${entity}Output> pageInfo = new PageInfo<>();
		pageInfo.setPageNum(page);
		pageInfo.setPageSize(rows);
		Long countRecords = ${entity?uncap_first}Dao.countPaginatedManual(${entity?uncap_first}Po);
		List<${entity}Po> list = ${entity?uncap_first}Dao.listPaginatedManual(${entity?uncap_first}Po);
		//对象转换工具
		PoToOutput<${entity}Po,${entity}Output> poToOutput = new PoToOutput<>();
		List<${entity}Output> listOutput = poToOutput.toListOutput(list,${entity}Output.class);
		int pageTotal = (int) Math.ceil(countRecords / (double) rows);
		pageInfo.setPages(pageTotal);
		pageInfo.setList(listOutput);
		pageInfo.setTotal(countRecords);
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(pageInfo);
		return resultData;
	}

<#list references as reference>
<#assign join=""/>
<#assign joinTableName=""/>
<#assign isJoin="false"/>
<#if table.code == reference.parentTable.code>
	<#assign join="left"/>
	<#assign isJoin="true"/>
	<#assign ctOriginalCode="${reference.childTable.originalCode}"/>
	<#assign ctCode="${reference.childTable.code}"/>
	<#assign ctIndex="${ctCode?index_of('_')}"/>
	<#assign ctLength="${ctCode?length}"/>
	<#assign joinTableName="${ctCode?substring(ctIndex?number+1,ctLength?number)?uncap_first}"/>
    /**
    * 动态条件关联查询列表数据
    * @param ${entity?uncap_first}Input input对象
    * @return ResultData<List<${entity}Output>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<${entity}Output>> listCriteria${join?cap_first}${joinTableName?cap_first}(${entity}Input ${entity?uncap_first}Input) throws BaseException{
		//返回业务结果对象
		ResultData<List<${entity}Output>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<${entity}Input,${entity}Po> inputToPo = new InputToPo<>();
		${entity}Po ${entity?uncap_first}Po = inputToPo.toPo(${entity?uncap_first}Input,${entity}Po.class);
		List<${entity}Output> listOutput = null;
		List<${entity}Po> list = ${entity?uncap_first}Dao.listCriteria${join?cap_first}${joinTableName?cap_first}(${entity?uncap_first}Po);
		if (list != null){
			//对象转换工具
			PoToOutput<${entity}Po,${entity}Output> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,${entity}Output.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }

    <#if idIsExist == 'true'>
    /**
    * 根据id串，in的方式查询数据
    * @param ${entity?uncap_first}Input Input对象
    * @return ResultData<List<${entity}Output>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<${entity}Output>> listInIds${join?cap_first}${joinTableName?cap_first}(${entity}Input ${entity?uncap_first}Input) throws BaseException{
		//返回业务结果对象
		ResultData<List<${entity}Output>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<${entity}Input,${entity}Po> inputToPo = new InputToPo<>();
		${entity}Po ${entity?uncap_first}Po = inputToPo.toPo(${entity?uncap_first}Input,${entity}Po.class);
		List<${entity}Output> listOutput = null;
		List<${entity}Po> list = ${entity?uncap_first}Dao.listInIds${join?cap_first}${joinTableName?cap_first}(${entity?uncap_first}Po);
		if (list != null){
			//对象转换工具
			PoToOutput<${entity}Po,${entity}Output> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,${entity}Output.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }
    </#if>
    /**
    * 查询所有数据
    * @return ResultData<List<${entity}Output>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<${entity}Output>> listAll${join?cap_first}${joinTableName?cap_first}() throws BaseException{
		//返回业务结果对象
		ResultData<List<${entity}Output>> resultData = new ResultData<>();
		List<${entity}Output> listOutput = null;
		List<${entity}Po> list = ${entity?uncap_first}Dao.listAll${join?cap_first}${joinTableName?cap_first}();
		if (list != null){
			//对象转换工具
			PoToOutput<${entity}Po,${entity}Output> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,${entity}Output.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }

	<#if idIsExist == 'true'>
	/**
	* 根据id，获取单条数据
	* @param id 主键id
	* @return ResultData<${entity}Output>
	*/
	@ReadDataSource
	@Override
	public ResultData<${entity}Output> getOneById${join?cap_first}${joinTableName?cap_first}(Long id) throws BaseException{
		//返回业务结果对象
		ResultData<${entity}Output> resultData = new ResultData<>();
		${entity}Output ${entity?uncap_first}Output = null;
		${entity}Po ${entity?uncap_first}Po = ${entity?uncap_first}Dao.getOneById${join?cap_first}${joinTableName?cap_first}(id);
		if (${entity?uncap_first}Po != null){
			//对象转换工具
			PoToOutput<${entity}Po,${entity}Output> poToOutput = new PoToOutput<>();
			${entity?uncap_first}Output = poToOutput.jsonOutput(${entity?uncap_first}Po,${entity}Output.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
        resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(${entity?uncap_first}Output);
		return resultData;
	}
	</#if>

    /**
    * 动态条件，获取单条数据
    * @param ${entity?uncap_first}Input input对象
    * @return ResultData<${entity}Output>
    */
    @ReadDataSource
    @Override
    public ResultData<${entity}Output> getOne${join?cap_first}${joinTableName?cap_first}(${entity}Input ${entity?uncap_first}Input) throws BaseException{
		//返回业务结果对象
		ResultData<${entity}Output> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<${entity}Input,${entity}Po> inputToPo = new InputToPo<>();
		${entity}Po ${entity?uncap_first}Po = inputToPo.toPo(${entity?uncap_first}Input,${entity}Po.class);
		${entity}Output ${entity?uncap_first}ResultOutput	= null;
		${entity}Po ${entity?uncap_first}ResultPo = ${entity?uncap_first}Dao.getOneCriteria${join?cap_first}${joinTableName?cap_first}(${entity?uncap_first}Po);
		if (${entity?uncap_first}ResultPo != null){
			//对象转换工具
			PoToOutput<${entity}Po,${entity}Output> poToOutput = new PoToOutput<>();
			${entity?uncap_first}ResultOutput = poToOutput.jsonOutput(${entity?uncap_first}ResultPo,${entity}Output.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(${entity?uncap_first}ResultOutput);
		return resultData;
    }

    /**
    * 动态条件，自动分页查询列表数据
    * @param  ${entity?uncap_first}Input Input对象
    * @return PageInfo
    */
    @ReadDataSource
    @Override
    public ResultData<PageInfo> listPaginated${join?cap_first}${joinTableName?cap_first}(${entity}Input ${entity?uncap_first}Input) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo> resultData = new ResultData<>();
		int page = ${entity?uncap_first}Input.getPage() == null?1 : ${entity?uncap_first}Input.getPage();
		int rows = ${entity?uncap_first}Input.getRows() == null?10 : ${entity?uncap_first}Input.getRows();
		PageHelper.startPage(page, rows);
		//Input to po 转换
		InputToPo<${entity}Input,${entity}Po> inputToPo = new InputToPo<>();
		${entity}Po ${entity?uncap_first}Po = inputToPo.toPo(${entity?uncap_first}Input,${entity}Po.class);
		PageInfo pageInfo = null;
		List<${entity}Po> list = ${entity?uncap_first}Dao.listPaginated${join?cap_first}${joinTableName?cap_first}(${entity?uncap_first}Po);
		if (list != null){
    		pageInfo = new PageInfo<>(list);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(pageInfo == null? new PageInfo<>():pageInfo);
		return resultData;
    }
</#if>
<#if table.code == reference.childTable.code>
	<#assign join="inner"/>
	<#assign isJoin="true"/>
	<#assign ctOriginalCode="${reference.parentTable.originalCode}"/>
	<#assign ctCode="${reference.parentTable.code}"/>
	<#assign ctIndex="${ctCode?index_of('_')}"/>
	<#assign ctLength="${ctCode?length}"/>
	<#assign joinTableName="${ctCode?substring(ctIndex?number+1,ctLength?number)?uncap_first}"/>
    /**
    * 动态条件关联查询列表数据
    * @param ${entity?uncap_first}Input input对象
    * @return ResultData<List<${entity}Output>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<${entity}Output>> listCriteria${join?cap_first}${joinTableName?cap_first}(${entity}Input ${entity?uncap_first}Input) throws BaseException{
		//返回业务结果对象
		ResultData<List<${entity}Output>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<${entity}Input,${entity}Po> inputToPo = new InputToPo<>();
		${entity}Po ${entity?uncap_first}Po = inputToPo.toPo(${entity?uncap_first}Input,${entity}Po.class);
		List<${entity}Output> listOutput = null;
		List<${entity}Po> list = ${entity?uncap_first}Dao.listCriteria${join?cap_first}${joinTableName?cap_first}(${entity?uncap_first}Po);
		if (list != null){
			//对象转换工具
			PoToOutput<${entity}Po,${entity}Output> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,${entity}Output.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
    	resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }

    <#if idIsExist == 'true'>
    /**
    * 根据id串，in的方式查询数据
    * @param ${entity?uncap_first}Input Input对象
    * @return ResultData<List<${entity}Output>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<${entity}Output>> listInIds${join?cap_first}${joinTableName?cap_first}(${entity}Input ${entity?uncap_first}Input) throws BaseException{
		//返回业务结果对象
		ResultData<List<${entity}Output>> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<${entity}Input,${entity}Po> inputToPo = new InputToPo<>();
		${entity}Po ${entity?uncap_first}Po = inputToPo.toPo(${entity?uncap_first}Input,${entity}Po.class);
		List<${entity}Output> listOutput = null;
		List<${entity}Po> list = ${entity?uncap_first}Dao.listInIds${join?cap_first}${joinTableName?cap_first}(${entity?uncap_first}Po);
		if (list != null){
			//对象转换工具
			PoToOutput<${entity}Po,${entity}Output> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,${entity}Output.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
   		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }
    </#if>
    /**
    * 查询所有数据
    * @return ResultData<List<${entity}Output>>
    */
    @ReadDataSource
    @Override
    public ResultData<List<${entity}Output>> listAll${join?cap_first}${joinTableName?cap_first}() throws BaseException{
		//返回业务结果对象
		ResultData<List<${entity}Output>> resultData = new ResultData<>();
		List<${entity}Output> listOutput = null;
		List<${entity}Po> list = ${entity?uncap_first}Dao.listAll${join?cap_first}${joinTableName?cap_first}();
		if (list != null){
			//对象转换工具
			PoToOutput<${entity}Po,${entity}Output> poToOutput = new PoToOutput<>();
			listOutput = poToOutput.jsonListOutput(list,${entity}Output.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(listOutput);
		return resultData;
    }

	<#if idIsExist == 'true'>
	/**
	* 根据id，获取单条数据
	* @param id 主键id
	* @return ResultData<${entity}Output>
	*/
	@ReadDataSource
	@Override
	public ResultData<${entity}Output> getOneById${join?cap_first}${joinTableName?cap_first}(Long id) throws BaseException{
		//返回业务结果对象
		ResultData<${entity}Output> resultData = new ResultData<>();
		${entity}Output ${entity?uncap_first}Output = null;
		${entity}Po ${entity?uncap_first}Po = ${entity?uncap_first}Dao.getOneById${join?cap_first}${joinTableName?cap_first}(id);
		if (${entity?uncap_first}Po != null){
			//对象转换工具
			PoToOutput<${entity}Po,${entity}Output> poToOutput = new PoToOutput<>();
			${entity?uncap_first}Output = poToOutput.jsonOutput(${entity?uncap_first}Po,${entity}Output.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(${entity?uncap_first}Output);
		return resultData;
	}
	</#if>

    /**
    * 动态条件，获取单条数据
    * @param ${entity?uncap_first}Input input对象
    * @return ResultData<${entity}Output>
    */
    @ReadDataSource
    @Override
    public ResultData<${entity}Output> getOne${join?cap_first}${joinTableName?cap_first}(${entity}Input ${entity?uncap_first}Input) throws BaseException{
		//返回业务结果对象
		ResultData<${entity}Output> resultData = new ResultData<>();
		//Input to po 转换
		InputToPo<${entity}Input,${entity}Po> inputToPo = new InputToPo<>();
		${entity}Po ${entity?uncap_first}Po = inputToPo.toPo(${entity?uncap_first}Input,${entity}Po.class);
		${entity}Output ${entity?uncap_first}ResultOutput	= null;
		${entity}Po ${entity?uncap_first}ResultPo = ${entity?uncap_first}Dao.getOneCriteria${join?cap_first}${joinTableName?cap_first}(${entity?uncap_first}Po);
		if (${entity?uncap_first}ResultPo != null){
			//对象转换工具
			PoToOutput<${entity}Po,${entity}Output> poToOutput = new PoToOutput<>();
			${entity?uncap_first}ResultOutput = poToOutput.jsonOutput(${entity?uncap_first}ResultPo,${entity}Output.class);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
		resultData.setData(${entity?uncap_first}ResultOutput);
		return resultData;
    }

    /**
    * 动态条件，自动分页查询列表数据
    * @param  ${entity?uncap_first}Input Input对象
    * @return PageInfo
    */
    @ReadDataSource
    @Override
    public ResultData<PageInfo> listPaginated${join?cap_first}${joinTableName?cap_first}(${entity}Input ${entity?uncap_first}Input) throws BaseException{
		//返回业务结果对象
		ResultData<PageInfo> resultData = new ResultData<>();
		int page = ${entity?uncap_first}Input.getPage() == null?1 : ${entity?uncap_first}Input.getPage();
		int rows = ${entity?uncap_first}Input.getRows() == null?10 : ${entity?uncap_first}Input.getRows();
		PageHelper.startPage(page, rows);
		//Input to po 转换
		InputToPo<${entity}Input,${entity}Po> inputToPo = new InputToPo<>();
		${entity}Po ${entity?uncap_first}Po = inputToPo.toPo(${entity?uncap_first}Input,${entity}Po.class);
		PageInfo pageInfo = null;
		List<${entity}Po> list = ${entity?uncap_first}Dao.listPaginated${join?cap_first}${joinTableName?cap_first}(${entity?uncap_first}Po);
		if (list != null){
    		pageInfo = new PageInfo<>(list);
		}
		resultData.setCode(ReturnCode.SUCCESS.getCode());
		resultData.setMessage(ReturnCode.SUCCESS.getName());
        resultData.setData(pageInfo == null?new PageInfo<>():pageInfo);
		return resultData;
    }
</#if>
</#list>

}
