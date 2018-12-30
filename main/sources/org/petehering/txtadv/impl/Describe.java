package org.petehering.txtadv.impl;

import static java.lang.System.out;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Item;
import org.petehering.txtadv.Model;
import org.petehering.txtadv.Player;
import org.petehering.txtadv.Room;

public class Describe implements Command // TODO: UI_Agnostic
{
    @Override
    public String execute (Model model, String[] args)
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

        StringBuilder sb = new StringBuilder();
        boolean found = false;

        if(!contents.isEmpty())
        {
            found = true;

            sb.append("in the ")
              .append(r.getName())
              .append(":\n");

            for(Item it : contents)
            {
                sb.append("  ")
                  .append(describe(it))
                  .append("\n");
            }
        }

        if(!inventory.isEmpty())
        {
            found = true;

            sb.append("in your inventory:\n");

            for(Item it : inventory)
            {
                sb.append("  ")
                  .append(describe(it))
                  .append("\n");
            }
        }

        if(found)
        {
            return sb.toString();
        }
        else
        {
            return "no items found";
        }
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
