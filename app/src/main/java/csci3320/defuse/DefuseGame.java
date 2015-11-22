package csci3320.defuse;

import java.util.ArrayList;

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
        Map map[] = new Map[43];
        maps = new ArrayList<Map>();
        for(int i = 0; i < 43; i++) {
            map[i] = new Map("Map" + i, R.drawable.map0);// Needs to change
            maps.add(map[i]);
        }
    }

    public void GreekChars() {

    }

    private int point;
    private ArrayList<Map> maps;
    private ArrayList<GreekChar> greekChars;
}
