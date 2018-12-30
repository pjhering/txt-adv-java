package org.petehering.txtadv.impl;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Item;
import org.petehering.txtadv.Model;
import org.petehering.txtadv.Player;
import org.petehering.txtadv.Room;

public class Describe implements Command
{
    @Override
    public List<String> execute (Model model, String[] args)
    {
        Player p = model.getPlayer();
        Room r = model.getRooms().get(p.getLocation());

        Set<Item> contents = new TreeSet<>();
        Set<Item> inventory = new TreeSet<>();

        for(int i = 1; i < args.length; i++)
        {
            if(r.getContents() != null)
            {
                for(Item it : r.getContents())
                {
                    if(it.getName().equals(args[i]))
                    {
                        contents.add(it);
                    }
                }
            }

            if(p.getInventory() != null)
            {
                for(Item it : p.getInventory())
                {
                    if(it.getName().equals(args[i]))
                    {
                        inventory.add(it);
                    }
                }
            }
        }

        List<String> response = new ArrayList<>();
        boolean found = false;

        if(!contents.isEmpty())
        {
            found = true;
            response.add("in the " + r.getName() + ":");

            for(Item it : contents)
            {
                response.add("  " + describe(it));
            }
        }

        if(!inventory.isEmpty())
        {
            found = true;
            response.add("in your inventory:");

            for(Item it : inventory)
            {
                response.add("  " + describe(it));
            }
        }

        if(!found)
        {
            response.add("no items found");
        }

        return response;
    }

    private String describe(Item i)
    {
        StringBuilder sb = new StringBuilder()
          .append(i.getName())
          .append(", ")
          .append(i.getDescription());

        Map<String, String> props = i.getProperties();

        if(props != null && !props.isEmpty())
        {
            for(String key : props.keySet())
            {
                sb.append(" [")
                  .append(key)
                  .append("=")
                  .append(props.get(key))
                  .append("]");
            }
        }

        return sb.toString();
    }
}
