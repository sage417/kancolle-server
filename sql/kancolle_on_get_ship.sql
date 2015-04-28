DELIMITER $$

USE `kancolle`$$

DROP TRIGGER /*!50032 IF EXISTS */ `tri_on_get_ship`$$

CREATE
    /*!50017 DEFINER = 'root'@'localhost' */
    TRIGGER `tri_on_get_ship` BEFORE INSERT ON `t_member_ship` 
    FOR EACH ROW BEGIN
	UPDATE	t_member SET member_ship_counter=member_ship_counter+1 WHERE member_id = new.member_id;

	DECLARE v_counter BIGINT;
	SET v_counter = (SELECT member_ship_counter FROM t_member WHERE member_id = new.member_id);
	SET new.ID = v_counter;
    END;
$$

DELIMITER ;