package org.petehering.txtadv.impl;

import org.petehering.txtadv.Command;
import org.petehering.txtadv.Model;

public class Quit implements Command
{
    @Override
    public String execute (Model model, String[] args)
    {
        model.setQuit(true);
        return "goodbye";
    }
}
