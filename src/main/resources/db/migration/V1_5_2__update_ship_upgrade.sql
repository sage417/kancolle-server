ALTER TABLE `t_ship_upgrade` ADD COLUMN `CURRENT_SHIP_ID` int NOT NULL DEFAULT 0 AFTER `ID`, CHANGE COLUMN `DRAWING_COUNT` `DRAWING_COUNT` smallint UNSIGNED NOT NULL, ADD COLUMN `CATAPULT_COUNT` smallint UNSIGNED NOT NULL AFTER `DRAWING_COUNT`;
ALTER TABLE `t_ship_upgrade` ADD COLUMN `index` bigint UNSIGNED NOT NULL AUTO_INCREMENT FIRST, DROP PRIMARY KEY, ADD PRIMARY KEY (`index`);