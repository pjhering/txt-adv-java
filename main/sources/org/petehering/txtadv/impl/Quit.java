package org.petehering.txtadv.impl;

import java.util.ArrayList;
import java.util.List;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Model;

public class Quit implements Command
{
    @Override
    public List<String> execute (Model model, String[] args)
    {
        List<String> response = new ArrayList<>();
        model.setQuit(true);
        response.add("goodbye");
        return response;
    }
}
