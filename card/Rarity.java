package skylordtools.card;


public enum Rarity {
    Common,
    Uncommon,
    Rare,
    Ultrarare,
    Unknown;

    /**
     * Get a Rarity from an integer
     * @param i the integer
     * @return the Rarity corresponding to a specific integer
     */
    public static Rarity fromInteger(int i){
        switch(i){
            case 0:
                return Common;
            case 1:
                return Uncommon;
            case 2:
                return Rare;
            case 3:
                return Ultrarare;
            default:
                return Unknown;
        }
    }
}
