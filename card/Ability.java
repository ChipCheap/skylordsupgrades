package skylordtools.card;

public class Ability {

    private String name;
    private AbilityType type;
    private int power;
    private String description;

    /**
     * Initialize all parameters
     * @param name the name of the Ability
     * @param type the AbilityType of the Ability
     * @param power the required power to activate/toggle the Ability
     * @param desc the description of the Ability
     */
    public Ability(String name, AbilityType type, int power, String desc){
        this.name = name;
        this.type = type;
        this.power = power;
        this.description = desc;
    }

    /**
     * Getter for name
     * @return name of the Ability
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getter for type
     * @return type of the Ability (see enum AbilityType)
     */
    public AbilityType getType(){
        return this.type;
    }

    /**
     * Getter for power
     * @return power required to activate/toggle the Ability
     */
    public int getPower(){
        return this.power;
    }

    /**
     * Getter for description
     * @return the description of the Ability
     */
    public String getDescription(){
        return this.description;
    }
}
