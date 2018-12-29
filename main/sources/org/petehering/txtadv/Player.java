package org.petehering.txtadv;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Player
{
    private String location;
    private Map<String, String> properties;
    private Set<Item> inventory;

    public Player()
    {
        this("", new HashMap<>(), new HashSet<>());
    }

    public Player(String location, Map<String, String> properties, Set<Item> inventory)
    {
        this.location = location;
        this.properties = properties;
        this.inventory = inventory;
    }

    public void setLocation(String value)
    {
        this.location = value;
    }

    public String getLocation ()
    {
        return location;
    }

    public void setProperties(Map<String, String> value)
    {
        this.properties = value;
    }

    public Map<String, String> getProperties()
    {
        return properties;
    }

    public void setInventory(Set<Item> value)
    {
        this.inventory = value;
    }

    public Set<Item> getInventory ()
    {
        return inventory;
    }
}
