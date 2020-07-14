<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/pages/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.role/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
var deptid = null;
var zNodes = <#noparse>${</#noparse>json<#noparse>}</#noparse>;
var setting = {
		check: {
			enable: false
		},
		view:{
			dblClickExpand:dblClickExpand,
			showLine:true
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback:{
			onClick:this.onClick
		}
	};
	
	function dblClickExpand(treeId,treeNode){
		return treeNode.level > 0;
	}
	
	function onClick(e,treeid,node){
	 	deptid = node.id;
	 	reload(deptid);
	}
	
	function reload(orgid){
		currcount = 0;
		jQuery("#userList").jqGrid("setGridParam",{postData:{c:"<#noparse>${</#noparse>user.c<#noparse>}</#noparse>",orgId:orgid},page:1});		
		jQuery("#userList").trigger("reloadGrid");	
	}
	


$(document).ready(function(){
	
	$.fn.zTree.init($("#treeDemo"), setting,zNodes);
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	treeObj.expandAll(true);
	
	<c:if test="<#noparse>${</#noparse>!empty user.orgId<#noparse>}</#noparse>">
		var node = treeObj.getNodeByParam("id", <#noparse>${</#noparse>user.orgId<#noparse>}</#noparse>, null);
		treeObj.selectNode(node);
	</c:if>

    jQuery("#userList").jqGrid({
        url: 'user/grid.json',
        datatype: 'json',
        colNames: ["<msg:message code='user.act'/>",
        		   "<msg:message code='user.name'/>", 
				   "<msg:message code='organization.name'/>",
				   "<msg:message code='user.position'/>",
                   "<msg:message code='user.operate'/>",
                   ""
                   ],
        colModel: [
				   {name: 'account',index:'u.account',width:'20%',align:'center',hidden: false,sortable:false<#noparse>}</#noparse>,
				   {name: 'name',index: 'u.name',width:'20%',align:'center',hidden: false,sortable:false<#noparse>}</#noparse>,
                   {name: 'orgName',index:'org.name',width:'20%',align:'center',hidden: true,sortable:false<#noparse>}</#noparse>,
				   {name: 'position',index:'u.position',width:'10%',align:'center',hidden: false,sortable:false<#noparse>}</#noparse>,
                   {name:'act',index:'act', width:'30%',align:'center',sortable:false,sortable:false,
                   		formatter:function(cellvalue, options, rowdata){
                   			var content = "";
						    var id = rowdata.id;
                   			<c:if test="<#noparse>${</#noparse>!empty system_user_bind<#noparse>}</#noparse>">
							<security:authorize ifAnyGranted="<#noparse>${</#noparse>system_user_bind.code<#noparse>}</#noparse>">	
							content += "<a href='javascript:void(0);' id='" + id + "' ";
							if(rowdata.type != 0){
								content += "class='shortcut_<#noparse>${</#noparse>system_user_bind.indexCode<#noparse>}</#noparse>' ";
							}else{
								content += "style='color:#999;' ";
							}
							content += " title='<#noparse>${</#noparse>system_user_bind.name<#noparse>}</#noparse>' >";
							content += "<img src='<#noparse>${</#noparse>base<#noparse>}</#noparse>/<#noparse>${</#noparse>skin<#noparse>}</#noparse>/images/icon/auth.png' weight='18' height='18' border='0' align='absmiddle'/><#noparse>${</#noparse>system_user_bind.name<#noparse>}</#noparse>";
							content += "</a>";
							</security:authorize>
							</c:if> 				
			    			<c:if test="<#noparse>${</#noparse>!empty system_user_cpwd<#noparse>}</#noparse>">
			    		    <security:authorize ifAnyGranted="<#noparse>${</#noparse>system_user_cpwd.code<#noparse>}</#noparse>">	
			    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_<#noparse>${</#noparse>system_user_cpwd.indexCode<#noparse>}</#noparse>' ";
			    			content += " title='<#noparse>${</#noparse>system_user_cpwd.name<#noparse>}</#noparse>' >";
			    			content += "<img src='<#noparse>${</#noparse>base<#noparse>}</#noparse>/<#noparse>${</#noparse>skin<#noparse>}</#noparse>/images/icon/password.png' weight='18' height='18' border='0' align='absmiddle'/><#noparse>${</#noparse>system_user_cpwd.name<#noparse>}</#noparse>";
			    			content += "</a>";
			    		    </security:authorize>
			    		    </c:if>
							<c:if test="<#noparse>${</#noparse>!empty system_user_dataauth<#noparse>}</#noparse>">
							<security:authorize ifAnyGranted="<#noparse>${</#noparse>system_user_dataauth.code<#noparse>}</#noparse>">	
							content += "<a href='javascript:void(0);' id='" + id + "' ";
							if(rowdata.type != 0){
								content += "class='shortcut_<#noparse>${</#noparse>system_user_dataauth.indexCode<#noparse>}</#noparse>' ";
							}else{
								content += "style='color:#999;' ";
							}
							content += " title='<#noparse>${</#noparse>system_user_dataauth.name<#noparse>}</#noparse>' >";
							content += "<img src='<#noparse>${</#noparse>base<#noparse>}</#noparse>/<#noparse>${</#noparse>skin<#noparse>}</#noparse>/images/icon/auth.png' weight='18' height='18' border='0' align='absmiddle'/><#noparse>${</#noparse>system_user_dataauth.name<#noparse>}</#noparse>";
							content += "</a>";
							</security:authorize>
							</c:if> 
			    			<c:if test="<#noparse>${</#noparse>!empty system_user_modify<#noparse>}</#noparse>">
			    		    <security:authorize ifAnyGranted="<#noparse>${</#noparse>system_user_modify.code<#noparse>}</#noparse>">	
			    			content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_<#noparse>${</#noparse>system_user_modify.indexCode<#noparse>}</#noparse>'";
			    			content += " title='<#noparse>${</#noparse>system_user_modify.name<#noparse>}</#noparse>' >";
			    			content += "<img src='<#noparse>${</#noparse>base<#noparse>}</#noparse>/<#noparse>${</#noparse>skin<#noparse>}</#noparse>/images/icon/modify.png' weight='18' height='18' border='0' align='absmiddle'/><#noparse>${</#noparse>system_user_modify.name<#noparse>}</#noparse>";
			    			content += "</a>";
			    		    </security:authorize>
			    		    </c:if>  
			    		    <c:if test="<#noparse>${</#noparse>!empty system_user_delete<#noparse>}</#noparse>">
			    		    <security:authorize ifAnyGranted="<#noparse>${</#noparse>system_user_delete.code<#noparse>}</#noparse>">
			    		    content += "<a href='javascript:void(0);' id='" + id + "' class='shortcut_<#noparse>${</#noparse>system_user_delete.indexCode<#noparse>}</#noparse>'";
			    		    content += " title='<#noparse>${</#noparse>system_user_delete.name<#noparse>}</#noparse>' >";
			    		    content += "<img src='<#noparse>${</#noparse>base<#noparse>}</#noparse>/<#noparse>${</#noparse>skin<#noparse>}</#noparse>/images/icon/delete.png' weight='18' height='18' border='0' align='absmiddle'/><#noparse>${</#noparse>system_user_delete.name<#noparse>}</#noparse>";
			    		    content += "</a>";	
			    		    </security:authorize>
			    		    </c:if> 
			    		    return content; 
                   		}
                   },
				   {name: 'type',index:'u.type',hidden: true,width:'1%',align:'center',sortable:false<#noparse>}</#noparse>],
        mtype:"POST",
        postData:{c:"<#noparse>${</#noparse>user.c<#noparse>}</#noparse>"<c:if test="<#noparse>${</#noparse>!empty user.orgId<#noparse>}</#noparse>">,orgId:<#noparse>${</#noparse>user.orgId<#noparse>}</#noparse></c:if><#noparse>}</#noparse>,
        rowNum:<msg:message code='jqgrid.row.num.10'/>,     
        page:"<#noparse>${</#noparse>user.page<#noparse>}</#noparse>",
        rowList: [<msg:message code='jqgrid.row.list.s5.10'/>],
        pager: '#pagered',
        height:<msg:message code='jqgrid.height.250'/>,
        autowidth: true,
        viewrecords: true,
        multiselect: true,
        jsonReader: {
        	repeatitems: false
        },
        caption:'<msg:message code="user.list"/>',
        toolbar: [true,"top"]
    });
	
    $(".shortcut_<#noparse>${</#noparse>system_user_delete.indexCode<#noparse>}</#noparse>").live("click",function(){
    	var rowid = $(this).attr("id");
    	var data = jQuery("#userList").jqGrid("getRowData",rowid);
		window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
			var rdata = "c=<#noparse>${</#noparse>system_user_delete.code<#noparse>}</#noparse>&id="+rowid+"&prompt=name&name="+encodeURIComponent(encodeURIComponent(data.name))+"";
			$.ajax({
				url:'<#noparse>${</#noparse>base<#noparse>}</#noparse><#noparse>${</#noparse>system_user_delete.controller<#noparse>}</#noparse>.json',
				type:'post',
				timeout:'60000',
				dataType:'json',
				data: rdata, 
				async:false,
				cache:false,
				success:function(jsonData){
					if (jsonData.status == "success"){
						//jQuery("#userList").jqGrid("delRowData",rowid);
						$("#userList").trigger("reloadGrid");    
					}else{
						window.parent.Boxy.alert("<msg:message code='info.prompt.delete.limit'/>", null, {title: "<msg:message code='info.prompt'/>"});
					}
				}
			});
		}, {title: "<msg:message code='info.prompt'/>"});
	});     		
    $(".shortcut_<#noparse>${</#noparse>system_user_modify.indexCode<#noparse>}</#noparse>").live("click",function(){
    	var rowid = $(this).attr("id");
		window.location.href = "<#noparse>${</#noparse>base<#noparse>}</#noparse><#noparse>${</#noparse>system_user_modify.controller<#noparse>}</#noparse>?c=<#noparse>${</#noparse>system_user_modify.code<#noparse>}</#noparse>&id="+ rowid;
    });   
    $(".shortcut_<#noparse>${</#noparse>system_user_cpwd.indexCode<#noparse>}</#noparse>").live("click",function(){
    	var rowid = $(this).attr("id");
		window.location.href = "<#noparse>${</#noparse>base<#noparse>}</#noparse><#noparse>${</#noparse>system_user_cpwd.controller<#noparse>}</#noparse>?c=<#noparse>${</#noparse>system_user_cpwd.code<#noparse>}</#noparse>&id="+rowid;
    });
    $(".shortcut_<#noparse>${</#noparse>system_user_dataauth.indexCode<#noparse>}</#noparse>").live("click",function(){
    	var rowid = $(this).attr("id");
		window.location.href = "<#noparse>${</#noparse>base<#noparse>}</#noparse><#noparse>${</#noparse>system_user_dataauth.controller<#noparse>}</#noparse>?c=<#noparse>${</#noparse>system_user_dataauth.code<#noparse>}</#noparse>&id="+rowid;
    }); 
    $(".shortcut_<#noparse>${</#noparse>system_user_bind.indexCode<#noparse>}</#noparse>").live("click",function(){
    	var rowid = $(this).attr("id");
		window.location.href = "<#noparse>${</#noparse>base<#noparse>}</#noparse><#noparse>${</#noparse>system_user_bind.controller<#noparse>}</#noparse>?c=<#noparse>${</#noparse>system_user_bind.code<#noparse>}</#noparse>&id="+rowid;
    });     	    
	
	jQuery("#userList").closest(".ui-jqgrid-bdiv").css("overflow-x","hidden");
	//JQgrid 添加导航工具
	jQuery("#userList").jqGrid('navGrid','#pagered',{edit:false,add:false,del:false,search:false,refresh:false});

	jQuery("#userList").jqGrid('navButtonAdd',"#pagered",{
		caption:"<msg:message code='button.search'/>",
		title:"<msg:message code='button.search'/>",
		buttonicon :'ui-icon-search',
		onClickButton:function(){
			$("#userList").jqGrid("searchGrid",{
				caption:'<msg:message code='button.search'/>',
				width:400,
				multipleSearch:true, 
				multipleGroup:true, 
				showQuery: false,
				jqModal:true,
				top:150,
				left:400
			});
		}
	});
	jQuery("#userList").jqGrid('navButtonAdd',"#pagered",{
		caption:"<msg:message code='jqgrid.button.refresh'/>",
		title:"<msg:message code='jqgrid.button.refresh'/>",
		buttonicon :'ui-icon-refresh',
		onClickButton:function(){
			$("#userList").trigger("reloadGrid"); 
		} 
	});
	
	jQuery("#userList").jqGrid('navButtonAdd','#pagered',{
	    caption: "<msg:message code='jqgrid.button.setting'/>",
	    title: "<msg:message code='jqgrid.button.setting'/>",
	    onClickButton : function (){
	        jQuery("#userList").jqGrid('columnChooser');
	    }
	});
    
    <c:if test="<#noparse>${</#noparse>!empty system_user_create<#noparse>}</#noparse>">
    <security:authorize ifAnyGranted="<#noparse>${</#noparse>system_user_create.code<#noparse>}</#noparse>">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","<#noparse>${</#noparse>system_user_create.indexCode<#noparse>}</#noparse>")
			  .append($("<img/>").attr("src","<#noparse>${</#noparse>base<#noparse>}</#noparse>/<#noparse>${</#noparse>skin<#noparse>}</#noparse>/images/icon/create.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("<#noparse>${</#noparse>system_user_create.name<#noparse>}</#noparse>");
	$("#t_userList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#<#noparse>${</#noparse>system_user_create.indexCode<#noparse>}</#noparse>","#t_userList").click(function(){
    	window.location.href = "<#noparse>${</#noparse>base<#noparse>}</#noparse><#noparse>${</#noparse>system_user_create.controller<#noparse>}</#noparse>?c=<#noparse>${</#noparse>system_user_create.code<#noparse>}</#noparse>&orgId="+deptid+"";
    });    
    </security:authorize>
    </c:if>
    
    <c:if test="<#noparse>${</#noparse>!empty system_user_delete<#noparse>}</#noparse>">
    <security:authorize ifAnyGranted="<#noparse>${</#noparse>system_user_delete.code<#noparse>}</#noparse>">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","<#noparse>${</#noparse>system_user_delete.indexCode<#noparse>}</#noparse>")
			  .append($("<img/>").attr("src","<#noparse>${</#noparse>base<#noparse>}</#noparse>/<#noparse>${</#noparse>skin<#noparse>}</#noparse>/images/icon/delete.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("<#noparse>${</#noparse>system_user_delete.name<#noparse>}</#noparse>");
	$("#t_userList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
    $("#<#noparse>${</#noparse>system_user_delete.indexCode<#noparse>}</#noparse>","#t_userList").click(function(){
    	var ids = jQuery("#userList").jqGrid("getGridParam","selarrrow");
    	var names = "";
    	for(var i=0;i < ids.length;i++){
    		var id = ids[i];
    		var data = jQuery("#userList").jqGrid("getRowData",id);
    		names += data.name;
    		if(i!= ids.length-1){
    			names += ",";
    		}
    	}
		if (ids && ids !=''){
			window.parent.Boxy.confirm("<msg:message code='info.prompt.delete.confirm'/>",  function(){
				var url = "<#noparse>${</#noparse>base<#noparse>}</#noparse><#noparse>${</#noparse>system_user_delete.controller<#noparse>}</#noparse>.json?c=<#noparse>${</#noparse>system_user_delete.code<#noparse>}</#noparse>&ids="+ids+"&name="+encodeURIComponent(encodeURIComponent(names))+"&prompt=name";
				$.ajax({
					url:url,
					type:'post',
					timeout:'60000',
					dataType:'json',
					success:function(jsonData,textStatus){
						if (textStatus == "success"){
							if (jsonData.status == "success"){
								//jQuery("#userList").jqGrid("delRowData",selrow);
								$("#userList").trigger("reloadGrid");    
							}else{
								window.parent.Boxy.alert("<msg:message code='info.prompt.delete.limit'/>", null, {title: "<msg:message code='info.prompt'/>"<#noparse>}</#noparse>);
							}
						}
					}
				});
			}, {title: "<msg:message code='info.prompt'/>"});
		}
		else{
    		window.parent.Boxy.alert("<msg:message code='info.prompt.delete'/>", null, {title: "<msg:message code='info.prompt'/>"<#noparse>}</#noparse>);
   		}    	
    });    
    </security:authorize>
    </c:if> 
    <c:if test="<#noparse>${</#noparse>!empty system_user_theytransfer<#noparse>}</#noparse>">
    <security:authorize ifAnyGranted="<#noparse>${</#noparse>system_user_theytransfer.code<#noparse>}</#noparse>">	 
    var $ea = $("<a></a>").attr("href","javascript:void(0)")
			  .attr("id","<#noparse>${</#noparse>system_user_theytransfer.indexCode<#noparse>}</#noparse>")
			  .append($("<img/>").attr("src","<#noparse>${</#noparse>base<#noparse>}</#noparse>/<#noparse>${</#noparse>skin<#noparse>}</#noparse>/images/icon/create.png")
			  .attr("width","18").attr("height","18").attr("border","0")
			  .attr("border","0").attr("align","absmiddle"))
			  .append("<#noparse>${</#noparse>system_user_theytransfer.name<#noparse>}</#noparse>");
	$("#t_userList").append("&nbsp;&nbsp;").append($("<span></span>").attr("class","jqgridContainer").append($ea));  
	
    $("#<#noparse>${</#noparse>system_user_theytransfer.indexCode<#noparse>}</#noparse>","#t_userList").click(function(){
    	window.location.href = "<#noparse>${</#noparse>base<#noparse>}</#noparse><#noparse>${</#noparse>system_user_theytransfer.controller<#noparse>}</#noparse>?c=<#noparse>${</#noparse>system_user_theytransfer.code<#noparse>}</#noparse>";
    });    
    </security:authorize>
    </c:if>
    
    $("#sbtn").click(function(){
    	var name = $("#name").val();
    	var url = "grid.json?c=1&name="+name;
    	jQuery("#bigset").jqGrid('setGridParam',{url:url,page:1<#noparse>}</#noparse>).trigger("reloadGrid");
    });
});
function submitForm(){
	$('#name').val($.trim($('#name').val()));
	jQuery("#userList").jqGrid("setGridParam",{postData:{c:"<#noparse>${</#noparse>user.c<#noparse>}</#noparse>",name:$('#name').val()},page:1});		
	jQuery("#userList").trigger("reloadGrid");
}
</script>
</head>
<body class="skinMain">
<form:form method="post" action="user" commandName="user" name="f">
<input type="hidden" name="c" value="<#noparse>${</#noparse>user.c<#noparse>}</#noparse>"/>
<table width="100%" border="0" cellspacing="1" cellpadding="0" class="skinMain">
	<tr>
		<td width="308" valign="top">
			<div class="ui-jqgrid ui-widget ui-widget-content ui-corner-all" dir="ltr" style="width:308px; ">
				<div class="ui-jqgrid-view" id="gview_dictionaryItemList" style="width:308px;">
					<div class="ui-jqgrid-view" id="gview_dictionaryItemList" style="width:308px;">
						<div class="ui-jqgrid-titlebar ui-widget-header ui-corner-top ui-helper-clearfix">
							<msg:message code="organization.list"/>
						</div>
						<div style="width:308px;height:100%; " class="ui-state-default ui-jqgrid-hdiv">
							<div class="ui-jqgrid-hbox">
								<ul id="treeDemo" class="ztree" style="margin-top:0px;width:300px;"></ul>  
							</div>
						</div>
					</div>
				</div>
			</div>		
		</td>
		<td width="80%" valign="top">
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<td align="center">
						<table cellpadding="0" cellspacing="1" border="0" width="100%" class="search">
							<tr>
								<td align="right" width="10%" class="search_info">
									<msg:message code='user.name'/><msg:message code="system.common.sign.colon"/>
								</td>
								<td align="left" width="70%"  class="search_lable">
									<form:input path="name" htmlEscape="true" />
								</td>
								<td rowspan="2" width="20%" class="button_area">
									<input type=button class="btn2" id="select" value="<msg:message code='button.search'/>" onclick="submitForm();">
								</td>				
							</tr>		
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<table id="userList"><tr><td>&nbsp;</td></tr></table>	
						<div id="pagered"></div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td></td>
	</tr>
</table>
</form:form>
</body>	
</html>