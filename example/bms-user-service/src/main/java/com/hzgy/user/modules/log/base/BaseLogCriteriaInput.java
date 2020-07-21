package com.hzgy.user.modules.log.base;

import com.hzgy.core.entity.BaseInput;
import io.swagger.annotations.ApiModelProperty;

public abstract class BaseLogCriteriaInput extends BaseInput {


    @ApiModelProperty(hidden=true)
    private String idOperator;

    @ApiModelProperty(hidden=true)
    private String idExpression;

    @ApiModelProperty(hidden=true)
    private String userIdOperator;

    @ApiModelProperty(hidden=true)
    private String userIdExpression;

    @ApiModelProperty(hidden=true)
    private String userNameAsLike;

    @ApiModelProperty(hidden=true)
    private String operateTimeOperator;

    @ApiModelProperty(hidden=true)
    private String operateTimeExpression;

    @ApiModelProperty(hidden=true)
    private String logTypeAsLike;

    @ApiModelProperty(hidden=true)
    private String contentAsLike;

    @ApiModelProperty(hidden=true)
    private String accessIpAsLike;

    @ApiModelProperty(hidden=true)
    private String isDeleteOperator;

    @ApiModelProperty(hidden=true)
    private String isDeleteExpression;

    @ApiModelProperty(hidden=true)
    private String createTimeOperator;

    @ApiModelProperty(hidden=true)
    private String createTimeExpression;


    public String getIdOperator() {
        return idOperator;
    }

    public void setIdOperator(String idOperator) {
        this.idOperator = idOperator;
    }

    public String getIdExpression() {
        return idExpression;
    }

    public void setIdExpression(String idExpression) {
        this.idExpression = idExpression;
    }

    public String getUserIdOperator() {
        return userIdOperator;
    }

    public void setUserIdOperator(String userIdOperator) {
        this.userIdOperator = userIdOperator;
    }

    public String getUserIdExpression() {
        return userIdExpression;
    }

    public void setUserIdExpression(String userIdExpression) {
        this.userIdExpression = userIdExpression;
    }

    public String getUserNameAsLike() {
        return userNameAsLike;
    }

    public void setUserNameAsLike(String userNameAsLike) {
        this.userNameAsLike = userNameAsLike;
    }

    public String getOperateTimeOperator() {
        return operateTimeOperator;
    }

    public void setOperateTimeOperator(String operateTimeOperator) {
        this.operateTimeOperator = operateTimeOperator;
    }

    public String getOperateTimeExpression() {
        return operateTimeExpression;
    }

    public void setOperateTimeExpression(String operateTimeExpression) {
        this.operateTimeExpression = operateTimeExpression;
    }

    public String getLogTypeAsLike() {
        return logTypeAsLike;
    }

    public void setLogTypeAsLike(String logTypeAsLike) {
        this.logTypeAsLike = logTypeAsLike;
    }

    public String getContentAsLike() {
        return contentAsLike;
    }

    public void setContentAsLike(String contentAsLike) {
        this.contentAsLike = contentAsLike;
    }

    public String getAccessIpAsLike() {
        return accessIpAsLike;
    }

    public void setAccessIpAsLike(String accessIpAsLike) {
        this.accessIpAsLike = accessIpAsLike;
    }

    public String getIsDeleteOperator() {
        return isDeleteOperator;
    }

    public void setIsDeleteOperator(String isDeleteOperator) {
        this.isDeleteOperator = isDeleteOperator;
    }

    public String getIsDeleteExpression() {
        return isDeleteExpression;
    }

    public void setIsDeleteExpression(String isDeleteExpression) {
        this.isDeleteExpression = isDeleteExpression;
    }

    public String getCreateTimeOperator() {
        return createTimeOperator;
    }

    public void setCreateTimeOperator(String createTimeOperator) {
        this.createTimeOperator = createTimeOperator;
    }

    public String getCreateTimeExpression() {
        return createTimeExpression;
    }

    public void setCreateTimeExpression(String createTimeExpression) {
        this.createTimeExpression = createTimeExpression;
    }

}
