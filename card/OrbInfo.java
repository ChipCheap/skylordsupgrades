package skylordtools.card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrbInfo {
    private String orbCode;
    private int neutral;
    private int fire;
    private int nature;
    private int frost;
    private int shadow;

    /**
     *
     * @param orbCode a text string which displays the required orbs like this:
     *      1 - represents a neutral orb
     *      R - represents a fire orb
     *      N - represents a nature orb
     *      B - represents a frost orb
     *      S - represents a shadow orb
     *
     *      The order in which the letters are written is:
     *      1 > R > N > B > S
     *
     *      Examples:
     *      A card requires 2 fire orbs, and 1 neutral orb
     *      1RR
     *
     *      A card requires 2 neutral orbs, 1 nature orb, and 1 shadow orb.
     *      11NS
     *
     * @param neutral the amount of neutral orbs
     * @param fire the amount of fire orbs
     * @param nature the amount of nature orbs
     * @param frost the amount of frost orbs
     * @param shadow the amount of shadow orbs
     */
    public OrbInfo(String orbCode,int neutral, int fire, int nature, int frost, int shadow){
        this.orbCode = orbCode;
        this.neutral = neutral;
        this.fire = fire;
        this.nature = nature;
        this.frost = frost;
        this.shadow = shadow;
    }


    /**
     * Getter for orbCode.
     * @return the orbCode
     */
    public String getOrbCode() {
        return this.orbCode;
    }

    /**
     * Getter for neutral
     * @return the amount of neutral orbs
     */
    public int getNeutral() {
        return this.neutral;
    }

    /**
     * Getter for fire
     * @return the amount of fire orbs
     */
    public int getFire() {
        return this.fire;
    }

    /**
     * Getter for nature
     * @return the amount of nature orbs
     */
    public int getNature() {
        return this.nature;
    }

    /**
     * Getter for frost
     * @return the amount of frost orbs
     */
    public int getFrost() {
        return this.frost;
    }

    /**
     * Getter for shadow
     * @return the amount of shadow orbs
     */
    public int getShadow() {
        return this.shadow;
    }

    /**
     * Generates all possible combinations of orbs, but not the different permutations
     * @return a list of all possible orb-combinations
     */
    private static List<String> comboGenerator(){
        List<String> combos = new ArrayList<String>();
        String orbs = "RNBS";

        Map<String, Integer> priority = new HashMap<String, Integer>();
        priority.put("R", 0);
        priority.put("N", 1);
        priority.put("B", 2);
        priority.put("S", 3);

        for (char one: orbs.toCharArray()){
            String c1 = String.valueOf(one);
            combos.add(c1);

            for (char two: orbs.toCharArray()){
                String c2 = String.valueOf(two);

                if (priority.get(c2) <= priority.get(c1)){
                    combos.add(c2+c1);
                }

                for (char three: orbs.toCharArray()){
                    String c3 = String.valueOf(three);

                    if (priority.get(c3) <= priority.get(c2) && priority.get(c2) <= priority.get(c1)){
                        combos.add(c3+c2+c1);
                    }

                    for (char four: orbs.toCharArray()){
                        String c4 = String.valueOf(four);

                        if (priority.get(c4) <= priority.get(c3) && priority.get(c3) <= priority.get(c2) && priority.get(c2) <= priority.get(c1)){
                            combos.add(c4+c3+c2+c1);
                        }
                    }
                }
            }
        }

        return combos;
    }

}
