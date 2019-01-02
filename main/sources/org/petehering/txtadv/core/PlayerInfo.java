package org.petehering.txtadv.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Model;
import org.petehering.txtadv.Player;
import org.petehering.txtadv.Room;

public class PlayerInfo implements Command
{
    @Override
    public List<String> execute (Model model, String[] args)
    {
        List<String> response = new ArrayList<>();

        Player p = model.getPlayer();
        Room r = model.getRooms().get(p.getLocation());
        response.add("you are in the " + r.getName());

        Map<String, String> props = p.getProperties();
        if(!props.isEmpty())
        {
            for(String key : props.keySet())
            {
                String value = props.get(key);
                response.add(key + " = " + value);
            }
        }

        return response;
    }
}
