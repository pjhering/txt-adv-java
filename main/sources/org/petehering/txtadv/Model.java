package org.petehering.txtadv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model
{
    private String title;
    private String author;
    private String preamble;
    private Map<String, Map<String, String>> commands;
    private Map<String, Map<String, String>> quests;
    private String currentQuest;
    private Map<String, Room> rooms;
    private Player player;
    private boolean quit;

    public Model()
    {
        this.title = "";
        this.author = "";
        this.preamble = "";
        this.commands = new HashMap<>();
        this.quests = new HashMap<>();
        this.currentQuest = "";
        this.rooms = new HashMap<>();
        this.player = new Player();
        this.quit = false;
    }

    public void setTitle(String value)
    {
        this.title = value;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setAuthor(String value)
    {
        this.author = value;
    }

    public String getAuthor ()
    {
        return author;
    }

    public void setPreamble(String value)
    {
        this.preamble = value;
    }

    public String getPreamble ()
    {
        return preamble;
    }

    public void setCommands(Map<String, Map<String, String>> value)
    {
        this.commands = value;
    }

    public Map<String, Map<String, String>> getCommands()
    {
        return commands;
    }

    public void setQuests(Map<String, Map<String, String>> value)
    {
        this.quests = value;
    }

    public Map<String, Map<String, String>> getQuests()
    {
        return quests;
    }

    public void setCurrentQuest(String value)
    {
        this.currentQuest = value;
    }

    public String getCurrentQuest()
    {
        return currentQuest;
    }

    public void setRooms(Map<String, Room> value)
    {
        this.rooms = value;
    }

    public Map<String, Room> getRooms()
    {
        return rooms;
    }

    public void setPlayer(Player value)
    {
        this.player = value;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setQuit(boolean value)
    {
        this.quit = value;
    }

    public boolean getQuit()
    {
        return quit;
    }
}
