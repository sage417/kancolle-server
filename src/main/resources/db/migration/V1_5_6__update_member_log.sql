ALTER TABLE `kancolle`.`t_member_log` CHANGE COLUMN `index` `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP, DROP PRIMARY KEY, ADD PRIMARY KEY (`createTime`);