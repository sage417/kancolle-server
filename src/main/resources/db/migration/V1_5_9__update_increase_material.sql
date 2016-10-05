
DROP PROCEDURE increase_material;

DELIMITER $$
CREATE DEFINER = `root`@`localhost` PROCEDURE `kancolle`.`increase_material`()
LANGUAGE SQL
NOT DETERMINISTIC
CONTAINS SQL
SQL SECURITY DEFINER
COMMENT ''
BEGIN
    declare now_member_id BIGINT;
    DECLARE max_material INT;

    DECLARE s INT DEFAULT FALSE;

    DECLARE cursor_material CURSOR FOR select member_id from t_member_material;

    DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET s=TRUE;

    OPEN cursor_material;

    while s <> TRUE do

      fetch cursor_material into now_member_id;

      SET max_material = (SELECT (750 + 250 * LEVEL) FROM t_member WHERE member_id = now_member_id);

      update t_member_material
      set FUEL = GREATEST(FUEL, LEAST(max_material, FUEL + 3)),
        BULL = GREATEST(BULL, LEAST(max_material, BULL + 3)),
        STEEL = GREATEST(STEEL, LEAST(max_material, STEEL + 3)),
        BAUXITE = GREATEST(BAUXITE, LEAST(max_material, BAUXITE + 1))
      where member_id = now_member_id;

    end while;

    CLOSE cursor_material ;

END $$
DELIMITER ;