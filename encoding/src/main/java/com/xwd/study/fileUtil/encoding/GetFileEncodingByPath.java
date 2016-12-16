package com.xwd.study.fileUtil.encoding;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

import java.io.File;

public class GetFileEncodingByPath implements GetFileEncodingByPathI {

	@Override
	public String getEncodingByPath(String filePath) {
		String fileEncoding=null;
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		detector.add(new ParsingDetector(false));
		detector.add(JChardetFacade.getInstance());
		detector.add(ASCIIDetector.getInstance());
		detector.add(UnicodeDetector.getInstance());
		 java.nio.charset.Charset charset = null;
		 File f = new File(filePath);
		 try {
			 charset = detector.detectCodepage(f.toURI().toURL());
			 if (charset != null){
				 fileEncoding=charset.name();
			 }else{
				 fileEncoding=null; 
			 }
		 } catch (Exception ex) {
			 ex.printStackTrace();
		 }
		return fileEncoding;
	}
}
