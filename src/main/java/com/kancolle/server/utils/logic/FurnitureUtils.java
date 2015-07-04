/**
 * 
 */
package com.kancolle.server.utils.logic;

import static com.kancolle.server.model.po.furniture.FurnitureType.DESK;
import static com.kancolle.server.model.po.furniture.FurnitureType.FLOOR;
import static com.kancolle.server.model.po.furniture.FurnitureType.SHELF;
import static com.kancolle.server.model.po.furniture.FurnitureType.WALLHANGING;
import static com.kancolle.server.model.po.furniture.FurnitureType.WALLPAPER;
import static com.kancolle.server.model.po.furniture.FurnitureType.WINDOW;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kancolle.server.controller.kcsapi.form.forniture.FurnitureChangeForm;
import com.kancolle.server.model.po.furniture.FurnitureType;
import com.kancolle.server.utils.BeanUtils;

/**
 * @author J.K.SAGE
 * @Date 2015年6月7日
 *
 */
public class FurnitureUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(FurnitureUtils.class);

    private static final Map<FurnitureType, Function<FurnitureChangeForm, Integer>> typeToFurnitureId;

    static {
        typeToFurnitureId = Maps.newHashMapWithExpectedSize(6);
        typeToFurnitureId.put(FLOOR, FurnitureChangeForm::getApi_floor);
        typeToFurnitureId.put(WALLPAPER, FurnitureChangeForm::getApi_wallpaper);
        typeToFurnitureId.put(WINDOW, FurnitureChangeForm::getApi_window);
        typeToFurnitureId.put(WALLHANGING, FurnitureChangeForm::getApi_wallhanging);
        typeToFurnitureId.put(SHELF, FurnitureChangeForm::getApi_shelf);
        typeToFurnitureId.put(DESK, FurnitureChangeForm::getApi_desk);
    }

    public static Integer getFurnitureIdByType(FurnitureChangeForm form, FurnitureType type) {
        return typeToFurnitureId.get(type).apply(form);
    }

    public static List<Integer> getChangeFurnitureIds(FurnitureChangeForm form) {
        int furnitureCount = (int) BeanUtils.getGetMethodStream(FurnitureChangeForm.class, int.class).count();
        List<Integer> furnitureIds = Lists.newArrayListWithCapacity(furnitureCount);
        BeanUtils.getGetMethodStream(FurnitureChangeForm.class, int.class).forEach(method -> {
            try {
                furnitureIds.add((int) method.invoke(form, ArrayUtils.EMPTY_OBJECT_ARRAY));
            } catch (Exception e) {
                LOGGER.error("Error Happen in FurnitureUtils", e);
            }
        });
        return furnitureIds;
    }
}
