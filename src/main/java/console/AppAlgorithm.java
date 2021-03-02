package console;

import core.OrderCapsuleImpl;

public class AppAlgorithm {
    public static void main(String[] args) {
        OrderCapsuleImpl orderCapsule = new OrderCapsuleImpl();
        orderCapsule.readJson("infile.json");
        orderCapsule.execute();
        orderCapsule.writeJson("outfile.json");

    }
}
