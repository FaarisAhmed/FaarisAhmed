import java.net.*;
import java.io.*;
import java.util.*;
import java.util.ArrayList;

public class HollomonClient{
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private BufferedReader reader;
    private BufferedWriter writer;

    public HollomonClient(String server, int port) {
        try{
            this.socket = new Socket(server, port);
            this.is = socket.getInputStream();
            this.os = socket.getOutputStream();
            this.reader = new BufferedReader(new InputStreamReader(is));
            this.writer = new BufferedWriter(new OutputStreamWriter(os));
        }
        catch (IOException e){
            System.err.println("Error occurred: " + e);
        }
        
    }

    public List<Card> login(String username, String password){
        try{
            username = username + "\n";
            password = password + "\n";
            writer.write(username);
            writer.write(password);
            writer.flush();
            String temp;
            temp = reader.readLine();
            if (temp.contains("success")){
                CardInputStream list = new CardInputStream(is);
                ArrayList<Card> card = new ArrayList<Card>();
                Card cardTemp = list.readCard();
                while (cardTemp != null){
                    card.add(cardTemp);
                    cardTemp = list.readCard();
                }

                Collections.sort(card);
                return card;
            }else{
                return null;
            }
        }
        catch (IOException e){
            System.err.println("Error occured: " + e);
            return null;
        }
    }

    public long getCredits(){ 
        try{
            writer.flush();
            String credits = "CREDITS\n";
            byte[] newCredits = credits.getBytes();
            writer.write(credits);
            writer.flush();
            return Long.parseLong(reader.readLine());
        }
        catch (IOException e){
            System.err.println("Error occured: " + e);
            return 0;
        }
    }

    public List<Card> getCards(){ 
        try{
            writer.flush();
            String cards = "CARDS\n";
            writer.write(cards);
            writer.flush();
            CardInputStream list = new CardInputStream(is);
            ArrayList<Card> card = new ArrayList<Card>();
            Card cardTemp = list.readCard();
            while (cardTemp != null){
                card.add(cardTemp);
                cardTemp = list.readCard();
            }
            Collections.sort(card);
            return card;
        }
        catch (IOException e){
            System.err.println("Error occured: " + e);
            return null;
        }
    }

    public List<Card> getOffers(){ 
        try{
            writer.flush();
            String offers = "OFFERS\n";
            writer.write(offers);
            writer.flush();
            CardInputStream list = new CardInputStream(is);
            ArrayList<Card> card = new ArrayList<Card>();
            Card cardTemp = list.readCard();
            while (cardTemp != null){
                card.add(cardTemp);
                cardTemp = list.readCard();
            }
            Collections.sort(card);
            return card;
        }
        catch (IOException e){
            System.err.println("Error occured: " + e);
            return null;
        }
    }

    public boolean buyCard(Card card){ 
        try{
            writer.flush();
            String buy = "BUY " + card.getnumericID() + "\n";
            CardInputStream list = new CardInputStream(is);
            writer.write(buy);
            writer.flush();
            String temp = reader.readLine();
            if (temp.equals("OK")){
                return true;    
            }else{
                return false;
            }

        }
        catch (IOException e){
            System.err.println("Error occured: " + e);
            return false;
        }
    }

    public boolean sellCard(Card card, long price){
        try{
            writer.flush();
            String sell = "SELL " + card.getnumericID() + " " +  price + " \n";
            byte[] selling = sell.getBytes();
            writer.write(sell);
            writer.flush();
            String temp = reader.readLine();
            if (temp.equals("OK")){
                return true;    
            }else{
                return false;
            }

        }
        catch (IOException e){
            System.err.println("Error occured: " + e);
            return false;
        }
    }

    public void close(){
        try{
            socket.close();
            is.close();
            os.close();
            reader.close();
            writer.close();
        }
        catch (IOException e){
            System.err.println("Error occurred: " + e);
        }
    }

}

