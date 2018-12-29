package org.petehering.txtadv.impl;

import org.petehering.txtadv.Command;
import org.petehering.txtadv.Model;

public class Save implements Command // TODO: implement this command
{
    @Override
    public String execute (Model model, String[] args)
    {
        return getClass().getSimpleName() + " not implemented";
    }
}
