ALTER TABLE `t_ship` CHANGE COLUMN `TYPE` `STYPE` tinyint(4) UNSIGNED NOT NULL COMMENT '舰种', CHANGE COLUMN `SLOTNUM` `SLOT_NUM` tinyint(4) UNSIGNED NOT NULL COMMENT '装备栏数';