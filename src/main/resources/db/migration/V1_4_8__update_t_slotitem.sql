ALTER TABLE `kancolle`.`t_slotitem`
  ADD COLUMN `COST` TINYINT UNSIGNED NULL AFTER `USEBULL`,
  ADD COLUMN `DISTANCE` TINYINT UNSIGNED NULL AFTER `COST`;