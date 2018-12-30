package org.petehering.txtadv.impl;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Door;
import org.petehering.txtadv.Item;
import org.petehering.txtadv.Model;
import org.petehering.txtadv.Player;
import org.petehering.txtadv.Room;

public class Look implements Command // TODO: UI_Agnostic
{
    @Override
    public String execute (Model model, String[] args)
    {
        Player p = model.getPlayer();
        Room r = model.getRooms().get(p.getLocation());
        Set<Item> c = r.getContents();

        StringBuilder sb = new StringBuilder("the ")
            .append(r.getName())
            .append(" ")
            .append(r.getDescription());

        Map<String, Integer> count = new TreeMap<>();

        for(Item it : c)
        {
            String name = it.getName();

            if(!count.containsKey(name))
            {
                count.put(name, 1);
            }
            else
            {
                count.put(name, count.get(name) + 1);
            }
        }

        sb.append("\nyou see:\n");

        for(String name : count.keySet())
        {
            sb.append("  ")
              .append(count.get(name))
              .append(" ")
              .append(name)
              .append("\n");
        }

        sb.append(door("north", r.getNorth(), model));
        sb.append(door("south", r.getSouth(), model));
        sb.append(door("east", r.getEast(), model));
        sb.append(door("west", r.getWest(), model));

        return sb.toString();
    }

    private String door(String dir, Door d, Model m)
    {
        if(d == null || d.getHidden())
        {
            return "";
        }

        StringBuilder sb = new StringBuilder("to the ")
            .append(dir)
            .append(" is a ")
            .append(d.getDescription());

        if(!d.getClosed())
        {
            sb.append(" to the ")
              .append(m.getRooms().get(d.getDestination()).getName());
        }

        sb.append("\n");

        return sb.toString();
    }
}
