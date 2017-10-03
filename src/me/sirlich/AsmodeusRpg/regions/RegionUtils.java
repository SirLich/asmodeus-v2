package me.sirlich.AsmodeusRpg.regions;

import me.sirlich.AsmodeusRpg.AsmodeusRpg;
import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class RegionUtils
{

    public static void loadFiles()
    {

        File dir = new File(AsmodeusRpg.getPlugin(AsmodeusRpg.class).getDataFolder() + "/regions");
        if (!dir.exists())
            dir.mkdir();

        for (File f : dir.listFiles()) {

            loadFile(f);

        }

    }

    public static void loadFile(File f)
    {

        Scanner scan = null;

        try {
            String next = "";
            scan = new Scanner(f);

            next = scan.nextLine().trim();
            String name = next;
            System.out.println("Region loaded with name: " + name);
            //World
            next = scan.nextLine().trim();
            String world = next;
            //Position 1
            next = scan.nextLine().trim();
            String[] pos1Array = next.split(",");
            double pos1x = Double.parseDouble(pos1Array[0].trim());

            double pos1y = Double.parseDouble(pos1Array[1].trim());

            double pos1z = Double.parseDouble(pos1Array[2].trim());
            //Position 2
            next = scan.nextLine().trim();
            String[] pos2Array = next.split(",");
            double pos2x = Double.parseDouble(pos2Array[0].trim());

            double pos2y = Double.parseDouble(pos2Array[1].trim());

            double pos2z = Double.parseDouble(pos2Array[2].trim());
            //Tags
            scan.nextLine();
            ArrayList<String> tags = new ArrayList<>();
            do {
                next = scan.nextLine().trim();
                tags.add(next);
            } while (scan.hasNext());

            new Region(world, pos1x, pos1y, pos1z, pos2x, pos2y, pos2z, name, tags);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error reading region in " + f.getName() + ".");
            e.printStackTrace();
        } finally {
            if (scan != null)
                scan.close();
        }
        System.out.println("Finished reading region " + f.getName() + ".");

    }

    public static Region getRegion(String id)
    {
        System.out.println("getRegion (Region list)");
        for (Region r : Region.regions) {
            System.out.println("Trying: " + r.getName() + " and " + id);
            if (r.getName().equalsIgnoreCase(id)) {
                return r;
            }
        }
        return null;
    }

    public static ArrayList<Region> getFromTag(String tag)
    {
        ArrayList<Region> regions = new ArrayList<>();
        for (Region r : Region.regions) {
            if (r.hasTag(tag)) {
                regions.add(r);
            }
        }
        return regions;
    }


    public static ArrayList<Region> getFromLocation(Location l)
    {
        ArrayList<Region> regions = new ArrayList<>();
        for (Region r : Region.regions) {
            if (r.containsLocation(l)) {
                regions.add(r);
            }
        }
        return regions;
    }

    public static ArrayList<Region> getFromVector(Vector v)
    {
        ArrayList<Region> regions = new ArrayList<>();
        for (Region r : Region.regions) {
            if (r.containsVector(v)) {
                regions.add(r);
            }
        }
        return regions;
    }

}
