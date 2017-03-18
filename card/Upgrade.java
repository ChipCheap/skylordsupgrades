package skylordtools.card;

import skylordtools.map.Difficulty;

public class Upgrade {
    private String description;
    private int era;
    private String mapName;
    private Difficulty mapDifficulty;

    /**
     * Initialize all parameters
     * @param desc the description of the upgrade, what will change, once the Upgrade is applied to the card
     * @param era the rank of the update: possible ranks 1, 2, 3
     * @param mapName the name of the map, where you can acquire the update
     * @param mapDifficulty the difficulty of the map (see enum map.enums.Difficulty)
     */
    public Upgrade(String desc, int era, String mapName, Difficulty mapDifficulty){
        this.description = desc;
        this.era = era;
        this.mapName = mapName;
        this.mapDifficulty = mapDifficulty;
    }

    /**
     * Getter for description
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter for era
     * @return the era
     */
    public int getEra() {
        return this.era;
    }

    /**
     * Getter for mapName
     * @return the name of the map
     */
    public String getMapName() {
        return this.mapName;
    }

    /**
     * Getter for mapDifficulty
     * @return the difficulty level of the map
     */
    public Difficulty getMapDifficulty() {
        return this.mapDifficulty;
    }
}
