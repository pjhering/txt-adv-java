package org.petehering.txtadv;

public abstract class Quest
{
    private String name;
    private String description;

    public Quest()
    {
        this("", "");
    }

    public Quest(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public abstract boolean isComplete ();

    public abstract void update (Model model, String[] args, List<String> response);

    public void setName (String value)
    {
        name = value;
    }

    public String getName ()
    {
        return name;
    }

    public void setDescription (String value)
    {
        description = value;
    }

    public String getDescription ()
    {
        return description;
    }
}
