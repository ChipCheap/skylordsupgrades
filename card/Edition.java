package skylordtools.card;

/**
 * Created by XY on 02.01.2017.
 */
public enum Edition {
    Twilight,
    Renegade,
    LostSouls,
    Amii,
    Unknown;

    /**
     * Get an Edition from an integer
     * @param i the integer
     * @return the Edition corresponding to a specific integer
     */
    public static Edition fromInteger(int i){
        switch(i){
            case 1:
                return Twilight;
            case 2:
                return Renegade;
            case 4:
                return LostSouls;
            case 8:
                return Amii;
            default:
                return Unknown;
        }
    }

}
