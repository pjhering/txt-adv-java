package org.petehering.txtadv.core;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Item;
import org.petehering.txtadv.Model;
import org.petehering.txtadv.Player;
import org.petehering.txtadv.Room;
import org.petehering.txtadv.Model;

public class PutIn implements Command
{
    @Override
    public List<String> execute(Model model, String[] args)
    {
        List<String> response = new ArrayList<>();

        boolean foundRequestItemNames = false;
        boolean foundInKeyWord = false;
        boolean foundRequestContainerName = false;

        List<String> requestItemNames = new ArrayList<>();
        String requestContainerName = null;

        LOOP:
        for(int i = 1; i < args.length; i++)
        {
            String arg = args[i];

            if(!foundInKeyWord)
            {
                if(arg.equals("in"))
                {
                    foundInKeyWord = true;
                }
                else
                {
                    requestItemNames.add(arg);
                    foundRequestItemNames = true;
                }
            }
            else
            {
                if(!foundRequestContainerName)
                {
                    requestContainerName = arg;
                    foundRequestContainerName = true;
                }
                else
                {
                    response.add("you can only put item in one container");
                    break LOOP;
                }
            }
        }

        if(foundRequestItemNames)
        {
            if(foundRequestContainerName)
            {
                Player p = model.getPlayer();
                Room r = model.getRooms().get(p.getLocation());
                Item container = null;

                LOOP:
                for(Item item : r.getContents())
                {
                    if(item.getName().equals(requestContainerName))
                    {
                        if(item.isContainer())
                        {
                            container = item;
                            break LOOP;
                        }
                        else
                        {
                            response.add("the " + requestContainerName + " is not a container");
                            break LOOP;
                        }
                    }
                }

                if(container != null)
                {
                    Set<Item> inventory = p.getInventory();

                    if(!inventory.isEmpty())
                    {
                        Set<Item> putItems = new HashSet<>();

                        for(String requestItemName : requestItemNames)
                        {
                            for(Item item : inventory)
                            {
                                if(item.getName().equals(requestItemName))
                                {
                                    if(!putItems.contains(item))
                                    {
                                        putItems.add(item);
                                    }
                                }
                            }
                        }

                        response.add("you put:");

                        if(!putItems.isEmpty())
                        {
                            Set<Item> contents = container.getContents();

                            for(Item item : putItems)
                            {
                                inventory.remove(item);
                                contents.add(item);
                                response.add("  " + item.getName());
                            }
                        }
                        else
                        {
                            response.add("  nothing");
                        }
                    }
                    else
                    {
                        response.add("you have nothing in your inventory");
                    }
                }
                else
                {
                    response.add("the " + requestContainerName + " is not here");
                }
            }
            else
            {
                response.add("put in which container?");
            }
        }
        else
        {
            response.add("put which items?");
        }

        return response;
    }
}
