# Introduction #
TipDM建模平台，是由广州泰迪智能科技股份有限公司研发并开源的数据挖掘工具，TipDM建模平台提供数据丰富的数据预处理、
数据分析与数据挖掘组件，帮助广大中小企业快速建立数据挖掘工程，提升数据处理的效能。同时，我们也在积极
推动大数据挖掘社区建设，构建校企对接桥梁，为企业精准推送优质大数据挖掘人才；在产业需求的基础上推动高
校的人才培养工作。

# Documentation #
[使用文档](http://python.tipdm.org/bzzx/index.jhtml?n=%E6%93%8D%E4%BD%9C%E6%96%87%E6%A1%A3)

# Communication #
[社区交流](http://bbs.tipdm.org)

# Features #
1. 基于Python，用于数据挖掘建模。
2. 使用直观的拖放式图形界面构建数据挖掘工作流程，无需编程。
3. 支持多种数据源，包括CSV文件和关系型数据库。
4. 支持挖掘流程每个节点的结果在线预览。
5. 提供5大类共40种算法组件，包括数据预处理、分类、聚类等数据挖掘算法。
6. 支持新增/编辑算法组件，自定义程度高。
7. 提供众多公开可用的数据挖掘示例工程，一键创建，快速运行。
8. 提供完善的交流社区，提供数据挖掘相关的学习资源（数据、代码和模型等）。

# Screenshot #
![输入图片说明](https://images.gitee.com/uploads/images/2019/0617/112412_0a4abed4_4964548.jpeg "1.jpg")
![输入图片说明](https://images.gitee.com/uploads/images/2019/0617/112438_656d0053_4964548.jpeg "2.jpg")
![输入图片说明](https://images.gitee.com/uploads/images/2019/0617/112450_a0ff4eb8_4964548.jpeg "3.jpg")
![输入图片说明](https://images.gitee.com/uploads/images/2019/0617/112509_238a7067_4964548.jpeg "4.jpg")

# Development #
## 环境依赖 ##
- [Oracle JDK 1.8.x及以上版本](http://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-javase6-419409.html)，安装详情可参考[JDK安装教程](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html)
- [Apache-Maven 3.x及以上版本](http://maven.apache.org)，安装详情可参考[Maven安装教程](https://maven.apache.org/install.html)
- [Apache-Tomcat 8.x及以上版本](http://tomcat.apache.org)
- [PostgreSQL 9.4.x及以上版本](http://www.postgresql.org/download/)
- [Python 3.6.x版本及以上版本](https://www.python.org/)
- [Redis 3.2.x及以上版本](https://redis.io/)
- [Alibaba DataX](https://github.com/alibaba/DataX)
- IntelliJ Idea IDE(可选，您也可以使用其他IDE，如eclipse、NetBeans)，安装详情可参考[IntelliJ安装教程](https://www.jetbrains.com/help/idea/installing-and-launching.html)

### 安装Java开发环境 ###
下载JDK 1.8.x和Apache-Maven并安装，设置JAVA_HOME和PATH环境变量，如添加以下到~/.bashrc中（不同的操作系统，环境变量的设置方式有所不同，请根据自己的情况设置环境变量）： 
    echo 'export JAVA_HOME=~/jdk_1.8.0_171' >> ~/.bashrc  
    echo 'export PATH=$JAVA_HOME/bin:~/apache-maven-3.3.9/bin:$PATH' >> ~/.bashrc
    . ~/.bashrc

检查java与maven环境及版本是否正确安装，运行如下命令检查：
> tipdm: ~ devp$ javac -version  
> javac 1.8.0_171  
> tipdm: ~ devp$ mvn -version  
> Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-11T00:41:47+08:00)   

如果返回"-bash: xxx: command not found"，或者版本号低于TipDM要求，请确认依赖软件是否安装正确，相应的环境变量是否设置生效。

### 安装Python ###

   下载Python 3.6.x，并完成环境变量的配置。
   
   required library:
   
   	arch==4.3.1
	docx==0.2.4
	gensim==3.6.0
	graphviz==0.10.1
	jieba==0.38
	jieba-fast==0.53
	Keras==2.2.4
	matplotlib==2.2.0
	numpy==1.14.2
	pandas==0.23.4
	pdfminer3k==1.3.1
	pyclust==0.2.0
	pydot==1.2.4
	python-docx==0.8.10
	scikit-learn==0.19.1
	scipy==0.19.1
	SQLAlchemy==1.2.0
	statsmodels==0.9.0
	tensorflow==1.12.0
	thulac==0.2.0
	wordcloud==1.5.0
	
#### 批量安装依赖库 ####
将上面的内容粘贴至requirements.txt（**可随意命名**），进入命令行，CD到requirements.txt所在的目录下，执行命令：

	pip install -r requirements.txt
   	
### 安装PostgreSQL ###
  
   下载9.4x并完成安装。[PostgreSQL中文社区](http://www.postgres.cn)
## 快速入门 ##
### 构建项目 ###
#### backend ####
下载源代码至本地,按照maven格式将源码导入IDE（Eclipse或IDEA）
##### 数据初始化 #####
首先在本机上要有PostgreSQL服务，使它监听127.0.0.1的5432端口（默认安装和初始化的PostgreSQL即监听127.0.0.1的5432端口），然后使用PG的管理员身份(一般是初始化PG数据库的linux账号,这里是postgres账户)，运行$TipDM_HOME/WEB-INF/classes/sql/目录下的.sql脚本，初始化元数据：

    psql -h 127.0.0.1 -p 5432 -U postgres -d tipdm_DB -f initData.sql
    psql -h 127.0.0.1 -p 5432 -U postgres -d tipdm_DB -f quartz_postgres.sql

##### 系统配置 #####
配置文件说明：

	sysconfig/database.properties			数据库配置文件
	sysconfig/dbSupport.config			在此配置系统可支持的数据库类型
	sysconfig/system.properties			系统的相关配置
	sysconfig/redis.properties			Redis
	PyConnection.xml				Python服务(该文件在sysconfig目录的上层)

##### 编译 #####
	cd进入到源码根目录，使用maven进行编译即可，源码结构如下：
	framework-common		公共模块
	framework-model  		数据模型
	framework-persist 		数据持久化
	framework-service 		service
	tipdm-server  			后台服务

看到
> BUILD SUCCESS  
> Total time: ...  

表示编译成功，生成的二进制包在$HOME/target/目录中。 

##### 部署 #####
部署详情参考[IntelliJ IDEA – Run / debug web application on Tomcat](https://www.mkyong.com/intellij/intellij-idea-run-debug-web-application-on-tomcat/)

# FAQ #
[http://python.tipdm.org/bzzx/index.jhtml?n=%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98](http://python.tipdm.org/bzzx/index.jhtml?n=%E5%B8%B8%E8%A7%81%E9%97%AE%E9%A2%98)
