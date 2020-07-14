<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/view/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<jsp:include page="/WEB-INF/view/resource.jsp"></jsp:include>
</head>
<body>
<form:form id="role" method="post" action="${base}/form/submit/role/create" commandName="role" name="role">
<input type="hidden" name="isLog" value="1"/>
<input type="hidden" name="replaceName" value="name"/>
<input type="hidden" name="buttonGroup" value="${uc_system_role_code},<msg:message code='button.back.list'/>"/>
<input type="hidden" name="buttonGroup" value="${uc_system_role_create_code},<msg:message code='button.continue.create'/>"/>
<div class="form-body" >
	<div class="form-title"><span><msg:message code='system.prompt.basic.info'/></span></div>
	<div class="form-content">
		<ul style="overflow-y:auto;height:100%;width:100%">
			<c:if test="${user.isAdmin == 1}">
			<li>
				<div class="form-field-title">
					<b>*</b><msg:message code='role.orgName'/>：
				</div>
				<div class="form-field-elt">
					<input type="hidden" name="roleType" value="1"/>
					<select name="orgIdAlias" id="orgIdAlias" class="dfinput">
					<option value="">请选择...</option>
					<c:forEach var="item" items="${organizations}" varStatus="vs">
						<option value="${item.id}">${item.name}</option>
					</c:forEach>
					</select>
				</div>
				<div class="form-field-prompt">
					<form:errors path="name" delimiter=" "></form:errors>
				</div>
			</li>
			</c:if>
			<c:if test="${user.isAdmin != 1}">
			<li>
				<div class="form-field-title">
					<b>*</b><msg:message code='role.orgName'/>：
				</div>
				<div class="form-field-elt">
					<input type="hidden" name="roleType" value="2"/>
					<input type="hidden" name="orgIdAlias" value="${user.ufOrgId}"/>
					${user.ufOrgName}
				</div>
				<div class="form-field-prompt">
					<form:errors path="name" delimiter=" "></form:errors>
				</div>
			</li>
			</c:if>
	        <li>
				<div class="form-field-title">
					<b>*</b><msg:message code='role.name'/>：
				</div>
				<div class="form-field-elt">
					<form:input path="name" class="dfinput"/>
				</div>
				<div class="form-field-prompt">
					<form:errors path="name" delimiter=" "></form:errors>
				</div>
			</li>
	        <li>
				<div class="form-field-title">
					<b>*</b><msg:message code='role.roleStatus'/>：
				</div>
				<div class="form-field-elt">
					<input type="radio" name="roleStatus" value="1" checked="checked" />启用
					<input type="radio" name="roleStatus" value="0" />禁用
				</div>
				<div class="form-field-prompt">
					<form:errors path="roleStatus" delimiter=" "></form:errors>
				</div>
			</li>
	        <li>
				<div class="form-field-title">
					<msg:message code='role.description'/>：
				</div>
				<div class="form-field-elt">
					<form:input path="description" class="dfinput"/>
				</div>
				<div class="form-field-prompt">
					<form:errors path="description" delimiter=" "></form:errors>
				</div>
			</li>
		</ul>
	</div>
	<div class="form-footer">
		<input type="submit" class="btn-80" value="<msg:message code='button.create'/>"/>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" onclick="window.location.href='${base}/history/back/${uc_system_role_code}'" class="btn-80" value="<msg:message code='button.back'/>"/>
	</div>
</div>
</form:form>
</body>	
</html>