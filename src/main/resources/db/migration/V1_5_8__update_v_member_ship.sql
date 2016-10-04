CREATE OR REPLACE ALGORITHM = UNDEFINED DEFINER = `kancolle`@`localhost` SQL SECURITY DEFINER VIEW `kancolle`.`v_member_ship` AS SELECT
t_member_ship.`index` AS IDX,
t_member_ship.member_id AS member_id,
t_member_ship.ID AS ID,
t_member_ship.SHIP_ID AS SHIP_ID,
t_member_ship.LV AS LV,
t_member_ship.EXP AS EXP,
t_member_ship.NOWHP AS NOWHP,
t_member_ship.MAXHP AS MAXHP,
t_member_ship.LENG AS LENG,
t_member_ship.SLOT AS SLOT,
t_member_ship.ONSLOT AS ONSLOT,
t_member_ship.KYOUKA AS KYOUKA,
t_member_ship.FUEL AS FUEL,
t_member_ship.BULL AS BULL,
t_member_ship.SRATE AS SRATE,
t_member_ship.COND AS COND,
t_member_ship.KARYOKU AS KARYOKU,
t_member_ship.RAISOU AS RAISOU,
t_member_ship.TAIKU AS TAIKU,
t_member_ship.SOUKOU AS SOUKOU,
t_member_ship.KAIHI AS KAIHI,
t_member_ship.TAISEN AS TAISEN,
t_member_ship.SAKUTEKI AS SAKUTEKI,
t_member_ship.LUCKY AS LUCKY,
t_member_ship.LOCKED AS LOCKED,
t_member_ship.LOCKED_EQUIP AS LOCKED_EQUIP
FROM
t_member_ship
WHERE
(`t_member_ship`.`DELETED` = 0)
ORDER BY
IDX ASC;