package org.apache.nutch.fetcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.nutch.protocol.Content;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IROBFetcherFilter{
	public static final Logger LOG = LoggerFactory.getLogger(IROBFetcherFilter.class);
	
	private static String S_FILTER_PATH = "keywords/keywords-filter.txt";
	private static List<String> S_KEYWORDS_LIST = new ArrayList<String>();

	static{
		Configuration t_conf = new Configuration();
		FSDataInputStream t_fsdInputStream = null;
		try {
			FileSystem t_fileSys = FileSystem.get(t_conf);
			Path t_keywords = new Path(S_FILTER_PATH);
			t_fsdInputStream = t_fileSys.open(t_keywords);
			BufferedReader t_bufferReader=new BufferedReader(new InputStreamReader(t_fsdInputStream));
			String t_keyword = t_bufferReader.readLine();
			while(null != t_keyword){
				S_KEYWORDS_LIST.add(t_keyword);
				t_keyword = t_bufferReader.readLine();
			}

		} catch (IOException e) {
			LOG.error(">>>>>>>>>>>>>>>>>>> Build keyword list error!");
		}
		if(S_KEYWORDS_LIST.isEmpty()){
			LOG.warn(">>>>>>>>>>>>>>>>>>> The keyword list is nil!");
		}
	}
	/*
	static{
		S_KEYWORDS_LIST.add("食品");
		S_KEYWORDS_LIST.add("安全");
		S_KEYWORDS_LIST.add("十八大");
	}
	*/
	public static boolean doFilter(final Content content){
		if(null == content){
			return false;
		}
		String t_contentStr, charType = "UTF-8";
		boolean t_filtered = false;
		try {
			String t_regEx = "text/html; charset=([.]?)";
			Pattern t_pattern = Pattern.compile(t_regEx);
			String t_contentType = content.getMetadata().get("Content-Type");
			Matcher t_matcher = t_pattern.matcher(t_contentType);
			
			if(t_matcher.find()){
				charType = t_matcher.replaceAll("");
				charType = charType.length() == 0 ? "UTF-8" : charType;
			}
			
			t_contentStr = new String(content.getContent(), charType);
			if(null == t_contentStr || t_contentStr.length() == 0){
				return false;
			}
			for(Iterator<String> t_iter = S_KEYWORDS_LIST.iterator(); t_iter.hasNext();){
				String t_keyword = t_iter.next();
				if(-1 != t_contentStr.indexOf(t_keyword)){
					t_filtered = true;
					return t_filtered;
				}
			}
			//System.out.println(t_contentStr);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
