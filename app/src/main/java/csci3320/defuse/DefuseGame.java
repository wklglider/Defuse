package csci3320.defuse;

import java.util.List;

public class DefuseGame {
    DefuseGame() {
        point = 0;
        Maps();
        GreekChars();
    }

    public void setPoint(int val) {
        this.point = val;
    }

    public int getPoint() {
        return this.point;
    }

    public void Maps() {
        for(int i = 0; i < 43; i++) {

        }
    }

    public void GreekChars() {

    }

    private int point;
    private List<Map> maps;
    private List<GreekChar> greekChars;
}
