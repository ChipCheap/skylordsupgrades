package skylordtools.map;

import java.util.*;

import org.json.JSONObject;

import skylordtools.card.Card;	//incase we have standard, advanced, expert as cards instead of strings


public class BFMap{
    private String name;
    private String description;
    private List<String> difficulties;
    private List<String> standard;
    private List<String> advanced;
    private List<String> expert;
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
        
        advanced = new ArrayList<String>();
        for(int i=0;i<obj.getJSONArray("Advanced").length();i++)
        {
        	String cName = 
        			obj.getJSONArray("Advanced").getJSONObject(i).isNull("CardName")
        				? ""
        			:obj.getJSONArray("Advanced").getJSONObject(i).getString("CardName");
        	advanced.add(cName);
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
    }
    
    public String Name()
    {
    	return this.name;
    }
    public String Description()
    {
    	return this.description;
    }
    public int Players()
    {
    	return this.players;
    }
    public List<String> Difficulties()
    {
    	return this.difficulties;
    }
    public List<String> Standard()
    {
    	return this.standard;
    }
    public List<String> Advanced()
    {
    	return this.advanced;
    }
    public List<String> Expert()
    {
    	return this.expert;
    }
}
