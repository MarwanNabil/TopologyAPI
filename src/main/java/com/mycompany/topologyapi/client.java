package com.mycompany.topologyapi;

public class client {
    public static void main(String []args){
        TopologyAPI Solve = new TopologyAPI();
        System.out.println(Solve.readTopAsJSON("topology.json"));
        System.out.println(Solve.writeTopAsJSON("top1"));
        System.out.println(Solve.queryAllTop());
        System.out.println(Solve.deleteTopology("top1"));
        System.out.println(Solve.queryAllTop());
        System.out.println(Solve.queryDevices("top1"));
        System.out.println(Solve.queryDevicesFromNetlist("top1", "drain"));
    }
}
