package skylordtools.map;

import java.awt.CardLayout;
import java.util.*;

import org.json.JSONObject;

import skylordtools.card.Card;	//incase we have standard, advanced, expert as cards instead of strings


public class BFMap{
    private String name;
    private String description;
    private List<String> difficulties;
    private List<String> standard;
    private List<Card> standardC;
    private List<String> advanced;
    private List<Card> advancedC;
    private List<String> expert;
    private List<Card> expertC;
    private int players;

    public BFMap(JSONObject obj){
        name = obj.getString("Name");
        description = obj.getString("Description");
        players = obj.getInt("Players");
        
        difficulties = new ArrayList<String>();
        for(int i=0;i<obj.getJSONArray("Difficulties").length();i++)
        {
        	String diff =
        			obj.getJSONArray("Difficulties").getJSONObject(i).getString("Difficulties");
        	difficulties.add(diff);
        }
        
        standard = new ArrayList<String>();
        for(int i=0;i<obj.getJSONArray("Standard").length();i++)
        {
        	String cName = 
        			obj.getJSONArray("Standard").getJSONObject(i).isNull("CardName")
        				? ""
        			:obj.getJSONArray("Standard").getJSONObject(i).getString("CardName");
        	standard.add(cName);
        }
        standardC = new ArrayList<Card>();
        for(int i=0;i<standard.size();i++)
        {
        	String[] stA=standard.toArray();
        	standardC.add(Card.fromStringName(stA[i]));
        }
        
        
        advanced = new ArrayList<String>();
        for(int i=0;i<obj.getJSONArray("Advanced").length();i++)
        {
        	String cName = 
        			obj.getJSONArray("Advanced").getJSONObject(i).isNull("CardName")
        				? ""
        			:obj.getJSONArray("Advanced").getJSONObject(i).getString("CardName");
        	advanced.add(cName);
        }
        advancedC = new ArrayList<Card>();
        for(int i=0;i<advanced.size();i++)
        {
        	String[] advA=advanced.toArray();
        	advancedC.add(Card.fromStringName(stA[i]));
        }
        
        expert = new ArrayList<String>();
        for(int i=0;i<obj.getJSONArray("Expert").length();i++)
        {
        	String cName = 
        			obj.getJSONArray("Expert").getJSONObject(i).isNull("CardName")
        				? ""
        			:obj.getJSONArray("Expert").getJSONObject(i).getString("CardName");
        	expert.add(cName);
        }
        expertC = new ArrayList<Card>();
        for(int i=0;i<expert.size();i++)
        {
        	String[] expA=expert.toArray();
        	expertC.add(Card.fromStringName(stA[i]));
        }
        //String ArrayLists can be deleted 
    }
    
    public String name()
    {
    	return this.name;
    }
    public String description()
    {
    	return this.description;
    }
    public int players()
    {
    	return this.players;
    }
    public List<String> difficulties()
    {
    	return this.difficulties;
    }
    public List<String> standard()
    {
    	return this.standard;
    }
    public List<String> advanced()
    {
    	return this.advanced;
    }
    public List<String> expert()
    {
    	return this.expert;
    }
    public List<Card> standardC()
    {
    	return this.standardC;
    }
    public List<Card> advancedC()
    {
    	return this.advancedC;
    }
    public List<Card> expertC()
    {
    	return this.expertC;
    }
}
