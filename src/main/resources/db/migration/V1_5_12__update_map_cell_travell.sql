ALTER TABLE `kancolle`.`t_map_cell_traveller` ADD COLUMN `NEXT` tinyint UNSIGNED NOT NULL DEFAULT 0 AFTER `EVENT_KIND`;