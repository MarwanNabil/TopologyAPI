package com.mycompany.topologyapi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

interface TAPI{
    public boolean readTopAsJSON(String fileName);
    public boolean writeTopAsJSON(String topId);
    public boolean deleteTopology(String topId);
    public JSONObject queryAllTop();
    public JSONObject queryDevices(String topId);
    public JSONObject queryDevicesFromNetlist(String topId , String netlist);
}

public class TopologyAPI implements TAPI {
    private ArrayList<topologyCore> topologies;
    
    public TopologyAPI(){
        this.topologies = new ArrayList<topologyCore>();
    }
    
    public boolean readTopAsJSON(String fileName){
        try {
            File f = new File(fileName);
            FileReader fr = new FileReader(f);
            Scanner sc = new Scanner(fr);
            StringBuilder buffer = new StringBuilder("");
            while(sc.hasNext()){
                String x = sc.next();
                buffer.append(x);
            }
            topologyCore candidate = new topologyCore(buffer.toString());
            this.topologies.add(candidate);
        } catch (Exception ex) {
          return false;
        }
        return true;
    }

    @Override
    public boolean writeTopAsJSON(String topId) {
        try{
            for(int i = 0; i < this.topologies.size(); i++){
                if(topologies.get(i).getTopId().equals(topId)){
                    File futureFile = new File("response0" + ".json");
                    futureFile.createNewFile();
                    FileWriter myWriter = new FileWriter(futureFile);
                    myWriter.append(topologies.get(i).getJsonCode().toString());
                    myWriter.close();
                }
            }
        } catch(Exception E){
            E.printStackTrace();
            return false;
        }
        return true;
    }
    
    @Override
    public boolean deleteTopology(String topId){
        for(int i = 0; i < this.topologies.size(); i++){
            if(this.topologies.get(i).getTopId().equals(topId)){
                this.topologies.remove(i);
                return true;
            }
        }
        return false; 
    }

    @Override
    public JSONObject queryAllTop() {
        JSONArray allTopos = new JSONArray();
        JSONObject retJson = new JSONObject();
        for(int i = 0; i < this.topologies.size(); i++){
            allTopos.put(topologies.get(i).getTopId());
        }
        try{
             retJson.put("topologies", allTopos);
        } catch(Exception e){
            retJson.put("topologies", "null");
        } finally{
            return retJson;
        }
    }

    @Override
    public JSONObject queryDevices(String topId) {
        JSONArray allDevices = new JSONArray();
        JSONObject retJson = new JSONObject();
        for(int i = 0; i < this.topologies.size(); i++){
            if(this.topologies.get(i).getTopId().equals(topId)){
                return topologies.get(i).getDevices();
            }
        }
        try {
            retJson.put("devices", "null");
        } catch (Exception ex) {
        } finally{
            return retJson;
        }
    }

    @Override
    public JSONObject queryDevicesFromNetlist(String topId, String netlist) {
        JSONArray allDevicesFronNetList = new JSONArray();
        JSONObject retJson = new JSONObject();
        boolean did = false;
        for(int i = 0; i < this.topologies.size(); i++){
            if(this.topologies.get(i).getTopId().equals(topId)){
                try {
                    retJson.put(netlist, this.topologies.get(i).getNetlistConnectedDevice(netlist));
                    did = true;
                } catch (JSONException ex) {
                } finally {
                    break;
                }
            }
        }
        try {
            if(!did)
                retJson.put(netlist, "null");
        } catch (JSONException ex) {
        }
        return retJson;
    }
}
