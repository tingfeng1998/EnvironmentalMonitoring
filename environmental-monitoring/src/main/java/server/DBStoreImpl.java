package server;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import bean.Environment;
import util.ConfigurationImpl;

public class DBStoreImpl implements DBStore {
	private static String driverClass;
	private static String url;
	private static String user;
	private static String password;
	private static int batchSize;
	
	private static Logger logger=Logger.getLogger(DBStoreImpl.class);

	static {
		Properties p = new Properties();
		InputStream in = DBStoreImpl.class.getResourceAsStream("/jdbc.properties");
		try {
			p.load(in);
			driverClass = p.getProperty("driver");
			url = p.getProperty("url");
			user = p.getProperty("username");
			password = p.getProperty("password");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doStore(List<Environment> list) {
		Connection conn=null;
		Statement st=null;
		try {
			Class.forName(driverClass);
			conn = DriverManager.getConnection(url, user, password);
			logger.debug("数据库连接成功");
			st = conn.createStatement();
			
			int j=1;
			int s=list.size()%batchSize;
			for (Environment en : list) {
				int i = en.getGather_date().getDate();//也可以Calendar c=Calendar.getInstance();c.setTime(date类型); int day=c.get(Calender.Day_OF_MONTH);
				String sql = "insert into e_detail_" + i + " values('"+en.getName()+"','"+en.getSendId()+"','"+en.getSmId()+"','"+en.getQmId()+"','"+en.getAddress()+"',"+en.getCount()+",'"+en.getOrdernumber()+"',"+en.getStatus()+","+en.getData()+",'"+en.getGather_date()+"')";
				
				st.addBatch(sql);
				if(j%batchSize==0) {
					st.executeBatch();
				}else if(j>(list.size()/batchSize)*batchSize && j%batchSize==s) {
					st.executeBatch();
					logger.debug("数据全部存入数据库！");
				}
				j++;
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}finally {
			try {
				if(st!=null)st.close();
				if(conn!=null)conn.close();
				logger.debug("DBStoreImpl关闭流成功！");
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		

	}

	@Override
	public void init(Properties properties) throws Exception {
		batchSize=Integer.parseInt(properties.getProperty("batchSize"));
	}

}
