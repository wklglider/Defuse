package csci3320.defuse;

import android.content.Context;
import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

//DefuseGame Class
public class DefuseGame {
    //Private member: score, maps, greekChars
    private int score;
    private ArrayList<Map> maps;
    private ArrayList<GreekCharacter> greekChars;

    //Constructor
    DefuseGame() {
        score = 0;
        Maps();
        GreekChars();
    }

//    //Randomly Generate Map
//    public static int randomMap() {
//        //Create seed by current time
//        long seed = System.currentTimeMillis();
//        //Get random number by the seed
//        Random gen = new Random(seed);
//        return gen.nextInt(43);
//    }
//
//    //Randomly Generate Greek Character
//    public static int randomGreekChar() {
//        //Create seed by current time
//        long seed = System.currentTimeMillis();
//        //Get random number by the seed
//        Random gen = new Random(seed);
//        return gen.nextInt(24);
//    }

    //Get Map resources
    public void Maps() {
        Map map[] = new Map[15];
        maps = new ArrayList<Map>();
//        for(int i = 0; i < map.length; i++) {
//            String fileName = "map" + i;
////            Context context = imageView.getContext();
//            int fileID = context.getResources().getIdentifier(fileName, "drawable", context.getPackageName());
//            map[i] = new Map("Map" + i, fileID);// Needs to change
////            map[i] = new Map(fileName, R.drawable.map0);
//            maps.add(map[i]);
//        }
        map[0] = new Map("Map0",R.drawable.map0);
        map[1] = new Map("Map1",R.drawable.map1);
        map[2] = new Map("Map2",R.drawable.map2);
        map[3] = new Map("Map3",R.drawable.map3);
        map[4] = new Map("Map4",R.drawable.map4);
        map[5] = new Map("Map5",R.drawable.map5);
        map[6] = new Map("Map6",R.drawable.map6);
        map[7] = new Map("Map7",R.drawable.map7);
        map[8] = new Map("Map8",R.drawable.map8);
        map[9] = new Map("Map9",R.drawable.map9);
        map[10] = new Map("Map10",R.drawable.map10);
        map[11] = new Map("Map11",R.drawable.map11);
        map[12] = new Map("Map12",R.drawable.map12);
        map[13] = new Map("Map13",R.drawable.map13);
        map[14] = new Map("Map14",R.drawable.map14);
//        map[15] = new Map("Map15",R.drawable.map15);
//        map[16] = new Map("Map16",R.drawable.map16);
//        map[17] = new Map("Map17",R.drawable.map17);
//        map[18] = new Map("Map18",R.drawable.map18);
//        map[19] = new Map("Map19",R.drawable.map19);
//        map[20] = new Map("Map20",R.drawable.map20);
//        map[21] = new Map("Map21",R.drawable.map21);
//        map[22] = new Map("Map22",R.drawable.map22);
//        map[23] = new Map("Map23",R.drawable.map23);
//        map[24] = new Map("Map24",R.drawable.map24);
//        map[25] = new Map("Map25",R.drawable.map25);
//        map[26] = new Map("Map26",R.drawable.map26);
//        map[27] = new Map("Map27",R.drawable.map27);
//        map[28] = new Map("Map28",R.drawable.map28);
//        map[29] = new Map("Map29",R.drawable.map29);
//        map[30] = new Map("Map30",R.drawable.map30);
//        map[31] = new Map("Map31",R.drawable.map31);
//        map[32] = new Map("Map32",R.drawable.map32);
//        map[33] = new Map("Map33",R.drawable.map33);
//        map[34] = new Map("Map34",R.drawable.map34);
//        map[35] = new Map("Map35",R.drawable.map35);
//        map[36] = new Map("Map36",R.drawable.map36);
//        map[37] = new Map("Map37",R.drawable.map37);
//        map[38] = new Map("Map38",R.drawable.map38);
//        map[39] = new Map("Map39",R.drawable.map39);
//        map[40] = new Map("Map40",R.drawable.map40);
//        map[41] = new Map("Map41",R.drawable.map41);
//        map[42] = new Map("Map42",R.drawable.map42);

        for(int i = 0; i < map.length; i++) {
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

    //Get Map array
    public Map[] getMaps() {
        Map[] mp = new Map[9];
        Collections.shuffle(maps);
        ArrayList used = new ArrayList();
        long seed = System.currentTimeMillis();
        Random gen = new Random(seed);
        int randomNumber;
        //Get generate numbers for greek char
        for(int i= 0; i < 9; i++) {
            do {
                randomNumber = gen.nextInt(15);
                mp[i] = maps.get(randomNumber);
            } while (used.contains(randomNumber) || mp[i] == null);
//            mp[i].setGreekChar(randomGreekChar());
            used.add(randomNumber);
        }
        return mp;
    }

    //Get Greek Character array
    public GreekCharacter[] getGreekChar() {
        GreekCharacter[] gc = new GreekCharacter[9];
        Collections.shuffle(greekChars);

        for(int i= 0; i < 9; i++){
            gc[i] = greekChars.get(i);
        }
        return gc;
    }

    //Set score
    public void setScore(int val) {
        score = val;
    }

    //Get score
    public int getScore() {
        return score;
    }

    //Private member context for get the map resource id by map name
    private Context context;
}
