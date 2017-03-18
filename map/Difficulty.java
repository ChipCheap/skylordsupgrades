package skylordtools.map;

public enum Difficulty {
    Standard,
    Advanced,
    Expert,
    Unknown;

    /**
     * Get a Difficulty from an integer
     * @param i the integer
     * @return the Difficulty corresponding to a specific integer
     */
    public static Difficulty fromInteger(int i){
        switch(i){
            case 0:
                return Standard;
            case 1:
                return Advanced;
            case 2:
                return Expert;
            default:
                return Unknown;
        }
    }

    /**
     * Get a Difficulty from a String
     * @param s the String
     * @return the Difficulty corresponding to a specific String
     */
    public static Difficulty fromString(String s){
        switch(s){
            case "Standard":
                return Standard;
            case "Advanced":
                return Advanced;
            case "Expert":
                return Expert;
            default:
                return Unknown;
        }
    }
}
