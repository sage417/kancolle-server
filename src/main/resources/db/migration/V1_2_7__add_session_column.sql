ALTER TABLE `t_member_map_battle_status`
ADD COLUMN `session`  varchar(1025) NOT NULL DEFAULT '{}' AFTER `battle_flag`;

