import java.net.*;
import java.io.*;
import java.util.*;

public class CardInputStream extends InputStream{
    private BufferedReader reader;

    public CardInputStream(InputStream input){
        this.reader = new BufferedReader(new InputStreamReader(input));
    }
    

    @Override
    public int read(){
        try{
            String temp = reader.readLine();
            int data = Integer.parseInt(temp);
            return data;
        }
        catch (IOException e){
            System.out.println("Error occured: " + e);
            return 1;
        }
    }


    public Card readCard(){
        try{
            String temp;
            temp = reader.readLine();
            if (temp.equals("CARD")){
                Card newCard = new Card(Long.parseLong(reader.readLine()), reader.readLine(), 
                                        Rank.valueOf(reader.readLine()), Long.parseLong(reader.readLine()));
                return newCard;
            }
            return null;
        }
        catch (IOException e){
            System.out.println("Error occured: " + e);
            return null;
        }
    }

    public String readResponse(){
        try{
            return reader.readLine();
        }
        catch(IOException e){
            System.err.println("Error occured: " + e);
            return null;
        }
    }

    @Override
    public void close(){
        try{    
            reader.close();
        }
        catch (IOException e){
            System.out.println("Error occured: " + e);
        }
    }
}