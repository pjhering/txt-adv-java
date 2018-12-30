package org.petehering.txtadv.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Door;
import org.petehering.txtadv.Item;
import org.petehering.txtadv.Model;
import org.petehering.txtadv.Player;
import org.petehering.txtadv.Room;

public class Go implements Command
{
    @Override
    public List<String> execute (Model model, String[] args)
    {
        List<String> response = new ArrayList<>();

        switch(args[0])
        {
            case "north":
            case "n":
                response.add(goNorth(model, args));
                break;

            case "south":
            case "s":
                response.add(goSouth(model, args));
                break;

            case "west":
            case "w":
                response.add(goWest(model, args));
                break;

            case "east":
            case "e":
                response.add(goEast(model, args));
                break;

            default:
                response.add("that's not a direction");
                break;
        }

        return response;
    }

    private String goNorth(Model model, String[] args)
    {
        Player p = model.getPlayer();
        Room r1 = model.getRooms().get(p.getLocation());
        Door d = r1.getNorth();

        return move(p, d, model);
    }

    private String goSouth(Model model, String[] args)
    {
        Player p = model.getPlayer();
        Room r1 = model.getRooms().get(p.getLocation());
        Door d = r1.getSouth();

        return move(p, d, model);
    }

    private String goWest(Model model, String[] args)
    {
        Player p = model.getPlayer();
        Room r1 = model.getRooms().get(p.getLocation());
        Door d = r1.getWest();

        return move(p, d, model);
    }

    private String goEast(Model model, String[] args)
    {
        Player p = model.getPlayer();
        Room r1 = model.getRooms().get(p.getLocation());
        Door d = r1.getEast();

        return move(p, d, model);
    }

    private String move(Player p, Door d, Model model)
    {
        if(d == null)
        {
            return "you can't go that way";
        }

        boolean ok = false;

        if(d.getLocked())
        {
            String keyId = d.getKeyId();

            Set<Item> inventory = p.getInventory();

            SEARCH:
            for(Item it : inventory)
            {
                String id = it.getProperties().get("keyId");

                if(id != null && id.equals(keyId))
                {
                    ok = true;
                    break SEARCH;
                }
            }
        }
        else
        {
            ok = true;
        }

        if(ok)
        {
            String dest = d.getDestination();
            String name = model.getRooms().get(dest).getName();
            p.setLocation(dest);

            return "you are in the " + name;
        }
        else
        {
            return "you don't have the key";
        }
    }
}
