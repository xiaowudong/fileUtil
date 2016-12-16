package com.xwd.study.fileUtil;

import org.junit.Test;

import com.xwd.study.fileUtil.encoding.GetFileEncodingByPath;
import com.xwd.study.fileUtil.encoding.GetFileEncodingByPathI;

public class FileCodeTest {
	@Test
	public void testGetFileEncodingByPath(){
		GetFileEncodingByPathI test=new GetFileEncodingByPath();
		System.out.println(test.getEncodingByPath("C:/Users/xwd/Desktop/闪银合同2007.docx"));
	}
}
