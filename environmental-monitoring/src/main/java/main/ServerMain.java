package main;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import bean.Environment;
import client.ClientImpl;
import server.DBStoreImpl;
import server.ServerImpl;
import util.ConfigurationImpl;

public class ServerMain {
	private static Logger logger=Logger.getLogger(ServerMain.class);
	
	public static void main(String[] args) {
		ConfigurationImpl cf=new ConfigurationImpl();
		Map<String, Properties> configuration = cf.getConfiguration();
		ServerImpl si = (ServerImpl)ConfigurationImpl.map2.get("Server");
		Properties properties = configuration.get("Server");
		try {
			si.init(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Environment> list = si.getEnvironment();
		logger.debug("ServerMain中获取的list尺寸:"+list.size());
		
		DBStoreImpl db = (DBStoreImpl)ConfigurationImpl.map2.get("DBStore");
		Properties properties2 = configuration.get("DBStore");		
		try {
			db.init(properties2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		db.doStore(list);
		logger.debug("ServerMain正常执行结束！");
	}

}
