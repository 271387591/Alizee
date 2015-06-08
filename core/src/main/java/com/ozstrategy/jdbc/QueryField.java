package com.ozstrategy.jdbc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
public class QueryField {

	private static Log logger = LogFactory.getLog(QueryField.class);
	private String property;
	private String operation;

    public QueryField(String property,String operation) {
        this.property = property;
        this.operation = operation;
    }

	public String getPartHql() {
		String s = "";
		if ("LT".equals(operation)){
			s = (new StringBuilder()).append(" and ").append(property).append(" < ?").toString();
		} else if ("GT".equals(operation)) {
			s = (new StringBuilder()).append(" and ").append(property).append(" > ?").toString();
		} else if ("LE".equals(operation)) {
			s = (new StringBuilder()).append(" and ").append(property).append(" <= ?").toString();
		} else if ("GE".equals(operation)) {
			s = (new StringBuilder()).append(" and ").append(property).append(" >= ?").toString();
		} else if ("LK".equals(operation)) {
			s = (new StringBuilder()).append(" and ").append(property).append(" like ?").toString();
		} else if ("LLK".equals(operation)) {
			s = (new StringBuilder()).append(" and ").append(property).append(" like ?").toString();
		} else if ("RLK".equals(operation)) {
			s = (new StringBuilder()).append(" and ").append(property).append(" like ?").toString();
		} else if ("NULL".equals(operation))
			s = (new StringBuilder()).append(" and ").append(property).append(" is null ").toString();
		else if ("NOTNULL".equals(operation))
			s = (new StringBuilder()).append(" and ").append(property).append(" is not null ").toString();
		else if ("NEQ".equals(operation)){
            s = (new StringBuilder()).append(" and ").append(property).append(" != ?").toString();
        }else if("EQ".equals(operation)){
            s = (new StringBuilder()).append(" and ").append(s).append(property).append(" = ?").toString();
        }
		return s;
	}
    public String getPartOrHql() {
		String s = "";
		if ("LT".equals(operation)){
			s = (new StringBuilder()).append(" or ").append(property).append(" < ?").toString();
		} else if ("GT".equals(operation)) {
			s = (new StringBuilder()).append(" or ").append(property).append(" > ?").toString();
		} else if ("LE".equals(operation)) {
			s = (new StringBuilder()).append(" or ").append(property).append(" <= ?").toString();
		} else if ("GE".equals(operation)) {
			s = (new StringBuilder()).append(" or ").append(property).append(" >= ?").toString();
		} else if ("LK".equals(operation)) {
			s = (new StringBuilder()).append(" or ").append(property).append(" like ?").toString();
		} else if ("LLK".equals(operation)) {
			s = (new StringBuilder()).append(" or ").append(property).append(" like ?").toString();
		} else if ("RLK".equals(operation)) {
			s = (new StringBuilder()).append(" or ").append(property).append(" like ?").toString();
		} else if ("NULL".equals(operation))
			s = (new StringBuilder()).append(" or ").append(property).append(" is null ").toString();
		else if ("NOTNULL".equals(operation))
			s = (new StringBuilder()).append(" or ").append(property).append(" is not null ").toString();
		else if ("NEQ".equals(operation)){
            s = (new StringBuilder()).append(" or ").append(property).append(" != ?").toString();
        }else if("EQ".equals(operation)){
            s = (new StringBuilder()).append(" or ").append(s).append(property).append(" = ?").toString();
        }
		return s;
	}


    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
