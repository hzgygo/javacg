<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/view/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<jsp:include page="/WEB-INF/view/resource.jsp"></jsp:include>
<script type="text/javascript">
var setting = {
	data: {
		simpleData: {
			enable: true
		}
	},callback: {
		
	}
	,check: {
		enable: true,
		chkStyle: "checkbox",
		chkboxType: { "Y": "p", "N": "s" }
	}
};
function getNode(){   
	var treeObj = $.fn.zTree.getZTreeObj("${viewConfig.entityName}Tree");
	var nodes = treeObj.getCheckedNodes(true);//获取选中的节点
	var selectNode;
	if(nodes.length>0){
		var arr = new Array();
		for(var j =0;j<nodes.length;j++){
		    selectNode = nodes[j];
			arr.push(selectNode.code);
		}
		return arr.join(",");
	}else{
		return null; 	
	}
};
$(document).ready(function(e) {
	var height = window.screen.height;
	$("#${viewConfig.entityName}Tree").height(height-500);
	var datas = $("#${viewConfig.entityName}").serialize();
	$.ajax({ 
		url:'<#noparse>${base}</#noparse>/ajax/getdata/${viewConfig.treeEntityName!viewConfig.entityName!''}/tree.json',
		type:'post', 
		dataType: "json", 
		data:datas,
		success: function(jsonData){ //成功 
			if (jsonData.data){
				$.fn.zTree.init($("#${viewConfig.entityName}Tree"), setting,jsonData.data);
				var zTree = $.fn.zTree.getZTreeObj("${viewConfig.entityName}Tree");
				zTree.expandAll(true);
			} 
		}
	});
	//动态添加权限
	$("#sub_btn").on("click",function(){
		var treeIds = getNode();
		var datas = $("#${viewConfig.entityName}").serialize();
		datas = $.extend(true, datas,{treeIds:treeIds});
		$.ajax({ 
			url:'<#noparse>${base}</#noparse>/ajax/submit/${viewConfig.entityName}/${viewConfig.dataMethodName}.json',
			data:datas,
			type:'post', //数据发送方式 
			dataType: "json", 
			success: function(jsonData,textStatus){
				if(jsonData.status == "success"){
					top.layer.msg(jsonData.promptMessage,{icon:1,time:2000,area:['450px','80px']},function(){
						window.location.reload();
					});
				}
				else{
					top.layer.msg(jsonData.promptMessage,{icon:2,time:2000,area:['450px','80px']}});
				}
			}
		});	
	});
});
</script>
</head>
<body>
<form:form method="post" action="<#noparse>${base}</#noparse>/form/submit/${viewConfig.entityName}/${viewConfig.dataMethodName}" commandName="${viewConfig.entityName}" name="${viewConfig.entityName}">
<input type="hidden" name="isLog" value="1"/>
<input type="hidden" name="replaceName" value=""/>
<#if viewFieldConfigs?? >
<#list viewFieldConfigs as viewFieldConfig>
<#if viewFieldConfig.fieldShowType == 6>
<form:hidden path="${viewFieldConfig.fieldCode}"/>
</#if>
</#list>
</#if>
<div class="form-body">
	<div class="form-title">
		<span><msg:message code='system.prompt.funcauth.info' /></span>
	</div>
	<div class="form-content">
		<ul style="overflow-y:auto;height:100%;width:100%">
			<li>
				<div class="form-field-title">
				&nbsp;
				</div>
				<div class="form-field-elt" style="padding-left: 50px;">
					<ul id="${viewConfig.entityName}Tree" class="ztree"></ul>
				</div>
			</li>
		</ul>
	</div>
	<div class="form-footer">
		<input type="button" id="sub_btn" class="btn-80" value="<msg:message code='button.save'/>"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" onclick="window.location.href='<#noparse>${base}</#noparse>/history/back/${viewConfig.authParentCode?c}'" class="btn-80" value="<msg:message code='button.back'/>"/>
	</div>
</div>
</form:form>
</body>	
</html>