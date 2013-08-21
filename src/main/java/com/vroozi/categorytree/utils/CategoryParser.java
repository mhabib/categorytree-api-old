/**
 * 
 */
package com.vroozi.categorytree.utils;

import java.util.regex.Pattern;

/**
 * @author Mamoon Habib
 *
 */
public class CategoryParser {

	public static boolean matches(String catalogCategoryCode1, String matGroup) {
		String[] catalogCategoryCodes = catalogCategoryCode1.split(",");
		for (String catalogCategoryCode : catalogCategoryCodes) {
			if(catalogCategoryCode.contains("-")) {
				catalogCategoryCode = catalogCategoryCode.trim();
				String[] ranges = getCategoryCodeRanges(catalogCategoryCode);
				try {
					long intMat = Long.parseLong(matGroup);
					if(intMat >= Long.parseLong(ranges[0]) && intMat <= Long.parseLong(ranges[1])) {
						return true;
					}
				} catch (NumberFormatException e) {}
			} else {
				if(catalogCategoryCode.endsWith("*")) {
					String regex = catalogCategoryCode.substring(0, catalogCategoryCode.length()-1) +".*";
					if(Pattern.matches(regex, matGroup)) {
						return true;
					}
				} else if (catalogCategoryCode.equals(matGroup)) {
					return true;
				}
			}
		}
		return false;
	}
	
public static void main(String[] args) {
	System.out.println("assdd*".substring(0, "assdd*".length()-1));
	String catalogCategoryCode = "45*";
	String matGroup = "451596789ee";
	String regex = catalogCategoryCode.substring(0, catalogCategoryCode.length()-1) +".*";
	System.out.println("catalogCategoryCode: "+catalogCategoryCode+", matGroup: "+matGroup+", matches: "+Pattern.matches(regex, matGroup));
	
}
	public static String[] getCategoryCodeRanges(String catalogCategoryCode) {
		int indexHyphen = catalogCategoryCode.indexOf('-');
		String unspscRangeStart = catalogCategoryCode.substring(0,indexHyphen);
		String unspscRangeEnd = catalogCategoryCode.substring(indexHyphen+1,catalogCategoryCode.length());
//        unspscRangeStart = createRangeEnds(unspscRangeStart, companyCategoryCode.length(), "0");
//        unspscRangeEnd = createRangeEnds(unspscRangeEnd, companyCategoryCode.length(), "9");
		unspscRangeStart = createRangeEnds(unspscRangeStart, 8, "0");
		unspscRangeEnd = createRangeEnds(unspscRangeEnd, 8, "9");
        
		String[] newColumnData = new String[2];
		newColumnData[0] = unspscRangeStart;
        newColumnData[1] = unspscRangeEnd;

        return newColumnData;
    }

    public static String createRangeEnds(String input, int unspscCodeLength, String toAppend)
    {
        String returnCode = input;
        if (input.contains("*"))
        {
            int indexAsterisk = input.indexOf('*');
            returnCode = input.substring(0,indexAsterisk);

            int AppendLoop = unspscCodeLength-indexAsterisk;

            for (int i=0; i<AppendLoop; i++)
            {
                returnCode += toAppend;
            }
        }

        return returnCode;
    }
}
