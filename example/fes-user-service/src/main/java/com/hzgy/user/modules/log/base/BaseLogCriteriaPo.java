package com.hzgy.user.modules.log.base;

import com.hzgy.core.entity.BasePo;

public abstract class BaseLogCriteriaPo extends BasePo {


    private String idOperator;

    private String idExpression;

    private String userIdOperator;

    private String userIdExpression;

	private String userNameAsLike;

    private String operateTimeOperator;

    private String operateTimeExpression;

	private String logTypeAsLike;

	private String contentAsLike;

	private String accessIpAsLike;

    private String isDeleteOperator;

    private String isDeleteExpression;

    private String createTimeOperator;

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
