-- 创建用户表
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除：0-未删除，1-已删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  UNIQUE KEY `uk_email` (`email`),
  KEY `idx_status` (`status`),
  KEY `idx_age` (`age`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 插入测试数据
INSERT INTO `user` (`username`, `email`, `age`, `status`) VALUES
('admin', 'admin@example.com', 30, 1),
('user1', 'user1@example.com', 25, 1),
('user2', 'user2@example.com', 28, 1),
('user3', 'user3@example.com', 22, 0),
('user4', 'user4@example.com', 35, 1),
('user5', 'user5@example.com', 27, 1);