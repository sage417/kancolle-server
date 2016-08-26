UPDATE t_slotitem SET CLASS_ID = func_get_split_string(TYPE,1),
PHOTOGRAPH_ID = func_get_split_string(TYPE,2),
CATEGORY_ID = func_get_split_string(TYPE,3),
ICON_ID = func_get_split_string(TYPE,4)