package core;

import java.util.ArrayList;
import java.util.List;

public class InputArrays {
    private List<Integer> corrections = new ArrayList<>();
    private List<Integer> cells = new ArrayList<>();

    public InputArrays(List<Integer> corrections, List<Integer> cells) {
        this.corrections = corrections;
        this.cells = cells;
    }

    public InputArrays() {
    }

    public List<Integer> getCorrections() {
        return corrections;
    }

    public void setCorrections(List<Integer> corrections) {
        this.corrections = corrections;
    }

    public List<Integer> getCells() {
        return cells;
    }

    public void setCells(List<Integer> cells) {
        this.cells = cells;
    }

    @Override
    public String toString() {
        return "InputArrays{" +
                "corrections=" + corrections +
                ", cells=" + cells +
                '}';
    }
}
