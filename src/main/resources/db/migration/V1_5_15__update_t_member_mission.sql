ALTER TABLE `kancolle`.`t_member_mission` CHANGE COLUMN `index` `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, DROP PRIMARY KEY, ADD PRIMARY KEY (`id`);