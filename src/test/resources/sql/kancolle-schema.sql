-- MySQL Script generated by MySQL Workbench
-- 01/25/16 16:02:00
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Table `t_bgm`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_bgm` (
  `ID` INT(10) UNSIGNED NOT NULL,
  `NAME` VARCHAR(63) NOT NULL,
  PRIMARY KEY (`ID`))
;


-- -----------------------------------------------------
-- Table `t_duty_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_duty_category` (
  `CATEGORY_ID` INT(11) UNSIGNED NOT NULL,
  `NAME` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`CATEGORY_ID`))
;


-- -----------------------------------------------------
-- Table `t_duty_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_duty_type` (
  `TYPE_ID` INT(3) UNSIGNED NOT NULL,
  `DESCRIPTION` VARCHAR(63) NOT NULL,
  PRIMARY KEY (`TYPE_ID`))
;


-- -----------------------------------------------------
-- Table `t_duty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_duty` (
  `DUTY_NO` INT(10) UNSIGNED NOT NULL,
  `CATEGORY_ID` INT(10) UNSIGNED NOT NULL DEFAULT '1',
  `TYPE` INT(10) UNSIGNED NOT NULL DEFAULT '1',
  `TITLE` CHAR(63) NOT NULL,
  `DETAIL` CHAR(255) NOT NULL,
  `OPERATE` TINYINT(3) UNSIGNED NOT NULL DEFAULT '1',
  `MATERIAL` VARCHAR(63) NOT NULL,
  `BONUS_FLAG` TINYINT(3) UNSIGNED NOT NULL DEFAULT '1',
  `INVALID_FLAG` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  `PARENT_ID` INT(10) UNSIGNED NULL DEFAULT NULL,
  PRIMARY KEY (`DUTY_NO`))
;


-- -----------------------------------------------------
-- Table `t_duty_bonus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_duty_bonus` (
  `ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DUTY_NO` INT(10) UNSIGNED NOT NULL,
  `BONUS_TYPE` TINYINT(3) UNSIGNED NOT NULL,
  `BONUS_ITEM_ID` INT(10) UNSIGNED NOT NULL DEFAULT '0',
  `BONUS_COUNT` TINYINT(3) UNSIGNED NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`))
;


-- -----------------------------------------------------
-- Table `t_map_cell`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_map_cell` (
  `ID` INT(10) UNSIGNED NOT NULL,
  `NO` TINYINT(3) UNSIGNED NOT NULL,
  `COLOR_NO` TINYINT(3) UNSIGNED NOT NULL,
  `MAP_NO` TINYINT(3) UNSIGNED NOT NULL,
  `MAPAREA_ID` TINYINT(3) UNSIGNED NOT NULL,
  `MAPINFO_NO` TINYINT(3) UNSIGNED NOT NULL,
  `DECKPORT_NAME`  VARCHAR(65) NOT NULL DEFAULT '',
  `BASE_EXP` INT(10) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`ID`))
;


-- -----------------------------------------------------
-- Table `t_enemy_deckport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_enemy_deckport` (
  `INDEX` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `MAPCELL_ID` INT(10) UNSIGNED NOT NULL,
  `NO` INT(10) UNSIGNED NOT NULL,
  `DECK_NAME` VARCHAR(255) NOT NULL DEFAULT '',
  `FORMATION` TINYINT(3) UNSIGNED NOT NULL,
  `SHIP` VARCHAR(511) NOT NULL DEFAULT '[-1,-1,-1,-1,-1,-1]',
  `LV`  VARCHAR(33) UNSIGNED NOT NULL DEFAULT '',
  `RANK`  VARCHAR(33) UNSIGNED NOT NULL DEFAULT '',
  `BASE_EXP` INT(10) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`INDEX`))
;


-- -----------------------------------------------------
-- Table `t_ship_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_ship_type` (
  `ID` TINYINT(4) UNSIGNED NOT NULL,
  `EQUIP_TYPE` TEXT NOT NULL,
  `KCNT` TINYINT(4) UNSIGNED NOT NULL,
  `NAME` VARCHAR(255) NOT NULL,
  `SCNT` TINYINT(4) UNSIGNED NOT NULL,
  `SORTNO` TINYINT(4) UNSIGNED NOT NULL,
  PRIMARY KEY (`ID`))
;


-- -----------------------------------------------------
-- Table `t_ship`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_ship` (
  `ID` INT(10) UNSIGNED NOT NULL,
  `SORTNO` SMALLINT(4) UNSIGNED NOT NULL COMMENT '图鉴ID',
  `NAME` VARCHAR(255) NOT NULL COMMENT '舰名',
  `YOMI` VARCHAR(255) NOT NULL,
  `TYPE` TINYINT(4) UNSIGNED NOT NULL COMMENT '舰种',
  `AFTERLV` TINYINT(4) UNSIGNED NOT NULL COMMENT '改造等级',
  `AFTERSHIPID` INT(10) UNSIGNED NOT NULL COMMENT '改造',
  `TAIK` VARCHAR(255) NOT NULL COMMENT '耐久',
  `SOUK` VARCHAR(255) NOT NULL COMMENT '装甲',
  `HOUG` VARCHAR(255) NOT NULL COMMENT '火力',
  `RAIG` VARCHAR(255) NOT NULL COMMENT '雷装',
  `TYKU` VARCHAR(255) NOT NULL COMMENT '对空',
  `LUCK` VARCHAR(255) NOT NULL COMMENT '幸运',
  `KAIHI` VARCHAR(255) NULL DEFAULT NULL COMMENT '回避',
  `TAISEN` VARCHAR(255) NULL DEFAULT NULL COMMENT '对潜',
  `SAKUTEKI` VARCHAR(255) NULL DEFAULT NULL COMMENT '索敌',
  `SOKU` TINYINT(4) UNSIGNED NOT NULL COMMENT '速力',
  `LENG` TINYINT(4) UNSIGNED NOT NULL COMMENT '射程',
  `SLOTNUM` TINYINT(4) UNSIGNED NOT NULL COMMENT '装备栏数',
  `MAXEQ` VARCHAR(255) NOT NULL COMMENT '最大搭载',
  `BUILDTIME` SMALLINT(4) UNSIGNED NOT NULL COMMENT '建造时间',
  `BROKEN` VARCHAR(255) NOT NULL COMMENT '解体资源',
  `POWUP` VARCHAR(63) NOT NULL COMMENT '改修',
  `BACKS` TINYINT(4) UNSIGNED NOT NULL,
  `GETMES` VARCHAR(255) NOT NULL COMMENT '介绍',
  `AFTERFUEL` SMALLINT(4) UNSIGNED NOT NULL COMMENT '改造燃油',
  `AFTERBULL` SMALLINT(4) UNSIGNED NOT NULL COMMENT '改造弹药',
  `FUEL_MAX` SMALLINT(4) UNSIGNED NOT NULL COMMENT '燃油',
  `BULL_MAX` SMALLINT(4) UNSIGNED NOT NULL COMMENT '弹药',
  `VOICEF` TINYINT(4) UNSIGNED NOT NULL,
  PRIMARY KEY (`ID`))
;


-- -----------------------------------------------------
-- Table `t_enemy_deckport_mapping`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_enemy_deckport_mapping` (
  `INDEX` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `MAPCELL_ID` INT(10) UNSIGNED NOT NULL,
  `NO` INT(10) UNSIGNED NOT NULL,
  `SHIP_ID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`INDEX`))
;


-- -----------------------------------------------------
-- Table `t_enemy_ship_slotitem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_enemy_ship_slotitem` (
  `ENEMY_SHIP_ID` INT(11) UNSIGNED NOT NULL,
  `SLOT` VARCHAR(63) NOT NULL DEFAULT '[-1,-1,-1,-1,-1]',
  PRIMARY KEY (`ENEMY_SHIP_ID`))
;


-- -----------------------------------------------------
-- Table `t_slotitem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_slotitem` (
  `ID` INT(11) UNSIGNED NOT NULL,
  `SORTNO` SMALLINT(5) UNSIGNED NOT NULL COMMENT '图鉴ID',
  `NAME` VARCHAR(63) NOT NULL COMMENT '装备名',
  `TYPE` VARCHAR(63) NOT NULL,
  `TAIK` TINYINT(4) NOT NULL,
  `SOUK` TINYINT(4) NOT NULL,
  `HOUG` TINYINT(4) NOT NULL,
  `RAIG` TINYINT(4) NOT NULL,
  `SOKU` TINYINT(4) NOT NULL,
  `BAKU` TINYINT(4) NOT NULL,
  `TYKU` TINYINT(4) NOT NULL,
  `TAIS` TINYINT(4) NOT NULL,
  `ATAP` TINYINT(4) NOT NULL,
  `HOUM` TINYINT(4) NOT NULL,
  `RAIM` TINYINT(4) NOT NULL,
  `HOUK` TINYINT(4) NOT NULL,
  `RAIK` TINYINT(4) NOT NULL,
  `BAKK` TINYINT(4) NOT NULL,
  `SAKU` TINYINT(4) NOT NULL,
  `SAKB` TINYINT(4) NOT NULL,
  `LUCK` TINYINT(4) NOT NULL,
  `LENG` TINYINT(4) NOT NULL,
  `RARE` TINYINT(4) NOT NULL,
  `BROKEN` VARCHAR(63) NOT NULL,
  `INFO` TEXT NOT NULL,
  `USEBULL` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`))
;


-- -----------------------------------------------------
-- Table `t_enemy_ship_slotitem_mapping`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_enemy_ship_slotitem_mapping` (
  `INDEX` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `SHIP_ID` INT(10) UNSIGNED NOT NULL,
  `SLOTITEM_ID` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`INDEX`))
;


-- -----------------------------------------------------
-- Table `t_exp_member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_exp_member` (
  `LV` TINYINT(10) UNSIGNED NOT NULL,
  `EXP` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`LV`))
;


-- -----------------------------------------------------
-- Table `t_mission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_mission` (
  `ID` INT(10) UNSIGNED NOT NULL,
  `NAME` VARCHAR(255) NOT NULL,
  `DIFFICULTY` TINYINT(4) UNSIGNED NOT NULL,
  `MAPAREA_ID` TINYINT(4) UNSIGNED NOT NULL,
  `TIME` SMALLINT(4) UNSIGNED NOT NULL,
  `USE_FUEL` FLOAT UNSIGNED NOT NULL,
  `USE_BULL` FLOAT UNSIGNED NOT NULL,
  `MATERIALS` VARCHAR(255) NOT NULL,
  `WIN_ITEM1` VARCHAR(255) NOT NULL,
  `WIN_ITEM2` VARCHAR(255) NOT NULL,
  `RETURN_FLAG` TINYINT(4) UNSIGNED NOT NULL,
  `DETAILS` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`))
;


-- -----------------------------------------------------
-- Table `t_exp_mission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_exp_mission` (
  `ID` INT(10) UNSIGNED NOT NULL,
  `EXP` INT(10) UNSIGNED NOT NULL,
  `SHIP_EXP` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`ID`))
;


-- -----------------------------------------------------
-- Table `t_exp_ship`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_exp_ship` (
  `LV` SMALLINT(11) UNSIGNED NOT NULL,
  `EXP` BIGINT(20) UNSIGNED NOT NULL,
  `NEXT` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`LV`))
;


-- -----------------------------------------------------
-- Table `t_furniture`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_furniture` (
  `ID` INT(10) UNSIGNED NOT NULL,
  `TYPE` SMALLINT(3) UNSIGNED NOT NULL,
  `NO` SMALLINT(3) UNSIGNED NOT NULL,
  `TITLE` VARCHAR(63) NOT NULL,
  `DESCRIPTION` VARCHAR(255) NOT NULL,
  `RARITY` TINYINT(3) UNSIGNED NOT NULL,
  `PRICE` INT(5) UNSIGNED NOT NULL,
  `SALEFLG` TINYINT(3) UNSIGNED NOT NULL,
  `SEASON` TINYINT(3) UNSIGNED NOT NULL,
  PRIMARY KEY (`ID`))
;


-- -----------------------------------------------------
-- Table `t_furniture_graph`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_furniture_graph` (
  `ID` INT(10) UNSIGNED NOT NULL,
  `TYPE` TINYINT(3) UNSIGNED NOT NULL,
  `NO` TINYINT(3) UNSIGNED NOT NULL,
  `FILENAME` VARCHAR(63) NOT NULL,
  `VERSION` TINYINT(3) UNSIGNED NOT NULL,
  PRIMARY KEY (`ID`))
;


-- -----------------------------------------------------
-- Table `t_furniture_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_furniture_type` (
  `type` INT(10) UNSIGNED NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`type`))
;


-- -----------------------------------------------------
-- Table `t_item_shop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_item_shop` (
  `NAME` VARCHAR(255) NOT NULL,
  `ITEM_ID` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`NAME`))
;


-- -----------------------------------------------------
-- Table `t_map_area`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_map_area` (
  `ID` INT(10) UNSIGNED NOT NULL,
  `NAME` VARCHAR(63) NOT NULL,
  `TYPE` TINYINT(3) UNSIGNED NOT NULL,
  PRIMARY KEY (`ID`))
;


-- -----------------------------------------------------
-- Table `t_map_bgm`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_map_bgm` (
  `ID` INT(10) UNSIGNED NOT NULL,
  `MAPAREA_ID` TINYINT(3) UNSIGNED NOT NULL,
  `NO` TINYINT(3) UNSIGNED NOT NULL,
  `MAP_BGM` VARCHAR(63) NOT NULL,
  `BOSS_BGM` VARCHAR(63) NOT NULL,
  PRIMARY KEY (`ID`))
;


-- -----------------------------------------------------
-- Table `t_map_cell_traveller`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_map_cell_traveller` (
  `CELL_ID` INT(10) UNSIGNED NOT NULL,
  `RASHIN_FLAG` TINYINT(4) NOT NULL,
  `RASHIN_ID` INT(11) NOT NULL,
  `COLOR_NO` INT(11) NOT NULL,
  `EVENT_ID` INT(11) NOT NULL,
  `EVENT_KIND` INT(11) NOT NULL,
  `NEXT` INT(11) NOT NULL,
  `COMMENT_KIND` INT(11) NOT NULL,
  `PRODUCTION_KIND` INT(11) NOT NULL,
  PRIMARY KEY (`CELL_ID`))
;


-- -----------------------------------------------------
-- Table `t_map_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_map_info` (
  `ID` INT(10) UNSIGNED NOT NULL,
  `INFOTEXT` VARCHAR(255) NOT NULL,
  `ITEM` VARCHAR(63) NOT NULL,
  `MAX_MAPHP` TINYINT(3) UNSIGNED NOT NULL,
  `REQUIRED_DEFEAT_COUNT` TINYINT(3) UNSIGNED NOT NULL,
  `LEVEL` TINYINT(3) UNSIGNED NOT NULL,
  `MAPAREA_ID` TINYINT(3) UNSIGNED NOT NULL,
  `NAME` VARCHAR(63) NOT NULL,
  `NO` TINYINT(3) UNSIGNED NOT NULL,
  `OPETEXT` VARCHAR(63) NOT NULL,
  `SALLY_FALG` VARCHAR(63) NOT NULL,
  PRIMARY KEY (`ID`))
;


-- -----------------------------------------------------
-- Table `t_member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_member` (
  `member_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `api_token` CHAR(40) NULL DEFAULT NULL,
  `nickname` VARCHAR(63) NOT NULL COMMENT '提督名',
  `nickname_id` VARCHAR(63) NOT NULL DEFAULT '',
  `active_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT '1',
  `starttime` BIGINT(20) UNSIGNED NULL DEFAULT NULL,
  `level` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '舰队司令部Level',
  `rank` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '称号',
  `experience` BIGINT(20) UNSIGNED NULL DEFAULT NULL,
  `fleetname` VARCHAR(255) NULL DEFAULT NULL,
  `comment` VARCHAR(255) NOT NULL DEFAULT '',
  `comment_id` VARCHAR(255) NOT NULL DEFAULT '',
  `max_chara` SMALLINT(5) UNSIGNED NOT NULL DEFAULT '100' COMMENT '最大保有可能舰娘数',
  `max_slotitem` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '497' COMMENT '最大保有可能装备数',
  `max_kagu` INT(11) UNSIGNED NOT NULL DEFAULT '0',
  `playtime` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0',
  `tutorial` INT(4) UNSIGNED NOT NULL DEFAULT '0',
  `furniture` CHAR(63) NOT NULL DEFAULT '[1,72,102,133,164,221,242]',
  `count_deck` TINYINT(3) UNSIGNED NOT NULL DEFAULT '2' COMMENT '最大保有可能舰队数',
  `count_kdock` TINYINT(3) UNSIGNED NOT NULL DEFAULT '2' COMMENT '工厂数',
  `count_ndock` TINYINT(3) UNSIGNED NOT NULL DEFAULT '2' COMMENT '入渠数',
  `fcoin` INT(11) UNSIGNED NOT NULL DEFAULT '0' COMMENT '家具币',
  `st_win` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '出击胜数',
  `st_lose` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '出击败数',
  `ms_count` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '远征回数',
  `ms_success` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '远征成功数',
  `pt_win` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '演戏胜数',
  `pt_lose` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '演戏败数',
  `pt_challenged` INT(10) UNSIGNED NOT NULL DEFAULT '0',
  `pt_challenged_win` INT(10) UNSIGNED NOT NULL DEFAULT '0',
  `firstflag` INT(10) UNSIGNED NOT NULL DEFAULT '1',
  `tutorial_progress` INT(10) UNSIGNED NOT NULL DEFAULT '100',
  `pvp` VARCHAR(255) NOT NULL DEFAULT '[0,0]',
  `medals` INT(10) UNSIGNED NOT NULL DEFAULT '0' COMMENT '甲徽章',
  `p_bgm_id` INT(10) UNSIGNED NOT NULL DEFAULT '101' COMMENT '母港职务室BGM',
  `parallel_quest_count` TINYINT(3) UNSIGNED NOT NULL DEFAULT '5' COMMENT '最大可能收付任务数',
  `large_dock` TINYINT(3) NOT NULL DEFAULT '0' COMMENT '是否可进行大建',
  PRIMARY KEY (`member_id`))
;


-- -----------------------------------------------------
-- Table `t_member_deckport`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_member_deckport` (
  `index` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT(20) UNSIGNED NOT NULL,
  `ID` TINYINT(3) UNSIGNED NOT NULL COMMENT '舰队ID',
  `NAME` VARCHAR(255) NOT NULL COMMENT '舰队名',
  `NAME_ID` VARCHAR(255) NOT NULL DEFAULT '',
  `MISSION_STATUS` TINYINT(4) UNSIGNED NOT NULL DEFAULT '0',
  `MISSION_ID` INT(11) UNSIGNED NULL DEFAULT NULL,
  `MISSION_COMPLETE_TIME` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0',
  `MISSION_FLAG` TINYINT(4) UNSIGNED NOT NULL DEFAULT '0',
  `FLAGSHIP` VARCHAR(255) NOT NULL DEFAULT '0',
  `SHIP` VARCHAR(255) NOT NULL DEFAULT '[-1,-1,-1,-1,-1,-1]' COMMENT '舰队信息',
  `lock` TINYINT(3) UNSIGNED NOT NULL DEFAULT '1' COMMENT '是否可用',
  PRIMARY KEY (`index`))
;


-- -----------------------------------------------------
-- Table `t_member_deckport_ship_mapping`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_member_deckport_ship_mapping` (
  `index` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT(20) UNSIGNED NOT NULL,
  `deck_id` TINYINT(3) UNSIGNED NOT NULL,
  `member_ship_id` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`index`))
;


-- -----------------------------------------------------
-- Table `t_member_duty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_member_duty` (
  `index` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT(20) UNSIGNED NOT NULL,
  `duty_no` INT(10) UNSIGNED NOT NULL,
  `state` TINYINT(3) UNSIGNED NOT NULL DEFAULT '1',
  `process_flag` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  `counter` INT(10) UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`index`))
;


-- -----------------------------------------------------
-- Table `t_member_furniture`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_member_furniture` (
  `index` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT(20) UNSIGNED NOT NULL,
  `furniture_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`index`))
;


-- -----------------------------------------------------
-- Table `t_member_kdock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_member_kdock` (
  `index` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT(20) UNSIGNED NOT NULL,
  `ID` TINYINT(3) UNSIGNED NOT NULL,
  `STATE` TINYINT(3) NOT NULL DEFAULT '-1',
  `CREATED_SHIP_ID` INT(10) UNSIGNED NOT NULL DEFAULT '0',
  `COMPLETE_TIME` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0',
  `COMPLETE_TIME_STR` VARCHAR(255) NOT NULL DEFAULT '0',
  `ITEM1` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0',
  `ITEM2` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0',
  `ITEM3` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0',
  `ITEM4` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0',
  `ITEM5` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`index`))
;


-- -----------------------------------------------------
-- Table `t_member_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_member_log` (
  `index` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT(20) UNSIGNED NOT NULL,
  `NO` TINYINT(4) NOT NULL,
  `TYPE` VARCHAR(255) NOT NULL,
  `STATE` VARCHAR(255) NOT NULL,
  `MESSAGE` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`index`))
;


-- -----------------------------------------------------
-- Table `t_member_map_battle_status`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `t_member_map_battle_status` (
  `member_id` BIGINT(20) UNSIGNED NOT NULL,
  `deck_id` TINYINT(3) UNSIGNED NOT NULL,
  `traveller_no` INT(10) UNSIGNED NULL DEFAULT NULL,
  `map_cell_id` INT(10) UNSIGNED NULL DEFAULT NULL,
  `map_get_resource` CHAR(64) NOT NULL DEFAULT '[0,0,0,0,0,0,0,0]',
  `map_fetch_rescource` CHAR(64) NOT NULL DEFAULT '[0,0,0,0,0,0,0,0]',
  `session` CHAR(1024) NOT NULL DEFAULT '{}',
  `battle_flag` tinyint(4) NOT NULL DEFAULT '0',
  `result_flag` tinyint(4) NOT NULL DEFAULT '0',
  `createTime` datetime NOT NULL,
  PRIMARY KEY (`member_id`))
;


-- -----------------------------------------------------
-- Table `t_member_mapcell_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_member_mapcell_info` (
  `INDEX` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT(20) UNSIGNED NOT NULL,
  `MAPCELL_ID` INT(10) UNSIGNED NOT NULL,
  `PASS_FLAG` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`INDEX`))
;


-- -----------------------------------------------------
-- Table `t_member_mapinfo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_member_mapinfo` (
  `INDEX` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT(20) UNSIGNED NOT NULL,
  `MAPINFO_ID` INT(10) UNSIGNED NOT NULL,
  `CLEAR_FLAG` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  `EXBOSS_FLAG` TINYINT(3) UNSIGNED NOT NULL,
  `DEFEATED_COUNT` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  `OPEN_FLAG` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`INDEX`))
;


-- -----------------------------------------------------
-- Table `t_member_material`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_member_material` (
  `member_id` BIGINT(20) UNSIGNED NOT NULL,
  `FUEL` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '1000',
  `BULL` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '1000',
  `STEEL` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '1000',
  `BAUXITE` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '1000',
  `FAST_REC` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0',
  `FAST_BUILD` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0',
  `DEV_ITEM` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0',
  `ENH_ITEM` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`member_id`))
;


-- -----------------------------------------------------
-- Table `t_member_mission`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_member_mission` (
  `index` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT(20) UNSIGNED NOT NULL,
  `mission_id` INT(10) UNSIGNED NOT NULL,
  `state` TINYINT(4) NOT NULL,
  PRIMARY KEY (`index`))
;


-- -----------------------------------------------------
-- Table `t_member_ndock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_member_ndock` (
  `index` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT(20) UNSIGNED NOT NULL,
  `ID` TINYINT(3) UNSIGNED NOT NULL,
  `STATE` TINYINT(3) NOT NULL DEFAULT '-1',
  `SHIP_ID` INT(11) UNSIGNED NOT NULL DEFAULT '0',
  `COMPLETE_TIME` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0',
  `COMPLETE_TIME_STR` VARCHAR(255) NOT NULL DEFAULT '0',
  `ITEM1` MEDIUMINT(9) UNSIGNED NOT NULL DEFAULT '0',
  `ITEM2` MEDIUMINT(9) UNSIGNED NOT NULL DEFAULT '0',
  `ITEM3` MEDIUMINT(9) UNSIGNED NOT NULL DEFAULT '0',
  `ITEM4` MEDIUMINT(9) UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`index`))
;


-- -----------------------------------------------------
-- Table `t_member_picturebook`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_member_picturebook` (
  `MEMBER_PICTUREBOOK_ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT(20) UNSIGNED NOT NULL,
  `SHIP_SORTNO` SMALLINT(10) UNSIGNED NOT NULL,
  `state` VARCHAR(255) NOT NULL DEFAULT '[[1,0,0,0]]',
  PRIMARY KEY (`MEMBER_PICTUREBOOK_ID`))
;


-- -----------------------------------------------------
-- Table `t_member_ship`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_member_ship` (
  `index` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT(20) UNSIGNED NOT NULL,
  `ID` BIGINT(20) UNSIGNED NOT NULL,
  `SHIP_ID` INT(11) UNSIGNED NOT NULL,
  `LV` TINYINT(3) UNSIGNED NOT NULL DEFAULT '1' COMMENT '等级',
  `EXP` VARCHAR(255) NOT NULL DEFAULT '[0,100,0]' COMMENT '经验总和,next经验，百分比',
  `NOWHP` SMALLINT(6) UNSIGNED NOT NULL COMMENT '当前血量',
  `MAXHP` SMALLINT(6) UNSIGNED NOT NULL COMMENT '最大血量',
  `LENG` TINYINT(3) UNSIGNED NOT NULL COMMENT '射程',
  `SLOT` VARCHAR(255) NOT NULL COMMENT '装备',
  `ONSLOT` VARCHAR(255) NOT NULL COMMENT '搭载量',
  `KYOUKA` VARCHAR(255) NOT NULL DEFAULT '[0,0,0,0]' COMMENT '成长(火雷空甲)',
  `FUEL` SMALLINT(4) UNSIGNED NOT NULL COMMENT '燃油',
  `BULL` SMALLINT(4) UNSIGNED NOT NULL COMMENT '弹药',
  `SRATE` TINYINT(3) UNSIGNED NOT NULL,
  `COND` TINYINT(3) UNSIGNED NOT NULL DEFAULT '40' COMMENT '状态',
  `KARYOKU` VARCHAR(255) NOT NULL COMMENT '火力',
  `RAISOU` VARCHAR(255) NOT NULL COMMENT '雷装',
  `TAIKU` VARCHAR(255) NOT NULL COMMENT '对空',
  `SOUKOU` VARCHAR(255) NOT NULL COMMENT '装甲',
  `KAIHI` VARCHAR(255) NOT NULL COMMENT '回避',
  `TAISEN` VARCHAR(255) NOT NULL COMMENT '对潜',
  `SAKUTEKI` VARCHAR(255) NOT NULL COMMENT '索敌',
  `LUCKY` VARCHAR(255) NOT NULL COMMENT '幸运',
  `LOCKED` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '上锁',
  `LOCKED_EQUIP` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0' COMMENT '是否拥有上锁装备',
  `DELETED` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  `DELETED_TIME` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`index`))
;


-- -----------------------------------------------------
-- Table `t_member_ship_slotitem_mapping`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_member_ship_slotitem_mapping` (
  `index` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT(20) UNSIGNED NOT NULL,
  `member_ship_id` BIGINT(20) UNSIGNED NOT NULL,
  `member_slotitem_id` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`index`))
;


-- -----------------------------------------------------
-- Table `t_member_slotitem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_member_slotitem` (
  `index` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT(20) UNSIGNED NOT NULL,
  `ID` BIGINT(20) UNSIGNED NOT NULL,
  `LEVEL` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  `LOCKED` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  `SLOTITEM_ID` INT(11) UNSIGNED NOT NULL,
  `DELETED` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  `DELETED_TIME` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`index`))
;


-- -----------------------------------------------------
-- Table `t_useitem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_useitem` (
  `ID` INT(11) UNSIGNED NOT NULL,
  `USETYPE` TINYINT(4) UNSIGNED NOT NULL,
  `CATEGORY` SMALLINT(6) UNSIGNED NOT NULL,
  `NAME` VARCHAR(255) NOT NULL,
  `DESCRIPTION` VARCHAR(255) NOT NULL,
  `PRICE` MEDIUMINT(9) UNSIGNED NOT NULL,
  PRIMARY KEY (`ID`))
;


-- -----------------------------------------------------
-- Table `t_member_useitem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_member_useitem` (
  `index` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT(20) UNSIGNED NOT NULL,
  `ID` INT(10) UNSIGNED NOT NULL,
  `COUNT` INT(10) NOT NULL,
  PRIMARY KEY (`index`))
;


-- -----------------------------------------------------
-- Table `t_mission_state`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_mission_state` (
  `MISSION_STATE` TINYINT(4) NOT NULL,
  `DESCRIPTION` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`MISSION_STATE`))
;


-- -----------------------------------------------------
-- Table `t_pay_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_pay_item` (
  `ID` INT(11) NOT NULL,
  `TYPE` INT(11) NOT NULL,
  `NAME` VARCHAR(255) NOT NULL,
  `DESCRIPTION` VARCHAR(255) NOT NULL,
  `ITEM` VARCHAR(255) NOT NULL,
  `PRICE` INT(11) NOT NULL,
  PRIMARY KEY (`ID`))
;


-- -----------------------------------------------------
-- Table `t_ship_exp`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_ship_exp` (
  `LV` INT(11) UNSIGNED NOT NULL,
  `EXP` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`LV`))
;


-- -----------------------------------------------------
-- Table `t_ship_graph`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_ship_graph` (
  `ID` INT(11) NOT NULL,
  `SORTNO` INT(11) NOT NULL,
  `FILENAME` VARCHAR(255) NOT NULL,
  `VERSION` VARCHAR(255) NOT NULL,
  `BOKO_N` VARCHAR(255) NOT NULL,
  `BOKO_D` VARCHAR(255) NOT NULL,
  `KAISYU_N` VARCHAR(255) NOT NULL,
  `KAISYU_D` VARCHAR(255) NOT NULL,
  `KAIZO_N` VARCHAR(255) NOT NULL,
  `KAIZO_D` VARCHAR(255) NOT NULL,
  `MAP_N` VARCHAR(255) NOT NULL,
  `MAP_D` VARCHAR(255) NOT NULL,
  `ENSYUF_N` VARCHAR(255) NOT NULL,
  `ENSYUF_D` VARCHAR(255) NOT NULL,
  `ENSYUE_N` VARCHAR(255) NOT NULL,
  `BATTLE_N` VARCHAR(255) NOT NULL,
  `BATTLE_D` VARCHAR(255) NOT NULL,
  `WEDA` VARCHAR(255) NOT NULL,
  `WEDB` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`))
;


-- -----------------------------------------------------
-- Table `t_ship_picturebook`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_ship_picturebook` (
  `SHIP_ID` INT(10) UNSIGNED NOT NULL,
  `CTYPE` TINYINT(3) UNSIGNED NOT NULL,
  `CNUM` TINYINT(3) UNSIGNED NOT NULL,
  `SHIP_INFO` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`SHIP_ID`))
;


-- -----------------------------------------------------
-- Table `t_ship_upgrade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_ship_upgrade` (
  `ID` INT(11) NOT NULL,
  `ORIGINAL_SHIP_ID` INT(11) NOT NULL,
  `UPGRADE_TYPE` INT(11) NOT NULL,
  `UPGRADE_LEVEL` INT(11) NOT NULL,
  `DRAWING_COUNT` INT(11) NOT NULL,
  `SORTNO` INT(11) NOT NULL,
  PRIMARY KEY (`ID`))
;


-- -----------------------------------------------------
-- Table `t_slotitem_equiptype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_slotitem_equiptype` (
  `ID` INT(11) NOT NULL,
  `TYPE` VARCHAR(63) NOT NULL,
  `SHOW_FLAG` TINYINT(4) NOT NULL,
  PRIMARY KEY (`ID`))
;


-- -----------------------------------------------------
-- Table `t_slotitem_graph`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_slotitem_graph` (
  `ID` INT(11) NOT NULL,
  `SORTNO` SMALLINT(5) UNSIGNED NOT NULL,
  `FILENAME` VARCHAR(255) NOT NULL,
  `VERSION` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`ID`))
;


-- -----------------------------------------------------
-- Table `t_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `t_user` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` CHAR(255) NULL DEFAULT NULL,
  `nickname` CHAR(255) NULL DEFAULT NULL,
  `mobile` CHAR(63) NULL DEFAULT NULL,
  `email` CHAR(63) NULL DEFAULT NULL,
  `password` CHAR(255) NULL DEFAULT NULL,
  `salt` CHAR(255) NULL DEFAULT NULL,
  `statue` INT(11) NULL DEFAULT NULL,
  `createtime` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
;

-- -----------------------------------------------------
-- View `v_member_deckport`
-- -----------------------------------------------------
CREATE  OR REPLACE  VIEW `v_member_deckport` AS select `t_member_deckport`.`member_id` AS `member_id`,`t_member_deckport`.`ID` AS `ID`,`t_member_deckport`.`NAME` AS `NAME`,`t_member_deckport`.`NAME_ID` AS `NAME_ID`,concat('[',concat_ws(',',`t_member_deckport`.`MISSION_STATUS`,ifnull(`t_member_deckport`.`MISSION_ID`,0),`t_member_deckport`.`MISSION_COMPLETE_TIME`,`t_member_deckport`.`MISSION_FLAG`),']') AS `MISSION`,`t_member_deckport`.`FLAGSHIP` AS `FLAGSHIP`,`t_member_deckport`.`SHIP` AS `SHIP`,`t_member_deckport`.`lock` AS `lock` from `t_member_deckport` where (`t_member_deckport`.`lock` = 0);

-- -----------------------------------------------------
-- View `v_member_mission`
-- -----------------------------------------------------
CREATE  OR  REPLACE VIEW `v_member_mission` AS select `t_member_mission`.`member_id` AS `member_id`,`t_member_mission`.`mission_id` AS `mission_id`,`t_member_mission`.`state` AS `state` from `t_member_mission` where (`t_member_mission`.`state` > -(1));

-- -----------------------------------------------------
-- View `v_member_ship`
-- -----------------------------------------------------
CREATE  OR REPLACE  VIEW `v_member_ship` AS select `t_member_ship`.`member_id` AS `member_id`,`t_member_ship`.`ID` AS `ID`,`t_member_ship`.`SHIP_ID` AS `SHIP_ID`,`t_member_ship`.`LV` AS `LV`,`t_member_ship`.`EXP` AS `EXP`,`t_member_ship`.`NOWHP` AS `NOWHP`,`t_member_ship`.`MAXHP` AS `MAXHP`,`t_member_ship`.`LENG` AS `LENG`,`t_member_ship`.`SLOT` AS `SLOT`,`t_member_ship`.`ONSLOT` AS `ONSLOT`,`t_member_ship`.`KYOUKA` AS `KYOUKA`,`t_member_ship`.`FUEL` AS `FUEL`,`t_member_ship`.`BULL` AS `BULL`,`t_member_ship`.`SRATE` AS `SRATE`,`t_member_ship`.`COND` AS `COND`,`t_member_ship`.`KARYOKU` AS `KARYOKU`,`t_member_ship`.`RAISOU` AS `RAISOU`,`t_member_ship`.`TAIKU` AS `TAIKU`,`t_member_ship`.`SOUKOU` AS `SOUKOU`,`t_member_ship`.`KAIHI` AS `KAIHI`,`t_member_ship`.`TAISEN` AS `TAISEN`,`t_member_ship`.`SAKUTEKI` AS `SAKUTEKI`,`t_member_ship`.`LUCKY` AS `LUCKY`,`t_member_ship`.`LOCKED` AS `LOCKED`,`t_member_ship`.`LOCKED_EQUIP` AS `LOCKED_EQUIP` from `t_member_ship` where (`t_member_ship`.`DELETED` = 0) order by `t_member_ship`.`member_id`,`t_member_ship`.`ID`;

-- -----------------------------------------------------
-- View `v_member_slotitem`
-- -----------------------------------------------------
CREATE  OR REPLACE  VIEW `v_member_slotitem` AS select `t_member_slotitem`.`member_id` AS `member_id`,`t_member_slotitem`.`ID` AS `ID`,`t_member_slotitem`.`LEVEL` AS `LEVEL`,`t_member_slotitem`.`LOCKED` AS `LOCKED`,`t_member_slotitem`.`SLOTITEM_ID` AS `SLOTITEM_ID` from `t_member_slotitem` where (`t_member_slotitem`.`DELETED` = 0) order by `t_member_slotitem`.`member_id`,`t_member_slotitem`.`ID`;

CREATE OR REPLACE VIEW `v_enemy_deckport`AS SELECT t_enemy_deckport.`INDEX`, t_enemy_deckport.MAPCELL_ID, t_enemy_deckport.`NO`, t_enemy_deckport.FORMATION, t_enemy_deckport.SHIP, t_enemy_deckport.RANK, t_enemy_deckport.LV, t_enemy_deckport.BASE_EXP, t_map_cell.DECKPORT_NAME FROM t_enemy_deckport INNER JOIN t_map_cell ON t_enemy_deckport.MAPCELL_ID = t_map_cell.ID ;