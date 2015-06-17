/**
 * 
 */
package com.kancolle.server.utils.logic;

import com.kancolle.server.model.po.resource.Resource;

/**
 * @author J.K.SAGE
 * @Date 2015年6月17日
 *
 */
public class ResourceUtils {

    public static boolean hasEnoughFuel(Resource memberResource, int targetFule) {
        return memberResource.getFuel() > targetFule;
    }

    public static boolean hasEnoughBull(Resource memberResource, int targetBull) {
        return memberResource.getBull() > targetBull;
    }

    public static boolean hasEnoughSteal(Resource memberResource, int targetSteal) {
        return memberResource.getSteal() > targetSteal;
    }

    public static boolean hasEnoughBauxite(Resource memberResource, int targetBauxite) {
        return memberResource.getBauxite() > targetBauxite;
    }

    public static boolean hasEnoughFastRecovery(Resource memberResource, int targetFastRecovery) {
        return memberResource.getFastRecovery() > targetFastRecovery;
    }

    public static boolean hasEnoughFastBuild(Resource memberResource, int targetFastBuild) {
        return memberResource.getFastBuild() > targetFastBuild;
    }

    public static boolean hasEnoughDevItem(Resource memberResource, int targetDevItem) {
        return memberResource.getDevItem() > targetDevItem;
    }

    public static boolean hasEnoughEhItem(Resource memberResource, int targetEhItem) {
        return memberResource.getEhItem() > targetEhItem;
    }

}
