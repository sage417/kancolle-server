ALTER TABLE `t_member_map_battle_status` DROP FOREIGN KEY `t_member_map_battle_status_ibfk_1`;

ALTER TABLE `t_member_map_battle_status` DROP FOREIGN KEY `t_member_map_battle_status_ibfk_2`;

ALTER TABLE `t_member_map_battle_status` DROP FOREIGN KEY `t_member_map_battle_status_ibfk_3`;

ALTER TABLE `t_member_map_battle_status` ADD CONSTRAINT `t_member_map_battle_status_ibfk_1` FOREIGN KEY (`map_cell_id`) REFERENCES `t_map_cell` (`ID`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `t_member_map_battle_status` ADD CONSTRAINT `t_member_map_battle_status_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`member_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;