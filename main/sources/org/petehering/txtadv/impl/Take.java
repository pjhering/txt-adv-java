package org.petehering.txtadv.impl;

import java.util.HashSet;
import java.util.Set;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Item;
import org.petehering.txtadv.Model;
import org.petehering.txtadv.Player;
import org.petehering.txtadv.Room;

public class Take implements Command // TODO: UI_Agnostic
{
    @Override
    public String execute (Model model, String[] args)
    {
        if(args.length < 2)
        {
            return "take what?";
        }

        Player p = model.getPlayer();
        Room r = model.getRooms().get(p.getLocation());
        Set<Item> contents = r.getContents();
        Set<Item> taken = new HashSet<>();

        OUTER:
        for(int i = 1; i < args.length; i++)
        {
            INNER:
            for(Item item : contents)
            {
                if(item.getName().equals(args[i])) // found a match
                {
                    if(!taken.contains(item)) // not already taken
                    {
                        taken.add(item);
                        break INNER;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder()
            .append("you took:\n");

        if(taken.isEmpty())
        {
            sb.append("  nothing\n");
        }
        else
        {
            Set<Item> inventory = p.getInventory();

            for(Item it : taken)
            {
                inventory.add(it);
                contents.remove(it);

                sb.append("  ")
                  .append(it.getName())
                  .append("\n");
            }
        }

        return sb.toString();
    }
}
