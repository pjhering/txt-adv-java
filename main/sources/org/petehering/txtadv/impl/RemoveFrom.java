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

public class RemoveFrom implements Command
{
    @Override
    public List<String> execute(Model model, String[] args)
    {
        List<String> response = new ArrayList<>();
        List<String> requestItemNames = new ArrayList<>();
        String requestContainerName = null;

        boolean foundRequestItemNames = false;
        boolean foundFromKeyWord = false;
        boolean foundRequestContainerName = false;

        LOOP:
        for(int i = 1; i < args.length; i++)
        {
            if(!foundFromKeyWord)
            {
                if(args[i].equals("from"))
                {
                    foundFromKeyWord = true;
                }
                else
                {
                    requestItemNames.add(args[i]);
                    foundRequestItemNames = true;
                }
            }
            else
            {
                if(!foundRequestContainerName)
                {
                    requestContainerName = args[i];
                    foundRequestContainerName = true;
                }
                else
                {
                    response.add("you can only remove items from one contaier");
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
                    Set<Item> contents = container.getContents();

                    if(!contents.isEmpty())
                    {
                        Set<Item> removed = new HashSet<>();

                        for(String requestItemName : requestItemNames)
                        {
                            for(Item item : contents)
                            {
                                if(item.getName().equals(requestItemName))
                                {
                                    if(!removed.contains(item))
                                    {
                                        removed.add(item);
                                    }
                                }
                            }
                        }

                        response.add("you removed:");

                        if(!removed.isEmpty())
                        {
                            Set<Item> inventory = p.getInventory();

                            for(Item item : removed)
                            {
                                contents.remove(item);
                                inventory.add(item);
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
                        response.add("the " + requestContainerName + " is empty");
                    }
                }
                else
                {
                    response.add("the " + requestContainerName + " is not here");
                }
            }
            else
            {
                response.add("remove from which container?");
            }
        }
        else
        {
            response.add("remove which items?");
        }

        return response;
    }
}
