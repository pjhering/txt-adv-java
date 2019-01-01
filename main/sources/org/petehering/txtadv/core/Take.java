package org.petehering.txtadv.core;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Item;
import org.petehering.txtadv.Model;
import org.petehering.txtadv.Player;
import org.petehering.txtadv.Room;

public class Take implements Command
{
    @Override
    public List<String> execute (Model model, String[] args)
    {
        List<String> response = new ArrayList<>();

        if(args.length < 2)
        {
            response.add("take what?");
        }
        else
        {
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
                            if(!item.getFixture())
                            {
                                taken.add(item);
                                break INNER;
                            }
                            else
                            {
                                response.add("  the " + item.getName() + " can not be taken");
                            }
                        }
                    }
                }
            }

            response.add("you took:");

            if(taken.isEmpty())
            {
                response.add("  nothing");
            }
            else
            {
                Set<Item> inventory = p.getInventory();

                for(Item it : taken)
                {
                    inventory.add(it);
                    contents.remove(it);

                    response.add("  " + it.getName());
                }
            }
        }

        return response;
    }
}
