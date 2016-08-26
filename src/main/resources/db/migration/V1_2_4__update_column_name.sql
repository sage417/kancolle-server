ALTER TABLE `t_member_map_battle_status`
CHANGE COLUMN `map_area_id` `traveller_no`  int(10) UNSIGNED NULL DEFAULT NULL AFTER `deck_id`;