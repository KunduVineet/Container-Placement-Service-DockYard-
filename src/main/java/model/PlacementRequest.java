package model;

import java.util.List;

public class PlacementRequest {

    private Container container;
    private List<Slot> yardMap;

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public List<Slot> getYardMap() {
        return yardMap;
    }

    public void setYardMap(List<Slot> yardMap) {
        this.yardMap = yardMap;
    }
}
