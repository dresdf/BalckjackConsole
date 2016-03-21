package blackjackconsole;

/**
 *
 *@author Dragos Secara
 */
public class Card {

    private final Rank RANK;
    private final Suit SUIT;
    private int value;
    private boolean isVisible = true;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isVisible) {
         sb.append("[").append(this.getRANK()).append(" of ").append(this.getSUIT()).append("]");   
        }else{
            sb.append("[HIDDEN CARD]");
        }
        return sb.toString();
    }

    public Rank getRANK() {
        return RANK;
    }

    public Suit getSUIT() {
        return SUIT;
    }

    public int getVALUE() {
        return value;
    }

    public boolean getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    /**
     * change the value for aces only
     * @param value 
     */
    public void setVALUE(int value) {
        if (this.RANK.equals(Rank.ACE)) {
            this.value = value;
        }
    }

    public Card(Suit suit, Rank rank) {
        this.SUIT = suit;
        this.RANK = rank;
        this.value = rank.value;
    }

}
