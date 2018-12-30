package org.petehering.txtadv.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Item;
import org.petehering.txtadv.Model;
import org.petehering.txtadv.Player;

public class Inventory implements Command
{
    @Override
    public List<String> execute (Model model, String[] args)
    {
        List<String> response = new ArrayList<>();
        Player p = model.getPlayer();
        Set<Item> i = p.getInventory();

        if(i.isEmpty())
        {
            response.add("there is nothing in your inventory");
        }
        else
        {
            Map<String, Integer> count = new TreeMap<>();

            for(Item it : i)
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

            response.add("you have:");

            for(String name : count.keySet())
            {
                response.add("  " + count.get(name) + " " + name);
            }
        }

        return response;
    }
}
