package skylordtools.card;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CardComparator implements Comparator<Card>{
    CardProperty compareBy;

    /**
     * The constructor of the Comparator
     * @param compareBy the CardProperty which will be used to compare two cards
     */
    public CardComparator(CardProperty compareBy) {
        this.compareBy = compareBy;
    }

    /**
     * Update the CardProperty, which is used in the compare(Card, Card) method
     * @param compareBy the new value for compareby
     */
    public void changeProperty(CardProperty compareBy){
        this.compareBy = compareBy;
    }

    /**
     * Sort a list by a property
     * @param compareBy the property the list should be sorted by
     * @param cards the list which should be sorted
     */
    public static void sort(CardProperty compareBy, List<Card> cards){
        Collections.sort(cards, new CardComparator(compareBy));
    }

    /**
     * Compare two cards by the specified CardProperty
     * @param c1 a Card
     * @param c2 another Card
     * @return  -1 (CardProperty of c2 is larger than c1)
     *          0  (CardProperty of c2 is equal to c1)
     *          1  (CardProperty of c2 is lower than c1)
     */
    public int compare(Card c1, Card c2) {
        if (compareBy == CardProperty.Tier){
            if (c1.getOrbInfo().getOrbCode().length() > c2.getOrbInfo().getOrbCode().length()) return 1;
            if (c1.getOrbInfo().getOrbCode().length() < c2.getOrbInfo().getOrbCode().length()) return -1;
        }
        else if (compareBy == CardProperty.CardType){
            if (c1.getType().ordinal() > c2.getType().ordinal()) return 1;
            if (c1.getType().ordinal() < c2.getType().ordinal()) return -1;
        }
        else if (compareBy == CardProperty.Color){
            if (c1.getColor().ordinal() > c2.getColor().ordinal()) return 1;
            if (c1.getColor().ordinal() < c2.getColor().ordinal()) return -1;
        }
        else if (compareBy == CardProperty.Cost){
            if (c1.getCost() > c2.getCost()) return 1;
            if (c1.getCost() < c2.getCost()) return -1;
        }
        else if (compareBy == CardProperty.Rarity){
            if (c1.getRarity().ordinal() > c2.getRarity().ordinal()) return 1;
            if (c1.getRarity().ordinal() < c2.getRarity().ordinal()) return -1;
        }
        else if (compareBy == CardProperty.Affinity){
            if (c1.getAffinity().ordinal() > c2.getAffinity().ordinal()) return 1;
            if (c1.getAffinity().ordinal() < c2.getAffinity().ordinal()) return -1;
        }
        else if (compareBy == CardProperty.OffenseType){
            if (c1.getOffenseType().ordinal() > c2.getOffenseType().ordinal()) return 1;
            if (c1.getOffenseType().ordinal() < c2.getOffenseType().ordinal()) return -1;
        }
        else if (compareBy == CardProperty.DefenseType){
            if (c1.getDefenseType().ordinal() > c2.getDefenseType().ordinal()) return 1;
            if (c1.getDefenseType().ordinal() < c2.getDefenseType().ordinal()) return -1;
        }
        else if (compareBy == CardProperty.OffenseValue){
            if (c1.getOffense() > c2.getOffense()) return 1;
            if (c1.getOffense() < c2.getOffense()) return -1;
        }
        else if (compareBy == CardProperty.DefenseValue){
            if (c1.getDefense() > c2.getDefense()) return 1;
            if (c1.getDefense() < c2.getDefense()) return -1;
        }
        else if (compareBy == CardProperty.Name){
            return c1.getName().compareTo(c2.getName());
        }

        return 0;
    }

}
