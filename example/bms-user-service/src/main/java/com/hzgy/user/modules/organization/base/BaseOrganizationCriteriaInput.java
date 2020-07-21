package com.hzgy.user.modules.organization.base;

import com.hzgy.core.entity.BaseInput;
import io.swagger.annotations.ApiModelProperty;

public abstract class BaseOrganizationCriteriaInput extends BaseInput {


    @ApiModelProperty(hidden=true)
    private String idOperator;

    @ApiModelProperty(hidden=true)
    private String idExpression;

    @ApiModelProperty(hidden=true)
    private String parentIdOperator;

    @ApiModelProperty(hidden=true)
    private String parentIdExpression;

    @ApiModelProperty(hidden=true)
    private String orgCodeAsLike;

    @ApiModelProperty(hidden=true)
    private String orgNameAsLike;

    @ApiModelProperty(hidden=true)
    private String orgTypeOperator;

    @ApiModelProperty(hidden=true)
    private String orgTypeExpression;

    @ApiModelProperty(hidden=true)
    private String emailAsLike;

    @ApiModelProperty(hidden=true)
    private String mobilePhoneAsLike;

    @ApiModelProperty(hidden=true)
    private String domainNameAsLike;

    @ApiModelProperty(hidden=true)
    private String logoAsLike;

    @ApiModelProperty(hidden=true)
    private String isRealnameOperator;

    @ApiModelProperty(hidden=true)
    private String isRealnameExpression;

    @ApiModelProperty(hidden=true)
    private String isForbiddenOperator;

    @ApiModelProperty(hidden=true)
    private String isForbiddenExpression;

    @ApiModelProperty(hidden=true)
    private String treeLevelOperator;

    @ApiModelProperty(hidden=true)
    private String treeLevelExpression;

    @ApiModelProperty(hidden=true)
    private String treePathAsLike;

    @ApiModelProperty(hidden=true)
    private String isChildNodeOperator;

    @ApiModelProperty(hidden=true)
    private String isChildNodeExpression;

    @ApiModelProperty(hidden=true)
    private String blockchainAddressAsLike;

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

    public String getParentIdOperator() {
        return parentIdOperator;
    }

    public void setParentIdOperator(String parentIdOperator) {
        this.parentIdOperator = parentIdOperator;
    }

    public String getParentIdExpression() {
        return parentIdExpression;
    }

    public void setParentIdExpression(String parentIdExpression) {
        this.parentIdExpression = parentIdExpression;
    }

    public String getOrgCodeAsLike() {
        return orgCodeAsLike;
    }

    public void setOrgCodeAsLike(String orgCodeAsLike) {
        this.orgCodeAsLike = orgCodeAsLike;
    }

    public String getOrgNameAsLike() {
        return orgNameAsLike;
    }

    public void setOrgNameAsLike(String orgNameAsLike) {
        this.orgNameAsLike = orgNameAsLike;
    }

    public String getOrgTypeOperator() {
        return orgTypeOperator;
    }

    public void setOrgTypeOperator(String orgTypeOperator) {
        this.orgTypeOperator = orgTypeOperator;
    }

    public String getOrgTypeExpression() {
        return orgTypeExpression;
    }

    public void setOrgTypeExpression(String orgTypeExpression) {
        this.orgTypeExpression = orgTypeExpression;
    }

    public String getEmailAsLike() {
        return emailAsLike;
    }

    public void setEmailAsLike(String emailAsLike) {
        this.emailAsLike = emailAsLike;
    }

    public String getMobilePhoneAsLike() {
        return mobilePhoneAsLike;
    }

    public void setMobilePhoneAsLike(String mobilePhoneAsLike) {
        this.mobilePhoneAsLike = mobilePhoneAsLike;
    }

    public String getDomainNameAsLike() {
        return domainNameAsLike;
    }

    public void setDomainNameAsLike(String domainNameAsLike) {
        this.domainNameAsLike = domainNameAsLike;
    }

    public String getLogoAsLike() {
        return logoAsLike;
    }

    public void setLogoAsLike(String logoAsLike) {
        this.logoAsLike = logoAsLike;
    }

    public String getIsRealnameOperator() {
        return isRealnameOperator;
    }

    public void setIsRealnameOperator(String isRealnameOperator) {
        this.isRealnameOperator = isRealnameOperator;
    }

    public String getIsRealnameExpression() {
        return isRealnameExpression;
    }

    public void setIsRealnameExpression(String isRealnameExpression) {
        this.isRealnameExpression = isRealnameExpression;
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

    public String getBlockchainAddressAsLike() {
        return blockchainAddressAsLike;
    }

    public void setBlockchainAddressAsLike(String blockchainAddressAsLike) {
        this.blockchainAddressAsLike = blockchainAddressAsLike;
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
