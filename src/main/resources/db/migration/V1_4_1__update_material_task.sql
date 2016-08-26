DROP PROCEDURE IF EXISTS `increase_material`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `increase_material`()
  BEGIN
    declare now_member_id BIGINT;
    DECLARE max_material INT;
    DECLARE now_fuel INT;
    DECLARE now_bull INT;
    DECLARE now_steel INT;
    DECLARE now_bauxite INT;

    DECLARE s INT DEFAULT 0;

    DECLARE cursor_material CURSOR FOR select FUEL,BULL,STEEL,BAUXITE,member_id from t_member_material;

    DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET s=1;

    OPEN cursor_material;

    while s <> 1 do

      fetch  cursor_material into now_fuel,now_bull,now_steel,now_bauxite,now_member_id;

      SET max_material = (SELECT (750 + 250 * LEVEL) FROM t_member WHERE member_id = now_member_id);

      update t_member_material
      set FUEL = LEAST(max_material, FUEL + 3),
        BULL = LEAST(max_material, BULL + 3),
        STEEL = LEAST(max_material, STEEL + 3),
        BAUXITE = LEAST(max_material, BAUXITE + 1)
      where member_id = now_member_id;

    end while;

    CLOSE cursor_material ;

  END;;
DELIMITER ;