package com.xwd.study.fileUtil.encoding;

public interface FileEncodingI extends GetFileEncodingByPathI,GetFileEncodingByConfigI {
	public int autoPre=1;
	public int cfgPre=2;	
	/**
	 * 该方法只能进最大努力判断文件内容的编码方式
	 * @param filePath 待分析文件的绝对路径
	 * @param bizeCode 配置的业务编号  
	 * @param pre 自动优先 还是 配置优先  1自动优先 ,2配置优先 ,如果 pre 参数有误  配置优先  优先失败自动尝试优先级低的
	 * @return 如果 分析成功  返回 文件的编码 名称  如果失败 返回null 如果方法发生异常 返回null
	 */
	public String getEncoding(String filePath,String bizeCode,int pre);

}
