package com.xwd.study.fileUtil.encoding;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class TransEncoding implements TransEncodingI {
	private FileEncodingI fileEncoding=new FileEncoding();
	@Override
	public int transCode(String rsAbsolutePath, String rsEncoding,
			String deAbsolutePath,String deEncoding) {
		int returnStatus=1;
		OutputStreamWriter of=null;
		InputStreamReader is=null;
		try {
			of =new OutputStreamWriter(new FileOutputStream(deAbsolutePath),deEncoding);
			is=new InputStreamReader(new FileInputStream(rsAbsolutePath),rsEncoding);
			int re=-1;
			while((re=is.read())!=-1){
				of.write(re);
			}
			of.flush();
			returnStatus=0;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(of!=null){
				try {
					of.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					if(is!=null){
						try {
							is.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		return returnStatus;
	}

	@Override
	public int transCode(String absolutePath,String deAbsolutePath,String deEncoding) {
		return transCode(absolutePath,fileEncoding.getEncodingByPath(absolutePath),deAbsolutePath,deEncoding);
	}

}
