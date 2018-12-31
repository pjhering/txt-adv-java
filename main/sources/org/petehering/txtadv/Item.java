package org.petehering.txtadv;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Item implements Comparable<Item>
{
    private static int count = 0;

    private int id;
    private String name;
    private String description;
    private Map<String, String> properties;
    private boolean fixture;
    private Set<Item> contents;

    public Item()
    {
        this("", "", new HashMap<>());
    }

    public Item(String name, String description, Map<String, String> properties)
    {
        this.id = count++;
        this.name = name;
        this.description = description;
        this.properties = properties;
    }

    // this is needed to:
    //   alphabetize the items
    //   ensure items with the same name are added to sets
    @Override
    public int compareTo(Item that)
    {
        if(this.id == that.id)
        {
            return 0;
        }
        else
        {
            int ct = this.name.compareTo(that.name);

            if(ct == 0)
            {
                return this.id - that.id;
            }
            else
            {
                return ct;
            }
        }
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

    public void setProperties(Map<String, String> value)
    {
        this.properties = value;
    }

    public Map<String, String> getProperties()
    {
        return properties;
    }

    public void setFixture(boolean value)
    {
        this.fixture = value;
    }

    public boolean getFixture()
    {
        return fixture;
    }

    public void setContents(Set<Item> value)
    {
        this.contents = value;
    }

    public Set<Item> getContents()
    {
        return contents;
    }

    public boolean isContainer()
    {
        return contents != null;
    }
}
