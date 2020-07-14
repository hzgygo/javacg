<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/view/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<jsp:include page="/WEB-INF/view/resource.jsp"></jsp:include>
<script type="text/javascript">
$(document).ready(function(e) {

});
</script>
</head>
<body>
<form:form method="post" action="<#noparse>${base}</#noparse>/form/submit/${viewConfig.entityName}/${viewConfig.dataMethodName}" commandName="${viewConfig.entityName}" name="${viewConfig.entityName}">
<input type="hidden" name="isLog" value="1"/>
<input type="hidden" name="replaceName" value="name"/>
<input type="hidden" name="buttonGroup" value="${viewConfig.authParentCode?c},<msg:message code='button.back.list'/>"/>
<input type="hidden" name="buttonGroup" value="${viewConfig.authCode?c},<msg:message code='button.continue.create'/>"/>
<#if viewFieldConfigs?? >
<#list viewFieldConfigs as viewFieldConfig>
<#if viewFieldConfig.fieldShowType == 6>
<form:hidden path="${viewFieldConfig.fieldCode}"/>
</#if>
</#list>
</#if>
<div class="form-body">
	<div class="form-title"><span><msg:message code='system.prompt.basic.info'/></span></div>
	<div class="form-content">
		<ul>
        	<#if viewFieldConfigs?? >
        	<#list viewFieldConfigs as viewFieldConfig>
        	<#if viewFieldConfig.fieldShowType == 1 >
        	<li>
				<div class="form-field-title">
					<b>*</b>${viewFieldConfig.fieldName}
				</div>
				<div class="form-field-elt">
					<form:input path="${viewFieldConfig.fieldCode?uncap_first}" class="dfinput"/>
				</div>
				<div class="form-field-prompt">
					<form:errors path="${viewFieldConfig.fieldCode?uncap_first}" delimiter=","></form:errors>
				</div>
			</li>         	
        	<#elseif viewFieldConfig.fieldShowType == 2 >
        	<li>
				<div class="form-field-title">
					<b>*</b>${viewFieldConfig.fieldName}
				</div>
				<div class="form-field-elt">    
	        		<form:select  path="${viewFieldConfig.fieldCode?uncap_first}" class="dfinput">
	        		<#if viewFieldConfig.viewFieldDataConfigs?? >
	        		<#list viewFieldConfig.viewFieldDataConfigs as viewFieldDataConfig>
						<form:option value="${viewFieldDataConfig.dataValue}">${viewFieldDataConfig.dataName!viewFieldDataConfig.dataLabel!''}</form:option>
			   	 	</#list>
			   	 	</#if>
			   	 	</form:select>
				</div>
				<div class="form-field-prompt">
					<form:errors path="${viewFieldConfig.fieldCode?uncap_first}" delimiter=","></form:errors>
				</div>
			</li>   
        	<#elseif viewFieldConfig.fieldShowType == 3>
        	<li>
				<div class="form-field-title">
					<b>*</b>${viewFieldConfig.fieldName}
				</div>
				<div class="form-field-elt">        	
        		<#if viewFieldConfig.viewFieldDataConfigs?? >
        		<#list viewFieldConfig.viewFieldDataConfigs as viewFieldDataConfig>
        			<form:radiobutton path="${viewFieldConfig.fieldCode?uncap_first}" value="${viewFieldDataConfig.dataValue}"/>${viewFieldDataConfig.dataLabel!''}
		   	 	</#list>
		   	 	</#if>
				</div>
				<div class="form-field-prompt">
					<form:errors path="${viewFieldConfig.fieldCode?uncap_first}" delimiter=","></form:errors>
				</div>
			</li>   
        	<#elseif viewFieldConfig.fieldShowType == 4>
        	<li>
				<div class="form-field-title">
					<b>*</b>${viewFieldConfig.fieldName}
				</div>
				<div class="form-field-elt">       	
        		<#if viewFieldConfig.viewFieldDataConfigs?? >
        		<#list viewFieldConfig.viewFieldDataConfigs as viewFieldDataConfig>
        			<form:checkbox path="${viewFieldConfig.fieldCode?uncap_first}" value="${viewFieldDataConfig.dataValue}"/>${viewFieldDataConfig.dataLabel!''}
		   	 	</#list>
		   	 	</#if>
				</div>
				<div class="form-field-prompt">
					<form:errors path="${viewFieldConfig.fieldCode?uncap_first}" delimiter=","></form:errors>
				</div>
			</li>   
		   	<#elseif viewFieldConfig.fieldShowType == 5>
        	<li>
				<div class="form-field-title">
					<b>*</b>${viewFieldConfig.fieldName}
				</div>
				<div class="form-field-elt">		   	
		   			<form:textarea path="${viewFieldConfig.fieldCode}" class="dfinput"/>
				</div>
				<div class="form-field-prompt">
					<form:errors path="${viewFieldConfig.fieldCode?uncap_first}" delimiter=","></form:errors>
				</div>
			</li>   		   	
		   	<#elseif viewFieldConfig.fieldShowType == 8>
        	<li>
				<div class="form-field-title">
					<b>*</b>${viewFieldConfig.fieldName}
				</div>
				<div class="form-field-elt">		   	
		   			<input type="hidden" name="${viewFieldConfig.fieldCode?uncap_first}" id="${viewFieldConfig.fieldCode}" class="editor"/>
					<script id="${viewFieldConfig.fieldCode?uncap_first}ContentEditor" type="text/plain" style="width:850px;height:500px;"><#noparse>${</#noparse>${viewConfig.entityName}<#noparse>}</#noparse></script>
					<script type="text/javascript">
						var ue = UE.getEditor('${viewFieldConfig.fieldCode?uncap_first}ContentEditor',{'enterTag':''});
					</script>
				</div>
				<div class="form-field-prompt">
					<form:errors path="${viewFieldConfig.fieldCode?uncap_first}" delimiter=","></form:errors>
				</div>
			</li>   		   	
		   	<#elseif viewFieldConfig.fieldShowType == 9>
        	<li>
				<div class="form-field-title">
					<b>*</b>${viewFieldConfig.fieldName}
				</div>
				<div class="form-field-elt">		   	
		   			<form:input path="${viewFieldConfig.fieldCode?uncap_first}" class="dfinput" onfocus="WdatePicker({isShowWeek:false,startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd'})"/>
				</div>
				<div class="form-field-prompt">
					<form:errors path="${viewFieldConfig.fieldCode?uncap_first}" delimiter=","></form:errors>
				</div>
			</li>   		   	
		   	<#elseif viewFieldConfig.fieldShowType == 10>
        	<li>
				<div class="form-field-title">
					<b>*</b>${viewFieldConfig.fieldName}
				</div>
				<div class="form-field-elt">		   	
					<a href="javascript:void();" class="file" id="select_file">
						选择文件
						<input type="file" id="file_select" name="uploadFiles" value="选择文件"/>
					</a>
					&nbsp;&nbsp;&nbsp;
					<a href="javascript:void();" id="upload" class="file-hide">
						上传文件
					</a>
				</div>
				<div class="form-field-prompt">
					<form:errors path="${viewFieldConfig.fieldCode?uncap_first}" delimiter=","></form:errors>
				</div>
			</li>   					
        	</#if>
        	</#list>
        	</#if>
		</ul>
	</div>
	<div class="form-footer">
		<input type="submit" class="btn-80" value="<msg:message code='button.create'/>"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" onclick="window.location.href='<#noparse>${base}</#noparse>/history/back/${viewConfig.authParentCode?c}'" class="btn-80" value="<msg:message code='button.back'/>"/>
	</div>
</div>
</form:form>
</body>	
</html>