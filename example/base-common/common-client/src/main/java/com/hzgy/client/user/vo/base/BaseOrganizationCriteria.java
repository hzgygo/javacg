package com.hzgy.client.user.vo.base;

import com.hzgy.core.entity.BaseClientVo;

public abstract class BaseOrganizationCriteria extends BaseClientVo{


    private String idOperator;

    private String idExpression;

    private String parentIdOperator;

    private String parentIdExpression;

    private String orgCodeAsLike;

    private String orgNameAsLike;

    private String orgTypeOperator;

    private String orgTypeExpression;

    private String emailAsLike;

    private String mobilePhoneAsLike;

    private String domainNameAsLike;

    private String logoAsLike;

    private String isRealnameOperator;

    private String isRealnameExpression;

    private String isForbiddenOperator;

    private String isForbiddenExpression;

    private String treeLevelOperator;

    private String treeLevelExpression;

    private String treePathAsLike;

    private String isChildNodeOperator;

    private String isChildNodeExpression;

    private String blockchainAddressAsLike;

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
