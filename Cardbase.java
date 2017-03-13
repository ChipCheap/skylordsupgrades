package skylordtools;

import skylordtools.card.Card;
import skylordtools.map.BFMap;
import java.util.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.*;
import org.apache.commons.io.FileUtils;

public class Cardbase {
    private static final String CardsURL = "https://cardbase.skylords.eu/cards/GetCards";
    private static final String MapsURL = "https://cardbase.skylords.eu/Maps/GetMaps";
    private static final String CardsPath = "./GetCards.json";
    private static final String MapsPath = "./GetMaps.json";

    public static List<Card> getCards(){
        System.out.println("Trying to generate cardlist ..");
        return parseCards(parseAPI(CardsURL, CardsPath));
    }

    public static List<BFMap> getMaps(){
        System.out.println("Trying to generate maplist ..");
        return parseMaps(parseAPI(MapsURL, MapsPath));
    }

    /**
     * Tries to download the CardBase-API from https://cardbase.skylords.eu/cards/GetCards
     * and tries to write it to the file "./GetCards.json" by calling the method
     * downloadAPI(String, String) with
     * downloadAPI("ttps://cardbase.skylords.eu/cards/GetCards", "./GetCards.json")
     *
     * @return the error, or success message
     */
    public static void downloadCards(){
        System.out.println("Trying to download cards API ...");
        System.out.println(downloadAPI(CardsURL, CardsPath));
    }

    /**
     * Tries to download the CardBase-API from https://cardbase.skylords.eu/Maps/GetMaps
     * and tries to write it to the file "./GetMaps.json" by calling the method
     * downloadAPI(String, String) with
     * downloadAPI("https://cardbase.skylords.eu/Maps/GetMaps", "./GetMaps.json")
     *
     * @return the error, or success message
     */
    public static void downloadMaps(){
        System.out.println("Trying to download maps API ...");
        System.out.println(downloadAPI(CardsPath, CardsURL));
    }

    /**
     * Parse the result of the Cardbase Card-API(http://cardbase.bfreborn.com/cards/GetCards)
     *
     * @param data the JSONArray of all cards from the API
     * @return the Cards in a ArrayList<Card>
     * @throws JSONException if the API gets changed, this might occur
     */
    private static List<Card> parseCards(JSONArray data) throws JSONException{
        List<Card> cards = new ArrayList<Card>();

        try {
            for (Object card : data) {
                cards.add(new Card((JSONObject) card));
            }
        }
        catch (JSONException e){
            System.out.println("Please redownload the Cards API (Options), if this problem persists,\n"+
                    "the application is broken due to changes to the API, please let me know!");
            return cards;
        }

        System.out.println("Success!");

        return cards;
    }

    /**
     * Parse the result of the Cardbase Map-API(http://cardbase.bfreborn.com/Maps/GetMaps)
     *
     * @param data the JSONArray of all maps from the API
     * @return the Cards in a ArrayList<BFMap>
     * @throws JSONException if the API gets changed, this might occur
     */
    private static List<BFMap> parseMaps(JSONArray data) throws JSONException{
        List<BFMap> maps = new ArrayList<BFMap>();

        try {
            for (Object map : data) {
                maps.add(new BFMap((JSONObject) map));
            }
        }
        catch (JSONException e){
            System.out.println("Please redownload the Maps API (Options), if this problem persists,\n"+
                    "the application is broken due to changes to the API, please let me know!");
            return maps;
        }

        System.out.println("Success!");

        return maps;
    }

    /**
     * Call parseAPI(String, String, int) with 0 tries.
     * This means: getCards(url, path, 0)
     *
     * @param url the url where the API is located
     * @param path the path where the API should be saved to or where it is located
     * @return the result of the API in a JSONArray
     */
    private static JSONArray parseAPI(String url, String path){
        return parseAPI(url, path, 0);
    }

    /**
     * Download(if not there) and parse the API, and check if the API-call was successful.
     * This is done by checking the boolean value of "Success".
     *
     * If the parsing failed, or the API-call was unsuccessful,
     * and a URL was specified, the method will recur a maximum of 5 times.
     *
     * @param url the url where the API is located
     * @param path the path where the API should be saved to
     * @param tries the amount of tries which have been made so far
     * @return the result of the API in a JSONArray
     */
    private static JSONArray parseAPI(String url, String path, int tries){
        if (tries >= 5) {
            System.out.println("Failed to get/read API after five tries. Exiting ...");

            try{
                Thread.sleep(2000);
            }
            catch (InterruptedException e){}

            System.exit(0);
        }

        try {
            String content = readFile(path, StandardCharsets.UTF_8);

            JSONObject data = new JSONObject(content);

            if(data.getBoolean("Success")){
                return data.getJSONArray("Result");
            }
            else{
                System.out.println("\nAPI-call was unsuccessful!");
            }
        }
        catch (IOException e){
            System.out.println("\nAn error occured when trying to read the API!");
        }
        catch (JSONException e){
            System.out.println("\nAn error occured when parsing!");
        }

        try{
            Thread.sleep(2000);
        }
        catch (InterruptedException ie){}

        System.out.println("Attempting to redownload ...");
        System.out.println(downloadAPI(url, path));

        return parseAPI(url, path, tries+1);
    }

    /**
     * Tries to download the CardBase-API from an URL
     * and tries to write it to a file
     *
     * @param url the url where the API is located
     * @param path the path where the API should be saved to
     * @return the error, or success message
     */
    private static String downloadAPI(String url, String path){
        try{
            FileUtils.copyURLToFile(new URL(url), new File(path));
        }
        catch (MalformedURLException e){
            return "Download failed! Something went wrong when trying to access \""+url+"\"";
        }
        catch (IOException e){
            return "Download failed! Something went wrong when trying to write to \""+path+"\"";
        }
        catch (Exception e){
            return "Download failed! Something went wrong!";
        }

        return "Success!";
    }


    /**
     * Reads the file in byte form and decodes it into a String
     *
     * @param path the path, where the file is located
     * @param encoding the encoding which should be used when reading the file
     * @return the contents of the file as a String
     * @throws IOException if the file could not be read
     */
    private static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}

