package skylordtools.map;

import org.json.JSONObject;


public class BFMap{
    private String name;

    public BFMap(JSONObject obj){
        this.name = obj.getString("Name");
    }
}
