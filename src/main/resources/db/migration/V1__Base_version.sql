/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : kancolle

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-01-17 21:59:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_bgm
-- ----------------------------
DROP TABLE IF EXISTS `t_bgm`;
CREATE TABLE `t_bgm` (
  `ID` int(10) unsigned NOT NULL,
  `NAME` varchar(63) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_duty
-- ----------------------------
DROP TABLE IF EXISTS `t_duty`;
CREATE TABLE `t_duty` (
  `DUTY_NO` int(10) unsigned NOT NULL,
  `CATEGORY_ID` int(10) unsigned NOT NULL DEFAULT '1',
  `TYPE` int(10) unsigned NOT NULL DEFAULT '1',
  `TITLE` char(63) NOT NULL,
  `DETAIL` char(255) NOT NULL,
  `OPERATE` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `MATERIAL` varchar(63) NOT NULL,
  `BONUS_FLAG` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `INVALID_FLAG` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `PARENT_ID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`DUTY_NO`),
  KEY `CATEGORY_ID` (`CATEGORY_ID`) USING BTREE,
  KEY `t_duty_ibfk_1` (`PARENT_ID`) USING BTREE,
  KEY `TYPE` (`TYPE`) USING BTREE,
  CONSTRAINT `t_duty_ibfk_1` FOREIGN KEY (`PARENT_ID`) REFERENCES `t_duty` (`DUTY_NO`),
  CONSTRAINT `t_duty_ibfk_2` FOREIGN KEY (`CATEGORY_ID`) REFERENCES `t_duty_category` (`CATEGORY_ID`),
  CONSTRAINT `t_duty_ibfk_3` FOREIGN KEY (`TYPE`) REFERENCES `t_duty_type` (`TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_duty_bonus
-- ----------------------------
DROP TABLE IF EXISTS `t_duty_bonus`;
CREATE TABLE `t_duty_bonus` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `DUTY_NO` int(10) unsigned NOT NULL,
  `BONUS_TYPE` tinyint(3) unsigned NOT NULL,
  `BONUS_ITEM_ID` int(10) unsigned NOT NULL DEFAULT '0',
  `BONUS_COUNT` tinyint(3) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `DUTY_NO` (`DUTY_NO`) USING BTREE,
  CONSTRAINT `t_duty_bonus_ibfk_1` FOREIGN KEY (`DUTY_NO`) REFERENCES `t_duty` (`DUTY_NO`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_duty_category
-- ----------------------------
DROP TABLE IF EXISTS `t_duty_category`;
CREATE TABLE `t_duty_category` (
  `CATEGORY_ID` int(11) unsigned NOT NULL,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`CATEGORY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_duty_type
-- ----------------------------
DROP TABLE IF EXISTS `t_duty_type`;
CREATE TABLE `t_duty_type` (
  `TYPE_ID` int(3) unsigned NOT NULL,
  `DESCRIPTION` varchar(63) NOT NULL,
  PRIMARY KEY (`TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_enemy_deckport
-- ----------------------------
DROP TABLE IF EXISTS `t_enemy_deckport`;
CREATE TABLE `t_enemy_deckport` (
  `INDEX` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `MAPCELL_ID` int(10) unsigned NOT NULL,
  `NO` int(10) unsigned NOT NULL,
  `FORMATION` tinyint(3) unsigned NOT NULL,
  `SHIP` varchar(511) NOT NULL DEFAULT '[-1,-1,-1,-1,-1,-1]',
  PRIMARY KEY (`INDEX`),
  UNIQUE KEY `MAPCELL_ID` (`MAPCELL_ID`,`NO`),
  CONSTRAINT `t_enemy_deckport_ibfk_1` FOREIGN KEY (`MAPCELL_ID`) REFERENCES `t_map_cell` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_enemy_deckport_mapping
-- ----------------------------
DROP TABLE IF EXISTS `t_enemy_deckport_mapping`;
CREATE TABLE `t_enemy_deckport_mapping` (
  `INDEX` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `MAPCELL_ID` int(10) unsigned NOT NULL,
  `NO` int(10) unsigned NOT NULL,
  `SHIP_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`INDEX`),
  KEY `MAPCELL_ID` (`MAPCELL_ID`),
  KEY `SHIP_ID` (`SHIP_ID`),
  CONSTRAINT `t_enemy_deckport_mapping_ibfk_1` FOREIGN KEY (`MAPCELL_ID`) REFERENCES `t_map_cell` (`ID`),
  CONSTRAINT `t_enemy_deckport_mapping_ibfk_2` FOREIGN KEY (`SHIP_ID`) REFERENCES `t_ship` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_enemy_ship_slotitem
-- ----------------------------
DROP TABLE IF EXISTS `t_enemy_ship_slotitem`;
CREATE TABLE `t_enemy_ship_slotitem` (
  `ENEMY_SHIP_ID` int(11) unsigned NOT NULL,
  `SLOT` varchar(63) NOT NULL DEFAULT '[-1,-1,-1,-1,-1]',
  PRIMARY KEY (`ENEMY_SHIP_ID`),
  CONSTRAINT `t_enemy_ship_slotitem_ibfk_1` FOREIGN KEY (`ENEMY_SHIP_ID`) REFERENCES `t_ship` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_enemy_ship_slotitem_mapping
-- ----------------------------
DROP TABLE IF EXISTS `t_enemy_ship_slotitem_mapping`;
CREATE TABLE `t_enemy_ship_slotitem_mapping` (
  `INDEX` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `SHIP_ID` int(10) unsigned NOT NULL,
  `SLOTITEM_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`INDEX`),
  KEY `SLOTITEM_ID` (`SLOTITEM_ID`),
  KEY `t_enemy_ship_slotitem_mapping_ibfk_1` (`SHIP_ID`),
  CONSTRAINT `t_enemy_ship_slotitem_mapping_ibfk_1` FOREIGN KEY (`SHIP_ID`) REFERENCES `t_ship` (`ID`),
  CONSTRAINT `t_enemy_ship_slotitem_mapping_ibfk_2` FOREIGN KEY (`SLOTITEM_ID`) REFERENCES `t_slotitem` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_exp_member
-- ----------------------------
DROP TABLE IF EXISTS `t_exp_member`;
CREATE TABLE `t_exp_member` (
  `LV` tinyint(10) unsigned NOT NULL,
  `EXP` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`LV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_exp_mission
-- ----------------------------
DROP TABLE IF EXISTS `t_exp_mission`;
CREATE TABLE `t_exp_mission` (
  `ID` int(10) unsigned NOT NULL,
  `EXP` int(10) unsigned NOT NULL,
  `SHIP_EXP` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `t_exp_mission_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `t_mission` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_exp_ship
-- ----------------------------
DROP TABLE IF EXISTS `t_exp_ship`;
CREATE TABLE `t_exp_ship` (
  `LV` smallint(11) unsigned NOT NULL,
  `EXP` bigint(20) unsigned NOT NULL,
  `NEXT` bigint(20) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`LV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_furniture
-- ----------------------------
DROP TABLE IF EXISTS `t_furniture`;
CREATE TABLE `t_furniture` (
  `ID` int(10) unsigned NOT NULL,
  `TYPE` smallint(3) unsigned NOT NULL,
  `NO` smallint(3) unsigned NOT NULL,
  `TITLE` varchar(63) NOT NULL,
  `DESCRIPTION` varchar(255) NOT NULL,
  `RARITY` tinyint(3) unsigned NOT NULL,
  `PRICE` int(5) unsigned NOT NULL,
  `SALEFLG` tinyint(3) unsigned NOT NULL,
  `SEASON` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_furniture_graph
-- ----------------------------
DROP TABLE IF EXISTS `t_furniture_graph`;
CREATE TABLE `t_furniture_graph` (
  `ID` int(10) unsigned NOT NULL,
  `TYPE` tinyint(3) unsigned NOT NULL,
  `NO` tinyint(3) unsigned NOT NULL,
  `FILENAME` varchar(63) NOT NULL,
  `VERSION` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_furniture_type
-- ----------------------------
DROP TABLE IF EXISTS `t_furniture_type`;
CREATE TABLE `t_furniture_type` (
  `type` int(10) unsigned NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_item_shop
-- ----------------------------
DROP TABLE IF EXISTS `t_item_shop`;
CREATE TABLE `t_item_shop` (
  `NAME` varchar(255) NOT NULL,
  `ITEM_ID` varchar(255) NOT NULL,
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_map_area
-- ----------------------------
DROP TABLE IF EXISTS `t_map_area`;
CREATE TABLE `t_map_area` (
  `ID` int(10) unsigned NOT NULL,
  `NAME` varchar(63) NOT NULL,
  `TYPE` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_map_bgm
-- ----------------------------
DROP TABLE IF EXISTS `t_map_bgm`;
CREATE TABLE `t_map_bgm` (
  `ID` int(10) unsigned NOT NULL,
  `MAPAREA_ID` tinyint(3) unsigned NOT NULL,
  `NO` tinyint(3) unsigned NOT NULL,
  `MAP_BGM` varchar(63) NOT NULL,
  `BOSS_BGM` varchar(63) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_map_cell
-- ----------------------------
DROP TABLE IF EXISTS `t_map_cell`;
CREATE TABLE `t_map_cell` (
  `ID` int(10) unsigned NOT NULL,
  `NO` tinyint(3) unsigned NOT NULL,
  `COLOR_NO` tinyint(3) unsigned NOT NULL,
  `MAP_NO` tinyint(3) unsigned NOT NULL,
  `MAPAREA_ID` tinyint(3) unsigned NOT NULL,
  `MAPINFO_NO` tinyint(3) unsigned NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_map_info
-- ----------------------------
DROP TABLE IF EXISTS `t_map_info`;
CREATE TABLE `t_map_info` (
  `ID` int(10) unsigned NOT NULL,
  `INFOTEXT` varchar(255) NOT NULL,
  `ITEM` varchar(63) NOT NULL,
  `MAX_MAPHP` tinyint(3) unsigned NOT NULL,
  `REQUIRED_DEFEAT_COUNT` tinyint(3) unsigned NOT NULL,
  `LEVEL` tinyint(3) unsigned NOT NULL,
  `MAPAREA_ID` tinyint(3) unsigned NOT NULL,
  `NAME` varchar(63) NOT NULL,
  `NO` tinyint(3) unsigned NOT NULL,
  `OPETEXT` varchar(63) NOT NULL,
  `SALLY_FALG` varchar(63) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member
-- ----------------------------
DROP TABLE IF EXISTS `t_member`;
CREATE TABLE `t_member` (
  `member_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `api_token` char(40) DEFAULT NULL,
  `nickname` varchar(63) NOT NULL COMMENT '提督名',
  `nickname_id` varchar(63) NOT NULL DEFAULT '',
  `active_flag` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `starttime` bigint(20) unsigned DEFAULT NULL,
  `level` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '舰队司令部Level',
  `rank` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '称号',
  `experience` bigint(20) unsigned DEFAULT NULL,
  `fleetname` varchar(255) DEFAULT NULL,
  `comment` varchar(255) NOT NULL DEFAULT '',
  `comment_id` varchar(255) NOT NULL DEFAULT '',
  `max_chara` smallint(5) unsigned NOT NULL DEFAULT '100' COMMENT '最大保有可能舰娘数',
  `max_slotitem` mediumint(8) unsigned NOT NULL DEFAULT '497' COMMENT '最大保有可能装备数',
  `max_kagu` int(11) unsigned NOT NULL DEFAULT '0',
  `playtime` bigint(20) unsigned NOT NULL DEFAULT '0',
  `tutorial` int(4) unsigned NOT NULL DEFAULT '0',
  `furniture` char(63) NOT NULL DEFAULT '[1,72,102,133,164,221,242]',
  `count_deck` tinyint(3) unsigned NOT NULL DEFAULT '2' COMMENT '最大保有可能舰队数',
  `count_kdock` tinyint(3) unsigned NOT NULL DEFAULT '2' COMMENT '工厂数',
  `count_ndock` tinyint(3) unsigned NOT NULL DEFAULT '2' COMMENT '入渠数',
  `fcoin` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '家具币',
  `st_win` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '出击胜数',
  `st_lose` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '出击败数',
  `ms_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '远征回数',
  `ms_success` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '远征成功数',
  `pt_win` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '演戏胜数',
  `pt_lose` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '演戏败数',
  `pt_challenged` int(10) unsigned NOT NULL DEFAULT '0',
  `pt_challenged_win` int(10) unsigned NOT NULL DEFAULT '0',
  `firstflag` int(10) unsigned NOT NULL DEFAULT '1',
  `tutorial_progress` int(10) unsigned NOT NULL DEFAULT '100',
  `pvp` varchar(255) NOT NULL DEFAULT '[0,0]',
  `medals` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '甲徽章',
  `p_bgm_id` int(10) unsigned NOT NULL DEFAULT '101' COMMENT '母港职务室BGM',
  `parallel_quest_count` tinyint(3) unsigned NOT NULL DEFAULT '5' COMMENT '最大可能收付任务数',
  `large_dock` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否可进行大建',
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9007384 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member_deckport
-- ----------------------------
DROP TABLE IF EXISTS `t_member_deckport`;
CREATE TABLE `t_member_deckport` (
  `index` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) unsigned NOT NULL,
  `ID` tinyint(3) unsigned NOT NULL COMMENT '舰队ID',
  `NAME` varchar(255) NOT NULL COMMENT '舰队名',
  `NAME_ID` varchar(255) NOT NULL DEFAULT '',
  `MISSION_STATUS` tinyint(4) unsigned NOT NULL DEFAULT '0',
  `MISSION_ID` int(11) unsigned DEFAULT NULL,
  `MISSION_COMPLETE_TIME` bigint(20) unsigned NOT NULL DEFAULT '0',
  `MISSION_FLAG` tinyint(4) unsigned NOT NULL DEFAULT '0',
  `FLAGSHIP` varchar(255) NOT NULL DEFAULT '0',
  `SHIP` varchar(255) NOT NULL DEFAULT '[-1,-1,-1,-1,-1,-1]' COMMENT '舰队信息',
  `locked` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '是否可用',
  PRIMARY KEY (`index`),
  UNIQUE KEY `member_id` (`member_id`,`ID`) USING BTREE,
  KEY `MISSION_ID` (`MISSION_ID`) USING BTREE,
  CONSTRAINT `t_member_deckport_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `t_member_deckport_ibfk_2` FOREIGN KEY (`MISSION_ID`) REFERENCES `t_mission` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member_deckport_ship_mapping
-- ----------------------------
DROP TABLE IF EXISTS `t_member_deckport_ship_mapping`;
CREATE TABLE `t_member_deckport_ship_mapping` (
  `index` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) unsigned NOT NULL,
  `deck_id` tinyint(3) unsigned NOT NULL,
  `member_ship_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`index`),
  UNIQUE KEY `unique_index` (`member_id`,`deck_id`,`member_ship_id`) USING BTREE,
  CONSTRAINT `t_member_deckport_ship_mapping_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member_duty
-- ----------------------------
DROP TABLE IF EXISTS `t_member_duty`;
CREATE TABLE `t_member_duty` (
  `index` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) unsigned NOT NULL,
  `duty_no` int(10) unsigned NOT NULL,
  `state` tinyint(3) unsigned NOT NULL DEFAULT '1',
  `process_flag` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `counter` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`index`),
  UNIQUE KEY `member_id` (`member_id`,`duty_no`) USING BTREE,
  KEY `t_member_duty_ibfk_2` (`duty_no`) USING BTREE,
  CONSTRAINT `t_member_duty_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`member_id`),
  CONSTRAINT `t_member_duty_ibfk_2` FOREIGN KEY (`duty_no`) REFERENCES `t_duty` (`DUTY_NO`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member_furniture
-- ----------------------------
DROP TABLE IF EXISTS `t_member_furniture`;
CREATE TABLE `t_member_furniture` (
  `index` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) unsigned NOT NULL,
  `furniture_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`index`),
  UNIQUE KEY `unique_index` (`member_id`,`furniture_id`) USING BTREE,
  KEY `member_id` (`member_id`) USING BTREE,
  KEY `furniture_id` (`furniture_id`) USING BTREE,
  CONSTRAINT `t_member_furniture_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `t_member_furniture_ibfk_2` FOREIGN KEY (`furniture_id`) REFERENCES `t_furniture` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=267 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member_kdock
-- ----------------------------
DROP TABLE IF EXISTS `t_member_kdock`;
CREATE TABLE `t_member_kdock` (
  `index` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) unsigned NOT NULL,
  `ID` tinyint(3) unsigned NOT NULL,
  `STATE` tinyint(3) NOT NULL DEFAULT '-1',
  `CREATED_SHIP_ID` int(10) unsigned NOT NULL DEFAULT '0',
  `COMPLETE_TIME` bigint(20) unsigned NOT NULL DEFAULT '0',
  `COMPLETE_TIME_STR` varchar(255) NOT NULL DEFAULT '0',
  `ITEM1` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `ITEM2` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `ITEM3` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `ITEM4` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `ITEM5` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`index`),
  UNIQUE KEY `member_id` (`member_id`,`ID`) USING BTREE,
  CONSTRAINT `t_member_kdock_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member_log
-- ----------------------------
DROP TABLE IF EXISTS `t_member_log`;
CREATE TABLE `t_member_log` (
  `index` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) unsigned NOT NULL,
  `NO` tinyint(4) NOT NULL,
  `TYPE` varchar(255) NOT NULL,
  `STATE` varchar(255) NOT NULL,
  `MESSAGE` varchar(255) NOT NULL,
  PRIMARY KEY (`index`),
  KEY `member_id` (`member_id`) USING BTREE,
  CONSTRAINT `t_member_log_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member_mapcell_info
-- ----------------------------
DROP TABLE IF EXISTS `t_member_mapcell_info`;
CREATE TABLE `t_member_mapcell_info` (
  `INDEX` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) unsigned NOT NULL,
  `MAPCELL_ID` int(10) unsigned NOT NULL,
  `PASS_FLAG` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`INDEX`),
  KEY `memmber_id` (`member_id`),
  KEY `MAPCELL_ID` (`MAPCELL_ID`),
  CONSTRAINT `t_member_mapcell_info_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`member_id`),
  CONSTRAINT `t_member_mapcell_info_ibfk_3` FOREIGN KEY (`MAPCELL_ID`) REFERENCES `t_map_cell` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=411 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member_mapinfo
-- ----------------------------
DROP TABLE IF EXISTS `t_member_mapinfo`;
CREATE TABLE `t_member_mapinfo` (
  `INDEX` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) unsigned NOT NULL,
  `MAPINFO_ID` int(10) unsigned NOT NULL,
  `CLEAR_FLAG` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `EXBOSS_FLAG` tinyint(3) unsigned NOT NULL,
  `DEFEATED_COUNT` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `OPEN_FLAG` tinyint(3) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`INDEX`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member_map_battle_status
-- ----------------------------
DROP TABLE IF EXISTS `t_member_map_battle_status`;
CREATE TABLE `t_member_map_battle_status` (
  `member_id` bigint(20) unsigned NOT NULL,
  `deck_id` tinyint(3) unsigned NOT NULL,
  `map_area_id` int(10) unsigned DEFAULT NULL,
  `map_cell_id` int(10) unsigned DEFAULT NULL,
  `map_get_resource` char(64) NOT NULL DEFAULT '[0,0,0,0,0,0,0,0]',
  `map_fetch_rescource` char(64) NOT NULL DEFAULT '[0,0,0,0,0,0,0,0]',
  PRIMARY KEY (`member_id`),
  KEY `map_area_id` (`map_area_id`) USING BTREE,
  KEY `map_cell_id` (`map_cell_id`) USING BTREE,
  CONSTRAINT `t_member_map_battle_status_ibfk_1` FOREIGN KEY (`map_area_id`) REFERENCES `t_map_area` (`ID`),
  CONSTRAINT `t_member_map_battle_status_ibfk_2` FOREIGN KEY (`map_cell_id`) REFERENCES `t_map_cell` (`ID`),
  CONSTRAINT `t_member_map_battle_status_ibfk_3` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member_material
-- ----------------------------
DROP TABLE IF EXISTS `t_member_material`;
CREATE TABLE `t_member_material` (
  `member_id` bigint(20) unsigned NOT NULL,
  `FUEL` mediumint(8) unsigned NOT NULL DEFAULT '1000',
  `BULL` mediumint(8) unsigned NOT NULL DEFAULT '1000',
  `STEEL` mediumint(8) unsigned NOT NULL DEFAULT '1000',
  `BAUXITE` mediumint(8) unsigned NOT NULL DEFAULT '1000',
  `FAST_REC` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `FAST_BUILD` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `DEV_ITEM` mediumint(8) unsigned NOT NULL DEFAULT '0',
  `ENH_ITEM` mediumint(8) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`member_id`),
  KEY `member_id` (`member_id`) USING BTREE,
  CONSTRAINT `t_member_material_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member_mission
-- ----------------------------
DROP TABLE IF EXISTS `t_member_mission`;
CREATE TABLE `t_member_mission` (
  `index` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) unsigned NOT NULL,
  `mission_id` int(10) unsigned NOT NULL,
  `state` tinyint(4) NOT NULL,
  PRIMARY KEY (`index`),
  UNIQUE KEY `member_id` (`member_id`,`mission_id`) USING BTREE,
  KEY `mission_id` (`mission_id`) USING BTREE,
  CONSTRAINT `t_member_mission_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_member_mission_ibfk_2` FOREIGN KEY (`mission_id`) REFERENCES `t_mission` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member_ndock
-- ----------------------------
DROP TABLE IF EXISTS `t_member_ndock`;
CREATE TABLE `t_member_ndock` (
  `index` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) unsigned NOT NULL,
  `ID` tinyint(3) unsigned NOT NULL,
  `STATE` tinyint(3) NOT NULL DEFAULT '-1',
  `SHIP_ID` int(11) unsigned NOT NULL DEFAULT '0',
  `COMPLETE_TIME` bigint(20) unsigned NOT NULL DEFAULT '0',
  `COMPLETE_TIME_STR` varchar(255) NOT NULL DEFAULT '0',
  `ITEM1` mediumint(9) unsigned NOT NULL DEFAULT '0',
  `ITEM2` mediumint(9) unsigned NOT NULL DEFAULT '0',
  `ITEM3` mediumint(9) unsigned NOT NULL DEFAULT '0',
  `ITEM4` mediumint(9) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`index`),
  UNIQUE KEY `member_id` (`member_id`,`ID`) USING BTREE,
  CONSTRAINT `t_member_ndock_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member_picturebook
-- ----------------------------
DROP TABLE IF EXISTS `t_member_picturebook`;
CREATE TABLE `t_member_picturebook` (
  `MEMBER_PICTUREBOOK_ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) unsigned NOT NULL,
  `SHIP_SORTNO` smallint(10) unsigned NOT NULL,
  `state` varchar(255) NOT NULL DEFAULT '[[1,0,0,0]]',
  PRIMARY KEY (`MEMBER_PICTUREBOOK_ID`),
  KEY `member_id` (`member_id`),
  KEY `SHIP_SORTNO` (`SHIP_SORTNO`),
  CONSTRAINT `t_member_picturebook_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`member_id`),
  CONSTRAINT `t_member_picturebook_ibfk_2` FOREIGN KEY (`SHIP_SORTNO`) REFERENCES `t_ship` (`SORTNO`)
) ENGINE=InnoDB AUTO_INCREMENT=369 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member_ship
-- ----------------------------
DROP TABLE IF EXISTS `t_member_ship`;
CREATE TABLE `t_member_ship` (
  `index` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) unsigned NOT NULL,
  `ID` bigint(20) unsigned NOT NULL,
  `SHIP_ID` int(11) unsigned NOT NULL,
  `LV` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '等级',
  `EXP` varchar(255) NOT NULL DEFAULT '[0,100,0]' COMMENT '经验总和,next经验，百分比',
  `NOWHP` smallint(6) unsigned NOT NULL COMMENT '当前血量',
  `MAXHP` smallint(6) unsigned NOT NULL COMMENT '最大血量',
  `LENG` tinyint(3) unsigned NOT NULL COMMENT '射程',
  `SLOT` varchar(255) NOT NULL COMMENT '装备',
  `ONSLOT` varchar(255) NOT NULL COMMENT '搭载量',
  `KYOUKA` varchar(255) NOT NULL DEFAULT '[0,0,0,0]' COMMENT '成长(火雷空甲)',
  `FUEL` smallint(4) unsigned NOT NULL COMMENT '燃油',
  `BULL` smallint(4) unsigned NOT NULL COMMENT '弹药',
  `SRATE` tinyint(3) unsigned NOT NULL,
  `COND` tinyint(3) unsigned NOT NULL DEFAULT '40' COMMENT '状态',
  `KARYOKU` varchar(255) NOT NULL COMMENT '火力',
  `RAISOU` varchar(255) NOT NULL COMMENT '雷装',
  `TAIKU` varchar(255) NOT NULL COMMENT '对空',
  `SOUKOU` varchar(255) NOT NULL COMMENT '装甲',
  `KAIHI` varchar(255) NOT NULL COMMENT '回避',
  `TAISEN` varchar(255) NOT NULL COMMENT '对潜',
  `SAKUTEKI` varchar(255) NOT NULL COMMENT '索敌',
  `LUCKY` varchar(255) NOT NULL COMMENT '幸运',
  `LOCKED` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '上锁',
  `LOCKED_EQUIP` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否拥有上锁装备',
  `DELETED` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `DELETED_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`index`),
  UNIQUE KEY `member_id` (`member_id`,`ID`) USING BTREE,
  KEY `SHIP_ID` (`SHIP_ID`) USING BTREE,
  CONSTRAINT `t_member_ship_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `t_member_ship_ibfk_2` FOREIGN KEY (`SHIP_ID`) REFERENCES `t_ship` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member_ship_slotitem_mapping
-- ----------------------------
DROP TABLE IF EXISTS `t_member_ship_slotitem_mapping`;
CREATE TABLE `t_member_ship_slotitem_mapping` (
  `index` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) unsigned NOT NULL,
  `member_ship_id` bigint(20) unsigned NOT NULL,
  `member_slotitem_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`index`),
  UNIQUE KEY `member_id` (`member_id`,`member_ship_id`,`member_slotitem_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member_slotitem
-- ----------------------------
DROP TABLE IF EXISTS `t_member_slotitem`;
CREATE TABLE `t_member_slotitem` (
  `index` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) unsigned NOT NULL,
  `ID` bigint(20) unsigned NOT NULL,
  `LEVEL` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `LOCKED` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `SLOTITEM_ID` int(11) unsigned NOT NULL,
  `DELETED` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `DELETED_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`index`),
  UNIQUE KEY `unique_index` (`member_id`,`ID`) USING BTREE,
  KEY `SLOTITEM_ID` (`SLOTITEM_ID`) USING BTREE,
  KEY `member_id` (`member_id`) USING BTREE,
  CONSTRAINT `t_member_slotitem_ibfk_1` FOREIGN KEY (`SLOTITEM_ID`) REFERENCES `t_slotitem` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `t_member_slotitem_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`member_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_member_useitem
-- ----------------------------
DROP TABLE IF EXISTS `t_member_useitem`;
CREATE TABLE `t_member_useitem` (
  `index` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) unsigned NOT NULL,
  `ID` int(10) unsigned NOT NULL,
  `COUNT` int(10) NOT NULL,
  PRIMARY KEY (`index`),
  UNIQUE KEY `member_id` (`member_id`,`ID`) USING BTREE,
  KEY `ID` (`ID`) USING BTREE,
  CONSTRAINT `t_member_useitem_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_member_useitem_ibfk_2` FOREIGN KEY (`ID`) REFERENCES `t_useitem` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_mission
-- ----------------------------
DROP TABLE IF EXISTS `t_mission`;
CREATE TABLE `t_mission` (
  `ID` int(10) unsigned NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `DIFFICULTY` tinyint(4) unsigned NOT NULL,
  `MAPAREA_ID` tinyint(4) unsigned NOT NULL,
  `TIME` smallint(4) unsigned NOT NULL,
  `USE_FUEL` float unsigned NOT NULL,
  `USE_BULL` float unsigned NOT NULL,
  `MATERIALS` varchar(255) NOT NULL,
  `WIN_ITEM1` varchar(255) NOT NULL,
  `WIN_ITEM2` varchar(255) NOT NULL,
  `RETURN_FLAG` tinyint(4) unsigned NOT NULL,
  `DETAILS` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_mission_state
-- ----------------------------
DROP TABLE IF EXISTS `t_mission_state`;
CREATE TABLE `t_mission_state` (
  `MISSION_STATE` tinyint(4) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MISSION_STATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_pay_item
-- ----------------------------
DROP TABLE IF EXISTS `t_pay_item`;
CREATE TABLE `t_pay_item` (
  `ID` int(11) NOT NULL,
  `TYPE` int(11) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(255) NOT NULL,
  `ITEM` varchar(255) NOT NULL,
  `PRICE` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ship
-- ----------------------------
DROP TABLE IF EXISTS `t_ship`;
CREATE TABLE `t_ship` (
  `ID` int(10) unsigned NOT NULL,
  `SORTNO` smallint(4) unsigned NOT NULL COMMENT '图鉴ID',
  `NAME` varchar(255) NOT NULL COMMENT '舰名',
  `YOMI` varchar(255) NOT NULL,
  `TYPE` tinyint(4) unsigned NOT NULL COMMENT '舰种',
  `AFTERLV` tinyint(4) unsigned NOT NULL COMMENT '改造等级',
  `AFTERSHIPID` int(10) unsigned NOT NULL COMMENT '改造',
  `TAIK` varchar(255) NOT NULL COMMENT '耐久',
  `SOUK` varchar(255) NOT NULL COMMENT '装甲',
  `HOUG` varchar(255) NOT NULL COMMENT '火力',
  `RAIG` varchar(255) NOT NULL COMMENT '雷装',
  `TYKU` varchar(255) NOT NULL COMMENT '对空',
  `LUCK` varchar(255) NOT NULL COMMENT '幸运',
  `KAIHI` varchar(255) DEFAULT NULL COMMENT '回避',
  `TAISEN` varchar(255) DEFAULT NULL COMMENT '对潜',
  `SAKUTEKI` varchar(255) DEFAULT NULL COMMENT '索敌',
  `SOKU` tinyint(4) unsigned NOT NULL COMMENT '速力',
  `LENG` tinyint(4) unsigned NOT NULL COMMENT '射程',
  `SLOTNUM` tinyint(4) unsigned NOT NULL COMMENT '装备栏数',
  `MAXEQ` varchar(255) NOT NULL COMMENT '最大搭载',
  `BUILDTIME` smallint(4) unsigned NOT NULL COMMENT '建造时间',
  `BROKEN` varchar(255) NOT NULL COMMENT '解体资源',
  `POWUP` varchar(63) NOT NULL COMMENT '改修',
  `BACKS` tinyint(4) unsigned NOT NULL,
  `GETMES` varchar(255) NOT NULL COMMENT '介绍',
  `AFTERFUEL` smallint(4) unsigned NOT NULL COMMENT '改造燃油',
  `AFTERBULL` smallint(4) unsigned NOT NULL COMMENT '改造弹药',
  `FUEL_MAX` smallint(4) unsigned NOT NULL COMMENT '燃油',
  `BULL_MAX` smallint(4) unsigned NOT NULL COMMENT '弹药',
  `VOICEF` tinyint(4) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `type_index` (`TYPE`) USING BTREE,
  KEY `sortno_index` (`SORTNO`) USING BTREE,
  KEY `AFTERSHIPID` (`AFTERSHIPID`) USING BTREE,
  CONSTRAINT `t_ship_ibfk_1` FOREIGN KEY (`TYPE`) REFERENCES `t_ship_type` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ship_exp
-- ----------------------------
DROP TABLE IF EXISTS `t_ship_exp`;
CREATE TABLE `t_ship_exp` (
  `LV` int(11) unsigned NOT NULL,
  `EXP` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`LV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ship_graph
-- ----------------------------
DROP TABLE IF EXISTS `t_ship_graph`;
CREATE TABLE `t_ship_graph` (
  `ID` int(11) NOT NULL,
  `SORTNO` int(11) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `VERSION` varchar(255) NOT NULL,
  `BOKO_N` varchar(255) NOT NULL,
  `BOKO_D` varchar(255) NOT NULL,
  `KAISYU_N` varchar(255) NOT NULL,
  `KAISYU_D` varchar(255) NOT NULL,
  `KAIZO_N` varchar(255) NOT NULL,
  `KAIZO_D` varchar(255) NOT NULL,
  `MAP_N` varchar(255) NOT NULL,
  `MAP_D` varchar(255) NOT NULL,
  `ENSYUF_N` varchar(255) NOT NULL,
  `ENSYUF_D` varchar(255) NOT NULL,
  `ENSYUE_N` varchar(255) NOT NULL,
  `BATTLE_N` varchar(255) NOT NULL,
  `BATTLE_D` varchar(255) NOT NULL,
  `WEDA` varchar(255) NOT NULL,
  `WEDB` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ship_picturebook
-- ----------------------------
DROP TABLE IF EXISTS `t_ship_picturebook`;
CREATE TABLE `t_ship_picturebook` (
  `SHIP_ID` int(10) unsigned NOT NULL,
  `CTYPE` tinyint(3) unsigned NOT NULL,
  `CNUM` tinyint(3) unsigned NOT NULL,
  `SHIP_INFO` varchar(255) NOT NULL,
  PRIMARY KEY (`SHIP_ID`),
  CONSTRAINT `t_ship_picturebook_ibfk_1` FOREIGN KEY (`SHIP_ID`) REFERENCES `t_ship` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ship_type
-- ----------------------------
DROP TABLE IF EXISTS `t_ship_type`;
CREATE TABLE `t_ship_type` (
  `ID` tinyint(4) unsigned NOT NULL,
  `EQUIP_TYPE` text NOT NULL,
  `KCNT` tinyint(4) unsigned NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `SCNT` tinyint(4) unsigned NOT NULL,
  `SORTNO` tinyint(4) unsigned NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ship_upgrade
-- ----------------------------
DROP TABLE IF EXISTS `t_ship_upgrade`;
CREATE TABLE `t_ship_upgrade` (
  `ID` int(11) NOT NULL,
  `ORIGINAL_SHIP_ID` int(11) NOT NULL,
  `UPGRADE_TYPE` int(11) NOT NULL,
  `UPGRADE_LEVEL` int(11) NOT NULL,
  `DRAWING_COUNT` int(11) NOT NULL,
  `SORTNO` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_slotitem
-- ----------------------------
DROP TABLE IF EXISTS `t_slotitem`;
CREATE TABLE `t_slotitem` (
  `ID` int(11) unsigned NOT NULL,
  `SORTNO` smallint(5) unsigned NOT NULL COMMENT '图鉴ID',
  `NAME` varchar(63) NOT NULL COMMENT '装备名',
  `TYPE` varchar(63) NOT NULL,
  `TAIK` tinyint(4) NOT NULL,
  `SOUK` tinyint(4) NOT NULL,
  `HOUG` tinyint(4) NOT NULL,
  `RAIG` tinyint(4) NOT NULL,
  `SOKU` tinyint(4) NOT NULL,
  `BAKU` tinyint(4) NOT NULL,
  `TYKU` tinyint(4) NOT NULL,
  `TAIS` tinyint(4) NOT NULL,
  `ATAP` tinyint(4) NOT NULL,
  `HOUM` tinyint(4) NOT NULL,
  `RAIM` tinyint(4) NOT NULL,
  `HOUK` tinyint(4) NOT NULL,
  `RAIK` tinyint(4) NOT NULL,
  `BAKK` tinyint(4) NOT NULL,
  `SAKU` tinyint(4) NOT NULL,
  `SAKB` tinyint(4) NOT NULL,
  `LUCK` tinyint(4) NOT NULL,
  `LENG` tinyint(4) NOT NULL,
  `RARE` tinyint(4) NOT NULL,
  `BROKEN` varchar(63) NOT NULL,
  `INFO` text NOT NULL,
  `USEBULL` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_slotitem_equiptype
-- ----------------------------
DROP TABLE IF EXISTS `t_slotitem_equiptype`;
CREATE TABLE `t_slotitem_equiptype` (
  `ID` int(11) NOT NULL,
  `TYPE` varchar(63) NOT NULL,
  `SHOW_FLAG` tinyint(4) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_slotitem_graph
-- ----------------------------
DROP TABLE IF EXISTS `t_slotitem_graph`;
CREATE TABLE `t_slotitem_graph` (
  `ID` int(11) NOT NULL,
  `SORTNO` smallint(5) unsigned NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `VERSION` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_useitem
-- ----------------------------
DROP TABLE IF EXISTS `t_useitem`;
CREATE TABLE `t_useitem` (
  `ID` int(11) unsigned NOT NULL,
  `USETYPE` tinyint(4) unsigned NOT NULL,
  `CATEGORY` smallint(6) unsigned NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(255) NOT NULL,
  `PRICE` mediumint(9) unsigned NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` char(255) DEFAULT NULL,
  `nickname` char(255) DEFAULT NULL,
  `mobile` char(63) DEFAULT NULL,
  `email` char(63) DEFAULT NULL,
  `password` char(255) DEFAULT NULL,
  `salt` char(255) DEFAULT NULL,
  `statue` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- View structure for v_member_deckport
-- ----------------------------
DROP VIEW IF EXISTS `v_member_deckport`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `v_member_deckport` AS select `t_member_deckport`.`member_id` AS `member_id`,`t_member_deckport`.`ID` AS `ID`,`t_member_deckport`.`NAME` AS `NAME`,`t_member_deckport`.`NAME_ID` AS `NAME_ID`,concat('[',concat_ws(',',`t_member_deckport`.`MISSION_STATUS`,ifnull(`t_member_deckport`.`MISSION_ID`,0),`t_member_deckport`.`MISSION_COMPLETE_TIME`,`t_member_deckport`.`MISSION_FLAG`),']') AS `MISSION`,`t_member_deckport`.`FLAGSHIP` AS `FLAGSHIP`,`t_member_deckport`.`SHIP` AS `SHIP`,`t_member_deckport`.`locked` AS `locked` from `t_member_deckport` where (`t_member_deckport`.`locked` = 0) ;

-- ----------------------------
-- View structure for v_member_mission
-- ----------------------------
DROP VIEW IF EXISTS `v_member_mission`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `v_member_mission` AS select `t_member_mission`.`member_id` AS `member_id`,`t_member_mission`.`mission_id` AS `mission_id`,`t_member_mission`.`state` AS `state` from `t_member_mission` where (`t_member_mission`.`state` > -(1)) ;

-- ----------------------------
-- View structure for v_member_ship
-- ----------------------------
DROP VIEW IF EXISTS `v_member_ship`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `v_member_ship` AS select `t_member_ship`.`member_id` AS `member_id`,`t_member_ship`.`ID` AS `ID`,`t_member_ship`.`SHIP_ID` AS `SHIP_ID`,`t_member_ship`.`LV` AS `LV`,`t_member_ship`.`EXP` AS `EXP`,`t_member_ship`.`NOWHP` AS `NOWHP`,`t_member_ship`.`MAXHP` AS `MAXHP`,`t_member_ship`.`LENG` AS `LENG`,`t_member_ship`.`SLOT` AS `SLOT`,`t_member_ship`.`ONSLOT` AS `ONSLOT`,`t_member_ship`.`KYOUKA` AS `KYOUKA`,`t_member_ship`.`FUEL` AS `FUEL`,`t_member_ship`.`BULL` AS `BULL`,`t_member_ship`.`SRATE` AS `SRATE`,`t_member_ship`.`COND` AS `COND`,`t_member_ship`.`KARYOKU` AS `KARYOKU`,`t_member_ship`.`RAISOU` AS `RAISOU`,`t_member_ship`.`TAIKU` AS `TAIKU`,`t_member_ship`.`SOUKOU` AS `SOUKOU`,`t_member_ship`.`KAIHI` AS `KAIHI`,`t_member_ship`.`TAISEN` AS `TAISEN`,`t_member_ship`.`SAKUTEKI` AS `SAKUTEKI`,`t_member_ship`.`LUCKY` AS `LUCKY`,`t_member_ship`.`LOCKED` AS `LOCKED`,`t_member_ship`.`LOCKED_EQUIP` AS `LOCKED_EQUIP` from `t_member_ship` where (`t_member_ship`.`DELETED` = 0) order by `t_member_ship`.`member_id`,`t_member_ship`.`ID` ;

-- ----------------------------
-- View structure for v_member_slotitem
-- ----------------------------
DROP VIEW IF EXISTS `v_member_slotitem`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER  VIEW `v_member_slotitem` AS select `t_member_slotitem`.`member_id` AS `member_id`,`t_member_slotitem`.`ID` AS `ID`,`t_member_slotitem`.`LEVEL` AS `LEVEL`,`t_member_slotitem`.`LOCKED` AS `LOCKED`,`t_member_slotitem`.`SLOTITEM_ID` AS `SLOTITEM_ID` from `t_member_slotitem` where (`t_member_slotitem`.`DELETED` = 0) order by `t_member_slotitem`.`member_id`,`t_member_slotitem`.`ID` ;

-- ----------------------------
-- Procedure structure for create_ship
-- ----------------------------
DROP PROCEDURE IF EXISTS `create_ship`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `create_ship`(IN `member_id` bigint,IN `ship_id` int)
BEGIN
	DECLARE now_id BIGINT;
	SET now_id = (SELECT ifnull(MAX(ID),0) FROM t_member_ship WHERE member_id = member_id);
	INSERT INTO t_member_ship (
	member_id,
	ID,
	SHIP_ID,
	NOWHP,
	MAXHP,
	LENG,
	SLOT,
	ONSLOT,
	FUEL,
	BULL,
	SRATE,
	KARYOKU,
	RAISOU,
	TAIKU,
	SOUKOU,
	KAIHI,
	TAISEN,
	SAKUTEKI,
	LUCKY
) SELECT
	member_id,
	now_id+1,
	ship_id,
	func_get_split_string(TAIK,1),
	func_get_split_string(TAIK,1),
	LENG,
	'[-1,-1,-1,-1,-1]',
	MAXEQ,
	FUEL_MAX,
	BULL_MAX,
	1,
	HOUG,
	RAIG,
	TYKU,
	SOUK,
	KAIHI,
	TAISEN,
	SAKUTEKI,
	LUCK
FROM
	t_ship ts
WHERE
	ts.ID = ship_id;
	
select * from t_member_ship where member_id = member_id and ID = now_id+1;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for create_slotitem
-- ----------------------------
DROP PROCEDURE IF EXISTS `create_slotitem`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `create_slotitem`(`member_id` bigint,`slotitem_id` int)
BEGIN
	DECLARE now_id BIGINT;
	SET now_id = (SELECT IFNULL(MAX(ID),0) FROM t_member_slotitem WHERE member_id = member_id);
	INSERT INTO t_member_slotitem (member_id,ID,SLOTITEM_ID)VALUES (member_id,now_id+1,slotitem_id);
	SELECT * FROM t_member_slotitem WHERE member_id = member_id AND ID = now_id+1;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for finish_ndock
-- ----------------------------
DROP PROCEDURE IF EXISTS `finish_ndock`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `finish_ndock`()
BEGIN
	DECLARE now_cond INT;
	DECLARE m_id BIGINT;
	DECLARE s_id BIGINT;
	DECLARE ndock_id TINYINT;

	DECLARE s INT DEFAULT 0; 
	
	DECLARE cursor_ndock CURSOR FOR SELECT member_id,SHIP_ID,ID FROM t_member_ndock WHERE STATE = 1 AND DATE_SUB(FROM_UNIXTIME(FLOOR(COMPLETE_TIME/1000)),	INTERVAL 1 MINUTE) < NOW();

	DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET s=1;
	
	OPEN cursor_ndock;
	
	WHILE s <> 1 DO  
		FETCH cursor_ndock into m_id,s_id,ndock_id; 
		SET now_cond = (SELECT COND FROM t_member_ship WHERE member_id = m_id AND ID = s_id);
		IF now_cond < 40 THEN
			UPDATE t_member_ship SET COND=40,NOWHP=MAXHP WHERE member_id = m_id AND ID = s_id;
		ELSE
			UPDATE t_member_ship SET NOWHP=MAXHP WHERE member_id = m_id AND ID = s_id;
		END IF;
		UPDATE t_member_ndock SET STATE =0,SHIP_ID=0, COMPLETE_TIME=0, COMPLETE_TIME_STR='0' ,ITEM1=0, ITEM3=0 WHERE member_id = m_id AND ID = ndock_id;
	END WHILE;

	CLOSE cursor_ndock ;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for increase_material
-- ----------------------------
DROP PROCEDURE IF EXISTS `increase_material`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `increase_material`()
BEGIN
	declare now_member_id bigint;
	DECLARE max_material INT;
	DECLARE now_fuel int;
	declare add_fuel int default 0;
	DECLARE now_bull int;
	DECLARE add_bull INT DEFAULT 0;
	DECLARE now_steel int;
	DECLARE add_steel INT DEFAULT 0;
	DECLARE now_bauxite int;
	DECLARE add_bauxite INT DEFAULT 0;
	
	DECLARE s INT DEFAULT 0; 
	
	DECLARE cursor_material CURSOR FOR select FUEL,BULL,STEEL,BAUXITE,member_id from t_member_material;
	
	DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET s=1;
	
	OPEN cursor_material;
	
	while s <> 1 do  
	
		fetch  cursor_material into now_fuel,now_bull,now_steel,now_bauxite,now_member_id; 
		
		SET max_material = (SELECT (750 + 250 * LEVEL) FROM t_member WHERE member_id = now_member_id);
		
		if now_fuel < (max_material -3)  then 
			set add_fuel = 3;
		else
			set add_fuel = 0;
		end if;
		
		if now_bull < (max_material -3)  THEN 
			set add_bull = 3;
		else 
			set add_bull = 0;
		end if;
		
		if now_steel < (max_material -3)  THEN 
			set add_steel = 3;
		else	
			set add_steel = 0;
		end if;
		
		if now_bauxite < (max_material - 1) THEN 
			set add_bauxite = 1;
		else
			set add_bauxite = 0;
		end if;
		
		update t_member_material set FUEL = FUEL+add_fuel, BULL=BULL+add_bull, STEEL=STEEL+add_steel,BAUXITE = BAUXITE + add_bauxite where member_id = now_member_id;
	
	end while; 
	
	CLOSE cursor_material ;
	
    END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for func_get_split_string
-- ----------------------------
DROP FUNCTION IF EXISTS `func_get_split_string`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `func_get_split_string`(
f_string varchar(255),f_order int) RETURNS varchar(15) CHARSET utf8
BEGIN
  declare result varchar(255) default '';
  DECLARE f_str varchar(255);
  set f_str = TRIM(LEADING '[' FROM f_string);
  set f_str = trim(trailing ']' from f_str);
  set result = reverse(substring_index(reverse(substring_index(f_str,',',f_order)),',',1));
  return result;
END
;;
DELIMITER ;

-- ----------------------------
-- Event structure for dock_task
-- ----------------------------
DROP EVENT IF EXISTS `dock_task`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` EVENT `dock_task` ON SCHEDULE EVERY 1 MINUTE STARTS '2015-06-23 18:13:25' ON COMPLETION PRESERVE ENABLE DO Begin
	CALL finish_ndock;
	
	UPDATE t_member_kdock SET STATE = 3 WHERE STATE = 2 AND DATE_SUB(FROM_UNIXTIME(FLOOR(COMPLETE_TIME/1000)),INTERVAL 1 MINUTE) < NOW();
END
;;
DELIMITER ;

-- ----------------------------
-- Event structure for material_task
-- ----------------------------
DROP EVENT IF EXISTS `material_task`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` EVENT `material_task` ON SCHEDULE EVERY 3 MINUTE STARTS '2015-06-23 18:13:25' ON COMPLETION PRESERVE ENABLE DO BEGIN
	    call increase_material();
	END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `tri_after_newmember`;
DELIMITER ;;
CREATE TRIGGER `tri_after_newmember` AFTER INSERT ON `t_member` FOR EACH ROW BEGIN
	SET UNIQUE_CHECKS=0;

	replace into t_member_material (member_id) values (new.member_id);
	/* 创建舰队 */
	replace INTO t_member_deckport (member_id, ID, NAME, locked, SHIP) VALUES
	(new.member_id, 1, '第1艦隊', 0, '[1,-1,-1,-1,-1,-1]'),
	(new.member_id, 2, '第2艦隊', 1, '[-1,-1,-1,-1,-1,-1]'),
	(new.member_id, 3, '第3艦隊', 1, '[-1,-1,-1,-1,-1,-1]'),
	(new.member_id, 4, '第4艦隊', 1, '[-1,-1,-1,-1,-1,-1]');

	/** 创建工厂 */
	replace into t_member_kdock (member_id, ID, STATE) values
	(new.member_id, 1, 0),
	(new.member_id, 2, 0),
	(new.member_id, 3, -1),
	(new.member_id, 4, -1);

	/** 创建渠 */
	replace into t_member_ndock (member_id, ID, STATE) VALUES
	(new.member_id, 1, 0),
	(new.member_id, 2, 0),
	(new.member_id, 3, -1),
	(new.member_id, 4, -1);

	/** 创建mission记录 */
	REPLACE INTO t_member_mission (member_id, mission_id, state)
	SELECT new.member_id, ID , -1 FROM t_mission;

	/** 创建furniture记录 */
	REPLACE INTO t_member_furniture (member_id, furniture_id)
	SELECT new.member_id, ID FROM t_furniture WHERE t_furniture.`NO` = 0;

	/** 创建item记录 */
	REPLACE INTO t_member_useitem (member_id, ID, COUNT) values
	(new.member_id, 10, 0),
	(new.member_id, 11, 0),
	(new.member_id, 12, 0),
	(new.member_id, 52, 0),
	(new.member_id, 54, 0),
	(new.member_id, 55, 0),
	(new.member_id, 56, 0),
	(new.member_id, 57, 0),
	(new.member_id, 58, 0),
	(new.member_id, 59, 0),
	(new.member_id, 60, 0),
	(new.member_id, 61, 0),
	(new.member_id, 62, 0),
	(new.member_id, 63, 0);

	/** 创建MapInfo记录 */
	REPLACE INTO t_member_mapinfo(member_id,MAPINFO_ID,EXBOSS_FLAG) SELECT new.member_id,ID,REQUIRED_DEFEAT_COUNT>0 FROM t_map_info;
	update t_member_mapinfo set OPEN_FLAG = 1 where member_id = new.member_id and MAPINFO_ID = 11;
	
	/** 创建MapCell记录 */
	REPLACE INTO t_member_mapcell_info(member_id,MAPCELL_ID) SELECT new.member_id,ID FROM t_map_cell;
	
	SET UNIQUE_CHECKS=1;
    END
;;
DELIMITER ;
