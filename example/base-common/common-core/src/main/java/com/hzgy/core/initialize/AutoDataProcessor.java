package com.hzgy.core.initialize;


import com.hzgy.core.service.IService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AutoDataProcessor {

    /**
     * 业务类存放容器
     */
    private List<IService> serviceContainer;

    /**
     * 向容器中增加执行业务对象
     * @param service 业务实现类
     */
    public void addService(IService service){
        if (serviceContainer == null){
            serviceContainer = new ArrayList<>();
        }
        serviceContainer.add(service);
    }

    /**
     * 执行业务实现类
     */
    public void execute(){
        if (serviceContainer != null){
            for (IService s:serviceContainer){
                s.init();
            }
        }
    }

}
