package org.petehering.txtadv.impl;

import java.util.ArrayList;
import java.util.List;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Model;

public class PutIn implements Command // TODO: finish this
{
    @Override
    public List<String> execute(Model model, String[] args)
    {
        List<String> response = new ArrayList<>();
        response.add(getClass().getName());
        response.add("not implemented");
        return response;
    }
}
