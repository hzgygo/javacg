package com.hzgy.user.modules.permission.base;

import com.hzgy.core.entity.BaseInput;
import io.swagger.annotations.ApiModelProperty;

public abstract class BasePermissionCriteriaInput extends BaseInput {


    @ApiModelProperty(hidden=true)
    private String idOperator;

    @ApiModelProperty(hidden=true)
    private String idExpression;

    @ApiModelProperty(hidden=true)
    private String codeOperator;

    @ApiModelProperty(hidden=true)
    private String codeExpression;

    @ApiModelProperty(hidden=true)
    private String parentCodeOperator;

    @ApiModelProperty(hidden=true)
    private String parentCodeExpression;

    @ApiModelProperty(hidden=true)
    private String nameAsLike;

    @ApiModelProperty(hidden=true)
    private String parentNameAsLike;

    @ApiModelProperty(hidden=true)
    private String serviceCodeAsLike;

    @ApiModelProperty(hidden=true)
    private String urlPathAsLike;

    @ApiModelProperty(hidden=true)
    private String wildcardPathAsLike;

    @ApiModelProperty(hidden=true)
    private String modelNameAsLike;

    @ApiModelProperty(hidden=true)
    private String entityNameAsLike;

    @ApiModelProperty(hidden=true)
    private String methodNameAsLike;

    @ApiModelProperty(hidden=true)
    private String viewNameAsLike;

    @ApiModelProperty(hidden=true)
    private String codeKeyAsLike;

    @ApiModelProperty(hidden=true)
    private String treeLevelOperator;

    @ApiModelProperty(hidden=true)
    private String treeLevelExpression;

    @ApiModelProperty(hidden=true)
    private String treePathAsLike;

    @ApiModelProperty(hidden=true)
    private String authTypeOperator;

    @ApiModelProperty(hidden=true)
    private String authTypeExpression;

    @ApiModelProperty(hidden=true)
    private String isShowOperator;

    @ApiModelProperty(hidden=true)
    private String isShowExpression;

    @ApiModelProperty(hidden=true)
    private String isChildNodeOperator;

    @ApiModelProperty(hidden=true)
    private String isChildNodeExpression;

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

    public String getCodeOperator() {
        return codeOperator;
    }

    public void setCodeOperator(String codeOperator) {
        this.codeOperator = codeOperator;
    }

    public String getCodeExpression() {
        return codeExpression;
    }

    public void setCodeExpression(String codeExpression) {
        this.codeExpression = codeExpression;
    }

    public String getParentCodeOperator() {
        return parentCodeOperator;
    }

    public void setParentCodeOperator(String parentCodeOperator) {
        this.parentCodeOperator = parentCodeOperator;
    }

    public String getParentCodeExpression() {
        return parentCodeExpression;
    }

    public void setParentCodeExpression(String parentCodeExpression) {
        this.parentCodeExpression = parentCodeExpression;
    }

    public String getNameAsLike() {
        return nameAsLike;
    }

    public void setNameAsLike(String nameAsLike) {
        this.nameAsLike = nameAsLike;
    }

    public String getParentNameAsLike() {
        return parentNameAsLike;
    }

    public void setParentNameAsLike(String parentNameAsLike) {
        this.parentNameAsLike = parentNameAsLike;
    }

    public String getServiceCodeAsLike() {
        return serviceCodeAsLike;
    }

    public void setServiceCodeAsLike(String serviceCodeAsLike) {
        this.serviceCodeAsLike = serviceCodeAsLike;
    }

    public String getUrlPathAsLike() {
        return urlPathAsLike;
    }

    public void setUrlPathAsLike(String urlPathAsLike) {
        this.urlPathAsLike = urlPathAsLike;
    }

    public String getWildcardPathAsLike() {
        return wildcardPathAsLike;
    }

    public void setWildcardPathAsLike(String wildcardPathAsLike) {
        this.wildcardPathAsLike = wildcardPathAsLike;
    }

    public String getModelNameAsLike() {
        return modelNameAsLike;
    }

    public void setModelNameAsLike(String modelNameAsLike) {
        this.modelNameAsLike = modelNameAsLike;
    }

    public String getEntityNameAsLike() {
        return entityNameAsLike;
    }

    public void setEntityNameAsLike(String entityNameAsLike) {
        this.entityNameAsLike = entityNameAsLike;
    }

    public String getMethodNameAsLike() {
        return methodNameAsLike;
    }

    public void setMethodNameAsLike(String methodNameAsLike) {
        this.methodNameAsLike = methodNameAsLike;
    }

    public String getViewNameAsLike() {
        return viewNameAsLike;
    }

    public void setViewNameAsLike(String viewNameAsLike) {
        this.viewNameAsLike = viewNameAsLike;
    }

    public String getCodeKeyAsLike() {
        return codeKeyAsLike;
    }

    public void setCodeKeyAsLike(String codeKeyAsLike) {
        this.codeKeyAsLike = codeKeyAsLike;
    }

    public String getTreeLevelOperator() {
        return treeLevelOperator;
    }

    public void setTreeLevelOperator(String treeLevelOperator) {
        this.treeLevelOperator = treeLevelOperator;
    }

    public String getTreeLevelExpression() {
        return treeLevelExpression;
    }

    public void setTreeLevelExpression(String treeLevelExpression) {
        this.treeLevelExpression = treeLevelExpression;
    }

    public String getTreePathAsLike() {
        return treePathAsLike;
    }

    public void setTreePathAsLike(String treePathAsLike) {
        this.treePathAsLike = treePathAsLike;
    }

    public String getAuthTypeOperator() {
        return authTypeOperator;
    }

    public void setAuthTypeOperator(String authTypeOperator) {
        this.authTypeOperator = authTypeOperator;
    }

    public String getAuthTypeExpression() {
        return authTypeExpression;
    }

    public void setAuthTypeExpression(String authTypeExpression) {
        this.authTypeExpression = authTypeExpression;
    }

    public String getIsShowOperator() {
        return isShowOperator;
    }

    public void setIsShowOperator(String isShowOperator) {
        this.isShowOperator = isShowOperator;
    }

    public String getIsShowExpression() {
        return isShowExpression;
    }

    public void setIsShowExpression(String isShowExpression) {
        this.isShowExpression = isShowExpression;
    }

    public String getIsChildNodeOperator() {
        return isChildNodeOperator;
    }

    public void setIsChildNodeOperator(String isChildNodeOperator) {
        this.isChildNodeOperator = isChildNodeOperator;
    }

    public String getIsChildNodeExpression() {
        return isChildNodeExpression;
    }

    public void setIsChildNodeExpression(String isChildNodeExpression) {
        this.isChildNodeExpression = isChildNodeExpression;
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
