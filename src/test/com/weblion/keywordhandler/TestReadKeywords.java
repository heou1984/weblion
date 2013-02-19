package com.weblion.keywordhandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import junit.framework.TestCase;

public class TestReadKeywords extends TestCase{
	
	public void readKeywordsTest(){

		String t_uri = "hftp://122.49.39.252:50070/";

	    System.out.println( "uri: " + t_uri );           
	    Configuration conf = new Configuration();

	    FileSystem fs;
		try {
			fs = FileSystem.get( URI.create( t_uri ), conf );
			Path t_keywords = new Path("/user/prodUser/keywords/keywords-filter.txt");
			FSDataInputStream t_fsdInputStream = fs.open(t_keywords);
			BufferedReader t_bufferReader=new BufferedReader(new InputStreamReader(t_fsdInputStream));
			String t_keyword = t_bufferReader.readLine();
			while(null != t_keyword){
				t_keyword = t_bufferReader.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

}
