package com.hzgy.user.modules.log.base;

import io.swagger.annotations.ApiModelProperty;
	import com.alibaba.fastjson.annotation.JSONField;
	import com.alibaba.fastjson.serializer.ToStringSerializer;

public abstract class BaseLogInput extends BaseLogCriteriaInput {


	/**日志ID-操作日志主键ID：操作日志记录表主键。**/
    @ApiModelProperty(hidden=true)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long id;

	/**用户ID-**/
    @ApiModelProperty(hidden=true)
	@JSONField(serializeUsing = ToStringSerializer.class)
	private Long userId;

	/**操作用户名称-**/
    @ApiModelProperty(hidden=true)
	private String userName;

	/**操作时间-操作时间：日志记录的详细时间。**/
    @ApiModelProperty(hidden=true)
	private String operateTime;

	/**操作类别-**/
    @ApiModelProperty(hidden=true)
	private String logType;

	/**操作内容-操作内容：系统用户操作的具体功能步骤的记录。**/
    @ApiModelProperty(hidden=true)
	private String content;

	/**访问IP-访问IP：系统用户远端访问的IP地址。**/
    @ApiModelProperty(hidden=true)
	private String accessIp;

	/**是否删除-**/
    @ApiModelProperty(hidden=true)
	private Integer isDelete;

	/**创建时间-**/
    @ApiModelProperty(hidden=true)
	private String createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public String getAccessIp() {
		return accessIp;
	}

	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
