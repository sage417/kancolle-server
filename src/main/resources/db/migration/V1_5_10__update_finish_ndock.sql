
DROP PROCEDURE finish_ndock;

DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE `kancolle`.`finish_ndock`()
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
	DECLARE now_cond INT;
	DECLARE m_id BIGINT;
	DECLARE s_id BIGINT;
	DECLARE ndock_id TINYINT;
	DECLARE ndock_idx BIGINT;

	DECLARE err INT DEFAULT FALSE;
	DECLARE s INT DEFAULT FALSE;

	DECLARE cursor_ndock CURSOR FOR SELECT member_id, SHIP_ID, ID, `index` FROM t_member_ndock WHERE STATE = 1 AND DATE_SUB(FROM_UNIXTIME(FLOOR(COMPLETE_TIME/1000)), INTERVAL 1 MINUTE) < NOW();

	DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET s=TRUE;
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET err=TRUE;

	OPEN cursor_ndock;

	WHILE s <> TRUE DO
		FETCH cursor_ndock into m_id, s_id, ndock_id, ndock_idx;
		SET now_cond = (SELECT COND FROM t_member_ship WHERE member_id = m_id AND ID = s_id);
		IF !ISNULL(now_cond) THEN
			START TRANSACTION;
			UPDATE t_member_ship SET COND = GREATEST(40, now_cond), NOWHP = MAXHP WHERE member_id = m_id AND ID = s_id;
		        UPDATE t_member_ndock SET STATE = 0, SHIP_ID= 0, COMPLETE_TIME= 0, COMPLETE_TIME_STR= '0' ,ITEM1= 0, ITEM3 = 0 WHERE `index` = ndock_idx;
			IF err = TRUE THEN
				SET err = FALSE;
				ROLLBACK;
			ELSE
				COMMIT;
			END IF;
		END IF;
	END WHILE;

	CLOSE cursor_ndock ;
  END $$
DELIMITER ;