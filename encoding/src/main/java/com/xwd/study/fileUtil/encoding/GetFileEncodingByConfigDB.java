package com.xwd.study.fileUtil.encoding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
/**
 * 
 * @author xwd
 * 注意:属性datasource 和 dbProps 其中一个必须正确赋值
 */
public class GetFileEncodingByConfigDB implements GetFileEncodingByConfigI {
	private static Logger logger = Logger.getLogger(GetFileEncodingByConfigDB.class.getName()); 
	private DataSource datasource=null;
	private Properties dbProps=null;
	private Properties props=null;
	private String getPropsSql="select bize_code,encode from file_encoding  where is_enable='1'";
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
		logger.info("initProps begin");
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=getConnection();
			ps=conn.prepareStatement(getGetPropsSql());
			rs=ps.executeQuery();
			props = new Properties();
			while(rs.next()){
				props.setProperty(rs.getString("BIZE_CODE"),rs.getString("ENCODE"));
			}
			logger.info("initProps success");
		} catch (SQLException e) {
			logger.error("initProps err");
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}finally{
			try {
				if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(ps!=null){
						ps.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}finally{
					try {
						if(conn!=null){
							conn.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	/**
	 * 得到数据库连接   
	 * @return 优先从datasource参数数据库连接   如果datasource==null  从dbProps产生连接   如果两个都为空 返回null
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	private Connection getConnection() throws SQLException, ClassNotFoundException{
		if(datasource!=null){
			return datasource.getConnection();
		}else if(dbProps !=null){
			Class.forName(dbProps.getProperty("driverName"));
			return DriverManager.getConnection(dbProps.getProperty("url"),dbProps.getProperty("username"),dbProps.getProperty("password"));
		}else{
			return null;
		}
	}
	
	public DataSource getDatasource() {
		return datasource;
	}
	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}
	public String getGetPropsSql() {
		return getPropsSql;
	}
	public void setGetPropsSql(String getPropsSql) {
		this.getPropsSql = getPropsSql;
	}
	public Properties getDbProps() {
		return dbProps;
	}
	public void setDbProps(Properties dbProps) {
		this.dbProps = dbProps;
	}
	
}
