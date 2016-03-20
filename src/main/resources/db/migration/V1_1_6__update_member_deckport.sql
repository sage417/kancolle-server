ALTER TABLE `t_member_deckport`
MODIFY COLUMN `SHIP`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '[-1,-1,-1,-1,-1,-1,]' COMMENT '舰队信息' AFTER `FLAGSHIP`;