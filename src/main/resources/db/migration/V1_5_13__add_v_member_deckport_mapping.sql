CREATE SQL SECURITY DEFINER VIEW `kancolle`.`v_member_deckport_ship_mapping` AS SELECT
t_member_deckport_ship_mapping.member_id,
t_member_deckport_ship_mapping.deck_id,
t_member_deckport_ship_mapping.member_ship_id
FROM
t_member_deckport_ship_mapping
WHERE
member_ship_id > 0;