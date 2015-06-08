package ${pakegeName};

import com.ozstrategy.dao.impl.BaseDaoImpl;
import ${daoFullName};
import ${modelFullName};
import org.springframework.stereotype.Repository;


/**
* Created by lihao1 on ${date}.
*/
@Repository("${firstLowwer}")
public class ${className} extends BaseDaoImpl<${modelName}> implements ${daoName}{

    public ${className}() {
    super(${modelName}.class);
    }

}