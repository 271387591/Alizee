package ${basepakege};
import ${modelNameFullName};
/**
* Created by lihao1 on ${date}.
*/
public class ${className} {
<#if fields?exists>
    <#list fields as key>
    private ${fieldMap[key]} ${key};
    </#list>
</#if>
    public ${className}() {
    }
    public ${className}(${modelName} model) {
    <#if fields?exists>
        <#list fields as key>
        this.${key}=model.get${key?cap_first}();
        </#list>
    </#if>
    }
<#if fields?exists>
    <#list fields as key>
    public ${fieldMap[key]} get${key?cap_first}() {
        return ${key};
    }
    public void set${key?cap_first}(${fieldMap[key]} ${key}) {
        this.${key} = ${key};
    }
    </#list>
</#if>
}
