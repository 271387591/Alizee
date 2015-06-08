package ${pakegeName};

import com.ozstrategy.dao.BaseDao;
import ${daoFullName};
import ${modelFullName};
import com.ozstrategy.service.impl.BaseManagerImpl;
import ${managerFullName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* Created by lihao1 on 6/8/15.
*/
@Service("${firstLowwer}")
public class ${className} extends BaseManagerImpl<${modelName}> implements ${managerName} {
    @Autowired
    private ${daoName} ${firstLowwerDao};

    @Override
    public BaseDao<${modelName}> baseDao() {
        return ${firstLowwerDao};
    }
}
