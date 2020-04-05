# 通讯录数据库
```sql
create table `person_info`(
	`person_id` varchar(32) not null,
	`person_name` varchar(64) not null comment '姓名',
	`person_phone1` varchar(11) not null comment '电话号码1',
	`person_phone2` varchar(11) comment '电话号码2',
	`person_wechat` varchar(64) comment '微信号码',
	`person_qq` varchar(20) comment 'QQ号码',
	`person_mail` varchar(64) comment 'e-mail',
	`person_icon` varchar(512) comment '图像',
	`person_company` varchar(64) comment '工作单位',
	`person_address` varchar(64) comment '家庭住址',
	`person_birthday` date comment '生日',
	`person_postcode` varchar(64) comment '邮编',
	`category_type` int not null default 0 comment '所属组',
	`person_remark` varchar(512) comment '备注',
	`book_id` varchar(32) not null,
	primary key (`person_id`)
) comment '联系人表' ;
create table `person_category`(
	`category_id` int not null auto_increment,
	`category_name` varchar(64) not null comment '组类名字',
	`category_type` int not null comment '组类编号',
	primary key (`category_id`)
) comment '组类表' ;
create table `book_info`(
	`book_id` varchar(32) not null,
	`book_number` int not null default 0 comment '联系人数量',
	`book_available` int not null default 100 comment '可用数量',
	primary key (`book_id`)
) comment '通讯录表' ;
create table `user_info`(
	`user_id` varchar(32) not null,
	`user_name` varchar(32) not null comment '昵称',
	`user_password` varchar(64) not null,
	`user_level` int not null default 0 comment '尊贵度',
	`book_id` varchar(32) not null comment '通讯录',
	primary key (`user_id`)
) comment '用户表' ;