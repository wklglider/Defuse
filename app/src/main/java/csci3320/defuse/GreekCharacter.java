package csci3320.defuse;

public class GreekCharacter {
    GreekCharacter() {
        charName = "";
    }

    GreekCharacter(String name ) {
        this.charName = name;
    }
    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getCharName() {
        return this.charName;
    }

    private String charName;
}
