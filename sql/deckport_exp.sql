UPDATE t_enemy_deckport d,
 t_map_cell c
SET d.BASE_EXP = c.BASE_EXP
WHERE
	d.mapcell_id = c.id