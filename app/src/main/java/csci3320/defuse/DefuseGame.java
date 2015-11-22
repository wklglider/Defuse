package csci3320.defuse;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;

public class DefuseGame {
    DefuseGame() {
        score = 0;
        Maps();
        GreekChars();
    }

    public void setScore(int val) {
        this.score = val;
    }

    public int getScore() {
        return this.score;
    }

    public void Maps() {
        Map map[] = new Map[43];
        maps = new ArrayList<Map>();
        for(int i = 0; i < map.length; i++) {
            String fileName = "map" + i;
//            Context context = imageView.getContext();
//            Context context = imageView.getContext();
//            int fileID = context.getResources().getIdentifier(fileName, "drawable", context.getPackageName());
//            map[i] = new Map("Map" + i, fileID);// Needs to change
            map[i] = new Map(fileName, R.drawable.map0);
            maps.add(map[i]);
        }
    }

    public void GreekChars() {
        GreekCharacter greekChar[] = new GreekCharacter[24];
        greekChars = new ArrayList<GreekCharacter>();
        ArrayList<String> charList = new ArrayList<>(Arrays.asList(
                "Α","Β","Γ","Δ","Ε","Ζ","Η","Θ","Ι","Κ","Λ","Μ","Ν","Ξ","Ο","Π","Ρ","Σ","Τ","Υ","Φ","Χ","Ψ","Ω"));
        for(int i = 0; i < greekChar.length; i++) {
            greekChar[i] = new GreekCharacter(charList.get(i));
            greekChars.add(greekChar[i]);
        }
    }

    private int score;
    private ArrayList<Map> maps;
    private ArrayList<GreekCharacter> greekChars;
}
