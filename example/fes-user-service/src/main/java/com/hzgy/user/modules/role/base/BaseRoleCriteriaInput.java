package com.hzgy.user.modules.role.base;

import com.hzgy.core.entity.BaseInput;
import io.swagger.annotations.ApiModelProperty;

public abstract class BaseRoleCriteriaInput extends BaseInput {


    @ApiModelProperty(hidden=true)
    private String idOperator;

    @ApiModelProperty(hidden=true)
    private String idExpression;

    @ApiModelProperty(hidden=true)
    private String orgIdOperator;

    @ApiModelProperty(hidden=true)
    private String orgIdExpression;

    @ApiModelProperty(hidden=true)
    private String nameAsLike;

    @ApiModelProperty(hidden=true)
    private String codeAsLike;

    @ApiModelProperty(hidden=true)
    private String isForbiddenOperator;

    @ApiModelProperty(hidden=true)
    private String isForbiddenExpression;

    @ApiModelProperty(hidden=true)
    private String isDeleteOperator;

    @ApiModelProperty(hidden=true)
    private String isDeleteExpression;

    @ApiModelProperty(hidden=true)
    private String createTimeOperator;

    @ApiModelProperty(hidden=true)
    private String createTimeExpression;

    @ApiModelProperty(hidden=true)
    private String modifyTimeOperator;

    @ApiModelProperty(hidden=true)
    private String modifyTimeExpression;

    @ApiModelProperty(hidden=true)
    private String commentsAsLike;


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

    public String getOrgIdOperator() {
        return orgIdOperator;
    }

    public void setOrgIdOperator(String orgIdOperator) {
        this.orgIdOperator = orgIdOperator;
    }

    public String getOrgIdExpression() {
        return orgIdExpression;
    }

    public void setOrgIdExpression(String orgIdExpression) {
        this.orgIdExpression = orgIdExpression;
    }

    public String getNameAsLike() {
        return nameAsLike;
    }

    public void setNameAsLike(String nameAsLike) {
        this.nameAsLike = nameAsLike;
    }

    public String getCodeAsLike() {
        return codeAsLike;
    }

    public void setCodeAsLike(String codeAsLike) {
        this.codeAsLike = codeAsLike;
    }

    public String getIsForbiddenOperator() {
        return isForbiddenOperator;
    }

    public void setIsForbiddenOperator(String isForbiddenOperator) {
        this.isForbiddenOperator = isForbiddenOperator;
    }

    public String getIsForbiddenExpression() {
        return isForbiddenExpression;
    }

    public void setIsForbiddenExpression(String isForbiddenExpression) {
        this.isForbiddenExpression = isForbiddenExpression;
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

    public String getModifyTimeOperator() {
        return modifyTimeOperator;
    }

    public void setModifyTimeOperator(String modifyTimeOperator) {
        this.modifyTimeOperator = modifyTimeOperator;
    }

    public String getModifyTimeExpression() {
        return modifyTimeExpression;
    }

    public void setModifyTimeExpression(String modifyTimeExpression) {
        this.modifyTimeExpression = modifyTimeExpression;
    }

    public String getCommentsAsLike() {
        return commentsAsLike;
    }

    public void setCommentsAsLike(String commentsAsLike) {
        this.commentsAsLike = commentsAsLike;
    }

}
