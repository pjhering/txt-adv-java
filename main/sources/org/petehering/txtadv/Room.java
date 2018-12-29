package org.petehering.txtadv;

import java.util.HashSet;
import java.util.Set;

public class Room
{
    private String name;
    private String description;
    private Set<Item> contents;
    private Door north;
    private Door east;
    private Door south;
    private Door west;

    public Room()
    {
        this("", "", new HashSet<Item>(), null, null, null, null);
    }

    public Room(String name, String description, Set<Item> contents, Door north, Door east, Door south, Door west)
    {
        this.name = name;
        this.description = description;
        this.contents = contents;
        this.north = north;
        this.east = east;
        this.south = south;
        this.west = west;
    }

    public void setName(String value)
    {
        this.name = value;
    }

    public String getName ()
    {
        return name;
    }

    public void setDescription(String value)
    {
        this.description = value;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setContents(Set<Item> value)
    {
        this.contents = value;
    }

    public Set<Item> getContents ()
    {
        return contents;
    }

    public void setNorth(Door value)
    {
        this.north = value;
    }

    public Door getNorth ()
    {
        return north;
    }

    public Door getEast ()
    {
        return east;
    }

    public void setEast(Door value)
    {
        this.east = value;
    }

    public Door getSouth ()
    {
        return south;
    }

    public void setSouth(Door value)
    {
        this.south = value;
    }

    public void setWest(Door value)
    {
        this.west = value;
    }

    public Door getWest ()
    {
        return west;
    }
}
