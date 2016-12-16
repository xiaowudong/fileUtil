package com.xwd.study.fileUtil.encoding;

public interface GetFileEncodingByPathI {
	/**
	 * 该方法只能进最大努力判断文件内容的编码方式
	 * @param filePath  待分析文件的绝对路径
	 * @return 如果 分析成功  返回 文件的编码 名称  如果失败 返回null 如果方法发生异常 返回null
	 */
	public String getEncodingByPath(String filePath);
}
