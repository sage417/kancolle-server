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