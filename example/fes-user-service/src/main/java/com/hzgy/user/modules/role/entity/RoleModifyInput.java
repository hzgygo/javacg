package com.hzgy.user.modules.role.entity;

import com.hzgy.user.modules.role.base.BaseRoleInput;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.BindingResult;

@ApiModel(value = "修改数据请求参数实体")
public class RoleModifyInput extends BaseRoleInput {


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
