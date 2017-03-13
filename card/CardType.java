package skylordtools.card;

public enum CardType {
    Creature,
    Spell,
    Building,
    Unknown;

    /**
     * Get a CardType from an integer
     * @param i the integer
     * @return the CardType corresponding to a specific integer
     */
    public static CardType fromInteger(int i){
        switch(i){
            case 0:
                return Spell;
            case 2:
                return Creature;
            case 3:
                return Building;
            default:
                return Unknown;
        }
    }
}
