package core;

import java.util.List;

public interface OrderCapsule {
    void readJson(String fileName);
    void execute();
    void writeJson(String fileName);
    void findCapsuleFirst();
    void findCapsuleSecond();
    void addToSecond(int restSpeed);
    void addToFirst(int speed);
}
