package csci3320.defuse;

public class Map {
    Map() {
        mapName = "";
        mapImage = 0;
        greekChar = 0;
    }

    Map(String name, int image) {
        this.mapName = name;
        this.mapImage = image;
    }

    Map(String name, int image, int charNum) {
        this.mapName = name;
        this.mapImage = image;
        this.greekChar = charNum;
    }

    public void setMapName(String num) {
        this.mapName = num;
    }

    public String getMapName() {
        return this.mapName;
    }

    public void setMapImage(int num) {
        this.mapImage = num;
    }

    public int getMapImage() {
        return this.mapImage;
    }

    public void setGreekChar(int num) {
        this.greekChar = num;
    }

    public int getGreekChar() {
        return this.greekChar;
    }

    private String mapName;
    private int mapImage;
    private int greekChar;
}
