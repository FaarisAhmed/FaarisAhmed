import java.util.List;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.Collections;
import java.util.ArrayList;

public class CardTest{

    public static void main(String[] args){
        Card c0 = new Card(1, "card1", Rank.COMMON, 323);
        Card c1 = new Card(2, "card2", Rank.RARE, 323);
        Card c2 = new Card(4, "card3", Rank.UNIQUE, 523);
        Card c3 = new Card(1, "card1", Rank.UNIQUE, 5323);
        Card c4 = new Card(1, "card1", Rank.UNIQUE, 3);
        List<Card> test = new ArrayList<Card>();
        test.add(c0);
        test.add(c1);
        test.add(c2);
        test.add(c3);
        test.add(c4);
        System.out.println(test);

        HashSet<Card> hashSet = new HashSet<Card>();
        hashSet.add(c0);
        hashSet.add(c1);
        hashSet.add(c2);
        hashSet.add(c3);
        hashSet.add(c4);
        System.out.println(hashSet);

        TreeSet<Card> testing = new TreeSet<Card>();
        testing.add(c0);
        testing.add(c1);
        testing.add(c2);
        testing.add(c3);
        testing.add(c4);
        System.out.println(testing);
    }
}