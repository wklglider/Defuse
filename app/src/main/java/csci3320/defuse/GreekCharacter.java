package csci3320.defuse;

//GreekCharacter Class
public class GreekCharacter {
    //Default Constructor
    GreekCharacter() {
        charName = "";
    }

    //Constructor with name
    GreekCharacter(String name ) {
        this.charName = name;
    }

    //Set character Name
    public void setCharName(String charName) {
        this.charName = charName;
    }

    //Get character Name
    public String getCharName() {
        return this.charName;
    }

    //Private character name
    private String charName;
}
