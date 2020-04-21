DROP TABLE IF EXISTS `person_info`;
CREATE TABLE `person_info`
(
    `person_id`       varchar(32)  NOT NULL,
    `person_name`     varchar(64)  NOT NULL COMMENT '姓名',
    `person_phone1`   varchar(11)  NOT NULL COMMENT '电话号码1',
    `person_phone2`   varchar(11) COMMENT '电话号码2',
    `person_wechat`   varchar(64) COMMENT '微信号码',
    `person_qq`       varchar(20) COMMENT 'QQ号码',
    `person_mail`     varchar(64) COMMENT 'e-mail',
    `person_icon`     varchar(512) COMMENT '图像',
    `person_company`  varchar(64) COMMENT '工作单位',
    `person_address`  varchar(64) COMMENT '家庭住址',
    `person_birthday` date COMMENT '生日',
    `person_postcode` varchar(64) COMMENT '邮编',
    `person_remark`   varchar(512) COMMENT '备注',
    `user_id`         varchar(32)  NOT NULL,
    `category_id`     varchar(128) NOT NULL COMMENT '所属组',
    PRIMARY KEY (`person_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT '联系人表';

INSERT INTO `person_info` (`person_id`, `person_name`, `person_phone1`, `person_phone2`, `person_wechat`, `person_qq`,
                           `person_mail`, `person_icon`, `person_company`, `person_address`, `person_birthday`,
                           `person_postcode`, `person_remark`, `category_id`, `user_id`)
VALUES ('10010', '小红', '13333333333', '13333333333', 'aa77', '111111111', '111111111@qq.com', 'src1', '保安', '北京',
        '1983-11-24 00:00:00', '511111', '爱人', '1', '10086'),
       ('10011', '小青', '13333333334', '', 'aa78', '111111112', '111111112@qq.com', 'src2', '程序员', '北京',
        '1977-11-23 00:00:00', '511111', '仇人', '0', '10086'),
       ('10012', '小紫', '13333333335', '', 'aa79', '111111113', '111111113@qq.com', 'src3', '保镖', '北京',
        '1999-11-23 00:00:00', '511115', '情人', '1', '10086'),
       ('10013', '小黄', '13333333336', '13333333336', 'aa80', '111111114', '111111114@qq.com', 'src4', '教师', '北京',
        '1978-11-23 00:00:00', '511111', '同事1', '2', '10086'),
       ('10014', '小橙', '13333333337', '', 'aa81', '111111115', '111111115@qq.com', 'src5', '爱国集团', '北京',
        '1983-9-20 00:00:00', '511111', '同事2', '2', '10086'),
       ('10015', '小黑', '13333333338', '13333333338', 'aa82', '111111116', '111111116@qq.com', 'src6', '水军团', '北京',
        '1990-11-23 00:00:00', '511111', '黑黑', '2', '10086'),
       ('10016', '小绿', '13333333339', '', 'aa83', '111111117', '111111117@qq.com', 'src7', '共青团', '北京',
        '1989-10-23 00:00:00', '511115', '绿帽子', '0', '10086'),
       ('10017', '小白', '13333333340', '13333333340', 'aa84', '111111118', '111111118@qq.com', 'src8', '党中央', '北京',
        '1996-11-23 00:00:00', '511116', '拜拜', '2', '10086');

DROP TABLE IF EXISTS `person_category`;
CREATE TABLE `person_category`
(
    `category_id`   varchar(128) NOT NULL,
    `category_name` varchar(64)  NOT NULL COMMENT '组类名字',
    `user_id`       varchar(32)  NOT NULL,
    PRIMARY KEY (`category_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT '组类表';

INSERT INTO `person_category` (`category_id`, `category_name`, `user_id`)
VALUES ('0', '未分组', '10086'),
       ('1', '家人', '10086'),
       ('2', '朋友', '10086');

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`
(
    `user_id`        varchar(32) NOT NULL,
    `user_name`      varchar(32) NOT NULL COMMENT '昵称',
    `user_password`  varchar(64) NOT NULL,
    `user_level`     int         NOT NULL DEFAULT 0 COMMENT '尊贵度',
    `book_number`    int         NOT NULL DEFAULT 0 COMMENT '联系人数量',
    `book_available` int         NOT NULL DEFAULT 100 COMMENT '可用数量',
    PRIMARY KEY (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT '用户表';

INSERT INTO `user_info` (`user_id`, `user_name`, `user_password`, `user_level`, `book_number`, `book_available`)
VALUES ('10086', '小明', '10086', 0, 8, 92);



