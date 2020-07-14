package ${relativeProjectPath}.${entityPath};

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ${relativeProjectPath}.${entityPath}.base.Base${entity};

@Component(value="${entity?lower_case}")
@Scope("prototype")
public class ${entity} extends Base${entity}{


}
