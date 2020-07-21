package com.hzgy.client.user.vo.base;

import com.hzgy.core.entity.BaseClientVo;

public abstract class BaseRoleCriteria extends BaseClientVo{


    private String idOperator;

    private String idExpression;

    private String orgIdOperator;

    private String orgIdExpression;

    private String nameAsLike;

    private String codeAsLike;

    private String isForbiddenOperator;

    private String isForbiddenExpression;

    private String isDeleteOperator;

    private String isDeleteExpression;

    private String createTimeOperator;

    private String createTimeExpression;

    private String modifyTimeOperator;

    private String modifyTimeExpression;

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
