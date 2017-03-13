package skylordtools.card;

public enum DefenseType {
    Small,
    Medium,
    Large,
    XL,
    Building,
    Unknown;

    /**
     * Get a DefenseType from an integer
     * @param i the integer
     * @return the DefenseType corresponding to a specific integer
     */
    public static DefenseType fromInteger(int i){
        switch(i){
            case 0:
                return Small;
            case 1:
                return Medium;
            case 2:
                return Large;
            case 3:
                return XL;
            case 4:
                return Building;
            default:
                return Unknown;
        }
    }
}
