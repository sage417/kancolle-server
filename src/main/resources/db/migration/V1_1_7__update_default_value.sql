ALTER TABLE `t_enemy_deckport` CHANGE `SHIP` `SHIP` VARCHAR(511)  CHARACTER SET utf8  COLLATE utf8_general_ci  NOT NULL  DEFAULT '[-1,-1,-1,-1,-1,-1,]';
ALTER TABLE `t_enemy_ship_slotitem` CHANGE `SLOT` `SLOT` VARCHAR(63)  CHARACTER SET utf8  COLLATE utf8_general_ci  NOT NULL  DEFAULT '[-1,-1,-1,-1,-1,]';
