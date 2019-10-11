package util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ConfigurationImpl implements Configuration{
	private static Map<String,Properties> map=null;
	public static Map<String,Object> map2=null;

	
	@Override
	public Map<String, Properties> getConfiguration() {
		SAXReader reader=null;
		Properties properties=null;
		File file=new File("src/main/resources/config.xml");
		try {
			map=new HashMap<String, Properties>();
			map2=new HashMap<String,Object>();
			reader=new SAXReader();
			Document doc = reader.read(file);
			Element root = doc.getRootElement();
			List<Element> elements = root.elements();
			properties=new Properties();
			for(Element ele:elements) {
				String na = ele.getQName().getName();
				Attribute attribute = ele.attribute("class");
				String className = attribute.getValue();

				Class<?> forName = Class.forName(className);
				
				Object obj = forName.newInstance();
				List<Element> elements2 = ele.elements();
				for(Element ele2:elements2) {
					String name = ele2.getQName().getName();
					System.out.println(name);
					String text = ele2.getText();
					System.out.println(text);
					properties.setProperty(name, text);
				}
				map.put(na, properties);
				map2.put(na,obj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	public static void main(String[] args) {
		ConfigurationImpl cf=new ConfigurationImpl();
		cf.getConfiguration();
	}

}
