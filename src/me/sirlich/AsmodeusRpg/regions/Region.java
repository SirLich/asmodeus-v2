package me.sirlich.AsmodeusRpg.regions;

import org.bukkit.util.Vector;

import java.util.ArrayList;

public class Region
{
    public Vector getPoint1()
    {
        return point1;
    }

    public void setPoint1(Vector point1)
    {
        this.point1 = point1;
    }

    public Vector getPoint2()
    {
        return point2;
    }

    public void setPoint2(Vector point2)
    {
        this.point2 = point2;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public ArrayList<String> getTags()
    {
        return tags;
    }

    public void setTags(ArrayList<String> tags)
    {
        this.tags = tags;
    }

    private Vector point1;
    private Vector point2;
    private String name;
    private ArrayList<String> tags;
}
