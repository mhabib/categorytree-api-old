/**
 * 
 */
package com.vroozi.categorytree.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Mamoon Habib
 *
 */
public class StringUtils {
	
	public static List<String> toList(String stringIds) {
		StringTokenizer tokens = new StringTokenizer(stringIds,",");
        List<String> strList= new LinkedList<String>();

        while (tokens.hasMoreTokens()){
            String token = tokens.nextToken();
            if(token!=null && !"".equalsIgnoreCase(token.trim())){
                strList.add(token);
            }
        }
		return strList;
	}

}
