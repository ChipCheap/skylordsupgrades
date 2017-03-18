package skylordtools;

import javafx.scene.image.Image;
import skylordtools.card.Affinity;
import skylordtools.card.Card;
import skylordtools.card.UpgradedCard;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileOperations {
    /**
     * Checks the ./decks/ directory and uses the getDeck() method
     * @return a HashMap containing all decks and their names
     */
    public static Map<String, List<Card>> getAllDecks(){
        Map<String, List<Card>> deckMap = new HashMap<>();
        File dir = new File("./decks/");

        if (!dir.exists()) {
            dir.mkdir();
            return deckMap;
        }

        File[] allFiles = dir.listFiles();

        for (File f: allFiles){
            try{
                String name = f.getName();
                List<Card> deck = getDeck(name);

                deckMap.put(name, deck);
            }
            catch(IOException e){}
        }

        return deckMap;
    }

    /**
     * Reads a deck from ./decks/{deckname}.deck into a list of cards
     * @param deckname the name of the deck/file to be read
     * @return a list of cards
     * @throws IOException if there is an issue when opening or reading from the file
     */
    public static List<Card> getDeck(String deckname) throws IOException{
        List<Card> deck = new ArrayList<>();
        DataInputStream dis = getDataInputStreamOnFile("./decks/" + deckname + ".deck");

        while (dis.available() > 0)
            deck.add(Card.fromName(dis.readUTF(), Affinity.fromInteger(dis.readInt()-1)));

        return deck;
    }

    /**
     * Write a deck to ./decks/{deckname}.deck
     * @param deckname name of the deck
     * @param deck list of cards in the deck
     * @throws IOException if there is an issue when opening or writing from the file
     */
    public static void writeDeck(String deckname, List<Card> deck) throws IOException{
        DataOutputStream dos;

        try{
            dos = getDataOutputStreamOnFile("./decks/" + deckname + ".deck");
        }
        catch (FileNotFoundException e){
            File dir = new File("./decks");

            if(dir.mkdir()) {
                writeDeck(deckname, deck);
                return;
            }
            else{
                throw e;
            }
        }

        for (Card c: deck){
            dos.writeUTF(c.getName());
            dos.writeInt(c.getAffinity().ordinal());
        }

        dos.close();
    }

    /**
     * Get all data regarding card upgrades from ./upgraded.dat and put them into a list of UpgradedCards
     * @return a list of UpgradedCards
     * @throws IOException if there is an issue when opening or reading from the file
     */
    public static List<UpgradedCard> getUpgradedData() throws IOException{
        List<UpgradedCard> result = new ArrayList<>();

        DataInputStream dis = getDataInputStreamOnFile("./upgraded.dat");

        while (dis.available() > 0)
            result.add(new UpgradedCard(Card.fromName(dis.readUTF(), Affinity.fromInteger(dis.readInt()-1)), //Affinity goes from -1 to 3
                    dis.readBoolean(), dis.readBoolean(), dis.readBoolean()));

        dis.close();

        return result;
    }

    /**
     * Write the data regarding card upgrades from a list of UpgradedCards into ./upgraded.dat
     * @param upgradedCards a list of UpgradedCards
     * @throws IOException if there is an issue when opening or writing from the file
     */
    public static void writeUpgradedData(List<UpgradedCard> upgradedCards) throws IOException{
        DataOutputStream dos = getDataOutputStreamOnFile("./upgraded.dat");

        for (UpgradedCard uc : upgradedCards) {
            dos.writeUTF(uc.getCard().getName());
            dos.writeInt(uc.getCard().getAffinity().ordinal());
            dos.writeBoolean(uc.getU1());
            dos.writeBoolean(uc.getU2());
            dos.writeBoolean(uc.getU3());
        }

        dos.close();
    }

    /**
     * Generate a list of UpgradedCards with all upgrades set to false
     * @return a default list of UpgradedCards
     */
    public static List<UpgradedCard> getDefaultUpgradedCardList(){
        List<UpgradedCard> result = new ArrayList<>();

        for (Card c: Card.allCards){
            result.add(new UpgradedCard(c, false, false, false));
        }

        return result;
    }


    private static DataOutputStream getDataOutputStreamOnFile(String filepath) throws FileNotFoundException{
        return new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File(filepath))));
    }

    private static DataInputStream getDataInputStreamOnFile(String filepath) throws FileNotFoundException{
        return new DataInputStream(new BufferedInputStream(new FileInputStream(new File(filepath))));
    }

    /**
     * Reads the file in byte form and decodes it into a String
     *
     * @param path the path, where the file is located
     * @param encoding the encoding which should be used when reading the file
     * @return the contents of the file as a String
     * @throws IOException if the file could not be read
     */
    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    /**
     * Opens an image from the directory
     *
     * @param filepath the filepath
     * @return the opened image
     */
    public static Image getImage(String filepath){
        return new Image(new BufferedInputStream(MainFrame.class.getResourceAsStream(filepath)));
    }
}
