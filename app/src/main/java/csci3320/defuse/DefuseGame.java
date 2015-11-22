package csci3320.defuse;

import android.content.Context;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

//DefuseGame Class
public class DefuseGame {
    //Constructor
    DefuseGame() {
        score = 0;
        Maps();
        GreekChars();
    }

    //Randomly Generate Map
    public static int randomMap() {
        //Create seed by current time
        long seed = System.currentTimeMillis();
        //Get random number by the seed
        Random gen = new Random(seed);
        return gen.nextInt(43);
    }

    //Randomly Generate Greek Character
    public static int randomGreekChar() {
        //Create seed by current time
        long seed = System.currentTimeMillis();
        //Get random number by the seed
        Random gen = new Random(seed);
        return gen.nextInt(24);
    }

    //Get Map array
    public Map[] getMaps() {
        Map[] mp = new Map[9];
        Collections.shuffle(maps);

        for(int i= 0; i < 9; ++i){
            mp[i] = maps.get(i);
            mp[i].setGreekChar(randomGreekChar());
        }
        return mp;
    }

    //Get Greek Character array
    public GreekCharacter[] getGreekChar() {
        GreekCharacter[] gc = new GreekCharacter[9];
        Collections.shuffle(greekChars);

        for(int i= 0; i < 9; ++i){
            gc[i] = greekChars.get(i);
        }
        return gc;
    }

    public void setScore(int val) {
        this.score = val;
    }

    //Get score
    public int getScore() {
        return this.score;
    }

    //Get Map resources
    public void Maps() {
        Map map[] = new Map[43];
        maps = new ArrayList<Map>();
        for(int i = 0; i < map.length; i++) {
            String fileName = "map" + i;
//            Context context = imageView.getContext();
            int fileID = context.getResources().getIdentifier(fileName, "drawable", context.getPackageName());
            map[i] = new Map("Map" + i, fileID);// Needs to change
//            map[i] = new Map(fileName, R.drawable.map0);
            maps.add(map[i]);
        }
    }

    //Get Greek Character resources
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

    //Private member: score, maps, greekChars
    private int score;
    private ArrayList<Map> maps;
    private ArrayList<GreekCharacter> greekChars;

    //Private member context for get the map resource id by map name
    private Context context;
}
