package org.petehering.txtadv;

public class Door
{
    private String description;
    private String destination;
    private boolean hidden;
    private boolean closed;
    private boolean locked;
    private String keyId;

    public Door()
    {
        this("", "", false, false, false, "");
    }

    public Door(String description, String destination, boolean hidden, boolean closed, boolean locked, String keyId)
    {
        this.description = description;
        this.destination = destination;
        this.hidden = hidden;
        this.closed = closed;
        this.locked = locked;
        this.keyId = keyId;
    }

    public void setDescription(String value)
    {
        this.description = value;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDestination(String value)
    {
        this.destination = value;
    }

    public String getDestination()
    {
        return destination;
    }

    public void setHidden(boolean value)
    {
        this.hidden = value;
    }

    public boolean getHidden()
    {
        return hidden;
    }

    public void setClosed(boolean value)
    {
        this.closed = value;
    }

    public boolean getClosed()
    {
        return closed;
    }

    public void setLocked(boolean value)
    {
        this.locked = value;
    }

    public boolean getLocked()
    {
        return locked;
    }

    public void setKeyId(String value)
    {
        this.keyId = value;
    }

    public String getKeyId()
    {
        return keyId;
    }
}
