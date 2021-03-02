package core;

import java.util.ArrayList;
import java.util.List;

public class CapsuleNominalValue {
    private List<Integer> capsuleNominalValue;
    private static CapsuleNominalValue instance;

    public static CapsuleNominalValue getInstance() {

        if (instance == null) {
            instance = new CapsuleNominalValue();
        }
        return instance;

    }

    private CapsuleNominalValue() {
        capsuleNominalValue = new ArrayList<>();
        capsuleNominalValue.add(10);
        capsuleNominalValue.add(8);
        capsuleNominalValue.add(6);
        capsuleNominalValue.add(4);
        capsuleNominalValue.add(2);
    }

    public List<Integer> getCapsuleNominalValue() {
        return capsuleNominalValue;
    }

    public void setCapsuleNominalValue(List<Integer> capsuleNominalValue) {
        this.capsuleNominalValue = capsuleNominalValue;
    }

    public static void setInstance(CapsuleNominalValue instance) {
        CapsuleNominalValue.instance = instance;
    }

    @Override
    public String toString() {
        return "CapsuleNominalValue{" +
                "capsuleNominalValue=" + capsuleNominalValue +
                '}';
    }
}
