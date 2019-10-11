package client;

import java.io.File;
import java.util.List;

import bean.Environment;
import util.WossModuleInit;

public interface Gather extends WossModuleInit{
	
	public  List<Environment> getData(File file)throws Exception;
}
