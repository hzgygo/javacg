package com.hzgy.user.modules.user.base;

import com.hzgy.core.entity.BaseInput;
import io.swagger.annotations.ApiModelProperty;

public abstract class BaseUserCriteriaInput extends BaseInput {


    @ApiModelProperty(hidden=true)
    private String idOperator;

    @ApiModelProperty(hidden=true)
    private String idExpression;

    @ApiModelProperty(hidden=true)
    private String orgIdOperator;

    @ApiModelProperty(hidden=true)
    private String orgIdExpression;

    @ApiModelProperty(hidden=true)
    private String accountAsLike;

    @ApiModelProperty(hidden=true)
    private String phoneAccountAsLike;

    @ApiModelProperty(hidden=true)
    private String emailAccountAsLike;

    @ApiModelProperty(hidden=true)
    private String userCodeAsLike;

    @ApiModelProperty(hidden=true)
    private String passwordAsLike;

    @ApiModelProperty(hidden=true)
    private String saltAsLike;

    @ApiModelProperty(hidden=true)
    private String userTypeOperator;

    @ApiModelProperty(hidden=true)
    private String userTypeExpression;

    @ApiModelProperty(hidden=true)
    private String isAdminOperator;

    @ApiModelProperty(hidden=true)
    private String isAdminExpression;

    @ApiModelProperty(hidden=true)
    private String nameAsLike;

    @ApiModelProperty(hidden=true)
    private String mobilePhoneAsLike;

    @ApiModelProperty(hidden=true)
    private String emailAsLike;

    @ApiModelProperty(hidden=true)
    private String certSnAsLike;

    @ApiModelProperty(hidden=true)
    private String issuerDnAsLike;

    @ApiModelProperty(hidden=true)
    private String bcaIdOperator;

    @ApiModelProperty(hidden=true)
    private String bcaIdExpression;

    @ApiModelProperty(hidden=true)
    private String bcaAddressAsLike;

    @ApiModelProperty(hidden=true)
    private String bcaOpenTimeOperator;

    @ApiModelProperty(hidden=true)
    private String bcaOpenTimeExpression;

    @ApiModelProperty(hidden=true)
    private String isOpenBlockAccountOperator;

    @ApiModelProperty(hidden=true)
    private String isOpenBlockAccountExpression;

    @ApiModelProperty(hidden=true)
    private String isRealnameOperator;

    @ApiModelProperty(hidden=true)
    private String isRealnameExpression;

    @ApiModelProperty(hidden=true)
    private String isForbiddenOperator;

    @ApiModelProperty(hidden=true)
    private String isForbiddenExpression;

    @ApiModelProperty(hidden=true)
    private String isDeleteOperator;

    @ApiModelProperty(hidden=true)
    private String isDeleteExpression;

    @ApiModelProperty(hidden=true)
    private String realnameTimeOperator;

    @ApiModelProperty(hidden=true)
    private String realnameTimeExpression;

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

    public String getAccountAsLike() {
        return accountAsLike;
    }

    public void setAccountAsLike(String accountAsLike) {
        this.accountAsLike = accountAsLike;
    }

    public String getPhoneAccountAsLike() {
        return phoneAccountAsLike;
    }

    public void setPhoneAccountAsLike(String phoneAccountAsLike) {
        this.phoneAccountAsLike = phoneAccountAsLike;
    }

    public String getEmailAccountAsLike() {
        return emailAccountAsLike;
    }

    public void setEmailAccountAsLike(String emailAccountAsLike) {
        this.emailAccountAsLike = emailAccountAsLike;
    }

    public String getUserCodeAsLike() {
        return userCodeAsLike;
    }

    public void setUserCodeAsLike(String userCodeAsLike) {
        this.userCodeAsLike = userCodeAsLike;
    }

    public String getPasswordAsLike() {
        return passwordAsLike;
    }

    public void setPasswordAsLike(String passwordAsLike) {
        this.passwordAsLike = passwordAsLike;
    }

    public String getSaltAsLike() {
        return saltAsLike;
    }

    public void setSaltAsLike(String saltAsLike) {
        this.saltAsLike = saltAsLike;
    }

    public String getUserTypeOperator() {
        return userTypeOperator;
    }

    public void setUserTypeOperator(String userTypeOperator) {
        this.userTypeOperator = userTypeOperator;
    }

    public String getUserTypeExpression() {
        return userTypeExpression;
    }

    public void setUserTypeExpression(String userTypeExpression) {
        this.userTypeExpression = userTypeExpression;
    }

    public String getIsAdminOperator() {
        return isAdminOperator;
    }

    public void setIsAdminOperator(String isAdminOperator) {
        this.isAdminOperator = isAdminOperator;
    }

    public String getIsAdminExpression() {
        return isAdminExpression;
    }

    public void setIsAdminExpression(String isAdminExpression) {
        this.isAdminExpression = isAdminExpression;
    }

    public String getNameAsLike() {
        return nameAsLike;
    }

    public void setNameAsLike(String nameAsLike) {
        this.nameAsLike = nameAsLike;
    }

    public String getMobilePhoneAsLike() {
        return mobilePhoneAsLike;
    }

    public void setMobilePhoneAsLike(String mobilePhoneAsLike) {
        this.mobilePhoneAsLike = mobilePhoneAsLike;
    }

    public String getEmailAsLike() {
        return emailAsLike;
    }

    public void setEmailAsLike(String emailAsLike) {
        this.emailAsLike = emailAsLike;
    }

    public String getCertSnAsLike() {
        return certSnAsLike;
    }

    public void setCertSnAsLike(String certSnAsLike) {
        this.certSnAsLike = certSnAsLike;
    }

    public String getIssuerDnAsLike() {
        return issuerDnAsLike;
    }

    public void setIssuerDnAsLike(String issuerDnAsLike) {
        this.issuerDnAsLike = issuerDnAsLike;
    }

    public String getBcaIdOperator() {
        return bcaIdOperator;
    }

    public void setBcaIdOperator(String bcaIdOperator) {
        this.bcaIdOperator = bcaIdOperator;
    }

    public String getBcaIdExpression() {
        return bcaIdExpression;
    }

    public void setBcaIdExpression(String bcaIdExpression) {
        this.bcaIdExpression = bcaIdExpression;
    }

    public String getBcaAddressAsLike() {
        return bcaAddressAsLike;
    }

    public void setBcaAddressAsLike(String bcaAddressAsLike) {
        this.bcaAddressAsLike = bcaAddressAsLike;
    }

    public String getBcaOpenTimeOperator() {
        return bcaOpenTimeOperator;
    }

    public void setBcaOpenTimeOperator(String bcaOpenTimeOperator) {
        this.bcaOpenTimeOperator = bcaOpenTimeOperator;
    }

    public String getBcaOpenTimeExpression() {
        return bcaOpenTimeExpression;
    }

    public void setBcaOpenTimeExpression(String bcaOpenTimeExpression) {
        this.bcaOpenTimeExpression = bcaOpenTimeExpression;
    }

    public String getIsOpenBlockAccountOperator() {
        return isOpenBlockAccountOperator;
    }

    public void setIsOpenBlockAccountOperator(String isOpenBlockAccountOperator) {
        this.isOpenBlockAccountOperator = isOpenBlockAccountOperator;
    }

    public String getIsOpenBlockAccountExpression() {
        return isOpenBlockAccountExpression;
    }

    public void setIsOpenBlockAccountExpression(String isOpenBlockAccountExpression) {
        this.isOpenBlockAccountExpression = isOpenBlockAccountExpression;
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

    public String getRealnameTimeOperator() {
        return realnameTimeOperator;
    }

    public void setRealnameTimeOperator(String realnameTimeOperator) {
        this.realnameTimeOperator = realnameTimeOperator;
    }

    public String getRealnameTimeExpression() {
        return realnameTimeExpression;
    }

    public void setRealnameTimeExpression(String realnameTimeExpression) {
        this.realnameTimeExpression = realnameTimeExpression;
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
