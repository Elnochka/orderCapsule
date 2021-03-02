package core;

import java.util.ArrayList;
import java.util.List;

public class DataForJson {
    private List<Integer> mainThruster = new ArrayList<>();
    private List<Integer> secThruster = new ArrayList<>();
    private int deltaVelocity = 0;

    public DataForJson() {
    }

    public DataForJson(List<Integer> mainThruster, List<Integer> secThruster, int deltaVelocity) {
        this.mainThruster = mainThruster;
        this.secThruster = secThruster;
        this.deltaVelocity = deltaVelocity;
    }

    public List<Integer> getMainThruster() {
        return mainThruster;
    }

    public void setMainThruster(List<Integer> mainThruster) {
        this.mainThruster = mainThruster;
    }

    public List<Integer> getSecThruster() {
        return secThruster;
    }

    public void setSecThruster(List<Integer> secThruster) {
        this.secThruster = secThruster;
    }

    public int getDeltaVelocity() {
        return deltaVelocity;
    }

    public void setDeltaVelocity(int deltaVelocity) {
        this.deltaVelocity = deltaVelocity;
    }

    @Override
    public String toString() {
        return "DataForJson{" +
                "mainThruster=" + mainThruster +
                ", secThruster=" + secThruster +
                ", deltaVelocity=" + deltaVelocity +
                '}';
    }
}
