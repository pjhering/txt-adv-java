package org.petehering.txtadv.impl;

import org.petehering.txtadv.Command;
import org.petehering.txtadv.Model;

import java.util.HashSet;
import java.util.Set;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Item;
import org.petehering.txtadv.Model;
import org.petehering.txtadv.Player;
import org.petehering.txtadv.Room;

public class Drop implements Command // TODO: UI_Agnostic
{
    @Override
    public String execute (Model model, String[] args)
    {
        if(args.length < 2)
        {
            return "drop what?";
        }

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

        StringBuilder sb = new StringBuilder()
            .append("you dropped:\n");

        if(dropped.isEmpty())
        {
            sb.append("  nothing\n");
        }
        else
        {
            Set<Item> contents = r.getContents();

            for(Item it : dropped)
            {
                contents.add(it);
                inventory.remove(it);

                sb.append("  ")
                  .append(it.getName())
                  .append("\n");
            }
        }

        return sb.toString();
    }
}
