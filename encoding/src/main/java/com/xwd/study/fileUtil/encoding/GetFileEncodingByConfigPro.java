package com.xwd.study.fileUtil.encoding;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetFileEncodingByConfigPro implements GetFileEncodingByConfigI {
	private String propertyFilePath;
	private Properties props=null;
	@Override
	public String getEncodingByBizeCode(String bizeCode) {
		if(props==null){
			initProps();
		}
		return props.getProperty(bizeCode);
	}
	
	/**
	 * 初始发 props
	 */
	private void initProps(){
		props = new Properties();  
		try {  
		    InputStream in = new BufferedInputStream(getInputStream());  
		    props.load(in);  
		    in.close();  
		} catch (IOException e) {  
		    e.printStackTrace();  
		}
	}
	
	/**
	 * 得到配置文件的输入流
	 * @return 文件输入流
	 */
	private InputStream getInputStream() throws IOException{
		if(propertyFilePath.startsWith("FILE:") || propertyFilePath.startsWith("file:")){
			return new FileInputStream(propertyFilePath.substring(5));        
		}else if(propertyFilePath.startsWith("CLASSPATH:") || propertyFilePath.startsWith("classpath:")){
			return this.getClass().getResourceAsStream(propertyFilePath.substring(10));
		}else{
			return null;
		}
	}
	
	
	
	public String getPropertyFilePath() {
		return propertyFilePath;
	}
	public void setPropertyFilePath(String propertyFilePath) {
		this.propertyFilePath = propertyFilePath;
	}
	
}
