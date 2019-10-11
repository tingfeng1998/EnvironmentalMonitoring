package server;

import java.sql.Statement;
import java.util.List;

import bean.Environment;
import util.WossModuleInit;

public interface DBStore extends WossModuleInit{
	void doStore(List<Environment> list);
}
