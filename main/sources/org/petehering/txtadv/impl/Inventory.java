package org.petehering.txtadv.impl;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Item;
import org.petehering.txtadv.Model;
import org.petehering.txtadv.Player;

public class Inventory implements Command // TODO: UI_Agnostic
{
    @Override
    public String execute (Model model, String[] args)
    {
        Player p = model.getPlayer();
        Set<Item> i = p.getInventory();

        if(i.isEmpty())
        {
            return "there is nothing in your inventory";
        }

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

        StringBuilder sb = new StringBuilder("you have:\n");

        for(String name : count.keySet())
        {
            sb.append("  ")
              .append(count.get(name))
              .append(" ")
              .append(name)
              .append("\n");
        }

        return sb.toString();
    }
}
