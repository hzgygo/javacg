<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/view/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<jsp:include page="/WEB-INF/view/resource.jsp"></jsp:include>
<script type="text/javascript">
var url = "<#noparse>${base}</#noparse>/ajax/getdata/${viewConfig.entityName}/paginated.json";
function loadComplete(gdatas){
	var rows = gdatas.rows;
	for(var i=0;i < rows.length;i++){
		var id = rows[i].id;
		var content = "";
		<#list subViewConfigs as subViewConfig>
		<#if subViewConfig.parentViewPosition == 6>
		<shiro:hasPermission name="${subViewConfig.authCode}">	
		content += "<a href='javascript:void(0)' id='shortcut_" + id + "'";
		content += " title='${subViewConfig.authName}'>";
		content += "<img src='<#noparse>${base}</#noparse>/static/images/icon/${subViewConfig.dataMethodName}.png'";
		content += " weight='18' height='18' border='0' align='absmiddle'/>";
		content += "${subViewConfig.authName}";
		content += "</a>";
	    </shiro:hasPermission>
		</#if>
	    </#list>
		jQuery("#${viewConfig.entityName}List").jqGrid('setRowData',id,{act:"<div class='jqgridContainer'>" + content + "</div>"});
		<#list subViewConfigs as subViewConfig>
		<#if subViewConfig.parentViewPosition == 6>
		<#if subViewConfig.viewType != 8>
		<shiro:hasPermission name="${subViewConfig.authCode}">
	    $("#${viewConfig.entityName}List").on("click","#shortcut_" + id,function(){
			window.location.href="${base}/goto/${viewConfig.entityName}/${subViewConfig.jumpMethodName}?viewCode=${subViewConfig.authCode}&isContextCode=1&id=" + id;
	    });  
	    </shiro:hasPermission>
	    <#else>
		<shiro:hasPermission name="${subViewConfig.authCode}">
    	$("#${viewConfig.entityName}List").on("click","#shortcut_" + id,function(){
	    	var datas = {
	    		id:id
    		};
			var layers = top.layer.confirm("<msg:message code='${viewConfig.entityName}.prompt.${subViewConfig.dataMethodName}.confirm'/>",  function(){
				$.ajax({
					url:"${base}/ajax/submit/${viewConfig.entityName}/${subViewConfig.dataMethodName}.json",
					type:'post',
					timeout:'60000',
					dataType:'json',
					data:datas,
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if(jsonData.status == "success"){
								top.layer.msg(jsonData.promptMessage,{icon:1,time:2000,area:['650px','80px']},function(){
									$("#${viewConfig.entityName}List").trigger("reloadGrid");
								});
							}
							else{
								top.layer.msg(jsonData.promptMessage,{icon:2,time:2000,area:['650px','80px']});
							}
						}
					}
				});
			});
	    });  
	    </shiro:hasPermission>
	    </#if>
		</#if>
	    </#list>
	}
    <#list subViewConfigs as subViewConfig>
	<#if subViewConfig.parentViewPosition == 5>
	<shiro:hasPermission name="${subViewConfig.authCode}">	
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
   			  .attr("id","top_${subViewConfig.dataMethodName}")
   			  .append($("<img/>").attr("src","<#noparse>${base}</#noparse>/static/images/icon/${subViewConfig.dataMethodName}.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("创建");
    $("#t_${viewConfig.entityName}List").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));   
    $("#${viewConfig.entityName}List").on("click","#top_${subViewConfig.dataMethodName}",function(){
		window.location.href="${base}/goto/${viewConfig.entityName}/${subViewConfig.jumpMethodName}?viewCode=${subViewConfig.authCode}&isContextCode=1";
	});  
    </shiro:hasPermission>
	</#if>
    </#list>
    <#if viewConfig.isPageBack == 1>
    var $eaback = $("<a></a>").attr("href","<#noparse>${base}</#noparse>/history/back/${viewConfig.parentCode}")
		  .attr("id","top_back")
		  .append($("<img/>").attr("src","${base}/static/images/icon/restore.png")
		  .attr("width","18").attr("height","18").attr("border","0")
		  .attr("border","0").attr("align","absmiddle"))
		  .append("返回");
    $("#t_${viewConfig.entityName}List").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($eaback));  
	</#if>
}
</script>
</head>
<body class="skinMain">
<div class="list-content">
<table width="98%" border="0" cellspacing="1" cellpadding="0" class="skinMain">
	<tr>
		<td width="100%">
			<table cellpadding="2" cellspacing="0" border="0" width="100%">
				<tr>
					<td width="15%" valign="top">
						<div class="ui-jqgrid ui-widget ui-widget-content ui-corner-all" dir="ltr" style="width: 208px;">
							<div class="ui-jqgrid-view" id="gview_dictionaryItemList" style="width: 208px;">
								<div class="ui-jqgrid-view" id="gview_dictionaryItemList" style="width: 208px;">
									<div class="ui-jqgrid-titlebar ui-widget-header ui-corner-top ui-helper-clearfix">
										<msg:message code="dictionary.differ" />
									</div>
									<div style="width: 208px; height: 502px;" class="ui-state-default ui-jqgrid-hdiv">
										<div class="ui-jqgrid-hbox">
											<select id="xxxxId" style="width: 208px; height: 490px; border: 0px; font-size: 13;" multiple="multiple">
											<c:forEach var="item" items="${xxxxList}" varStatus="pt">
												<c:if test="${item.id == dictionary.xxxxId}">
												<option value="${item.id}" selected="selected">${item.name}</option>
												</c:if>
												<c:if test="${item.id != dictionary.xxxxId}">
												<option value="${item.id}">${item.name}</option>
												</c:if>
											</c:forEach>
											</select>
										</div>
									</div>
								</div>
							</div>
						</div>
					</td>
					<td valign="top">
						<table width="100%" id="${viewConfig.entityName}List">
							<tr>
								<td>&nbsp;</td>
							</tr>
						</table>
						<div id="pagered"></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</div>
</body>
</html>