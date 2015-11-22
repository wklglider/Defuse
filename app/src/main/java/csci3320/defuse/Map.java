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
        this.mapName = name;
        this.mapImage = image;
    }

    //Constructor with name, image and character number
    Map(String name, int image, int charNum) {
        this.mapName = name;
        this.mapImage = image;
        this.greekChar = charNum;
    }

    //Set Map Name
    public void setMapName(String num) {
        this.mapName = num;
    }

    //Get Map Name
    public String getMapName() {
        return this.mapName;
    }

    //Set Map Image
    public void setMapImage(int num) {
        this.mapImage = num;
    }

    //Get Map Image
    public int getMapImage() {
        return this.mapImage;
    }

    //Set Greek Character
    public void setGreekChar(int num) {
        this.greekChar = num;
    }

    //Get Greek Character
    public int getGreekChar() {
        return this.greekChar;
    }

    //Private members: mapName, mapImage, greekChar
    private String mapName;
    private int mapImage;
    private int greekChar;
}
