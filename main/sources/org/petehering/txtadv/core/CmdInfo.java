package org.petehering.txtadv.core;

public class CmdInfo implements Comparable<CmdInfo>
{
    private String name;
    private String shortcut;
    private String description;
    private String string;

    public CmdInfo(String name, String shortcut, String description)
    {
        this.name = (name != null)? name.trim() : null;
        this.shortcut = (shortcut != null)? shortcut.trim() : null;
        this.description = (description != null)? description.trim() : null;

        string = this.name + " [" + this.shortcut + "] " + this.description;
    }

    @Override
    public int compareTo(CmdInfo that)
    {
        return this.name.compareTo(that.name);
    }

    @Override
    public String toString ()
    {
        return string;
    }

    public String getName()
    {
        return name;
    }

    public String getShortcut()
    {
        return shortcut;
    }

    public String getDescription()
    {
        return description;
    }
}
