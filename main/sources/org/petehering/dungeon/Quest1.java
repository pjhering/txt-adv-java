package org.petehering.dungeon;

import org.petehering.txtadv.Model;
import org.petehering.txtadv.Player;
import org.petehering.txtadv.Quest;

public class Quest1 extends Quest
{
    private boolean complete = false;

    @Override
    public void update(Model model)
    {
        Player p = model.getPlayer();
        complete = p.getLocation().equals("2-A1");
    }

    @Override
    public boolean isComplete()
    {
        return complete;
    }
}
