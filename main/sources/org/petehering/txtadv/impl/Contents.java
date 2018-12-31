package org.petehering.txtadv.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Item;
import org.petehering.txtadv.Model;
import org.petehering.txtadv.Player;
import org.petehering.txtadv.Room;

public class Contents implements Command
{
    @Override
    public List<String> execute(Model model, String[] args)
    {
        List<String> response = new ArrayList<>();

        if(args.length < 2)
        {
            response.add("contents of what?");
        }
        else
        {
            Player p = model.getPlayer();
            Room r = model.getRooms().get(p.getLocation());
            Set<Item> contents = r.getContents();
            Set<Item> found = new HashSet<>();

            for(int i = 1; i < args.length; i++)
            {
                String name = args[i];

                for(Item it : contents)
                {
                    if(it.getName().equals(name)) // match name
                    {
                        if(it.isContainer()) // ignore non-containers
                        {
                            if(!found.contains(it)) // ignore already found
                            {
                                found.add(it);
                            }
                        }
                    }
                }
            }

            if(found.isEmpty())
            {
                response.add("no containers found");
            }
            else
            {
                for(Item container : found)
                {
                    response.add(container.getName() + " contains:");
                    Set<Item> inside = container.getContents();
                    Map<String, Integer> count = new HashMap<>();

                    for(Item item : inside)
                    {
                        String name = item.getName();

                        if(!count.containsKey(name))
                        {
                            count.put(name, 1);
                        }
                        else
                        {
                            count.put(name, count.get(name) + 1);
                        }
                    }

                    if(count.isEmpty())
                    {
                        response.add("  nothing");
                    }
                    else
                    {
                        for(String name : count.keySet())
                        {
                            response.add("  " + count.get(name) + " " + name);
                        }
                    }
                }
            }
        }

        return response;
    }
}
