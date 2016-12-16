package com.xwd.study.fileUtil.encoding;

import java.io.File;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

public class FileEncoding implements FileEncodingI {
	public static Logger logger=Logger.getLogger(FileEncoding.class);
	// 根据文件自身判断编码
	private GetFileEncodingByPathI getFileEncodingByPath = null;
	// 根据业务编码获得文件编码
	private GetFileEncodingByConfigI getFileEncodingByConfig = null;

	@Override
	public String getEncodingByPath(String filePath) {
		String fileEncoding = null;
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		detector.add(new ParsingDetector(false));
		detector.add(JChardetFacade.getInstance());
		detector.add(ASCIIDetector.getInstance());
		detector.add(UnicodeDetector.getInstance());
		Charset charset = null;
		File f = new File(filePath);
		try {
			logger.info(f.toURI().toURL());
			charset = detector.detectCodepage(f.toURI().toURL());
			if (charset != null) {
				fileEncoding = charset.name();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		logger.info(fileEncoding);
		return fileEncoding;
	}

	@Override
	public String getEncoding(String filePath, String bizeCode, int pre) {
		String encoding = null;
		if (pre == FileEncodingI.autoPre) {// 自动优先
			encoding = getEncodingByPath(filePath);
			if (encoding == null) {
				encoding = getFileEncodingByConfig
						.getEncodingByBizeCode(bizeCode);
			}
		} else {// 配置优先
			encoding = getFileEncodingByConfig.getEncodingByBizeCode(bizeCode);
			if (encoding == null) {
				encoding = getEncodingByPath(filePath);
			}
		}
		return encoding;
	}

	@Override
	public String getEncodingByBizeCode(String bizeCode) {
		return getFileEncodingByConfig.getEncodingByBizeCode(bizeCode);
	}

	public GetFileEncodingByConfigI getGetFileEncodingByConfig() {
		return getFileEncodingByConfig;
	}

	public void setGetFileEncodingByConfig(
			GetFileEncodingByConfigI getFileEncodingByConfig) {
		this.getFileEncodingByConfig = getFileEncodingByConfig;
	}

	public GetFileEncodingByPathI getGetFileEncodingByPath() {
		return getFileEncodingByPath;
	}

	public void setGetFileEncodingByPath(
			GetFileEncodingByPathI getFileEncodingByPath) {
		this.getFileEncodingByPath = getFileEncodingByPath;
	}

}
