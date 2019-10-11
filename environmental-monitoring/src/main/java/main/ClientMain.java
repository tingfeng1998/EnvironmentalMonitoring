package main;

import java.util.Map;
import java.util.Properties;

import client.ClientImpl;
import util.ConfigurationImpl;

public class ClientMain {

	public static void main(String[] args) {
		ConfigurationImpl cf=new ConfigurationImpl();
		Map<String, Properties> configuration = cf.getConfiguration();
		ClientImpl ci = (ClientImpl)ConfigurationImpl.map2.get("Client");
		
		Properties properties = configuration.get("Client");
		try {
			ci.init(properties);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ci.sendEnvironment();
	}

}
