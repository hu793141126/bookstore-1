1.搭建开发环境
	1.1 导入开发包
		mysql驱动   mysql-connector-java-5.0.8-bin.jar
		c3p0连接池  c3p0-0.9.2-pre1.jar 、 mchange-commons-0.2.jar
		dbUtils    commons-dbutils-1.2.jar
		beanUtils  commons-beanutils-1.8.0.jar
		log4j      commons-logging-1.1.1.jar  (commons支持beanUtils)
		文件上传	   commons-fileupload-1.2.1.jar 、 commons-io-1.4.jar
		JavaMail   activation.jar 、 mail.jar
		
	1.2 建立程序包
		cn.mengmei.domain
		cn.mengmei.dao
		cn.mengmei.dao.impl
		cn.mengmei.service
		cn.mengmei.service.impl
		cn.mengmei.web.manager
		cn.mengmei.web.client
		cn.mengmei.utils
		
		WebRoot/manager  保存后台相关的jsp
		WebRoot/client  保存前台相关的jsp
		WebRoot/images  保存网站图片
		
		前台分帧：
		index.jsp -- client -- head.jsp
							   body.jsp
		后台分帧：
		manager.jsp -- manager -- head.jsp
								  left.jsp
								  body.jsp
		
		
		
1.3 创建数据库和表
create database bookstore character set utf8 collate utf8_general_ci;
use bookstore;
		
create table category(
	id varchar(40) primary key,
	name varchar(100) not null unique,
	description varchar(200)
)character set utf8 collate utf8_general_ci;
		
create table book(
	id varchar(40) primary key,
	name varchar(100) not null unique,
	author varchar(40) not null,
	price double not null,
	image varchar(100),
	description varchar(200),
	category_id varchar(40),
	constraint category_id_FK foreign key(category_id) references category(id)
)character set utf8 collate utf8_general_ci;

	
create table user(
	id varchar(40) primary key,
	username varchar(40) not null unique,
	password varchar(40) not null,
	address varchar(200),
	cellphone varchar(20),
	email varchar(255) not null,
	state boolean not null default false
)character set utf8 collate utf8_general_ci;


保存用户激活码的表，激活后将删除对应的一条记录。
create table active(
  user_id varchar(40) primary key,
  verifyCode varchar(80),
  constraint user_id_FK2 foreign key(user_id) references user(id)
)character set utf8 collate utf8_general_ci;


/*order为保留字，不可使用*/
create table orders(
	id varchar(40) primary key,
	ordertime datetime not null,
	state boolean,
	price double,
	user_id varchar(40),
	constraint user_id_FK foreign key(user_id) references user(id)
)character set utf8 collate utf8_general_ci;


create table orderitem(
	id varchar(40) primary key,
	quantity int not null,
	price double,
	book_id varchar(40),
	order_id varchar(40),
	constraint book_id_FK foreign key(book_id) references book(id),
	constraint order_id_FK foreign key(order_id) references orders(id)
)character set utf8 collate utf8_general_ci;

	
1.4 写网站的一些常用工具类
	CharacterEncodingFilter
	HtmlFilter
	JdbcUtils
	DaoFactory
		
	

2.创建实体

3.写dao

4.写service

5.写web层


	
	
	