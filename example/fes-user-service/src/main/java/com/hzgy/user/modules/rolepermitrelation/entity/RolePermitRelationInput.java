package com.hzgy.user.modules.rolepermitrelation.entity;

import com.hzgy.user.modules.rolepermitrelation.base.BaseRolePermitRelationInput;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.BindingResult;

@ApiModel(value = "请求参数实体")
public class RolePermitRelationInput extends BaseRolePermitRelationInput {


    /**
    * 自定义验证业务逻辑
    *
    * @param groups 分组名称，可以为空
    * @param args   验证的参数
    * @return 返回 BeanPropertyBindingResult 对象
    */
    @Override
    public BindingResult _validated(Class<?>[] groups, Object[] args) {
        return null;
    }
}
