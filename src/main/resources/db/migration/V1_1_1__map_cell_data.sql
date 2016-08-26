CREATE TABLE `t_map_cell_traveller` (
  `CELL_ID` int(11) unsigned NOT NULL,
  `MAPAREA_ID` int(11) unsigned NOT NULL,
  `MAPINFO_ID` int(11) unsigned NOT NULL,
  `NO` int(11) NOT NULL,
  `RASHIN_FLAG` tinyint(4) NOT NULL,
  `RASHIN_ID` int(11) NOT NULL,
  `COLOR_NO` int(11) NOT NULL,
  `EVENT_ID` int(11) NOT NULL,
  `EVENT_KIND` int(11) NOT NULL,
  `NEXT` int(11) NOT NULL,
  `COMMENT_KIND` int(11) NOT NULL,
  `PRODUCTION_KIND` int(11) NOT NULL,
  PRIMARY KEY (`CELL_ID`),
  KEY `MAPAREA_ID` (`MAPAREA_ID`),
  KEY `MAPINFO_NO` (`MAPINFO_ID`),
  CONSTRAINT `t_map_cell_traveller_ibfk_1` FOREIGN KEY (`CELL_ID`) REFERENCES `t_map_cell` (`ID`),
  CONSTRAINT `t_map_cell_traveller_ibfk_2` FOREIGN KEY (`MAPAREA_ID`) REFERENCES `t_map_area` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;