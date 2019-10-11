package server;

import java.util.List;

import bean.Environment;
import util.WossModuleInit;

public interface Server extends WossModuleInit {
	public List<Environment> getEnvironment();
}
