package skylordtools.card;

import skylordtools.card.Card;

public class UpgradedCard {
    private Card card;
    private boolean u1, u2, u3;

    public UpgradedCard(Card card, boolean u1, boolean u2, boolean u3){
        this.card = card;
        this.u1 = u1;
        this.u2 = u2;
        this.u3 = u3;
    }

    public Card getCard(){
        return card;
    }

    public boolean getU1(){
        return u1;
    }

    public boolean getU2(){
        return u2;
    }

    public boolean getU3(){
        return u3;
    }

    public void setU1(boolean u1){
        this.u1 = u1;
    }

    public void setU2(boolean u2){
        this.u2 = u2;
    }

    public void setU3(boolean u3){
        this.u3 = u3;
    }


}
