<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >

<mapper namespace="${relativeProjectPath}.${entityPath}.I${entity}Dao">

	<cache-ref namespace="${relativeProjectPath}.${entityPath}.I${entity}Dao"/>

<#assign idx="${table.code?index_of('_')}"/>
<#assign len="${table.code?length}"/>
<#assign entityName="${table.code?substring(idx?number+1,len?number)}"/>
<#assign tableAliasCode="${entityName?uncap_first}"/>
	<!--
		<include refid="${tableAliasCode}_select"/>
		<include refid="${tableAliasCode}_select_count"/>
		<include refid="${tableAliasCode}_select_distinct"/>
		<include refid="${tableAliasCode}_select_id"/>
		<include refid="${tableAliasCode}_select_fields"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${tableAliasCode}_where"/>
		<include refid="${tableAliasCode}_inner_all"/>
	<#list references as reference>
	<#if table.code == reference.childTable.code>
		<#assign ctCode="${reference.parentTable.code}"/>
		<#assign ctIndex="${ctCode?index_of('_')}"/>
		<#assign ctLength="${ctCode?length}"/>
		<#assign childTableCode="${ctCode?substring(ctIndex?number+1,ctLength?number)?uncap_first}"/>
		<include refid="${tableAliasCode}_inner_${childTableCode}"/>
		</#if>
	</#list>
		<include refid="${tableAliasCode}_left_all"/>
	<#list references as reference>
		<#if table.code == reference.parentTable.code>
		<#assign ctCode="${reference.childTable.code}"/>
		<#assign ctIndex="${ctCode?index_of('_')}"/>
		<#assign ctLength="${ctCode?length}"/>
		<#assign parentTableCode="${ctCode?substring(ctIndex?number+1,ctLength?number)?uncap_first}"/>
		<include refid="${tableAliasCode}_left_${parentTableCode}"/>
		</#if>
	</#list>
		<include refid="${tableAliasCode}_where"/>
		<include refid="${tableAliasCode}_where_id"/>
		<include refid="${tableAliasCode}_where_ids"/>
		<include refid="${tableAliasCode}_where_reference_id"/>
		<include refid="${tableAliasCode}_order"/>
		<include refid="${tableAliasCode}_group"/>
		<include refid="${tableAliasCode}_limit"/>
		<include refid="${tableAliasCode}_update"/>
		<include refid="${tableAliasCode}_update_set"/>
		<include refid="${tableAliasCode}_update_set_null"/>
		<include refid="${tableAliasCode}_insert"/>
		<include refid="${tableAliasCode}_delete"/>
		###########################################
		### **where criteria example**
		###########################################
		<#list table.columns as column >
		<if test="${column.code?uncap_first} != null and ${column.code?uncap_first} != ''">and ${entityName?uncap_first}.${column.originalCode}=<#noparse>#{</#noparse>${column.code?uncap_first},jdbcType=${column.dataType?upper_case}<#noparse>}</#noparse></if>
		</#list>
		###########################################
		### **update where criteria example**
		###########################################
		<#list table.columns as column >
		<if test="${column.code?uncap_first} != null and ${column.code?uncap_first} != ''">and ${column.originalCode}=<#noparse>#{</#noparse>${column.code?uncap_first},jdbcType=${column.dataType?upper_case}<#noparse>}</#noparse></if>
		</#list>
		###########################################
		### **set fields example**
		###########################################
		<#list table.columns as column >
		<if test="${column.code?uncap_first} != null and ${column.code?uncap_first} != ''">,${column.originalCode}=<#noparse>#{</#noparse>${column.code?uncap_first},jdbcType=${column.dataType?upper_case}<#noparse>}</#noparse></if>
		</#list>
		###########################################
		### **insert fields example**
		###########################################
		<#list table.columns as column >
		<if test="${column.code?uncap_first} != null and ${column.code?uncap_first} != ''">,${column.originalCode}</if>
		</#list>
		###########################################
		### **insert values criteria example**
		###########################################	
		<#list table.columns as column >
		<if test="${column.code?uncap_first} != null and ${column.code?uncap_first} != ''">,<#noparse>#{</#noparse>${column.code?uncap_first},jdbcType=${column.dataType?upper_case}<#noparse>}</#noparse></if>
		</#list>
		###########################################
		### **sql example**
		###########################################	
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
	<insert id="save" parameterMap="${tableAliasCode}">
		<include refid="${tableAliasCode}_insert"/>
<#--		<selectKey keyProperty="id" resultType="Long">-->
<#--			select LAST_INSERT_ID() as id-->
<#--		</selectKey>-->
	</insert>
	
	<#if idIsExist=='true' >
	<update id="updateById" parameterMap="${tableAliasCode}">
		<include refid="${tableAliasCode}_update"/>
		<include refid="${tableAliasCode}_update_set"/>
		<include refid="${tableAliasCode}_update_where_id"/>
	</update>
	</#if>

	<delete id="removeCriteria" parameterMap="${tableAliasCode}">
		<include refid="${tableAliasCode}_delete"/>
		<include refid="${tableAliasCode}_update_where"/>
	</delete>
	
	<#if idIsExist=='true' >
	<delete id="removeById" parameterType="java.lang.Long">
		<include refid="${tableAliasCode}_delete"/>
		<include refid="${tableAliasCode}_update_where_id"/>
	</delete>
	</#if>
	
	<select id="listCriteria" resultMap="${tableAliasCode}" parameterMap="${tableAliasCode}" useCache="true">
		<include refid="${tableAliasCode}_select"/>
		<include refid="${tableAliasCode}_select_fields"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${tableAliasCode}_where"/>
		<include refid="${tableAliasCode}_order"/>
		<include refid="${tableAliasCode}_group"/>
	</select>
	
	<#if idIsExist == 'true' >
	<select id="getOneById" resultMap="${tableAliasCode}" parameterType="java.lang.Long" useCache="true">
		<include refid="${tableAliasCode}_select"/>
		<include refid="${tableAliasCode}_select_fields"/>
		<include refid="${tableAliasCode}_from"/>
		<include refid="${tableAliasCode}_where_id"/>
	</select>		
	</#if>
	-->
</mapper>