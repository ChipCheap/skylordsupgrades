package skylordtools.card;

import org.json.*;
import skylordtools.Cardbase;
import skylordtools.map.Difficulty;

import java.util.*;

public class Card {
    public static List<Card> allCards = Cardbase.getCards();

    private String name;
    private Rarity rarity;
    private int cost;
    private CardType type;
    private Color color;
    private Affinity affinity;
    private OffenseType offenseType;
    private DefenseType defenseType;
    private int offense;
    private int defense;
    private boolean isRanged;
    private String extra;
    private List<Ability> abilities;
    private List<Upgrade> upgrades;
    private OrbInfo orbInfo;


    /**
     * Constructor for the Card
     * @param obj a card, extracted from the CardBase-API (http://cardbase.bfreborn.com/cards/GetCards)
     */
    public Card(JSONObject obj){
        name = obj.getString("Name");
        rarity = Rarity.fromInteger(obj.getInt("Rarity"));
        cost = obj.getInt("Cost");
        type = CardType.fromInteger(obj.getInt("Type"));
        color = Color.fromInteger(obj.getInt("Color"));
        affinity = Affinity.fromInteger(obj.getInt("Affinity"));
        offenseType = OffenseType.fromInteger(obj.getInt("OffenseType"));
        defenseType = DefenseType.fromInteger(obj.getInt("DefenseType"));
        offense = obj.getInt("Offense");
        defense = obj.getInt("Defense");
        isRanged = obj.getBoolean("IsRanged");
        extra = obj.isNull("Extra") ? "" : obj.getString("Extra");

        abilities = new ArrayList<Ability>();

        JSONArray jsonabilities = obj.getJSONArray("Abilities");

        for (int i = 0; i < jsonabilities.length(); i++) {

            String name = jsonabilities.getJSONObject(i).isNull("Name")
                            ? ""
                            : jsonabilities.getJSONObject(i).getString("Name");


            AbilityType type = jsonabilities.getJSONObject(i).isNull("Type")
                            ? AbilityType.Unknown
                            : AbilityType.fromInteger(jsonabilities.getJSONObject(i).getInt("Type"));


            int power = jsonabilities.getJSONObject(i).isNull("Power")
                            ? 0
                            : jsonabilities.getJSONObject(i).getInt("Power");


            String description = jsonabilities.getJSONObject(i).isNull("Description")
                            ? ""
                            : jsonabilities.getJSONObject(i).getString("Description");


            abilities.add(new Ability(name, type, power, description));
        }


        upgrades = new ArrayList<Upgrade>();
        JSONArray jsonupgrades = obj.getJSONArray("Upgrades");

        for (int i = 0; i < jsonupgrades.length(); i++) {

                String desc = jsonupgrades.getJSONObject(i).isNull("Description")
                        ? ""
                        : jsonupgrades.getJSONObject(i).getString("Description");

                int era = jsonupgrades.getJSONObject(i).isNull("Era")
                        ? 0
                        : jsonupgrades.getJSONObject(i).getInt("Era");

                //Check if map is null
                boolean mapIsNull = jsonupgrades.getJSONObject(i).isNull("Map");

                String mapName = mapIsNull
                        ? ""
                        : jsonupgrades.getJSONObject(i).getJSONObject("Map").getString("Name");

                Difficulty mapDif = mapIsNull
                        ? Difficulty.Unknown
                        : Difficulty.fromInteger(jsonupgrades.getJSONObject(i).getJSONObject("Map").getInt("Difficulty"));

            upgrades.add(new Upgrade(desc, era, mapName, mapDif));
        }

        if(!obj.isNull("OrbInfo")) {
            JSONObject jsonOrbInfo = obj.getJSONObject("OrbInfo");
            this.orbInfo = new OrbInfo(
                    jsonOrbInfo.getString("OrbCode"),
                    jsonOrbInfo.getInt("Neutral"),
                    jsonOrbInfo.getInt("Fire"),
                    jsonOrbInfo.getInt("Nature"),
                    jsonOrbInfo.getInt("Frost"),
                    jsonOrbInfo.getInt("Shadow"));
        }
        else{
            this.orbInfo = new OrbInfo("", 0, 0, 0, 0, 0);
        }
    }


    public static Card fromName(String name, Affinity affinity) throws NoSuchElementException{
        for (Card c: allCards){
            if (c.getName().equals(name) && c.getAffinity().equals(affinity))
                return c;
        }

        throw new NoSuchElementException();
    }

    public static Card fromStringName(String stringName) throws NoSuchElementException{
        for (Card c: allCards){
            if (c.toString().equals(stringName))
                return c;
        }

        throw new NoSuchElementException();
    }

    /**
     * Getter for name
     * @return the name of the Card
     */
    public String getName(){
        return this.name;
    }

    /**
     * Getter for Rarity
     * @return the Rarity of the Card (see enum Rarity)
     */
    public Rarity getRarity(){
        return this.rarity;
    }

    /**
     * Getter for cost
     * @return the cost of the Card
     */
    public int getCost(){
        return this.cost;
    }

    /**
     * Getter for type
     * @return the CardType of the Card (see enum CardType)
     */
    public CardType getType(){
        return this.type;
    }

    /**
     * Getter for color
     * @return the Color of the Card (see enum Color)
     */
    public Color getColor(){
        return this.color;
    }

    /**
     * Getter for affinity
     * @return the Affinity of the Card (see enum Affinity)
     */
    public Affinity getAffinity(){
        return this.affinity;
    }

    /**
     * Getter for offenseType
     * @return the OffenseType of the Card (see enum OffenseType)
     */
    public OffenseType getOffenseType(){
        return this.offenseType;
    }

    /**
     * Getter for defenseType
     * @return the DefenseType of the Card (see enum DefenseType)
     */
    public DefenseType getDefenseType(){
        return this.defenseType;
    }

    /**
     * Getter for offense
     * @return the offense value of the Card
     */
    public int getOffense(){
        return this.offense;
    }

    /**
     * Getter for defense
     * @return the defense value of the card
     */
    public int getDefense(){
        return this.defense;
    }

    /**
     * Getter for isRanged
     * @return whether the Card is ranged, or not (boolean)
     */
    public boolean getIsRanged(){
        return this.isRanged;
    }

    /**
     * Getter for extra
     * @return extra comments giving more details about the Card
     */
    public String getExtra(){
        return this.extra;
    }

    /**
     * Getter for abilities
     * @return a list of the abilities (see class Ability) of the Card
     */
    public List<Ability> getAbilities(){
        return this.abilities;
    }

    /**
     * Getter for upgrades
     * @return a list of the upgrades (see class Upgrade) of the Card
     */
    public List<Upgrade> getUpgrades(){
        return this.upgrades;
    }

    /**
     * Getter for orbInfo
     * @return the orbInfo for the card (OrbCode, and required amount of each orb)
     */
    public OrbInfo getOrbInfo(){
        return this.orbInfo;
    }


    /**
     * This will create an map which contains all possible orb combinations, this is useful for
     * automatically deducing orbs when building a deck, filtering cards by certain criteria, etc.
     * @param combos orb combinations for each tier (T1: R, N, B, S; T2: RR, RN, ...)
     * @return a HashMap which contains an empty ArrayList for each OrbCode
     */
    private static Map<String, List<Card>> initCardLists(List<String> combos){
        Map<String, List<Card>> cardLists = new HashMap<String, List<Card>>();

        for(String c: combos){
            cardLists.put(c, new ArrayList<Card>());
        }

        return cardLists;
    }

    /**
     * Generates the filename of a card based on their name and affinity
     * @return the filename of a card
     */
    public String getFilename(){
        String filename = this.getName().replaceAll("[)('.]", "").replace(" ", "-");
        Affinity affinity = this.getAffinity();

        return filename + (affinity==Affinity.Neutral ? "" : ("_"+affinity.toString().toLowerCase())) + ".jpg";
    }

    /**
     * Generate Tooltip in HTML.
     *
     * @return Tooltip in this format:
     * {NAME} {(AFFINITY)} | {COUNTERED BY X} {COUNTERS BY Y}
     *
     * {ABILITYNAME}
     * {ABILITYDESCRIPTION}
     *
     * {UPGRADES}
     */
    public String getToolTip(){
        String start="<html><head/><body>";
        String mid="";
        String end="</body></html>";

        //Name
        mid+="<p><span style=\" font-weight:600; \">"+this.toString();

        //Counters ...
        //Size
        if (this.type== CardType.Creature){
            mid+="\t|\tCounters: "+this.getOffenseType();

            mid+="\t|\tSize: "+this.getDefenseType();
        }

        mid+="</p>";

        //Abilities
        for (Ability a: this.abilities){
            mid+="<p><span style=\" font-weight:600; text-decoration: underline;\">"
                    +a.getName()+"</span> <span style=\" font-weight:600\">"
                    +(a.getPower()!=0 ? "("+a.getPower()+" power)" : "")+"</span></p><p>"+a.getDescription()+"</p>";
        }

        //Upgrades
        if (upgrades!=null){
            mid+="<br><p>";
            for (Upgrade u: this.upgrades){
                mid+="<span style=\" font-weight:600; \">U"+(u.getEra())+" ("+u.getMapName()+
                        "): </span>"+u.getDescription()+"<br>";
            }

            mid+="</p>";
        }

        return start+mid+end;
    }

    /**
     * Get the name of a card in this format:
     * (no affinity):   {NAME}
     * (affinity):      {NAME} ({AFFNINTY})
     * @return the name of a card
     */
    public String toString(){
        return this.name + (this.affinity==Affinity.Neutral ? "" : " ("+this.affinity.toString()+")");
    }

    /**
     * Check if a card can be played with the orbs(list)
     * @param list the orbs of the list. ( e.g. "R", "NR, "S", "SSNR" )
     * @return card can (not) be played with these orbs
     */
    public boolean cardInList(String list){

        return false;
    }
}



