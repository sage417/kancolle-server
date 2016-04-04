ALTER TABLE `t_member_map_battle_status`
ADD COLUMN `result_flag`  tinyint(4) NOT NULL DEFAULT 0 AFTER `battle_flag`;