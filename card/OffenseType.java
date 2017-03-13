package skylordtools.card;

public enum OffenseType{
    Small,
    Medium,
    Large,
    XL,
    Special,
    Unknown;

    /**
     * Get an OffenseType from an integer
     * @param i the integer
     * @return the OffenseType corresponding to a specific integer
     */
    public static OffenseType fromInteger(int i){
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
                return Special;
            default:
                return Unknown;
        }
    }
}
