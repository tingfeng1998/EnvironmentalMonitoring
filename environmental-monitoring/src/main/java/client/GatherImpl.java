package client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import bean.Environment;
import oracle.sql.TIMESTAMP;
import util.ConfigurationImpl;

public class GatherImpl implements Gather{
	private static List<Environment> list;
	private static Environment environment=null;
	private static Logger logger=Logger.getLogger(GatherImpl.class);
	
	public static String path;
	
//	public static void main(String[] args) throws Exception {
//		File file=new File("src/main/resources/radwtmp");
//		GatherImpl gi=new GatherImpl();
//		List<Environment> data1 = gi.getData(file);
//		System.out.println(data1.get(1));
//	}

	@Override
	public List<Environment> getData(File file) throws Exception {
		FileReader fr=null;
		BufferedReader br=null;
		list=new ArrayList();
		try {
			fr=new FileReader(file);
			br=new BufferedReader(fr);
			String data=null;
			while((data=br.readLine())!=null) {
				String[] str = data.split("[|]");
				long parseLong = Long.parseLong(str[8]);
				Timestamp timestamp=new Timestamp(parseLong);
				float value = Integer.valueOf(str[6].substring(0, 4), 16);
				if(str[3].equals("16")) {
					float Temperature = (float) ((Integer.parseInt(str[6].substring(0, 4), 16) * 0.00268127) - 46.85);
					float Humidity = (float) ((Integer.parseInt(str[6].substring(4, 8), 16)*0.00190735)-6);
					environment=new Environment("温度",str[0],str[1],str[2],str[3],Integer.parseInt(str[4]),str[5],Integer.parseInt(str[7]),Temperature,timestamp);
					list.add(environment);
					environment=new Environment("湿度",str[0],str[1],str[2],str[3],Integer.parseInt(str[4]),str[5],Integer.parseInt(str[7]),Humidity,timestamp);
					list.add(environment);
				}else {

					if(str[3].equals("1280")) {
						environment=new Environment("CO2",str[0],str[1],str[2],str[3],Integer.parseInt(str[4]),str[5],Integer.parseInt(str[7]),value,timestamp);
						list.add(environment);
					}else {
						environment=new Environment("光照强度",str[0],str[1],str[2],str[3],Integer.parseInt(str[4]),str[5],Integer.parseInt(str[7]),value,timestamp);
						list.add(environment);
					}
				}
			}
			logger.debug("采集到了:"+list.size()+"条数据！");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}finally {
			if(br!=null)br.close();
			if(fr!=null)fr.close();
			logger.debug("GatherImpl类中流关闭成功");
		}
		
		return list;
	}

	@Override
	public void init(Properties properties) throws Exception {
		path=properties.getProperty("path");
	}


	
	
	

	
}
