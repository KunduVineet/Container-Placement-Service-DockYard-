package model;

public class Slot {
    private String sizeCap;
    private int x;
    private int y;
    private boolean hasColdUnit;
    private boolean isOccupied;

    public String getSizeCap() {
        return sizeCap;
    }

    public void setSizeCap(String sizeCap) {
        this.sizeCap = sizeCap;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isHasColdUnit() {
        return hasColdUnit;
    }

    public void setHasColdUnit(boolean hasColdUnit) {
        this.hasColdUnit = hasColdUnit;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
