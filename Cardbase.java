package skylordtools;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import skylordtools.card.Card;
import skylordtools.map.BFMap;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Cardbase {
    private static final String CardsURL = "https://cardbase.skylords.eu/cards/GetCards";
    private static final String MapsURL = "https://cardbase.skylords.eu/Maps/GetMaps";
    private static final String CardsPath = "./GetCards.json";
    private static final String MapsPath = "./GetMaps.json";

    public static List<Card> getCards(){
        System.out.println("Trying to generate cardlist ..");
        return parseData(parseAPI(CardsURL, CardsPath, true), Card.class);
    }

    public static List<BFMap> getMaps(){
        System.out.println("Trying to generate maplist ..");
        return parseData(parseAPI(MapsURL, MapsPath, true), BFMap.class);
    }

    /**
     * Tries to download the CardBase Card-API
     * and tries to write it to the file "./GetCards.json".
     */
    public static void downloadCards(){
        System.out.println("Trying to download cards API ...");
        try {
            downloadAPI(CardsURL, CardsPath);
        }
        catch(DownloadException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Tries to download the CardBase Maps-API
     * and tries to write it to the file "./GetMaps.json".
     */
    public static void downloadMaps(){
        System.out.println("Trying to download maps API ...");
        try {
            downloadAPI(MapsURL, MapsPath);
        }
        catch(DownloadException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Parse the result of the Cardbase API
     *
     * @param data the JSONArray of the data from the API
     * @return the Cards in a ArrayList<T>
     */
    private static <T> List<T> parseData(JSONArray data, Class<T> typeKey){
        List<T> dataInList = new ArrayList<>();

        try {
            for (Object o : data) {
                dataInList.add(typeKey.getConstructor(JSONObject.class).newInstance((JSONObject) o));
            }
        }
        catch (JSONException e){
            System.out.println("Changes in the cardbase API! The application will now exit.");
            System.exit(1);
        }
        catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Success!");

        return dataInList;
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
     * @param attemptDownload when an error occurs
     *                        - if true: attempt to redownload the API and call the method again
     *                        - if false: exit the Application
     * @return the result of the API in a JSONArray
     */
    private static JSONArray parseAPI(String url, String path, boolean attemptDownload) {
        try {
            String content = FileOperations.readFile(path, StandardCharsets.UTF_8);
            JSONObject data = new JSONObject(content);

            if(data.getBoolean("Success"))
                return data.getJSONArray("Result");
            else
                throw new JSONException("API-call unsuccessful!");

        }
        catch (JSONException|IOException e1) {
            if (attemptDownload){
                try{
                    downloadAPI(url, path);
                    return parseAPI(url, path, false);
                }
                catch(DownloadException e2){
                    System.out.println(e2.getMessage() + " The application will now exit.");

                    System.exit(1);
                }
            }
            else{
                System.out.println("Unable to parse the API. The application will now exit.");

                System.exit(1);
            }
        }

        return null;
    }

    /**
     * Tries to download the CardBase-API from an URL
     * and tries to write it to a file
     *
     * @param url the url where the API is located
     * @param path the path where the API should be saved to
     * @return the error, or success message
     */
    private static void downloadAPI(String url, String path) throws DownloadException{
        try{
            FileUtils.copyURLToFile(new URL(url), new File(path));
        }
        catch (MalformedURLException e){
            throw new DownloadException("Download failed! An exception occurred when trying to access \""+url+"\"");
        }
        catch (IOException e){
            throw new DownloadException("Download failed! An exception occurred when trying to write to \""+path+"\"");
        }
        catch (Exception e){
            throw new DownloadException("Download failed! An exception occurred!");
        }
    }
}

