ALTER TABLE `kancolle`.`t_slotitem`
  ADD COLUMN `CLASS_ID` BIGINT UNSIGNED  NOT NULL  COMMENT '装备类型' AFTER `TYPE`,
  ADD COLUMN `PHOTOGRAPH_ID` BIGINT UNSIGNED  NOT NULL  COMMENT '图鉴类型' AFTER `CLASS_ID`,
  ADD COLUMN `CATEGORY_ID` BIGINT UNSIGNED  NOT NULL  COMMENT '装备详细类型' AFTER `PHOTOGRAPH_ID`,
  ADD COLUMN `ICON_ID` BIGINT UNSIGNED NOT NULL  COMMENT '装备图标' AFTER `CATEGORY_ID`,
  ADD FOREIGN KEY (`CLASS_ID`) REFERENCES `kancolle`.`t_slotitem_class`(`SLOTITEM_CLASS_ID`),
  ADD FOREIGN KEY (`PHOTOGRAPH_ID`) REFERENCES `kancolle`.`t_slotitem_photograph`(`SLOTITEM_PHOTOGRAPH_ID`),
  ADD FOREIGN KEY (`CATEGORY_ID`) REFERENCES `kancolle`.`t_slotitem_categoty`(`SLOTITEM_CATEGORY_ID`),
  ADD FOREIGN KEY (`ICON_ID`) REFERENCES `kancolle`.`t_slotitem_icon`(`SLOTITEM_ICON_ID`);
