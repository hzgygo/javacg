CREATE TABLE `role` ( 
	`id` bigint NOT NULL,
	`org_id` bigint NOT NULL,
	`name` varchar(50) NOT NULL,
	`code` varchar(50) DEFAULT NULL,
	`is_forbidden` tinyint DEFAULT 0 COMMENT '0：否1：是',
	`is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '角色状态：0：禁用1：开启',
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`modify_time` timestamp DEFAULT '0000-00-00 00:00:00',
	`comments` varchar(200) DEFAULT NULL,
	 primary key (id)
);CREATE TABLE `user_role_relation` ( 
	`user_id` bigint NOT NULL COMMENT '用户随机编号：系统生成',
	`role_id` bigint NOT NULL,
	 primary key (user_id,role_id)
);CREATE TABLE `user` ( 
	`id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键Id',
	`org_id` bigint NOT NULL,
	`account` varchar(50) NOT NULL COMMENT '用户登录账号',
	`phone_account` varchar(15) DEFAULT NULL,
	`email_account` varchar(100) DEFAULT NULL,
	`user_code` varchar(64) NOT NULL,
	`password` varchar(100) NOT NULL COMMENT '用户密码',
	`salt` varchar(10) NOT NULL,
	`user_type` tinyint NOT NULL COMMENT '1：平台方用户2：非平台用户',
	`is_admin` tinyint NOT NULL DEFAULT 0 COMMENT '1：超级管理员0：普通管理员',
	`name` varchar(50) NOT NULL COMMENT '用户姓名。',
	`mobile_phone` varchar(15) DEFAULT NULL COMMENT '移动电话号码',
	`email` varchar(200) DEFAULT NULL COMMENT '邮件地址',
	`cert_sn` varchar(50) DEFAULT NULL,
	`issuer_dn` varchar(256) DEFAULT NULL,
	`bca_id` bigint DEFAULT 0 COMMENT '0：否1：是',
	`bca_address` varchar(128) DEFAULT 0 COMMENT '0：否1：是',
	`bca_open_time` timestamp DEFAULT '0000-00-00 00:00:00' COMMENT '0：否1：是',
	`is_open_block_account` tinyint DEFAULT 0 COMMENT '0：否1：是',
	`is_realname` tinyint DEFAULT 0,
	`is_forbidden` tinyint NOT NULL DEFAULT 0 COMMENT '0：否1：是',
	`is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '1：是0：否',
	`realname_time` timestamp DEFAULT '0000-00-00 00:00:00',
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间：记录用户的创建时间。',
	`modify_time` timestamp DEFAULT '0000-00-00 00:00:00',
	`comments` varchar(200) DEFAULT NULL,
	 primary key (id),
	 unique key AK_Key_2 (account), 
 unique key AK_Key_3 (phone_account), 
 unique key AK_Key_4 (email_account), 
 unique key AK_Key_5 (user_code)
);CREATE TABLE `role_permit_relation` ( 
	`role_id` bigint NOT NULL,
	`permit_id` bigint NOT NULL COMMENT '系统权限代码，编码为数字，具有一定的规律。格式如下：1，11，111，112，12，121，122，13，131，132……',
	 primary key (role_id,permit_id)
);CREATE TABLE `permission` ( 
	`id` bigint NOT NULL,
	`code` bigint NOT NULL,
	`parent_code` bigint NOT NULL COMMENT '父权限代码，编码也为数字，格式同上（code）。',
	`name` varchar(100) NOT NULL COMMENT '权限名称，用2-6个汉字进行规整描述，以方便在页面显示。',
	`parent_name` varchar(100) DEFAULT NULL COMMENT '权限名称，用2-6个汉字进行规整描述，以方便在页面显示。',
	`service_code` varchar(100) DEFAULT NULL,
	`url_path` varchar(200) DEFAULT NULL,
	`wildcard_path` varchar(100) DEFAULT NULL COMMENT '树形结构描述关系',
	`model_name` varchar(100) DEFAULT NULL,
	`entity_name` varchar(100) DEFAULT NULL,
	`method_name` varchar(200) DEFAULT NULL,
	`view_name` varchar(200) NOT NULL,
	`code_key` varchar(100) DEFAULT NULL,
	`tree_level` int NOT NULL COMMENT '此权限是否有效。1：有效0：无效',
	`tree_path` varchar(500) NOT NULL COMMENT '树形结构描述关系',
	`auth_type` tinyint DEFAULT 0 COMMENT '0：授权权限1：非授权权限',
	`is_show` tinyint DEFAULT NULL COMMENT '0：否1：是',
	`is_child_node` tinyint NOT NULL DEFAULT 1 COMMENT '0：否1：是',
	`is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '1：是0：否',
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间：角色的创建时间，为以后进行跟踪查询。',
	`modify_time` timestamp DEFAULT '0000-00-00 00:00:00',
	`comments` varchar(200) DEFAULT NULL,
	 primary key (id),
	 unique key AK_Key_2 (code)
);CREATE TABLE `organization` ( 
	`id` bigint NOT NULL COMMENT '父机构ID：机构树状结构父节点标识',
	`parent_id` bigint NOT NULL,
	`org_code` varchar(50) NOT NULL COMMENT '机构代码',
	`org_name` varchar(100) NOT NULL COMMENT '机构名称：定义记录机构的名称。',
	`org_type` tinyint NOT NULL COMMENT '1：平台方2：企业方',
	`email` varchar(100) DEFAULT NULL,
	`mobile_phone` varchar(15) DEFAULT NULL,
	`domain_name` varchar(100) DEFAULT NULL,
	`logo` varchar(200) DEFAULT NULL,
	`is_realname` tinyint DEFAULT 0 COMMENT '0：否1：是',
	`is_forbidden` tinyint NOT NULL DEFAULT 0 COMMENT '0：否1：是',
	`tree_level` tinyint NOT NULL,
	`tree_path` varchar(500) NOT NULL,
	`is_child_node` tinyint NOT NULL DEFAULT 1 COMMENT '1：是0：否',
	`blockchain_address` varchar(100) DEFAULT NULL,
	`is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '0：未删除1：删除',
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间：机构创建的时间，以备后来跟踪查询',
	`modify_time` timestamp DEFAULT '0000-00-00 00:00:00',
	`comments` varchar(200) DEFAULT NULL,
	 primary key (id),
	 unique key AK_Key_2 (org_code)
);CREATE TABLE `log` ( 
	`id` bigint NOT NULL COMMENT '操作日志主键ID：操作日志记录表主键。',
	`user_id` bigint DEFAULT NULL,
	`user_name` varchar(100) DEFAULT NULL,
	`operate_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间：日志记录的详细时间。',
	`log_type` varchar(100) NOT NULL,
	`content` varchar(256) NOT NULL COMMENT '操作内容：系统用户操作的具体功能步骤的记录。',
	`access_ip` varchar(20) NOT NULL COMMENT '访问IP：系统用户远端访问的IP地址。',
	`is_delete` tinyint NOT NULL DEFAULT 0,
	`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	 primary key (id)
); alter table role_permit_relation add constraint  FK_Reference_role_auth_relation foreign key  (RoleId)  references role (Id)  on delete restrict on update restrict;
 alter table role_permit_relation add constraint  FK_Reference_auth_role_relation foreign key  (PermitId)  references permission (Id)  on delete restrict on update restrict;
 alter table user_role_relation add constraint  FK_Reference_role_use_relation foreign key  (RoleId)  references role (Id)  on delete restrict on update restrict;
 alter table user_role_relation add constraint  FK_Reference_user_role_relation foreign key  (UserId)  references user (Id)  on delete restrict on update restrict;
 alter table user add constraint  FK_Reference_organization_user foreign key  (OrgId)  references organization (Id)  on delete restrict on update restrict;
