package skylordtools.map;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import skylordtools.card.Card;


public class BFMap{
    private String name;
    private String description;
    private List<Difficulty> difficulties;
    private List<Card> standard;
    private List<Card> advanced;
    private List<Card> expert;
    private int players;

    public BFMap(JSONObject obj){
        name = obj.getString("Name");
        description = obj.getString("Description");
        players = obj.getInt("Players");

        difficulties = new ArrayList<>();
        JSONArray jsonDifficulties = obj.getJSONArray("Difficulties");

        for(int i=0;i<jsonDifficulties.length();i++)
        {
            String diff = jsonDifficulties.getJSONObject(i).getString("Difficulties");

            difficulties.add(Difficulty.fromString(diff));
        }

        standard = new ArrayList<>();
        JSONArray jsonStandard = obj.getJSONArray("Standard");

        for(int i=0;i<jsonStandard.length();i++)
        {
            String cName = jsonStandard.getJSONObject(i).getString("CardName");
            standard.add(Card.fromStringName(cName));
        }


        advanced = new ArrayList<>();
        JSONArray jsonAdvanced = obj.getJSONArray("Advanced");

        for(int i=0;i<jsonAdvanced.length();i++)
        {
            String cName = jsonAdvanced.getJSONObject(i).getString("CardName");
            advanced.add(Card.fromStringName(cName));
        }

        expert = new ArrayList<>();
        JSONArray jsonExpert = obj.getJSONArray("Expert");

        for(int i=0;i<jsonExpert.length();i++)
        {
            String cName = jsonExpert.getJSONObject(i).getString("CardName");
            expert.add(Card.fromStringName(cName));
        }

    }

    public String getName()
    {
        return this.name;
    }
    public String getDescription()
    {
        return this.description;
    }
    public int getPlayers()
    {
        return this.players;
    }
    public List<Difficulty> getDifficulties()
    {
        return this.difficulties;
    }
    public List<Card> getStandard()
    {
        return this.standard;
    }
    public List<Card> getAdvanced()
    {
        return this.advanced;
    }
    public List<Card> getExpert()
    {
        return this.expert;
    }
}