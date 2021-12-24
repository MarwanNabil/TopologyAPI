package com.mycompany.topologyapi;

import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class topologyCore {
    private String topId;
    private JSONObject jsonCode;
    private JSONObject devicesJson;
    private HashMap<String , String> netlist;
    
    public topologyCore(String jsonCode) throws Exception{
        netlist = new HashMap<String , String >();
        this.jsonCode = new JSONObject(jsonCode);
        
        JSONObject toBeConverted = new JSONObject(jsonCode);
        
        if(!toBeConverted.has("id")){
            return;
        }
        //ensuring that the json file has an id
        this.topId = (String)toBeConverted.get("id");

        if(!toBeConverted.has("components")){
            return;
        }
        //System.out.println(toBeConverted.get("components"));
        
        //loop on the component array
        JSONArray compJSONArray = toBeConverted.getJSONArray("components");
        JSONArray devicesJsonArray = new JSONArray();
        for(int i = 0; i < compJSONArray.length(); i++){
            //System.out.println("At comp " + i + "\n" + compJSONArray.getJSONObject(i) +  "\n\n");
           
            try{
                JSONObject compIJson = compJSONArray.getJSONObject(i);
                devicesJsonArray.put(compIJson.get("type"));
                //the above line add the current device.
                
                JSONObject netlistJson = compIJson.getJSONObject("netlist");
                //System.out.println(compIJson.getJSONObject("netlist").toString());
                Iterator<String> netlistKeys = netlistJson.keys();

                while(netlistKeys.hasNext()) {
                    String key = netlistKeys.next();
                    //System.out.print(key + " ");
                    //System.out.println(netlistJson.get(key));
                    
                    if(netlistJson.get(key) != null){
                        //do nothing
                        netlist.put(key, netlistJson.get(key).toString());
                    }
                }
                
            }  catch(Exception E){
                //do nothing
                continue;
            }
        }
        
        JSONObject devJson = new JSONObject();
        devJson.put("devices", devicesJsonArray);
        this.devicesJson = devJson;
    }
    
    public String getTopId(){
        return this.topId;
    }
    
    public JSONObject getJsonCode(){
        return this.jsonCode;
    }
    
    public JSONObject getDevices(){
        return this.devicesJson;
    }
    
    public String getNetlistConnectedDevice(String netlistID){
        return this.netlist.get(netlistID);
    }
}

