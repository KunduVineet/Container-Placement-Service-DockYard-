package model;

public class PlacementResponse {
    private String containerId;
    private Integer targetX;
    private Integer targetY;
    private String error;

    public PlacementResponse(String containerId, Integer targetX, Integer targetY) {
        this.containerId = containerId;
        this.targetX = targetX;
        this.targetY = targetY;
    }

    public PlacementResponse(String error) {
        this.error = error;
    }

    public String getContainerId() {
        return containerId;
    }

    public void setContainerId(String containerId) {
        this.containerId = containerId;
    }

    public Integer getTargetX() {
        return targetX;
    }

    public void setTargetX(Integer targetX) {
        this.targetX = targetX;
    }

    public Integer getTargetY() {
        return targetY;
    }

    public void setTargetY(Integer targetY) {
        this.targetY = targetY;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
