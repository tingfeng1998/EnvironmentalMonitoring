package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import bean.Environment;
import client.ClientImpl;
import util.ConfigurationImpl;

public class ServerImpl implements Server{
	private static Logger logger=Logger.getLogger(ServerImpl.class);
	private static int port;
	
	@Override
	public List<Environment> getEnvironment() {
		ServerSocket serverSocket=null;
		Socket client=null;
		InputStream in=null;
		ObjectInput oi=null;
		List<Environment> list=null;
		try {
			serverSocket=new ServerSocket(port);
			client = serverSocket.accept();
			logger.debug("连接的客户端主机名："+client.getInetAddress().getHostName());
			in = client.getInputStream();
			oi=new ObjectInputStream(in);
			Object readObject = oi.readObject();
			list = (List<Environment>)readObject;
			logger.debug("服务端成功接收客户端发送的数据！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(e.getMessage());
		}finally {
			try {
				if(oi!=null)oi.close();
				if(in!=null)in.close();
				if(client!=null)client.close();
				if(serverSocket!=null)serverSocket.close();
				logger.debug("ServerImpl关闭流成功！");
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
			
		}
		
		return list;
	}

	@Override
	public void init(Properties properties) throws Exception {
		port=Integer.parseInt(properties.getProperty("port"));
	}
	

	
}
