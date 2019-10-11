package client;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import bean.Environment;
import util.ConfigurationImpl;

public class ClientImpl implements Client{
	private static Logger logger=Logger.getLogger(ClientImpl.class);
	
	private static String ip;
	private static int port;
	
	@Override
	public void sendEnvironment(){
		ConfigurationImpl cImpl=new ConfigurationImpl();
		Map<String, Properties> configuration = cImpl.getConfiguration();
		GatherImpl gi = (GatherImpl)ConfigurationImpl.map2.get("Gather");
		Properties properties2 = configuration.get("Gather");
		
		OutputStream os = null;
		ObjectOutputStream oos=null;
		
		Socket socket=null;
		try {
			gi.init(properties2);
			
			socket=new Socket(ip,port);
			
			File file=new File(gi.path);
			List<Environment> data = gi.getData(file);
			os = socket.getOutputStream();
			oos=new ObjectOutputStream(os);
			oos.writeObject(data);
			logger.debug("客户端传送数据给服务端成功");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}finally {
			try {
				if(oos!=null)oos.close();
				if(os!=null)os.close();
				if(socket!=null)socket.close();
				logger.debug("ClientImpl关闭流成功！");
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
	}

	@Override
	public void init(Properties properties) throws Exception {
		ip = properties.getProperty("ip");
		port = Integer.parseInt(properties.getProperty("port"));
	}

}
