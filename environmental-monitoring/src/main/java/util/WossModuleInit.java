package util;

import java.util.Properties;
/**
 * Simple to Introduction
 * @ProjectName:  物联网环境监测系统
 * @Package: com.briup.environment.util
 * @InterfaceName:  WossModuleInit
 * @Description:  该接口是除配置模块以外的所有模块的父接口。<br/>
 * 			用于模块接收初始化配置信息和注入配置模块。
 * @CreateDate:   2018-1-25 14:28:30
 * @author briup
 * @Version: 1.0
 */
public interface WossModuleInit {
	/**
	 * 将所需要的配置信息传递进该类，该类得到配置信息后进行初始化。<br/>
	 * 建议在执行该类其他方法之前，先执行这个方法
	 * @param properties 一个Properties实例封装了初始化所需的各种配置信息
	 * @throws Exception
	 */
	public void init(Properties properties) throws Exception;
}
