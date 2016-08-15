CREATE TABLE `kancolle`.`t_member_preset_deck`(
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT UNSIGNED NOT NULL,
  `no` TINYINT UNSIGNED,
  `name` VARCHAR(255) NOT NULL DEFAULT '',
  `name_id` VARCHAR(255) NOT NULL DEFAULT '',
  `ship` VARCHAR(255) NOT NULL DEFAULT '[-1,-1,-1,-1,-1,-1]',
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`member_id`, `no`),
  FOREIGN KEY (`member_id`) REFERENCES `kancolle`.`t_member`(`member_id`)
);
