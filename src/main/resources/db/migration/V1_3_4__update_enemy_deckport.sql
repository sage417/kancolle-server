ALTER TABLE `t_enemy_deckport`
MODIFY COLUMN `RANK`  varchar(33) NOT NULL DEFAULT '' AFTER `SHIP`,
MODIFY COLUMN `LV`  varchar(33) NOT NULL DEFAULT '' AFTER `RANK`;