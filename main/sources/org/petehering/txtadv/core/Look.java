package org.petehering.txtadv.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Door;
import org.petehering.txtadv.Item;
import org.petehering.txtadv.Model;
import org.petehering.txtadv.Player;
import org.petehering.txtadv.Room;

public class Look implements Command
{
    @Override
    public List<String> execute (Model model, String[] args)
    {
        List<String> response = new ArrayList<>();
        Player p = model.getPlayer();
        Room r = model.getRooms().get(p.getLocation());
        Set<Item> c = r.getContents();

        response.add("the " + r.getName() + " " + r.getDescription());

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

        response.add("you see:");

        for(String name : count.keySet())
        {
            response.add("  " + count.get(name) + " " + name);
        }

        door("north", r.getNorth(), model, response);
        door("south", r.getSouth(), model, response);
        door("east", r.getEast(), model, response);
        door("west", r.getWest(), model, response);

        return response;
    }

    private void door(String dir, Door d, Model m, List<String> response)
    {
        if(d == null || d.getHidden())
        {
            return;
        }

        String line = "to the " + dir + " is a " + d.getDescription();

        if(!d.getClosed())
        {
            line += " to the " + m.getRooms().get(d.getDestination()).getName();
        }

        response.add(line);
    }
}
