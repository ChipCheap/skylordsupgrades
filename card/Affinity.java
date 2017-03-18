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
}
