<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="${relativeProjectPath}.${entityPath}.I${entity}Dao">

	<cache type="com.hzgy.db.mybatis.cache.MybatisRedisCache">
		<property name="eviction" value="LRU" />
		<property name="flushInterval" value="6000000" />
		<property name="size" value="1024" />
		<property name="readOnly" value="false" />
	</cache>

<#list tables as table>
	<#assign idx="${table.code?index_of('_')}"/>
	<#assign len="${table.code?length}"/>
	<#assign entityName="${table.code?substring(idx?number+1,len?number)}"/>
	<parameterMap id="${entityName?uncap_first}" type="${entityName?uncap_first}" />
	<resultMap id="${entityName?uncap_first}" type="${entityName?uncap_first}">
	<#assign idIsExist="false"/>
	<#list table.columns as column>
	<#assign property="result"/>
	<#if column.code?lower_case=='id' >
		<#assign property="id"/>
		<#assign idIsExist="true"/>
	</#if>
	<#if column.dataType?contains('bigint')>
		<${property} property="${column.code?uncap_first}" column="${entityName?uncap_first}_${column.code?uncap_first}" javaType="java.lang.Long" />
	<#elseif column.dataType?contains('float')>
		<${property} property="${column.code?uncap_first}" column="${entityName?uncap_first}_${column.code?uncap_first}" javaType="java.lang.Float" />
	<#elseif column.dataType?contains('smallint')>
		<${property} property="${column.code?uncap_first}" column="${entityName?uncap_first}_${column.code?uncap_first}" javaType="java.lang.Integer" />
	<#elseif column.dataType?contains('double')>
		<${property} property="${column.code?uncap_first}" column="${entityName?uncap_first}_${column.code?uncap_first}" javaType="java.lang.Double" />
	<#elseif column.dataType?contains('decimal')>
		<${property} property="${column.code?uncap_first}" column="${entityName?uncap_first}_${column.code?uncap_first}" javaType="java.math.BigDecimal" />
	<#elseif column.dataType?contains('integer')>
		<${property} property="${column.code?uncap_first}" column="${entityName?uncap_first}_${column.code?uncap_first}" javaType="java.lang.Long" />
	<#elseif column.dataType?contains('tinyint')>
		<${property} property="${column.code?uncap_first}" column="${entityName?uncap_first}_${column.code?uncap_first}" javaType="java.lang.Integer" />
	<#elseif column.dataType?contains('int')>
		<${property} property="${column.code?uncap_first}" column="${entityName?uncap_first}_${column.code?uncap_first}" javaType="java.lang.Integer" />
	<#else>
		<${property} property="${column.code?uncap_first}" column="${entityName?uncap_first}_${column.code?uncap_first}" javaType="java.lang.String" />
	</#if>
	</#list>
	</resultMap>

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
	<resultMap id="${entityName?uncap_first}${joinTableName?cap_first}LeftResult" type="${entityName?uncap_first}" extends="${entityName?uncap_first}">
		<collection property="list${joinTableName?cap_first}" resultMap="${joinTableName?uncap_first}"/>
	</resultMap>
		</#if>
		<#if table.code == reference.childTable.code>
			<#assign join="inner"/>
			<#assign isJoin="true"/>
			<#assign ctOriginalCode="${reference.parentTable.originalCode}"/>
			<#assign ctCode="${reference.parentTable.code}"/>
			<#assign ctIndex="${ctCode?index_of('_')}"/>
			<#assign ctLength="${ctCode?length}"/>
			<#assign joinTableName="${ctCode?substring(ctIndex?number+1,ctLength?number)?uncap_first}"/>
	<resultMap id="${entityName?uncap_first}${joinTableName?cap_first}InnerResult" type="${entityName?uncap_first}" extends="${entityName?uncap_first}">
		<association property="${joinTableName?uncap_first}" resultMap="${joinTableName?uncap_first}"/>
	</resultMap>
		</#if>
	</#list>

	<sql id="${entityName?uncap_first}_select">
		select
	</sql>
	<sql id="${entityName?uncap_first}_select_count">
		select count(*)
	</sql>
	<#if idIsExist == 'true' >
	<sql id="${entityName?uncap_first}_select_id">
		select ${entityName?uncap_first}.id
	</sql>
	</#if>
	<sql id="${entityName?uncap_first}_select_distinct">
		select distinct
	</sql>
	
	<sql id="${entityName?uncap_first}_select_fields">
        select
		<#list table.columns as column>
		<#if column_has_next>
			${entityName?uncap_first}.${column.originalCode} ${entityName?uncap_first}_${column.code?uncap_first},
		</#if>
		<#if !column_has_next>
			${entityName?uncap_first}.${column.originalCode} ${entityName?uncap_first}_${column.code?uncap_first}
		</#if>
		</#list>
	</sql>

    <sql id="${entityName?uncap_first}_select_distinct_fields">
        select distinct
		<#list table.columns as column>
			<#if column_has_next>
            ${entityName?uncap_first}.${column.originalCode} ${entityName?uncap_first}_${column.code?uncap_first},
			</#if>
			<#if !column_has_next>
            ${entityName?uncap_first}.${column.originalCode} ${entityName?uncap_first}_${column.code?uncap_first}
			</#if>
		</#list>
    </sql>

	<#assign isRef="false"/>
	<#list references as reference>
		<#if table.code == reference.childTable.code>
			<#assign isRef="true"/>
		</#if>
		<#if table.code == reference.parentTable.code>
			<#assign isRef="true"/>
		</#if>
	</#list>
	<#if isRef == 'true'>
	<sql id="${entityName?uncap_first}_select_ref_fields">
		<#list table.columns as column>
			<#if column_has_next>
		${entityName?uncap_first}.${column.originalCode} ${entityName?uncap_first}_${column.code?uncap_first},
			</#if>
			<#if !column_has_next>
		${entityName?uncap_first}.${column.originalCode} ${entityName?uncap_first}_${column.code?uncap_first}
			</#if>
		</#list>
	</sql>
	</#if>

	<sql id="${entityName?uncap_first}_from">
		from ${table.originalCode} ${entityName?uncap_first}
	</sql>

	<sql id="${entityName?uncap_first}_inner_all">
	<#list references as reference>
	<#if table.code == reference.childTable.code>
		<#assign ctOriginalCode="${reference.parentTable.originalCode}"/>
		<#assign ctCode="${reference.parentTable.code}"/>
		<#assign ctIndex="${ctCode?index_of('_')}"/>
		<#assign ctLength="${ctCode?length}"/>
		<#assign parentTableCode="${ctCode?substring(ctIndex?number+1,ctLength?number)?uncap_first}"/>
		<#list reference.joins as join>
		inner join ${ctOriginalCode} ${parentTableCode} on ${parentTableCode}.${join.parentTableColumn.originalCode}=${entityName?uncap_first}.${join.childTableColumn.originalCode}
		</#list>
	</#if>
	</#list>
	</sql>

	<#list references as reference>
	<#if table.code == reference.childTable.code>
		<#assign ctOriginalCode="${reference.parentTable.originalCode}"/>
		<#assign ctCode="${reference.parentTable.code}"/>
		<#assign ctIndex="${ctCode?index_of('_')}"/>
		<#assign ctLength="${ctCode?length}"/>
		<#assign parentTableCode="${ctCode?substring(ctIndex?number+1,ctLength?number)?uncap_first}"/>
	<sql id="${entityName?uncap_first}_inner_${parentTableCode}">
		<#list reference.joins as join>
		inner join ${ctOriginalCode} ${parentTableCode} on ${parentTableCode}.${join.parentTableColumn.originalCode}=${entityName?uncap_first}.${join.childTableColumn.originalCode}
		</#list>
	</sql>
		</#if>
	</#list>

	<sql id="${entityName?uncap_first}_left_all">
	<#list references as reference>
	<#if table.code == reference.parentTable.code>
		<#assign ctOriginalCode="${reference.childTable.originalCode}"/>
		<#assign ctCode="${reference.childTable.code}"/>
		<#assign ctIndex="${ctCode?index_of('_')}"/>
		<#assign ctLength="${ctCode?length}"/>
		<#assign childTableCode="${ctCode?substring(ctIndex?number+1,ctLength?number)?uncap_first}"/>
		<#list reference.joins as join>
		left join ${ctOriginalCode} ${childTableCode} on ${childTableCode}.${join.childTableColumn.originalCode}=${entityName?uncap_first}.${join.parentTableColumn.originalCode}
		</#list>
	</#if>
	</#list>
	</sql>

	<#list references as reference>
	<#if table.code == reference.parentTable.code>
	<#assign ctOriginalCode="${reference.childTable.originalCode}"/>
	<#assign ctCode="${reference.childTable.code}"/>
	<#assign ctIndex="${ctCode?index_of('_')}"/>
	<#assign ctLength="${ctCode?length}"/>
	<#assign childTableCode="${ctCode?substring(ctIndex?number+1,ctLength?number)?uncap_first}"/>
	<sql id="${entityName?uncap_first}_left_${childTableCode}">
	<#list reference.joins as join>
		left join ${ctOriginalCode} ${childTableCode} on ${childTableCode}.${join.childTableColumn.originalCode}=${entityName?uncap_first}.${join.parentTableColumn.originalCode}
	</#list>
	</sql>
	</#if>
	</#list>

	<sql id="${entityName?uncap_first}_where">
		<where>
			<trim prefixOverrides="and | or">
			<#list table.columns as column >
				<#if column.dataType?contains('bigint')>
					<#assign jdbcType="BIGINT"/>
				<#elseif column.dataType?contains('long')>
					<#assign jdbcType="BIGINT"/>
				<#elseif column.dataType?contains('float')>
					<#assign jdbcType="FLOAT"/>
				<#elseif column.dataType?contains('smallint')>
					<#assign jdbcType="SMALLINT"/>
				<#elseif column.dataType?contains('short')>
					<#assign jdbcType="SMALLINT"/>
				<#elseif column.dataType?contains('boolean')>
					<#assign jdbcType="BOOLEAN"/>
				<#elseif column.dataType?contains('double')>
					<#assign jdbcType="DOUBLE"/>
				<#elseif column.dataType?contains('decimal')>
					<#assign jdbcType="DECIMAL"/>
				<#elseif column.dataType?contains('integer')>
					<#assign jdbcType="INTEGER"/>
				<#elseif column.dataType?contains('tinyint')>
					<#assign jdbcType="TINYINT"/>
				<#elseif column.dataType?contains('byte')>
					<#assign jdbcType="TINYINT"/>
				<#elseif column.dataType?contains('int')>
					<#assign jdbcType="INTEGER"/>
				<#elseif column.dataType?contains('date')>
					<#assign jdbcType="DATE"/>
				<#elseif column.dataType?contains('time')>
					<#assign jdbcType="TIME"/>
				<#elseif column.dataType?contains('datetime')>
					<#assign jdbcType="TIMESTAMP"/>
				<#elseif column.dataType?contains('timestamp')>
					<#assign jdbcType="TIMESTAMP"/>
				<#elseif column.dataType?contains('clob')>
					<#assign jdbcType="CLOB"/>
				<#elseif column.dataType?contains('blob')>
					<#assign jdbcType="BLOB"/>
				<#else>
					<#assign jdbcType="VARCHAR"/>
				</#if>
				<#if column.dataType?contains('varchar')>
				<if test="${column.code?uncap_first} != null and ${column.code?uncap_first} != ''">and ${entityName?uncap_first}.${column.originalCode}=<#noparse>#{</#noparse>${column.code?uncap_first},jdbcType=${jdbcType?upper_case}<#noparse>}</#noparse></if>
                <if test="${column.code?uncap_first}AsLike != null and ${column.code?uncap_first}AsLike != ''">and ${entityName?uncap_first}.${column.originalCode} like concat('%',<#noparse>#{</#noparse>${column.code?uncap_first}AsLike,jdbcType=${jdbcType?upper_case}<#noparse>}</#noparse>,'%')</if>
				<#else>
                <if test="${column.code?uncap_first} != null">and ${entityName?uncap_first}.${column.originalCode}=<#noparse>#{</#noparse>${column.code?uncap_first},jdbcType=${jdbcType?upper_case}<#noparse>}</#noparse></if>
					<#if column.dataType?contains('date') || column.dataType?contains('time')>
                <if test="${column.code?uncap_first}Operator != null and ${column.code?uncap_first}Expression != null">and ${entityName?uncap_first}.${column.originalCode} <#noparse>${</#noparse>${column.code?uncap_first}Operator<#noparse>}</#noparse> <#noparse>${</#noparse>${column.code?uncap_first}Expression<#noparse>}</#noparse></if>
					<#else>
                <if test="${column.code?uncap_first}Operator != null and ${column.code?uncap_first}Expression != null">and ${entityName?uncap_first}.${column.originalCode} <#noparse>${</#noparse>${column.code?uncap_first}Operator<#noparse>}</#noparse> <#noparse>${</#noparse>${column.code?uncap_first}Expression<#noparse>}</#noparse></if>
					</#if>
				</#if>
			</#list>
                <if test="searchCriteria != null and searchCriteria != ''">and (<#noparse>${</#noparse>searchCriteria<#noparse>}</#noparse>)</if>
			</trim>
		</where>
	</sql>

    <sql id="${entityName?uncap_first}_delete_where">
        <where>
            <trim prefixOverrides="and">
				<#list table.columns as column >
					<#if column.dataType?contains('bigint')>
						<#assign jdbcType="BIGINT"/>
					<#elseif column.dataType?contains('long')>
						<#assign jdbcType="BIGINT"/>
					<#elseif column.dataType?contains('float')>
						<#assign jdbcType="FLOAT"/>
					<#elseif column.dataType?contains('smallint')>
						<#assign jdbcType="SMALLINT"/>
					<#elseif column.dataType?contains('short')>
						<#assign jdbcType="SMALLINT"/>
					<#elseif column.dataType?contains('boolean')>
						<#assign jdbcType="BOOLEAN"/>
					<#elseif column.dataType?contains('double')>
						<#assign jdbcType="DOUBLE"/>
					<#elseif column.dataType?contains('decimal')>
						<#assign jdbcType="DECIMAL"/>
					<#elseif column.dataType?contains('integer')>
						<#assign jdbcType="INTEGER"/>
					<#elseif column.dataType?contains('tinyint')>
						<#assign jdbcType="TINYINT"/>
					<#elseif column.dataType?contains('byte')>
						<#assign jdbcType="TINYINT"/>
					<#elseif column.dataType?contains('int')>
						<#assign jdbcType="INTEGER"/>
					<#elseif column.dataType?contains('date')>
						<#assign jdbcType="DATE"/>
					<#elseif column.dataType?contains('time')>
						<#assign jdbcType="TIME"/>
					<#elseif column.dataType?contains('datetime')>
						<#assign jdbcType="TIMESTAMP"/>
					<#elseif column.dataType?contains('timestamp')>
						<#assign jdbcType="TIMESTAMP"/>
					<#elseif column.dataType?contains('clob')>
						<#assign jdbcType="CLOB"/>
					<#elseif column.dataType?contains('blob')>
						<#assign jdbcType="BLOB"/>
					<#else>
						<#assign jdbcType="VARCHAR"/>
					</#if>
					<#if column.dataType?contains('varchar')>
                    <if test="${column.code?uncap_first} != null and ${column.code?uncap_first} != ''">and ${column.originalCode}=<#noparse>#{</#noparse>${column.code?uncap_first},jdbcType=${jdbcType?upper_case}<#noparse>}</#noparse></if>
					<#else>
                    <if test="${column.code?uncap_first} != null">and ${column.originalCode}=<#noparse>#{</#noparse>${column.code?uncap_first},jdbcType=${jdbcType?upper_case}<#noparse>}</#noparse></if>
					</#if>
				</#list>
            </trim>
        </where>
    </sql>
	
	<sql id="${entityName?uncap_first}_where_fields">
	<#list table.columns as column >
		<#if column.dataType?contains('bigint')>
			<#assign jdbcType="BIGINT"/>
		<#elseif column.dataType?contains('long')>
			<#assign jdbcType="BIGINT"/>
		<#elseif column.dataType?contains('float')>
			<#assign jdbcType="FLOAT"/>
		<#elseif column.dataType?contains('smallint')>
			<#assign jdbcType="SMALLINT"/>
		<#elseif column.dataType?contains('short')>
			<#assign jdbcType="SMALLINT"/>
		<#elseif column.dataType?contains('boolean')>
			<#assign jdbcType="BOOLEAN"/>
		<#elseif column.dataType?contains('double')>
			<#assign jdbcType="DOUBLE"/>
		<#elseif column.dataType?contains('decimal')>
			<#assign jdbcType="DECIMAL"/>
		<#elseif column.dataType?contains('integer')>
			<#assign jdbcType="INTEGER"/>
		<#elseif column.dataType?contains('tinyint')>
			<#assign jdbcType="TINYINT"/>
		<#elseif column.dataType?contains('byte')>
			<#assign jdbcType="TINYINT"/>
		<#elseif column.dataType?contains('int')>
			<#assign jdbcType="INTEGER"/>
		<#elseif column.dataType?contains('date')>
			<#assign jdbcType="DATE"/>
		<#elseif column.dataType?contains('time')>
			<#assign jdbcType="TIME"/>
		<#elseif column.dataType?contains('datetime')>
			<#assign jdbcType="TIMESTAMP"/>
		<#elseif column.dataType?contains('timestamp')>
			<#assign jdbcType="TIMESTAMP"/>
		<#elseif column.dataType?contains('clob')>
			<#assign jdbcType="CLOB"/>
		<#elseif column.dataType?contains('blob')>
			<#assign jdbcType="BLOB"/>
		<#else>
			<#assign jdbcType="VARCHAR"/>
		</#if>
		<#if column.dataType?contains('varchar')>
        <if test="${column.code?uncap_first} != null and ${column.code?uncap_first} != ''">and ${entityName?uncap_first}.${column.originalCode}=<#noparse>#{</#noparse>${column.code?uncap_first},jdbcType=${jdbcType?upper_case}<#noparse>}</#noparse></if>
		<#else>
        <if test="${column.code?uncap_first} != null">and ${entityName?uncap_first}.${column.originalCode}=<#noparse>#{</#noparse>${column.code?uncap_first},jdbcType=${jdbcType?upper_case}<#noparse>}</#noparse></if>
		</#if>
		</#list>
	</sql>

	<sql id="${entityName?uncap_first}_update_where">
		<where>
			<trim prefixOverrides="and">
			<#list table.columns as column >
				<#if column.dataType?contains('bigint')>
					<#assign jdbcType="BIGINT"/>
				<#elseif column.dataType?contains('long')>
					<#assign jdbcType="BIGINT"/>
				<#elseif column.dataType?contains('float')>
					<#assign jdbcType="FLOAT"/>
				<#elseif column.dataType?contains('smallint')>
					<#assign jdbcType="SMALLINT"/>
				<#elseif column.dataType?contains('short')>
					<#assign jdbcType="SMALLINT"/>
				<#elseif column.dataType?contains('boolean')>
					<#assign jdbcType="BOOLEAN"/>
				<#elseif column.dataType?contains('double')>
					<#assign jdbcType="DOUBLE"/>
				<#elseif column.dataType?contains('decimal')>
					<#assign jdbcType="DECIMAL"/>
				<#elseif column.dataType?contains('integer')>
					<#assign jdbcType="INTEGER"/>
				<#elseif column.dataType?contains('tinyint')>
					<#assign jdbcType="TINYINT"/>
				<#elseif column.dataType?contains('byte')>
					<#assign jdbcType="TINYINT"/>
				<#elseif column.dataType?contains('int')>
					<#assign jdbcType="INTEGER"/>
				<#elseif column.dataType?contains('date')>
					<#assign jdbcType="DATE"/>
				<#elseif column.dataType?contains('time')>
					<#assign jdbcType="TIME"/>
				<#elseif column.dataType?contains('datetime')>
					<#assign jdbcType="TIMESTAMP"/>
				<#elseif column.dataType?contains('timestamp')>
					<#assign jdbcType="TIMESTAMP"/>
				<#elseif column.dataType?contains('clob')>
					<#assign jdbcType="CLOB"/>
				<#elseif column.dataType?contains('blob')>
					<#assign jdbcType="BLOB"/>
				<#else>
					<#assign jdbcType="VARCHAR"/>
				</#if>
				<#if column.dataType?contains('varchar')>
				<if test="${column.code?uncap_first} != null and ${column.code?uncap_first} != ''">and ${column.originalCode}=<#noparse>#{</#noparse>${column.code?uncap_first},jdbcType=${jdbcType?upper_case}<#noparse>}</#noparse></if>
				<#else>
				<if test="${column.code?uncap_first} != null">and ${column.originalCode}=<#noparse>#{</#noparse>${column.code?uncap_first},jdbcType=${jdbcType?upper_case}<#noparse>}</#noparse></if>
				</#if>
			</#list>
			</trim>
		</where>
	</sql>

	<sql id="${entityName?uncap_first}_update_where_fields">
		<where>
			<trim prefixOverrides="and">
			<#list table.columns as column >
				<#if column.dataType?contains('bigint')>
					<#assign jdbcType="BIGINT"/>
				<#elseif column.dataType?contains('long')>
					<#assign jdbcType="BIGINT"/>
				<#elseif column.dataType?contains('float')>
					<#assign jdbcType="FLOAT"/>
				<#elseif column.dataType?contains('smallint')>
					<#assign jdbcType="SMALLINT"/>
				<#elseif column.dataType?contains('short')>
					<#assign jdbcType="SMALLINT"/>
				<#elseif column.dataType?contains('boolean')>
					<#assign jdbcType="BOOLEAN"/>
				<#elseif column.dataType?contains('double')>
					<#assign jdbcType="DOUBLE"/>
				<#elseif column.dataType?contains('decimal')>
					<#assign jdbcType="DECIMAL"/>
				<#elseif column.dataType?contains('integer')>
					<#assign jdbcType="INTEGER"/>
				<#elseif column.dataType?contains('tinyint')>
					<#assign jdbcType="TINYINT"/>
				<#elseif column.dataType?contains('byte')>
					<#assign jdbcType="TINYINT"/>
				<#elseif column.dataType?contains('int')>
					<#assign jdbcType="INTEGER"/>
				<#elseif column.dataType?contains('date')>
					<#assign jdbcType="DATE"/>
				<#elseif column.dataType?contains('time')>
					<#assign jdbcType="TIME"/>
				<#elseif column.dataType?contains('datetime')>
					<#assign jdbcType="TIMESTAMP"/>
				<#elseif column.dataType?contains('timestamp')>
					<#assign jdbcType="TIMESTAMP"/>
				<#elseif column.dataType?contains('clob')>
					<#assign jdbcType="CLOB"/>
				<#elseif column.dataType?contains('blob')>
					<#assign jdbcType="BLOB"/>
				<#else>
					<#assign jdbcType="VARCHAR"/>
				</#if>
				<#if column.dataType?contains('varchar')>
                    <if test="${column.code?uncap_first} != null and ${column.code?uncap_first} != ''">and ${column.originalCode}=<#noparse>#{</#noparse>${column.code?uncap_first},jdbcType=${jdbcType?upper_case}<#noparse>}</#noparse></if>
				<#else>
                    <if test="${column.code?uncap_first} != null">and ${column.originalCode}=<#noparse>#{</#noparse>${column.code?uncap_first},jdbcType=${jdbcType?upper_case}<#noparse>}</#noparse></if>
				</#if>
				</#list>
			</trim>
		</where>
	</sql>

	<#if idIsExist == 'true' >
	<sql id="${entityName?uncap_first}_where_id">
		<#list table.columns as column >
		<#if column.dataType?contains('bigint')>
		<#if column.code?lower_case == 'id'>
			<#if column.dataType?contains('bigint')>
				<#assign jdbcType="BIGINT"/>
			<#elseif column.dataType?contains('long')>
				<#assign jdbcType="BIGINT"/>
			<#elseif column.dataType?contains('float')>
				<#assign jdbcType="FLOAT"/>
			<#elseif column.dataType?contains('smallint')>
				<#assign jdbcType="SMALLINT"/>
			<#elseif column.dataType?contains('short')>
				<#assign jdbcType="SMALLINT"/>
			<#elseif column.dataType?contains('boolean')>
				<#assign jdbcType="BOOLEAN"/>
			<#elseif column.dataType?contains('double')>
				<#assign jdbcType="DOUBLE"/>
			<#elseif column.dataType?contains('decimal')>
				<#assign jdbcType="DECIMAL"/>
			<#elseif column.dataType?contains('integer')>
				<#assign jdbcType="INTEGER"/>
			<#elseif column.dataType?contains('tinyint')>
				<#assign jdbcType="TINYINT"/>
			<#elseif column.dataType?contains('byte')>
				<#assign jdbcType="TINYINT"/>
			<#elseif column.dataType?contains('int')>
				<#assign jdbcType="INTEGER"/>
			<#elseif column.dataType?contains('date')>
				<#assign jdbcType="DATE"/>
			<#elseif column.dataType?contains('time')>
				<#assign jdbcType="TIME"/>
			<#elseif column.dataType?contains('datetime')>
				<#assign jdbcType="TIMESTAMP"/>
			<#elseif column.dataType?contains('timestamp')>
				<#assign jdbcType="TIMESTAMP"/>
			<#elseif column.dataType?contains('clob')>
				<#assign jdbcType="CLOB"/>
			<#elseif column.dataType?contains('blob')>
				<#assign jdbcType="BLOB"/>
			<#else>
				<#assign jdbcType="VARCHAR"/>
			</#if>
		where ${entityName?uncap_first}.id=<#noparse>#{</#noparse>id,jdbcType=${jdbcType?upper_case}<#noparse>}</#noparse>
		</#if>
		</#if>
		</#list>
	</sql>

    <sql id="${entityName?uncap_first}_delete_where_id">
		<#list table.columns as column >
			<#if column.dataType?contains('bigint')>
				<#if column.code?lower_case == 'id'>
					<#if column.dataType?contains('bigint')>
						<#assign jdbcType="BIGINT"/>
					<#elseif column.dataType?contains('long')>
						<#assign jdbcType="BIGINT"/>
					<#elseif column.dataType?contains('float')>
						<#assign jdbcType="FLOAT"/>
					<#elseif column.dataType?contains('smallint')>
						<#assign jdbcType="SMALLINT"/>
					<#elseif column.dataType?contains('short')>
						<#assign jdbcType="SMALLINT"/>
					<#elseif column.dataType?contains('boolean')>
						<#assign jdbcType="BOOLEAN"/>
					<#elseif column.dataType?contains('double')>
						<#assign jdbcType="DOUBLE"/>
					<#elseif column.dataType?contains('decimal')>
						<#assign jdbcType="DECIMAL"/>
					<#elseif column.dataType?contains('integer')>
						<#assign jdbcType="INTEGER"/>
					<#elseif column.dataType?contains('tinyint')>
						<#assign jdbcType="TINYINT"/>
					<#elseif column.dataType?contains('byte')>
						<#assign jdbcType="TINYINT"/>
					<#elseif column.dataType?contains('int')>
						<#assign jdbcType="INTEGER"/>
					<#elseif column.dataType?contains('date')>
						<#assign jdbcType="DATE"/>
					<#elseif column.dataType?contains('time')>
						<#assign jdbcType="TIME"/>
					<#elseif column.dataType?contains('datetime')>
						<#assign jdbcType="TIMESTAMP"/>
					<#elseif column.dataType?contains('timestamp')>
						<#assign jdbcType="TIMESTAMP"/>
					<#elseif column.dataType?contains('clob')>
						<#assign jdbcType="CLOB"/>
					<#elseif column.dataType?contains('blob')>
						<#assign jdbcType="BLOB"/>
					<#else>
						<#assign jdbcType="VARCHAR"/>
					</#if>
        where id=<#noparse>#{</#noparse>id,jdbcType=${jdbcType?upper_case}<#noparse>}</#noparse>
				</#if>
			</#if>
		</#list>
    </sql>

	<sql id="${entityName?uncap_first}_update_where_id">
		<#list table.columns as column >
		<#if column.dataType?contains('bigint')>
		<#if column.code?lower_case == 'id'>
			<#if column.dataType?contains('bigint')>
				<#assign jdbcType="BIGINT"/>
			<#elseif column.dataType?contains('long')>
				<#assign jdbcType="BIGINT"/>
			<#elseif column.dataType?contains('float')>
				<#assign jdbcType="FLOAT"/>
			<#elseif column.dataType?contains('smallint')>
				<#assign jdbcType="SMALLINT"/>
			<#elseif column.dataType?contains('short')>
				<#assign jdbcType="SMALLINT"/>
			<#elseif column.dataType?contains('boolean')>
				<#assign jdbcType="BOOLEAN"/>
			<#elseif column.dataType?contains('double')>
				<#assign jdbcType="DOUBLE"/>
			<#elseif column.dataType?contains('decimal')>
				<#assign jdbcType="DECIMAL"/>
			<#elseif column.dataType?contains('integer')>
				<#assign jdbcType="INTEGER"/>
			<#elseif column.dataType?contains('tinyint')>
				<#assign jdbcType="TINYINT"/>
			<#elseif column.dataType?contains('byte')>
				<#assign jdbcType="TINYINT"/>
			<#elseif column.dataType?contains('int')>
				<#assign jdbcType="INTEGER"/>
			<#elseif column.dataType?contains('date')>
				<#assign jdbcType="DATE"/>
			<#elseif column.dataType?contains('time')>
				<#assign jdbcType="TIME"/>
			<#elseif column.dataType?contains('datetime')>
				<#assign jdbcType="TIMESTAMP"/>
			<#elseif column.dataType?contains('timestamp')>
				<#assign jdbcType="TIMESTAMP"/>
			<#elseif column.dataType?contains('clob')>
				<#assign jdbcType="CLOB"/>
			<#elseif column.dataType?contains('blob')>
				<#assign jdbcType="BLOB"/>
			<#else>
				<#assign jdbcType="VARCHAR"/>
			</#if>
		where id=<#noparse>#{</#noparse>id,jdbcType=${jdbcType?upper_case}<#noparse>}</#noparse>
		</#if>
		</#if>
		</#list>
	</sql>
	</#if>

	<sql id="${entityName?uncap_first}_where_reference_id">
		<where>
			<trim prefixOverrides="and">
			<#list table.columns as column >
			<#if column.dataType?contains('bigint')>
			<#if column.code?lower_case != 'id'>
			<#if column.code?lower_case?contains('id')>
				<#if column.dataType?contains('bigint')>
					<#assign jdbcType="BIGINT"/>
				<#elseif column.dataType?contains('long')>
					<#assign jdbcType="BIGINT"/>
				<#elseif column.dataType?contains('float')>
					<#assign jdbcType="FLOAT"/>
				<#elseif column.dataType?contains('smallint')>
					<#assign jdbcType="SMALLINT"/>
				<#elseif column.dataType?contains('short')>
					<#assign jdbcType="SMALLINT"/>
				<#elseif column.dataType?contains('boolean')>
					<#assign jdbcType="BOOLEAN"/>
				<#elseif column.dataType?contains('double')>
					<#assign jdbcType="DOUBLE"/>
				<#elseif column.dataType?contains('decimal')>
					<#assign jdbcType="DECIMAL"/>
				<#elseif column.dataType?contains('integer')>
					<#assign jdbcType="INTEGER"/>
				<#elseif column.dataType?contains('tinyint')>
					<#assign jdbcType="TINYINT"/>
				<#elseif column.dataType?contains('byte')>
					<#assign jdbcType="TINYINT"/>
				<#elseif column.dataType?contains('int')>
					<#assign jdbcType="INTEGER"/>
				<#elseif column.dataType?contains('date')>
					<#assign jdbcType="DATE"/>
				<#elseif column.dataType?contains('time')>
					<#assign jdbcType="TIME"/>
				<#elseif column.dataType?contains('datetime')>
					<#assign jdbcType="TIMESTAMP"/>
				<#elseif column.dataType?contains('timestamp')>
					<#assign jdbcType="TIMESTAMP"/>
				<#elseif column.dataType?contains('clob')>
					<#assign jdbcType="CLOB"/>
				<#elseif column.dataType?contains('blob')>
					<#assign jdbcType="BLOB"/>
				<#else>
					<#assign jdbcType="VARCHAR"/>
				</#if>
				<#if column.dataType?contains('varchar')>
				<if test="${column.code?uncap_first} != null and ${column.code?uncap_first} != ''">and ${entityName?uncap_first}.${column.originalCode}=<#noparse>#{</#noparse>${column.code?uncap_first},jdbcType=${jdbcType?upper_case}<#noparse>}</#noparse></if>
				<#else>
                <if test="${column.code?uncap_first} != null">and ${entityName?uncap_first}.${column.originalCode}=<#noparse>#{</#noparse>${column.code?uncap_first},jdbcType=${jdbcType?upper_case}<#noparse>}</#noparse></if>
				</#if>
			</#if>
			</#if>
			</#if>
			</#list>
			</trim>
		</where>
	</sql>

	<#if idIsExist == 'true' >
	<sql id="${entityName?uncap_first}_where_ids">
		<if test="ids != null">
			where ${entityName?uncap_first}.id in
			<trim prefixOverrides=",">
				<foreach collection="ids" index="index" item="item" open="(" separator="," close=")"> 
            	<#noparse>${item}</#noparse>
        		</foreach>
			</trim>
		</if>
	</sql>
	</#if>

	<sql id="${entityName?uncap_first}_order">
        <if test="sorder != null">
            order by <#noparse>${sorder}</#noparse>
        </if>
        <if test="dorder != null or aorder != null">
			order by
			<trim prefixOverrides=",">
				<if test="dorder != null">
				<foreach collection="dorder" index="index" item="item" separator=",">
				${entityName?uncap_first}.<#noparse>${item}</#noparse> desc
        		</foreach>
        		</if>  
        		<if test="aorder != null">
				<foreach collection="aorder" index="index" item="item" separator=",">
				${entityName?uncap_first}.<#noparse>${item}</#noparse> asc
        		</foreach>
        		</if>  
			</trim>
		</if>  
	</sql>

	<sql id="${entityName?uncap_first}_group">
		<if test="sgroup != null">
			group by
			<trim prefixOverrides=",">
				<foreach collection="sgroup" index="index" item="item" separator=",">
				${entityName?uncap_first}.<#noparse>${item}</#noparse>
        		</foreach>
			</trim>
		</if>
	</sql>

	<sql id="${entityName?uncap_first}_limit">
		limit <#noparse>${start}</#noparse>,<#noparse>${offset}</#noparse>
	</sql>

	<sql id="${entityName?uncap_first}_update">
		update ${table.originalCode}
	</sql>

	<sql id="${entityName?uncap_first}_update_set">
		<set>
			<trim prefixOverrides=",">
			<#list table.columns as column >
			<#if column.code != 'id'>
				<#assign columnName ="${column.code?uncap_first}"/>
				<#if columnName?contains("modifyTime")>
				,${column.originalCode}=now()
				<#else>
					<#if column.dataType?contains('bigint')>
						<#assign jdbcType="BIGINT"/>
					<#elseif column.dataType?contains('long')>
						<#assign jdbcType="BIGINT"/>
					<#elseif column.dataType?contains('float')>
						<#assign jdbcType="FLOAT"/>
					<#elseif column.dataType?contains('smallint')>
						<#assign jdbcType="SMALLINT"/>
					<#elseif column.dataType?contains('short')>
						<#assign jdbcType="SMALLINT"/>
					<#elseif column.dataType?contains('boolean')>
						<#assign jdbcType="BOOLEAN"/>
					<#elseif column.dataType?contains('double')>
						<#assign jdbcType="DOUBLE"/>
					<#elseif column.dataType?contains('decimal')>
						<#assign jdbcType="DECIMAL"/>
					<#elseif column.dataType?contains('integer')>
						<#assign jdbcType="INTEGER"/>
					<#elseif column.dataType?contains('tinyint')>
						<#assign jdbcType="TINYINT"/>
					<#elseif column.dataType?contains('byte')>
						<#assign jdbcType="TINYINT"/>
					<#elseif column.dataType?contains('int')>
						<#assign jdbcType="INTEGER"/>
					<#elseif column.dataType?contains('date')>
						<#assign jdbcType="DATE"/>
					<#elseif column.dataType?contains('time')>
						<#assign jdbcType="TIME"/>
					<#elseif column.dataType?contains('datetime')>
						<#assign jdbcType="TIMESTAMP"/>
					<#elseif column.dataType?contains('timestamp')>
						<#assign jdbcType="TIMESTAMP"/>
					<#elseif column.dataType?contains('clob')>
						<#assign jdbcType="CLOB"/>
					<#elseif column.dataType?contains('blob')>
						<#assign jdbcType="BLOB"/>
					<#else>
						<#assign jdbcType="VARCHAR"/>
					</#if>
				<#if column.dataType?contains('varchar')>
				<if test="${column.code?uncap_first} != null and ${column.code?uncap_first} != ''">,${column.originalCode}=<#noparse>#{</#noparse>${column.code?uncap_first},jdbcType=${jdbcType?upper_case}<#noparse>}</#noparse></if>
				<#else>
                <if test="${column.code?uncap_first} != null">,${column.originalCode}=<#noparse>#{</#noparse>${column.code?uncap_first},jdbcType=${jdbcType?upper_case}<#noparse>}</#noparse></if>
				</#if>
				</#if>
			</#if>
			</#list>
			</trim>
		</set>
	</sql>

	<sql id="${entityName?uncap_first}_update_set_null">
		<set>
			<trim prefixOverrides=",">
			<#list table.columns as column >
				<#if column.code != 'id'>
				<#if column.dataType?contains('varchar')>
				<if test="${column.code?uncap_first} != null and  ${column.code?uncap_first} != null">,${column.originalCode}=NULL</if>
				<#else>
                <if test="${column.code?uncap_first} != null">,${column.originalCode}=NULL</if>
				</#if>
				</#if>
			</#list>
			</trim>
		</set>
	</sql>

	<sql id="${entityName?uncap_first}_insert">
		insert into ${table.originalCode}
			<trim prefix="(" prefixOverrides="," suffix=")">
		<#list table.columns as column >
			<#assign columnName ="${column.code?uncap_first}"/>
			<#if columnName?contains("createTime")>
                ,${column.originalCode}
			<#elseif columnName?contains("modifyTime")>
                ,${column.originalCode}
			<#else>
				<#if column.dataType?contains('varchar')>
                <if test="${column.code?uncap_first} != null and ${column.code?uncap_first} != ''">,${column.originalCode}</if>
				<#else>
                <if test="${column.code?uncap_first} != null">,${column.originalCode}</if>
				</#if>
			</#if>
		</#list>
			</trim>
		values
			<trim prefix="(" prefixOverrides="," suffix=")">
		<#list table.columns as column >
				<#assign columnName ="${column.code?uncap_first}"/>
				<#if columnName?contains("createTime")>
                ,now()
				<#elseif columnName?contains("modifyTime")>
				,now()
				<#else>
					<#if column.dataType?contains('bigint')>
						<#assign jdbcType="BIGINT"/>
					<#elseif column.dataType?contains('long')>
						<#assign jdbcType="BIGINT"/>
					<#elseif column.dataType?contains('float')>
						<#assign jdbcType="FLOAT"/>
					<#elseif column.dataType?contains('smallint')>
						<#assign jdbcType="SMALLINT"/>
					<#elseif column.dataType?contains('short')>
						<#assign jdbcType="SMALLINT"/>
					<#elseif column.dataType?contains('boolean')>
						<#assign jdbcType="BOOLEAN"/>
					<#elseif column.dataType?contains('double')>
						<#assign jdbcType="DOUBLE"/>
					<#elseif column.dataType?contains('decimal')>
						<#assign jdbcType="DECIMAL"/>
					<#elseif column.dataType?contains('integer')>
						<#assign jdbcType="INTEGER"/>
					<#elseif column.dataType?contains('tinyint')>
						<#assign jdbcType="TINYINT"/>
					<#elseif column.dataType?contains('byte')>
						<#assign jdbcType="TINYINT"/>
					<#elseif column.dataType?contains('int')>
						<#assign jdbcType="INTEGER"/>
					<#elseif column.dataType?contains('date')>
						<#assign jdbcType="DATE"/>
					<#elseif column.dataType?contains('time')>
						<#assign jdbcType="TIME"/>
					<#elseif column.dataType?contains('datetime')>
						<#assign jdbcType="TIMESTAMP"/>
					<#elseif column.dataType?contains('timestamp')>
						<#assign jdbcType="TIMESTAMP"/>
					<#elseif column.dataType?contains('clob')>
						<#assign jdbcType="CLOB"/>
					<#elseif column.dataType?contains('blob')>
						<#assign jdbcType="BLOB"/>
					<#else>
						<#assign jdbcType="VARCHAR"/>
					</#if>
				<#if column.dataType?contains('varchar')>
				<if test="${column.code?uncap_first} != null and ${column.code?uncap_first} != ''">,<#noparse>#{</#noparse>${column.code?uncap_first},jdbcType=${jdbcType?upper_case}<#noparse>}</#noparse></if>
				<#else>
                <if test="${column.code?uncap_first} != null">,<#noparse>#{</#noparse>${column.code?uncap_first},jdbcType=${jdbcType?upper_case}<#noparse>}</#noparse></if>
				</#if>
				</#if>
		</#list>
			</trim>
	</sql>

	<sql id="${entityName?uncap_first}_delete">
		delete from ${table.originalCode}
	</sql>

</#list>

<#assign idIsExist="false"/>
<#list columns as column>
<#assign property="result"/>
<#if column.code?lower_case == 'id' >
	<#assign property="id"/>
	<#assign idIsExist="true"/>
</#if>
</#list>	
<#assign idx="${table.code?index_of('_')}"/>
<#assign len="${table.code?length}"/>
<#assign entityName="${table.code?substring(idx?number+1,len?number)}"/>
<#assign tableAliasCode="${entityName?uncap_first}"/>
	<insert id="save" parameterMap="${tableAliasCode}" flushCache="true">
		<include refid="${tableAliasCode}_insert"/>
<#--		<selectKey keyProperty="id" resultType="Long">-->
<#--			select LAST_INSERT_ID() as id-->
<#--		</selectKey>-->
	</insert>
	
	<#if idIsExist=='true' >
	<update id="updateById" parameterMap="${tableAliasCode}" flushCache="true">
		<include refid="${tableAliasCode}_update"/>
		<include refid="${tableAliasCode}_update_set"/>
		<include refid="${tableAliasCode}_update_where_id"/>
	</update>
	</#if>

    <update id="update" parameterMap="${tableAliasCode}" flushCache="true">
        <include refid="${tableAliasCode}_update"/>
        <include refid="${tableAliasCode}_update_set"/>
        <include refid="${tableAliasCode}_update_where"/>
    </update>

	<delete id="removeCriteria" parameterMap="${tableAliasCode}" flushCache="true">
		<include refid="${tableAliasCode}_delete"/>
		<include refid="${tableAliasCode}_delete_where"/>
	</delete>
	
	<#if idIsExist=='true' >
	<delete id="removeById" parameterType="java.lang.Long" flushCache="true">
		<include refid="${tableAliasCode}_delete"/>
		<include refid="${tableAliasCode}_delete_where_id"/>
	</delete>

	<select id="listIdsCriteria" resultType="java.lang.Long" parameterMap="${tableAliasCode}" useCache="false">
		<include refid="${tableAliasCode}_select_id"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${tableAliasCode}_where"/>
	</select>
	</#if>
	
	<delete id="removeAll" parameterMap="${tableAliasCode}" flushCache="true">
		<include refid="${tableAliasCode}_delete"/>
	</delete>

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
	<#if idIsExist == 'true' >
	<select id="listInIds${join?cap_first}${joinTableName?cap_first}" resultMap="${entityName?uncap_first}${joinTableName?cap_first}LeftResult" parameterMap="${tableAliasCode}" useCache="false">
			<include refid="${tableAliasCode}_select_fields"/>,
			<include refid="${joinTableName}_select_ref_fields"/>
			<include refid="${tableAliasCode}_from"/>
			<include refid="${entityName?uncap_first}_${join}_${joinTableName}"/>
			<include refid="${tableAliasCode}_where_ids"/>
	</select>
	</#if>

	<select id="listCriteria${join?cap_first}${joinTableName?cap_first}" resultMap="${entityName?uncap_first}${joinTableName?cap_first}LeftResult" parameterMap="${tableAliasCode}" useCache="false">
		<include refid="${tableAliasCode}_select_fields"/>,
		<include refid="${joinTableName}_select_ref_fields"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${entityName?uncap_first}_${join}_${joinTableName}"/>
		<include refid="${tableAliasCode}_where"/>
		<include refid="${tableAliasCode}_order"/>
		<include refid="${tableAliasCode}_group"/>
	</select>

	<select id="listAll${join?cap_first}${joinTableName?cap_first}" resultMap="${entityName?uncap_first}${joinTableName?cap_first}LeftResult" useCache="false">
		<include refid="${tableAliasCode}_select_fields"/>,
		<include refid="${joinTableName}_select_ref_fields"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${entityName?uncap_first}_${join}_${joinTableName}"/>
	</select>

	<#if idIsExist == 'true' >
	<select id="getOneById${join?cap_first}${joinTableName?cap_first}" resultMap="${entityName?uncap_first}${joinTableName?cap_first}LeftResult" parameterType="java.lang.Long" useCache="false">
		<include refid="${tableAliasCode}_select_fields"/>,
		<include refid="${joinTableName}_select_ref_fields"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${entityName?uncap_first}_${join}_${joinTableName}"/>
		<include refid="${tableAliasCode}_where_id"/>
	</select>
	</#if>

	<select id="getOneCriteria${join?cap_first}${joinTableName?cap_first}" resultMap="${entityName?uncap_first}${joinTableName?cap_first}LeftResult" parameterMap="${tableAliasCode}" useCache="false">
		<include refid="${tableAliasCode}_select_fields"/>,
		<include refid="${joinTableName}_select_ref_fields"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${entityName?uncap_first}_${join}_${joinTableName}"/>
		<include refid="${tableAliasCode}_where"/>
	</select>

	<select id="listPaginated${join?cap_first}${joinTableName?cap_first}" resultMap="${entityName?uncap_first}${joinTableName?cap_first}LeftResult" parameterMap="${tableAliasCode}" useCache="false">
		<include refid="${tableAliasCode}_select_fields"/>,
		<include refid="${joinTableName}_select_ref_fields"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${entityName?uncap_first}_${join}_${joinTableName}"/>
		<include refid="${tableAliasCode}_where"/>
		<include refid="${tableAliasCode}_order"/>
		<include refid="${tableAliasCode}_group"/>
	</select>
	</#if>
	<#if table.code == reference.childTable.code>
		<#assign join="inner"/>
		<#assign isJoin="true"/>
		<#assign ctOriginalCode="${reference.parentTable.originalCode}"/>
		<#assign ctCode="${reference.parentTable.code}"/>
		<#assign ctIndex="${ctCode?index_of('_')}"/>
		<#assign ctLength="${ctCode?length}"/>
		<#assign joinTableName="${ctCode?substring(ctIndex?number+1,ctLength?number)?uncap_first}"/>
	<#if idIsExist == 'true' >
	<select id="listInIds${join?cap_first}${joinTableName?cap_first}" resultMap="${entityName?uncap_first}${joinTableName?cap_first}InnerResult" parameterMap="${tableAliasCode}" useCache="false">
		<include refid="${tableAliasCode}_select_fields"/>,
		<include refid="${joinTableName}_select_ref_fields"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${entityName?uncap_first}_${join}_${joinTableName}"/>
		<include refid="${tableAliasCode}_where_ids"/>
	</select>
	</#if>

	<select id="listCriteria${join?cap_first}${joinTableName?cap_first}" resultMap="${entityName?uncap_first}${joinTableName?cap_first}InnerResult" parameterMap="${tableAliasCode}" useCache="false">
		<include refid="${tableAliasCode}_select_fields"/>,
		<include refid="${joinTableName}_select_ref_fields"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${entityName?uncap_first}_${join}_${joinTableName}"/>
		<include refid="${tableAliasCode}_where"/>
		<include refid="${tableAliasCode}_order"/>
		<include refid="${tableAliasCode}_group"/>
	</select>

	<select id="listAll${join?cap_first}${joinTableName?cap_first}" resultMap="${entityName?uncap_first}${joinTableName?cap_first}InnerResult" useCache="false">
		<include refid="${tableAliasCode}_select_fields"/>,
		<include refid="${joinTableName}_select_ref_fields"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${entityName?uncap_first}_${join}_${joinTableName}"/>
	</select>

	<#if idIsExist == 'true' >
	<select id="getOneById${join?cap_first}${joinTableName?cap_first}" resultMap="${entityName?uncap_first}${joinTableName?cap_first}InnerResult" parameterType="java.lang.Long" useCache="false">
		<include refid="${tableAliasCode}_select_fields"/>,
		<include refid="${joinTableName}_select_ref_fields"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${entityName?uncap_first}_${join}_${joinTableName}"/>
		<include refid="${tableAliasCode}_where_id"/>
	</select>
	</#if>

	<select id="getOneCriteria${join?cap_first}${joinTableName?cap_first}" resultMap="${entityName?uncap_first}${joinTableName?cap_first}InnerResult" parameterMap="${tableAliasCode}" useCache="false">
		<include refid="${tableAliasCode}_select_fields"/>,
		<include refid="${joinTableName}_select_ref_fields"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${entityName?uncap_first}_${join}_${joinTableName}"/>
		<include refid="${tableAliasCode}_where"/>
	</select>

	<select id="listPaginated${join?cap_first}${joinTableName?cap_first}" resultMap="${entityName?uncap_first}${joinTableName?cap_first}InnerResult" parameterMap="${tableAliasCode}" useCache="false">
		<include refid="${tableAliasCode}_select_fields"/>,
		<include refid="${joinTableName}_select_ref_fields"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${entityName?uncap_first}_${join}_${joinTableName}"/>
		<include refid="${tableAliasCode}_where"/>
		<include refid="${tableAliasCode}_order"/>
		<include refid="${tableAliasCode}_group"/>
	</select>
	</#if>
</#list>

	<#if idIsExist == 'true' >
	<select id="listByInIds" resultMap="${tableAliasCode}" parameterMap="${tableAliasCode}" useCache="false">
		<include refid="${tableAliasCode}_select_fields"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${tableAliasCode}_where_ids"/>
	</select>
	</#if>
	<select id="listCriteria" resultMap="${tableAliasCode}" parameterMap="${tableAliasCode}" useCache="false">
		<include refid="${tableAliasCode}_select_fields"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${tableAliasCode}_where"/>
		<include refid="${tableAliasCode}_order"/>
		<include refid="${tableAliasCode}_group"/>
	</select>
	
	<select id="listAll" resultMap="${tableAliasCode}" useCache="false">
		<include refid="${tableAliasCode}_select_fields"/>
		<include refid="${tableAliasCode}_from"/>
	</select>
	
	<#if idIsExist == 'true' >
	<select id="getOneById" resultMap="${tableAliasCode}" parameterType="java.lang.Long" useCache="false">
		<include refid="${tableAliasCode}_select_fields"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${tableAliasCode}_where_id"/>
	</select>		
	</#if>
	
	<select id="getOneCriteria" resultMap="${tableAliasCode}" parameterMap="${tableAliasCode}" useCache="false">
		<include refid="${tableAliasCode}_select_fields"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${tableAliasCode}_where"/>
	</select>	

	<select id="listPaginated" resultMap="${tableAliasCode}" parameterMap="${tableAliasCode}" useCache="false">
		<include refid="${tableAliasCode}_select_fields"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${tableAliasCode}_where"/>
		<include refid="${tableAliasCode}_order"/>
		<include refid="${tableAliasCode}_group"/>
	</select>
	
	<select id="count" resultType="java.lang.Long" parameterMap="${tableAliasCode}">
		<include refid="${tableAliasCode}_select_count"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${tableAliasCode}_where"/>
	</select>

</mapper>