package com.xwd.study.fileUtil.encoding;

public interface TransEncodingI {
	public int transCode(String rsAbsolutePath,String rsEncoding,String deAbsolutePath,String deEncoding);
	public int transCode(String absolutePath,String deAbsolutePath,String deEncoding);
	
}
