-- 不存在表则创建
CREATE TABLE IF NOT EXISTS `file_encryption` (
   `id` int NOT NULL AUTO_INCREMENT COMMENT '主键,自增,无符号',
   `file_name` varchar(64) NOT NULL DEFAULT '-' COMMENT '文件名称',
   `key` char(32) NOT NULL DEFAULT '-' COMMENT 'AES-key',
   `iv` char(16) NOT NULL DEFAULT '-' COMMENT 'AES-iv',
   PRIMARY KEY (`id`)
) ENGINE=InnoDB;