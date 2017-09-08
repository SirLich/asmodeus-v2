package me.sirlich.AsmodeusRpg.regions;

import java.util.HashMap;

public class RegionList
{
    public static HashMap<String,Region> regionMap = new HashMap<String,Region>();
    public static void addRegion(Region r){
        regionMap.put(r.getName(),r);
    }
    public static Region getRegion(String s){
        return regionMap.get(s);
    }
}
