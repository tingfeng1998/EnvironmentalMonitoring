# 智能家居环境监测系统

## 技术栈

1、java(IO,NET,JDBC)

2、jdbc(java database connection)

3、oracel

4、xml(dom4j解析)

5、log4j



## 功能

1、定期采集（次/小时）原始环境Log文件，整理成温度,湿度,二氧化碳数据清单，并上传给中心处理系统。

2、中心处理系统（接收系统）侦听并收集采集系统发送的数据信息温度和湿度，并将数据保存的数据库表 t_detail_X(1-31)

3、采集原始环境Log文件，整理成Environment类数据清单

4、将采集系统客户端采集形成的Environment类数据清单传输给位于中心处理系统的服务器端；

5、调用入库模块插入数据

6、通过dom4解析，仿SpringIOC容器，降低模块之间的偶核性
