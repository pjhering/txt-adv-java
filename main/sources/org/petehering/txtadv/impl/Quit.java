package org.petehering.txtadv.impl;

import org.petehering.txtadv.Command;
import org.petehering.txtadv.Model;

public class Quit implements Command // TODO: UI_Agnostic
{
    @Override
    public String execute (Model model, String[] args)
    {
        model.setQuit(true);
        return "goodbye";
    }
}
