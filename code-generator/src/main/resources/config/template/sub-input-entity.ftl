package ${relativeProjectPath}.${entityPath}.entity;

import ${relativeProjectPath}.${entityPath}.base.Base${entity}Input;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.BindingResult;

@ApiModel(value = "${methodDesc!''}请求参数实体")
public class ${entity}${methodName!''}Input extends Base${entity}Input {


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
