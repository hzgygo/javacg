<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/view/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<jsp:include page="/WEB-INF/view/resource.jsp"></jsp:include>
<script type="text/javascript">
<#if (subViewConfigs?size>0) >
var url = "<#noparse>${base}</#noparse>/ajax/getdata/${viewConfig.entityName}/paginated.json";
var postData = {};
function loadComplete(gdatas){
	var rows = gdatas.rows;
	for(var i=0;i < rows.length;i++){
		var id = rows[i].id;
		var content = "";
		<#list subViewConfigs as subViewConfig>
		<#if subViewConfig.parentViewPosition == 6>
	<#if (subViewConfig.viewType != 0) && (subViewConfig.viewType != 7) && (subViewConfig.viewType != 8) && (subViewConfig.viewType != 9)>
	<shiro:hasPermission name="${subViewConfig.authCode?c}">
    $(".shortcut_${subViewConfig.dataMethodName?uncap_first}").click(function(){
    	var data = $(this).attr("data-init");
    	var jsonData = jQuery.parseJSON(data);
    	var id = jsonData.id;
		window.location.href="<#noparse>${base}</#noparse>/goto/${viewConfig.entityName}/${subViewConfig.jumpMethodName}?viewCode=${subViewConfig.authCode?c}&isContextCode=1&id=" + id;
    });  
    </shiro:hasPermission>
    <#elseif subViewConfig.viewType == 7>
    <shiro:hasPermission name="${subViewConfig.authCode?c}">
    $(".shortcut_${subViewConfig.dataMethodName?uncap_first}").click(function(){
    	var data = $(this).attr("data-init");
    	var jsonData = jQuery.parseJSON(data);
    	var id = jsonData.id;
		var tindex = top.layer.confirm('您确定要导出所选择条件下的所有数据吗？', {
    		  btn: ['是','否'] //按钮
    	}, 
    	function(){
    		top.layer.close(tindex);
    		var lindex = top.layer.load(1, {
	    		  shade: [0.4,'#000']
	    	});
    		var datas = search.getFormData();
    		datas = $.extend(datas,{id:id,downloadType:1});
    		var url = "<#noparse>${base}</#noparse>/ajax/submit/${viewConfig.entityName}/g${viewConfig.dataMethodName}.json";
			$.ajax({
				url:url,
				type:'post',
				timeout:'60000',
				dataType:'json',
				data:datas,
				success:function(jsonData,textStatus){
					if (textStatus == "success"){
						if(jsonData.status == "success"){
							top.layer.close(lindex);
							var fileName = escape(jsonData.fileName);
							var downloadPath = escape(jsonData.downloadPath);
							var url = "<#noparse>${base}</#noparse>/download/${viewConfig.entityName}/${viewConfig.dataMethodName}?fileName=" + fileName + "&downloadPath=" + downloadPath;
							var tipHtml = "<div class='tipinfo'>您选择数据文件已生成完成!";
			    	    	tipHtml += "<br/><br/><a href='"+url+"' target='_blank' style='background:#1cb2f8;color:#fff;'>请点击下载</a>";
			    	    	tipHtml += "</div>";
			    	    	var layers = top.layer.open({
			    	    		title: "资产文件下载",
			    	    		btn: ['关闭'],
			    	    		closeBtn: 1,
			    	    		area: ['420px','210px'],
			    	    		shadeClose: false,
			    	    		content: tipHtml,
			    	    		yes: function(index, layero){
			    	    			top.layer.close(index);
			    	    		}
			    	    	});
						}
						else{
							top.layer.msg(jsonData.promptMessage,{icon:2,time:2000},function(index){
				    			top.layer.close(index);
							});
						}
					}
				}
			});
    	}, function(){
    		top.layer.close(tindex);
    	});
    });    
    </shiro:hasPermission>
    <#elseif subViewConfig.viewType == 8>
	<shiro:hasPermission name="${subViewConfig.authCode?c}">
	$(".shortcut_${subViewConfig.dataMethodName?uncap_first}").click(function(){
		var data = $(this).attr("data-init");
    	var jsonData = jQuery.parseJSON(data);
    	var id = jsonData.id;
		var tipHtml = "<div class='tipinfo'>您选择要上传的文件!";
		tipHtml += "<a href='javascript:void();' class='file' id='select_file'>选择文件";
		tipHtml += "<input type="file" id="upload_file_element" name="uploadFiles" value="选择文件"/>";
		tipHtml += "</a>";
		tipHtml += "<a href="javascript:void();" id="select_file_name"></a>";
		tipHtml += "</div>";
		var layers = top.layer.open({
			title: "文件上传",
			btn: ['上传','关闭'],
			area: ['450px','250px'],
			shadeClose: false,
			content: tipHtml,
			yes: function(index, layero){
				//选择上传文件后显示，文件名称
				var fileElement = layero.find("#select_file").click(function(){
					var fileName = $(this).val();
					layero.find("#select_file_name").text(fileName);
				});
				//上传文件
				var fileElement = layero.find("#upload_file_element");
				top.layer.close(index);
				var url = "<#noparse>${base}</#noparse>/upload/${viewConfig.entityName}/${viewConfig.dataMethodName}.json";
				$.ajaxFileUpload ({
					url:url,
					secureuri :false,
					fileElement :fileElement,
					dataType : 'json',
					data:datas,
					success : function (data, status){
						if(data.status == 'success'){
							var filePath = data.filePath;
							var fileType = data.fileType;
							if (filePath){
								var $img = $("<img/>").attr("src","${base}/"+filePath)
								 	.attr("alt",filePath)
									.attr("width","300")
									.attr("height","200")
									.attr("id","uploadimg")
									.attr("border","0");
								$("#image_preview").html("");
								$("#image_preview").append("&nbsp;&nbsp;").append($img);
								jsonData = $.extends(true,jsonData,{
									imageType:fileType,
									imageDataType:1,
									imageName:imageName,
									imageLocalPath:filePath
								});
								$.ajax({
									url:"<#noparse>${base}</#noparse>/ajax/getdata/${viewConfig.entityName}/uploadModify.json",
			    					type:'post',
			    					timeout:'60000',
			    					dataType:'json',
			    					data:jsonData,
									success:function(jsonData,textStatus){
										if(textStatus == "success"){
											if(jsonData.status == "success"){
												top.layer.msg(jsonData.promptMessage,{icon:1,time:1000,closeBtn: 0,area:['350px','70px']});
											}
											else{
												top.layer.msg(jsonData.promptMessage,{icon:2,time:1000,closeBtn: 0,area:['350px','70px']});
											}
										}
										else{
											top.layer.msg(jsonData.promptMessage,{icon:2,time:1000,closeBtn: 0,area:['350px','70px']});
										}
									}
								});
							}
							else{
								top.layer.msg(jsonData.promptMessage,{icon:2,time:1000,closeBtn: 0});
							}
						}
						else{
							top.layer.msg(data.promptMessage,{icon:2,time:1000,closeBtn: 0});
						}
					},
					error: function (data, status, e){
						top.layer.msg('文件下载失败!',{icon:2,time:1000});
					}
				});
			}
		});
    });
    </shiro:hasPermission>
    <#elseif subViewConfig.viewType == 9>
	<shiro:hasPermission name="${subViewConfig.authCode?c}">
	$(".shortcut_${subViewConfig.dataMethodName?uncap_first}").click(function(){
		var data = $(this).attr("data-init");
    	var jsonData = jQuery.parseJSON(data);
    	var name = jsonData.name
    	var id = jsonData.id;
    	var datas = {
    		id:id,
    		name:name,
    		replaceName:'name'
		};
		var layers = top.layer.confirm("<msg:message code='system.prompt.delete.confirm'/>",  function(){
			$.ajax({
				url:"<#noparse>${base}</#noparse>/ajax/submit/${viewConfig.entityName}/${subViewConfig.dataMethodName}.json",
				type:'post',
				timeout:'60000',
				dataType:'json',
				data:datas,
				success:function(jsonData,textStatus){
					if (textStatus == "success"){
						if(jsonData.status == "success"){
							top.layer.msg(jsonData.promptMessage,{icon:1,time:2000,area:['450px','80px']},function(){
								window.location.reload();
							});
						}
						else{
							top.layer.msg(jsonData.promptMessage,{icon:2,time:2000,area:['450px','80px']});
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
	<shiro:hasPermission name="${subViewConfig.authCode?c}">	
    var $ea = $("<a></a>").attr("href","<#noparse>${base}</#noparse>/goto/${viewConfig.entityName}/${subViewConfig.jumpMethodName}?viewCode=${subViewConfig.authCode?c}&isContextCode=1")
   			  .attr("id","top_${subViewConfig.dataMethodName}")
   			  .append($("<img/>").attr("src","<#noparse>${base}</#noparse>/static/images/icon/${subViewConfig.dataMethodName}.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("${subViewConfig.authName}");
    $("#t_${viewConfig.entityName}List").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));   
    </shiro:hasPermission>
	</#if>
    </#list>
    <#if viewConfig.isPageBack == 1>
    var $eaback = $("<a></a>").attr("href","<#noparse>${base}</#noparse>/history/back/${viewConfig.parentCode}")
		  .attr("id","top_back")
		  .append($("<img/>").attr("src","<#noparse>${base}</#noparse>/static/images/icon/restore.png")
		  .attr("width","18").attr("height","18").attr("border","0")
		  .attr("border","0").attr("align","absmiddle"))
		  .append("<msg:message code='button.back'/>");
    $("#t_${viewConfig.entityName}List").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($eaback));  
	</#if>
</#if>
}
</script>
</head>
<body class="skinMain">
<div class="list-content">
<table width="98%" border="0" cellspacing="1" cellpadding="0" class="skinMain">
	<tr>
		<td width="100%">
			<table width="100%" id="${viewConfig.entityName}List">
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			<div id="pagered"></div>
		</td>
	</tr>
</table>
</div>
<script src="<#noparse>${base}</#noparse>/genjs/${viewConfig.viewParentPath}/${viewConfig.viewName}.js" type="text/javascript"></script>
</body>
</html>