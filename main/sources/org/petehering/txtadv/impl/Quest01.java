package org.petehering.txtadv.impl;

import org.petehering.txtadv.Model;
import org.petehering.txtadv.Quest;

public class Quest01 extends Quest
{
    public Quest01()
    {
        super("the first quest", "walk around and do stuff");
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
