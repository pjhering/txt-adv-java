package org.petehering.txtadv.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Item;
import org.petehering.txtadv.Model;
import org.petehering.txtadv.Player;
import org.petehering.txtadv.Room;

public class Drop implements Command
{
    @Override
    public List<String> execute (Model model, String[] args)
    {
        List<String> response = new ArrayList<>();

        if(args.length < 2)
        {
            response.add("drop what?");
        }
        else
        {
            Player p = model.getPlayer();
            Room r = model.getRooms().get(p.getLocation());
            Set<Item> inventory = p.getInventory();
            Set<Item> dropped = new HashSet<>();

            OUTER:
            for(int i = 1; i < args.length; i++)
            {
                INNER:
                for(Item item : inventory)
                {
                    if(item.getName().equals(args[i])) // found a match
                    {
                        if(!dropped.contains(item)) // not already dropped
                        {
                            dropped.add(item);
                            break INNER;
                        }
                    }
                }
            }

            response.add("you dropped:");

            if(dropped.isEmpty())
            {
                response.add("  nothing");
            }
            else
            {
                Set<Item> contents = r.getContents();

                for(Item it : dropped)
                {
                    contents.add(it);
                    inventory.remove(it);

                    response.add("  " + it.getName());
                }
            }
        }

        return response;
    }
}
