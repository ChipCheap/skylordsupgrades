package skylordtools.card;

public enum Color {
    Fire,
    Nature,
    Frost,
    Shadow,
    Twilight,
    Bandit,
    Stonekin,
    LostSouls,
    Amii,
    Neutral,
    Unknown;

    /**
     * Get a Color from an integer
     * @param i the integer
     * @return the Color corresponding to a specific integer
     */
    public static Color fromInteger(int i){
        switch(i){
            case 0:
                return Frost;
            case 1:
                return Fire;
            case 2:
                return Nature;
            case 3:
                return Shadow;
            case 4:
                return Twilight;
            case 5:
                return LostSouls;
            case 6:
                return Neutral;
            case 7:
                return Stonekin;
            case 8:
                return Bandit;
            case 9:
                return Amii;
            default:
                return Unknown;
        }
    }
}
