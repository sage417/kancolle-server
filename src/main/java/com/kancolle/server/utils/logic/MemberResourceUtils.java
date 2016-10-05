package com.kancolle.server.utils.logic;

import com.kancolle.server.model.po.resource.Resource;

/**
 * Created by SAGE on 2016/10/5.
 */
public class MemberResourceUtils {

    public static boolean hasEnoughResource(Resource resource, int chargeFuel, int chargeBull, int consumeSteel, int consumeBauxite,
                                            int fastRecovery, int fastBuild, int devItem, int ehItem) {
        return resource.getFuel() >= chargeFuel &&
                resource.getBull() >= chargeBull &&
                resource.getSteel() >= consumeSteel &&
                resource.getBauxite() >= consumeBauxite &&
                resource.getFastRecovery() >= fastRecovery &&
                resource.getFastBuild() >= fastBuild &&
                resource.getDevItem() >= devItem &&
                resource.getEhItem() >= ehItem;
    }
}
