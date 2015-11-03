package com.ozstrategy.service.appstore;

import com.ozstrategy.model.appstore.AppStore;
import com.ozstrategy.service.BaseManager;

/**
* Created by lihao1 on 2015-08-30.
*/
public interface AppStoreManager extends BaseManager<AppStore> {
    void saveApp(AppStore appStore);
}
