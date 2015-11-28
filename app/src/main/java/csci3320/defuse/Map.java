package csci3320.defuse;

//Map Class
public class Map {
    //Default constructor
    Map() {
        mapName = "";
        mapImage = 0;
        greekChar = 0;
    }

    //Constructor with name and image
    Map(String name, int image) {
        mapName = name;
        mapImage = image;
    }

    //Constructor with name, image and character number
    Map(String name, int image, int charNum) {
        mapName = name;
        mapImage = image;
        greekChar = charNum;
    }

    //Set Map Name
    public void setMapName(String num) {
        mapName = num;
    }

    //Get Map Name
    public String getMapName() {
        return mapName;
    }

    //Set Map Image
    public void setMapImage(int num) {
        mapImage = num;
    }

    //Get Map Image
    public int getMapImage() {
        return mapImage;
    }

    //Set Greek Character
    public void setGreekChar(int num) {
        greekChar = num;
    }

    //Get Greek Character
    public int getGreekChar() {
        return greekChar;
    }

    //Private members: mapName, mapImage, greekChar
    private String mapName;
    private int mapImage;
    private int greekChar;
}
