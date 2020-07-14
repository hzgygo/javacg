package ${relativeProjectPath}.${entityPath};

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ${relativeProjectPath}.${entityPath}.base.Base${entity}Service;

@Service
public class ${entity}Service extends Base${entity}Service implements I${entity}Service{

    private static Logger logger = LoggerFactory.getLogger(${entity}Service.class);

	@Override
	public void init(){

	}
}
