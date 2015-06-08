package com.ozstrategy.jdbc;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public final class ParserHelper {

	public static final String HQL_VARIABLE_PREFIX = ":";

	public static final String HQL_SEPARATORS = " \n\r\f\t,()=<>&|+-=/*'^![]#~\\";
    public static final BitSet HQL_SEPARATORS_BITSET = new BitSet();

	static {
		for ( int i = 0; i < HQL_SEPARATORS.length(); i++ ) {
			HQL_SEPARATORS_BITSET.set( HQL_SEPARATORS.charAt( i ) );
		}
	}
    public static int firstIndexOfChar(String sqlString, int startindex) {
        for ( int i = startindex, size = sqlString.length(); i < size; i++ ) {
            if ( HQL_SEPARATORS_BITSET.get( sqlString.charAt( i ) ) ) {
                return i;
            }
        }
        return -1;

    }
    public static List<String> parseQueryName(String sqlString){
        List<String> list=new ArrayList<String>();
        int stringLength=sqlString.length();
        for ( int indx = 0; indx < stringLength; indx++ ){
            char c = sqlString.charAt( indx );
            if ( c == ':' ) {
                int right = firstIndexOfChar( sqlString, indx + 1 );
                int chopLocation = right < 0 ? sqlString.length() : right;
                String param = sqlString.substring( indx + 1, chopLocation );
                if ( StringUtils.isEmpty(param) ) {
                    throw new RuntimeException("Space is not allowed after parameter prefix ':' [" + sqlString + "]");
                }
                list.add(param);
            }
        }
        return list;
    }

}