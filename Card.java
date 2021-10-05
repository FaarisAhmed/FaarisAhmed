

public class Card implements Comparable<Card>{
    private long numericID;
    private String name;
    private Rank rank;
    private long price;

    public Card(long id, String name, Rank rank){
        this.numericID = id;
        this.name = name;
        this.rank = rank;
        this.price = 0;
    }

    public Card(long id, String name, Rank rank, long price){
        this.numericID = id;
        this.name = name;
        this.rank = rank;
        this.price = price;
    }

    public String toString(){
        return (this.numericID + " : " + this.name + " " +  this.rank + " £" + this.price + "  ");
    }

    public boolean equals(Object card){
        if (!(card instanceof Card)){
            return false;
        }
        Card other = (Card)card;
        return (Long.toString(numericID).hashCode() == Long.toString(other.numericID).hashCode()
                 && name.equals(other.name) && rank == other.rank);
    }

    public int hashCode(){
        return (int)((numericID >> 32) ^ numericID);
    }

    public long getPrice(){
        return this.price;
    }

    public long getnumericID(){
        return numericID;
    }
    
    public int compareTo(Card card){
        int rankResult = rank.compareTo(card.rank);
        int nameResult = name.compareTo(card.name);
        if (rankResult == 0){
            if (nameResult == 0){
                return Long.toString(numericID).compareTo(Long.toString(card.numericID));
            }else{
                return nameResult;
            }
        }else{
            return rankResult;
        }
        
    }
}

