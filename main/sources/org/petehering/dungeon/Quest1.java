package org.petehering.dungeon;

import java.util.List;
import org.petehering.txtadv.Model;
import org.petehering.txtadv.Player;
import org.petehering.txtadv.Quest;

public class Quest1 extends Quest
{
    private boolean complete = false;

    @Override
    public void update(Model model, String[] args, List<String> response)
    {
        Player p = model.getPlayer();
        complete = p.getLocation().equals("2-A1");

        if(complete)
        {
            response.add("you've completed " + getName());
        }
    }

    @Override
    public boolean isComplete()
    {
        return complete;
    }
}
