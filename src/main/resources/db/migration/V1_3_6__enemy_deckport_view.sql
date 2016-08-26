CREATE 
VIEW `v_enemy_deckport`AS
SELECT
	t_enemy_deckport.`INDEX`,
	t_enemy_deckport.MAPCELL_ID,
	t_enemy_deckport.`NO`,
	t_enemy_deckport.FORMATION,
	t_enemy_deckport.SHIP,
	t_enemy_deckport.RANK,
	t_enemy_deckport.LV,
	t_enemy_deckport.BASE_EXP,
	t_map_cell.DECKPORT_NAME
FROM
	t_enemy_deckport
INNER JOIN t_map_cell ON t_enemy_deckport.MAPCELL_ID = t_map_cell.ID ;