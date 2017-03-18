package skylordtools.card;

import com.sun.org.apache.bcel.internal.classfile.Unknown;
import javafx.scene.effect.Shadow;

public enum Affinity {
    Neutral,
    Frost,
    Fire,
    Nature,
    Shadow,
    Unknown;

    /**
     * Get an Affinity from an integer
     * @param i the integer
     * @return the Affinity corresponding to a specific integer
     */
    public static Affinity fromInteger(int i){
        switch(i){
            case -1:
                return Neutral;
            case 0:
                return Frost;
            case 1:
                return Fire;
            case 2:
                return Nature;
            case 3:
                return Shadow;
            default:
                return Unknown;
        }
    }

    /**
     * Get an Affinity from a String
     * @param s the String
     * @return the Affinity corresponding to a specific String
     */
    public static Affinity fromString(String s){
        switch(s){
            case "":
                return Neutral;
            case "Frost":
                return Frost;
            case "Fire":
                return Fire;
            case "Nature":
                return Nature;
            case "Shadow":
                return Shadow;
            default:
                return Unknown;
        }
    }
}
