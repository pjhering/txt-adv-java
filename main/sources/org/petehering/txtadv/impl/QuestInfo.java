package org.petehering.txtadv.impl;

import java.util.Map;
import org.petehering.txtadv.Command;
import org.petehering.txtadv.Model;

public class QuestInfo implements Command
{
    @Override
    public String execute (Model model, String[] args)
    {
        String s = model.getCurrentQuest();

        if(s != null && !s.equals(""))
        {
            Map<String, String> info = model.getQuests().get(s);

            if(info != null)
            {
                String name = info.get("name");
                String desc = info.get("description");

                name = (name == null || name.equals(""))? "unnamed" : name;
                desc = (desc == null || desc.equals(""))? "no description available" : desc;

                return name + "... " + desc;
            }
        }

        return "no information is available";
    }
}
