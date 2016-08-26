DROP PROCEDURE IF EXISTS `increase_material`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `increase_material`()
  BEGIN
    declare now_member_id BIGINT;
    DECLARE max_material INT;
    DECLARE now_fuel INT;
    declare add_fuel INT default 3;
    DECLARE now_bull INT;
    DECLARE add_bull INT DEFAULT 3;
    DECLARE now_steel INT;
    DECLARE add_steel INT DEFAULT 3;
    DECLARE now_bauxite INT;
    DECLARE add_bauxite INT DEFAULT 1;

    DECLARE s INT DEFAULT 0;

    DECLARE cursor_material CURSOR FOR select FUEL,BULL,STEEL,BAUXITE,member_id from t_member_material;

    DECLARE CONTINUE HANDLER FOR SQLSTATE '02000' SET s=1;

    OPEN cursor_material;

    while s <> 1 do

      fetch  cursor_material into now_fuel,now_bull,now_steel,now_bauxite,now_member_id;

      SET max_material = (SELECT (750 + 250 * LEVEL) FROM t_member WHERE member_id = now_member_id);

      if (now_fuel + add_fuel) > max_material  THEN
        set add_fuel =  max_material - now_fuel;
      end if;

      if (now_bull + add_bull) > max_material  THEN
        set add_bull = max_material - now_bull;
      end if;

      if (now_steel + add_steel) > max_material THEN
        set add_steel = max_material - now_steel;
      end if;

      if (now_bauxite + add_bauxite) > max_material THEN
        set add_bauxite = max_material - now_bauxite;
      end if;

      update t_member_material set FUEL = FUEL+add_fuel, BULL=BULL+add_bull, STEEL=STEEL+add_steel,BAUXITE = BAUXITE + add_bauxite where member_id = now_member_id;

    end while;

    CLOSE cursor_material ;

  END;;
DELIMITER ;