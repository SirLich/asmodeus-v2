package me.sirlich.AsmodeusRpg.regions;

/**
 * Created by Jo on 11/10/2015.
 */

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class Region implements Cloneable
{

    public static ArrayList<Region> regions = new ArrayList<>();
    protected final Vector minimumPoint, maximumPoint;
    protected String worldName;
    protected ArrayList<String> tags = new ArrayList<>();
    protected String name;

    public Region(Region region)
    {
        this(region.worldName, region.minimumPoint.getX(), region.minimumPoint.getY(), region.minimumPoint.getZ(), region.maximumPoint.getX(), region.maximumPoint.getY(), region.maximumPoint.getZ(), region.name, region.tags);
    }

    public Region(Location loc, String name, ArrayList<String> tags)
    {
        this(loc, loc, name, tags);
    }

    public Region(Location loc1, Location loc2, String name, ArrayList<String> tags)
    {
        if (loc1 != null && loc2 != null) {
            if (loc1.getWorld() != null && loc2.getWorld() != null) {
                if (!loc1.getWorld().getUID().equals(loc2.getWorld().getUID()))
                    throw new IllegalStateException("The 2 locations of the cuboid must be in the same world!");
            } else {
                throw new NullPointerException("One/both of the worlds is/are null!");
            }
            this.worldName = loc1.getWorld().getName();

            double xPos1 = Math.min(loc1.getX(), loc2.getX());
            double yPos1 = Math.min(loc1.getY(), loc2.getY());
            double zPos1 = Math.min(loc1.getZ(), loc2.getZ());
            double xPos2 = Math.max(loc1.getX(), loc2.getX());
            double yPos2 = Math.max(loc1.getY(), loc2.getY());
            double zPos2 = Math.max(loc1.getZ(), loc2.getZ());
            this.minimumPoint = new Vector(xPos1, yPos1, zPos1);
            this.maximumPoint = new Vector(xPos2, yPos2, zPos2);
            this.name = name;
            this.tags = tags;
            regions.add(this);
        } else {
            throw new NullPointerException("One/both of the locations is/are null!");
        }
    }

    public Region(String worldName, double x1, double y1, double z1, double x2, double y2, double z2, String name, ArrayList<String> tags)
    {
        if (worldName == null || Bukkit.getServer().getWorld(worldName) == null)
            throw new NullPointerException("One/both of the worlds is/are null!");
        this.worldName = worldName;

        double xPos1 = Math.min(x1, x2);
        double xPos2 = Math.max(x1, x2);
        double yPos1 = Math.min(y1, y2);
        double yPos2 = Math.max(y1, y2);
        double zPos1 = Math.min(z1, z2);
        double zPos2 = Math.max(z1, z2);
        this.minimumPoint = new Vector(xPos1, yPos1, zPos1);
        this.maximumPoint = new Vector(xPos2, yPos2, zPos2);
        this.name = name;
        this.tags = tags;
        regions.add(this);
    }

    public boolean containsLocation(Location location)
    {

        return location != null && location.toVector().isInAABB(this.minimumPoint, this.maximumPoint) && location.getWorld().getName().equalsIgnoreCase(this.worldName);
    }

    public boolean containsVector(Vector vector)
    {
        return vector != null && vector.isInAABB(this.minimumPoint, this.maximumPoint);
    }

    public Location getLowerLocation()
    {
        return this.minimumPoint.toLocation(this.getWorld());
    }

    public double getLowerX()
    {
        return this.minimumPoint.getX();
    }

    public double getLowerY()
    {
        return this.minimumPoint.getY();
    }

    public double getLowerZ()
    {
        return this.minimumPoint.getZ();
    }

    public Location getUpperLocation()
    {
        return this.maximumPoint.toLocation(this.getWorld());
    }

    public double getUpperX()
    {
        return this.maximumPoint.getX();
    }

    public double getUpperY()
    {
        return this.maximumPoint.getY();
    }

    public double getUpperZ()
    {
        return this.maximumPoint.getZ();
    }

    public Location getRandomLoc()
    {
        System.out.println("getRandomLoc");
        return new Location(Bukkit.getWorld("world"), ThreadLocalRandom.current().nextDouble(this.getLowerX(), this.getUpperX()),
                ThreadLocalRandom.current().nextDouble(this.getLowerY(), this.getUpperY()),
                ThreadLocalRandom.current().nextDouble(this.getLowerZ(), this.getUpperZ()));
    }

    public String getName()
    {
        return this.name;
    }

    public World getWorld()
    {
        World world = Bukkit.getServer().getWorld(this.worldName);
        if (world == null) throw new NullPointerException("World '" + this.worldName + "' is not loaded.");
        return world;
    }

    public ArrayList<String> getTags()
    {
        return this.tags;
    }

    public boolean hasTag(String tag)
    {
        return this.tags.contains(tag);
    }

    @Override
    public Region clone()
    {
        return new Region(this);
    }

}

