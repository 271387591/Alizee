package com.ozstrategy.service.system;

import com.ozstrategy.model.system.ApplicationConfig;
import com.ozstrategy.service.BaseManager;

/**
* Created by lihao1 on 2015-06-10.
*/
public interface ApplicationConfigManager extends BaseManager<ApplicationConfig> {
    ApplicationConfig getConfig(String key);
    String getValue(String key);
}
