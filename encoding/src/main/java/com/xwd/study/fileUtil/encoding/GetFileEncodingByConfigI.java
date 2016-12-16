package com.xwd.study.fileUtil.encoding;

public interface GetFileEncodingByConfigI {
	/**
	 * 该方法只能进最大努力判断文件内容的编码方式
	 * @param bizeCode 配置的业务编号
	 * @return 如果 分析成功  返回 文件的编码 名称  如果失败 返回null 
	 */
	public String getEncodingByBizeCode(String bizeCode);
}
