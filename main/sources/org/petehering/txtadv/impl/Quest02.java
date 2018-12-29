package org.petehering.txtadv.impl;

import org.petehering.txtadv.Model;
import org.petehering.txtadv.Quest;

public class Quest02 extends Quest
{
    public Quest02()
    {
        super("the second quest", "walk around and do stuff");
    }

    @Override
    public boolean isComplete ()
    {
        return false;
    }

    @Override
    public void update (Model model)
    {
    }
}
