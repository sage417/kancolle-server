package com.kancolle.server.service.ship;


public interface ShipService {

    long getSumExpByLevel(int level);

    long getNextLVExp(int nowLevel);

    int getShipLVByExp(long afterExp);

}
