package org.petehering.txtadv;

import java.util.List;

public interface Command
{
    public List<String> execute (Model model, String[] args);
}
