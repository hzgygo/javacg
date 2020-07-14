$(document).ready(function(e) {
	var url;
	if (!gridUrl){
		url = "<#noparse>${base}</#noparse>/ajax/getdata/${viewConfig.entityName}/${viewConfig.dataMethodName}.json";
	}
	else{
		url = gridUrl;
	}
	var postData = {};
	if (!gridPostData){
		postData = {c:"${viewConfig.authCode?c}"};
	}
	else{
		postData = $.extend(true, postData,gridPostData);
	}
	
    jQuery("#${viewConfig.entityName}List").jqGrid({
        url: url,
        datatype: "json",
        colModel: [
            <#if (viewFieldConfigs?size>0) >
        	{name:"act",index:"act",label:"操作",width:"${actWidth}",align:"center",sortable:false,title:false,frozen:true},
        	<#else>
        	{name:"act",index:"act",label:"操作",width:"${actWidth}",align:"center",sortable:false,title:false,frozen:true}
        	</#if>
        	<#assign isFrozen="false"/>
        	<#if viewFieldConfigs?? >
        	<#list viewFieldConfigs as viewFieldConfig>
        	<#assign label="${viewFieldConfig.fieldLabel!viewFieldConfig.fieldName}"/>
        	<#assign name="${viewFieldConfig.fieldCode!''}"/>
        	<#assign index="${viewFieldConfig.fieldIndex!''}"/>
        	<#if viewFieldConfig.fieldType != 1 >
        	<#assign name="${viewFieldConfig.entityName!''}.${viewFieldConfig.fieldCode!''}"/>
        	<#assign index="${viewFieldConfig.entityName!''}.${viewFieldConfig.fieldIndex!''}"/>
        	</#if>
        	<#assign width="${viewFieldConfig.fieldWidth!'0'}"/>
        	<#assign align="${viewFieldConfig.fieldAlign!'1'}"/>
        	<#if align == '1' >
        		<#assign align="left"/>
        	<#elseif align == '2' >
        		<#assign align="center"/>
        	<#elseif align == '3' >
        		<#assign align="right"/>
        	</#if>
        	<#assign hidden="${viewFieldConfig.fieldHidden!'0'}"/>
        	<#if hidden == '1' >
        		<#assign hidden="true"/>
        	<#elseif hidden == '0' >
        		<#assign hidden="false"/>
        	</#if>
        	<#assign sortable="${viewFieldConfig.fieldSortable!'0'}"/>
        	<#if sortable == '1' >
        		<#assign sortable="true"/>
        	<#elseif sortable == '0' >
        		<#assign sortable="false"/>
        	</#if>
        	<#assign frozen="${viewFieldConfig.fieldFrozen!'0'}"/>
        	<#assign frozenVal=""/>
        	<#if frozen == '1' >
        	<#assign isFrozen="true"/>
        	<#assign frozenVal=",frozen:true"/>
        	</#if>
        	<#assign filedFormatter="${viewFieldConfig.filedFormatter!'0'}"/>
        	<#if viewFieldConfig_has_next>
        	<#if filedFormatter == '1' >
        	{name:"${name?uncap_first}",index:"${name?uncap_first}",label:"${label}",width:"${width}",align:"${align}",hidden:${hidden},sortable:${sortable}${frozenVal},formatter:function(data, options, rowObject){
        		<#list viewFieldConfig.viewFieldDataConfigs as viewFieldDataConfig>
        			<#if viewFieldDataConfig_index == 0 >
    		   		if(data == "${viewFieldDataConfig.dataValue}"){
    		   			return "${viewFieldDataConfig.dataLabel!''}";
    		   		}
    		   		</#if>
    		   		<#if viewFieldDataConfig_index >= 0 >
    		   		<#if viewFieldDataConfig_has_next>
    		   		else if(data == "${viewFieldDataConfig.dataValue!''}"){
    		   			return "${viewFieldDataConfig.dataLabel!''}";
    		   		}
    		   		</#if>
    		   		<#if !viewFieldDataConfig_has_next>
		   			else{
		   				return "${viewFieldDataConfig.dataLabel!''}";
		   			}
					</#if>
    		   		</#if>
    		   	 </#list>
    		}},
    		<#else>
    		{name:"${name?uncap_first}",index:"${name?uncap_first}",label:"${label}",width:"${width}",align:"${align}",hidden:${hidden},sortable:${sortable}${frozenVal}},
    	  	</#if>
    	  	<#else>
    	  	<#if filedFormatter == '1' >
        	{name:"${name?uncap_first}",index:"${name?uncap_first}",label:"${label}",width:"${width}",align:"${align}",hidden:${hidden},sortable:${sortable}${frozenVal},formatter:function(data, options, rowObject){
    		<#list viewFieldConfig.viewFieldDataConfigs as viewFieldDataConfig>
    			<#if viewFieldDataConfig_index == 0 >
		   		if(data == "${viewFieldDataConfig.dataValue}"){
		   			return "${viewFieldDataConfig.dataLabel!''}";
		   		}
		   		</#if>
		   		<#if viewFieldDataConfig_index >= 0 >
		   		<#if viewFieldDataConfig_has_next>
		   		else if(data == "${viewFieldDataConfig.dataValue!''}"){
		   			return "${viewFieldDataConfig.dataLabel!''}";
		   		}
		   		</#if>
		   		<#if !viewFieldDataConfig_has_next>
	   			else{
	   				return "${viewFieldDataConfig.dataLabel!''}";
	   			}
				</#if>
		   		</#if>
		   	 </#list>
    		}}
    		<#else>
    		{name:"${name?uncap_first}",index:"${name?uncap_first}",label:"${label}",width:"${width}",align:"${align}",hidden:${hidden},sortable:${sortable}${frozenVal}}
    	  	</#if>
    	  	</#if>
        	</#list>
        	</#if>
        ],
        <#if (viewConfig.viewGridParam??)>
		${viewConfig.viewGridParam}
        <#else>
        mtype:"POST",
        postData:postData,
        rowNum:pageRows,    
        page:currentPage,
        rowList: [10,20,30,40,50],
        pager: "#pagered",
        height:"350",
        width:$(window).innerWidth()-25,
        rownumbers:true,//显示行号
        viewrecords: true,
        multiselect: false,
        shrinkToFit: false,
        jsonReader: {
        	repeatitems: false
        },
    	loadComplete:loadComplete,
        caption:"${viewConfig.viewName}",
        toolbar: [true,"top"]
        </#if>
    });
  	//冻结列设置
    $("#${viewConfig.entityName}List") .jqGrid('setFrozenColumns').triggerHandler("jqGridAfterGridComplete");
    //分页下拉选中
	var rows = pageRows;
	$(".ui-pg-selbox").find("option").each(function(){
		var sval = $(this).val();
		if (rows && sval == rows){
			$(this).attr("selected",true);
		}
	});
});
