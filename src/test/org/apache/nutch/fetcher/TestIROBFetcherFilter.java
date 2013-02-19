package org.apache.nutch.fetcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.TestCase;

public class TestIROBFetcherFilter extends TestCase{
	
	public void testDoFilter(){
		String t_contentStr, charType = "UTF-8";
		String t_regEx = "text/html; charset=([.]?)";
		Pattern t_pattern = Pattern.compile(t_regEx);
		String t_contentType = "text/html;";
		Matcher t_matcher = t_pattern.matcher(t_contentType);
		
		if(t_matcher.find()){
			charType = t_matcher.replaceAll("");
			charType = charType.length() == 0 ? "UTF-8" : charType;
		}
		System.out.println(charType);
	}

}
