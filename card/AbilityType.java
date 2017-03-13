package skylordtools.card;

public enum AbilityType {
    Attack,
    Passive,
    Activated,
    Interval,
    Cast,
    Unknown;

    /**
     * Get an AbilityType from an integer
     * @param i the integer
     * @return the AbilityType corresponding to a specific integer
     */
    public static AbilityType fromInteger(int i){
        switch(i){
            case 0:
                return Attack;
            case 1:
                return Passive;
            case 2:
                return Activated;
            case 3:
                return Interval;
            case 4:
                return Cast;
            default:
                return Unknown;
        }
    }
}
