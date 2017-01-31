/**
 *
 */
package com.kancolle.server.model.po.deckport;

import com.kancolle.server.model.po.ship.SlimShip;
import org.apache.ibatis.type.Alias;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Property;

import java.util.List;

/**
 * @author J.K.SAGE
 * @Date 2015年6月30日
 */
@Alias("SlimDeckPort")
public class SlimDeckPort {
    @Property("deck_id")
    private int deckId;
    @Embedded("ships")
    private List<SlimShip> ships;

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public List<SlimShip> getShips() {
        return ships;
    }

    public void setShips(List<SlimShip> ships) {
        this.ships = ships;
    }
}
